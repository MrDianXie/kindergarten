package com.dq408.kindergarten.controller.affiche;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dq408.kindergarten.domain.Affiche;
import com.dq408.kindergarten.domain.User;
import com.dq408.kindergarten.service.AfficheService;
import com.dq408.kindergarten.service.UserService;
import com.dq408.kindergarten.utils.AjaxResult;
import com.dq408.kindergarten.utils.jwt.JwtUtil;
import com.dq408.kindergarten.utils.jwt.anntation.UserLoginToken;
import com.dq408.kindergarten.vo.AfficheVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author XIE_HRZGZ
 */
@RestController
@RequestMapping("/admin/affiche")
public class AfficheController {


    @Autowired
    private UserService userService;
    @Autowired
    private AfficheService afficheService;
    /**
     * 分页查询公告列表
     * @param token token
     * @param pager 查询参数
     * @return map
     */
    @UserLoginToken
    @GetMapping("/list")
    public Map<String,Object> list(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            Page<Affiche> pager
    ) {
        Map<String, Object> data = JwtUtil.renewalToken(token);
        Page<Affiche> page = afficheService.page(pager,new QueryWrapper<Affiche>()
                .eq("state",1)
                .or()
                .eq("state",0)
        );
        if (page != null){
            List<AfficheVo> list = passList(page);
            data.put("list",list);
            data.put("total",page.getTotal());
            return AjaxResult.success(data);
        }
        return AjaxResult.fail();
    }


    /**
     * 查询公告（模糊）
     * @param token token
     * @param selectKey 查询条件
     * @param page 当前页
     * @param pageSize 单页数据条数
     * @return map
     */
    @UserLoginToken
    @GetMapping("/selectByKey")
    public Map<String,Object> selectByKey(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            String selectKey,
            Integer page,
            Integer pageSize
    ){
        //更新token
        Map<String, Object> data = JwtUtil.renewalToken(token);
        selectKey = selectKey == null ? "" : selectKey;
        //查询公告
        Page<Affiche> affichePage = afficheService.page(new Page<>(page, pageSize),
                new QueryWrapper<Affiche>()
                    .like("title", selectKey)
        );
        if (affichePage != null){
            List<AfficheVo> list = passList(affichePage);
            data.put("list",list);
            return AjaxResult.success(data);
        }
        return AjaxResult.fail();
    }


    /**
     * 通过id查询
     * @param token token
     * @param aid 公告id
     * @return map
     */
    @UserLoginToken
    @GetMapping("/selectById")
    public Map<String,Object> selectById(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            Long aid
    ){
        Map<String, Object> data = JwtUtil.renewalToken(token);
        Affiche affiche = afficheService.getOne(
                new QueryWrapper<Affiche>().eq("aid", aid));
        if (affiche != null){
            data.put("affiche",affiche);
            return AjaxResult.success(data);
        } else {
            return AjaxResult.fail();
        }
    }


    /**
     * 新增公告
     * @param token token
     * @param affiche 公告信息
     * @return map
     */
    @UserLoginToken
    @PostMapping("/insert")
    public Map<String,Object> insert(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            @RequestBody Affiche affiche
    ){
        affiche.setCreateTime(LocalDateTime.now());
        boolean result = afficheService.save(affiche);
        Map<String, Object> data = JwtUtil.renewalToken(token);
        if (result){
            return AjaxResult.success(data);
        }
        return AjaxResult.fail();
    }


    /**
     * 通过id删除一条班级信息
     * @param token token
     * @param aid 公告id
     * @return map
     */
    @UserLoginToken
    @DeleteMapping("/del")
    public Map<String, Object> del(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            Long aid
    ){

        Map<String, Object> data = JwtUtil.renewalToken(token);

        boolean result = afficheService.removeById(aid);
        if (result){
            //删除成功
            return AjaxResult.success(data);
        }
        return AjaxResult.fail();
    }


    /**
     * 批量删除班级
     * @param token token
     * @param aids 公告ids
     * @return map
     */
    @UserLoginToken
    @DeleteMapping("/delAll")
    public Map<String,Object> deleteAll(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            String aids
    ){
        //续签token
        //将id解析出来
        String[] ids = aids.split("-");
        ArrayList<Long> id = new ArrayList<>();
        Map<String, Object> data = JwtUtil.renewalToken(token);
        //遍历ids
        for (String aid: ids){
            //将uid转换成Long类型
            id.add(Long.parseLong(aid));
        }
        //批量删除
        boolean result = afficheService.removeByIds(id);
        //判断是否删除成功
        if (result){
            //成功
            return AjaxResult.success(data);
        }
        return AjaxResult.fail();
    }


    /**
     * 通过id修改班级信息
     * @param token token令牌
     * @param affiche 班级信息
     * @return map
     */
    @UserLoginToken
    @PutMapping("/update")
    public Map<String,Object> update(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            @RequestBody Affiche affiche
    ){
        //更新token
        Map<String, Object> data = JwtUtil.renewalToken(token);
        //设置更改时间
        affiche.setUpdateTime(LocalDateTime.now());

        boolean result = afficheService.updateById(affiche);
        if (result){
            return AjaxResult.success(data);
        } else {
            return AjaxResult.fail();
        }
    }

    /**
     * 查询待审核的公告
     * @param token 令牌
     * @return map
     */
    @UserLoginToken
    @GetMapping("/selectAudit")
    public Map<String, Object> selectAudit(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token
    ){

        //刷新token
        Map<String, Object> data = JwtUtil.renewalToken(token);
        //获取未审核的公告
        List<Affiche> reviewed = afficheService.list(
                new QueryWrapper<Affiche>().eq("state", 2));
        //判断是否查询到结果
        if (reviewed != null){
            return AjaxResult.success(data);
        }
        //操作失败
        return AjaxResult.fail();
    }



    /**
     * 用于组装AfficheVo
     * @param data 分页数据
     * @return List<AfficheVo>
     */
    private List<AfficheVo> passList(Object data){
        ArrayList<AfficheVo> list = new ArrayList<>();
        List<Affiche> reviewedList;
        if (data instanceof Page){
            Page<Affiche> page = (Page<Affiche>) data;
            reviewedList = page.getRecords();
        } else {
            reviewedList = (List<Affiche>) data;
        }
        for (Affiche a: reviewedList) {
            AfficheVo vo = new AfficheVo();
            vo.setAid(a.getAid());
            vo.setTitle(a.getTitle());
            vo.setUid(a.getUid());
            vo.setState(a.getState());
            vo.setBody(a.getBody());
            vo.setCreateTime(a.getCreateTime());
            vo.setUpdateTime(a.getUpdateTime());
            User user = userService.getById(a.getUid());
            if (user != null){
                vo.setPublisher(user.getUsername());
            }
            list.add(vo);
        }
        return list;
    }

}

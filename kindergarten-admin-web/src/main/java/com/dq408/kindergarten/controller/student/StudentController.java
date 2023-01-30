package com.dq408.kindergarten.controller.student;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dq408.kindergarten.domain.Classandgrade;
import com.dq408.kindergarten.domain.Student;
import com.dq408.kindergarten.domain.User;
import com.dq408.kindergarten.service.ClassandgradeService;
import com.dq408.kindergarten.service.StudentService;
import com.dq408.kindergarten.service.UserService;
import com.dq408.kindergarten.utils.AjaxResult;
import com.dq408.kindergarten.utils.jwt.JwtUtil;
import com.dq408.kindergarten.utils.jwt.anntation.UserLoginToken;
import com.dq408.kindergarten.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 处理学生相关事务的Controller
 * @author XIE_HRZGZ
 */
@RestController
@RequestMapping("/admin/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassandgradeService classAndGradeService;
    @Autowired
    private UserService userService;


    /**
     * 获取学生列表
     * @param token token令牌
     * @param pages 分页参数
     * @return map
     */
    @UserLoginToken
    @GetMapping("/studentList")
    public Map<String, Object> getStudentList(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            Page<Student> pages
    ){

        Map<String, Object> data = JwtUtil.renewalToken(token);
        Page<Student> studentPage = studentService.page(pages);

        ArrayList<StudentVo> list = new ArrayList<>();

        passPage(studentPage,list);

        if (studentPage != null){
            data.put("total",studentPage.getTotal());
            data.put("list",list);
            return AjaxResult.success(data);
        }
        return AjaxResult.fail(data);
    }


    private void passPage(Page<Student> studentPage, List<StudentVo> list){
        for (Student s: studentPage.getRecords()){
            StudentVo studentVo = new StudentVo();
            //查询该学生的班级
            Classandgrade classAndGrade = classAndGradeService.getById(s.getCid());
            //查询该学生的家长
            User patriarch = userService.getById(s.getUid());
            studentVo.setSid(s.getSid());
            studentVo.setSname(s.getSname());
            studentVo.setGander(s.getGander());
            studentVo.setAge(s.getAge());
            studentVo.setCreateTime(s.getCreateTime());
            studentVo.setUpdateTime(s.getUpdateTime());
            studentVo.setAddress(s.getAddress());
            studentVo.setCid(s.getCid());
            studentVo.setUid(s.getUid());
            studentVo.setClassName(classAndGrade.getCname());
            studentVo.setPatriarchName(patriarch.getUsername());

            list.add(studentVo);
        }
    }


    /**
     *
     * @param token token令牌
     * @param selectKey 查询条件
     * @param page 当前页
     * @param pageSize 每页信息条数
     * @return map
     */
    @UserLoginToken
    @GetMapping("/selectByKey")
    public Map<String, Object> selectByKey(
            @RequestHeader(JwtUtil.HEADER_TOKEN_NAME) String token,
            String selectKey,
            Integer page,
            Integer pageSize
    ){

        //更新token
        Map<String, Object> data = JwtUtil.renewalToken(token);

        selectKey = selectKey == null ? "" : selectKey;
        //查询学生
        Page<Student> studentPage = studentService.page(new Page<Student>(page, pageSize),
                new QueryWrapper<Student>()
                        .like("sname", selectKey)
        );

        ArrayList<StudentVo> list = new ArrayList<>();

        this.passPage(studentPage,list);

        if (studentPage != null){
            data.put("list",list);
            return AjaxResult.success(data);
        }
        return AjaxResult.fail(data);

    }
}

package com.dq408.kindergarten.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author XieJinHong
 * @since 2023-01-14
 */
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评语id
     */
    @TableId(value = "cid", type = IdType.AUTO)
    private Long cid;

    /**
     * 评语老师
     */
    private Long uid;

    /**
     * 评语标题
     */
    private String title;

    /**
     * 评语内容
     */
    private String cbody;

    /**
     * 被评学生
     */
    private Long sid;

    /**
     * 评语状态
     */
    private Integer state;

    /**
     * 创建时间
     */
    private LocalDateTime create_time;

    /**
     * 修改时间
     */
    private LocalDateTime update_time;

    /**
     * 预留
     */
    private String yl1;

    /**
     * 预留
     */
    private String yl2;

    /**
     * 预留
     */
    private String yl3;

    /**
     * 预留
     */
    private String yl4;


    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCbody() {
        return cbody;
    }

    public void setCbody(String cbody) {
        this.cbody = cbody;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public LocalDateTime getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(LocalDateTime update_time) {
        this.update_time = update_time;
    }

    public String getYl1() {
        return yl1;
    }

    public void setYl1(String yl1) {
        this.yl1 = yl1;
    }

    public String getYl2() {
        return yl2;
    }

    public void setYl2(String yl2) {
        this.yl2 = yl2;
    }

    public String getYl3() {
        return yl3;
    }

    public void setYl3(String yl3) {
        this.yl3 = yl3;
    }

    public String getYl4() {
        return yl4;
    }

    public void setYl4(String yl4) {
        this.yl4 = yl4;
    }

    @Override
    public String toString() {
        return "Comment{" +
        "cid=" + cid +
        ", uid=" + uid +
        ", title=" + title +
        ", cbody=" + cbody +
        ", sid=" + sid +
        ", state=" + state +
        ", create_time=" + create_time +
        ", update_time=" + update_time +
        ", yl1=" + yl1 +
        ", yl2=" + yl2 +
        ", yl3=" + yl3 +
        ", yl4=" + yl4 +
        "}";
    }
}

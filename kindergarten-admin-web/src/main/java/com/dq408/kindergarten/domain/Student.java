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
 * @since 2023-01-19
 */
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生id
     */
    @TableId(value = "sid", type = IdType.AUTO)
    private Long sid;

    /**
     * 学生名字
     */
    private String sname;

    /**
     * 学生性别
     */
    private String gander;

    /**
     * 学生年龄
     */
    private Integer age;

    /**
     * 学生家庭住址
     */
    private String address;

    /**
     * 班级id
     */
    private Long cid;

    /**
     * 学生家长
     */
    private Long uid;

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


    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getGander() {
        return gander;
    }

    public void setGander(String gander) {
        this.gander = gander;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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
        return "Student{" +
        "sid=" + sid +
        ", sname=" + sname +
        ", gander=" + gander +
        ", age=" + age +
        ", address=" + address +
        ", cid=" + cid +
        ", uid=" + uid +
        ", create_time=" + create_time +
        ", update_time=" + update_time +
        ", yl1=" + yl1 +
        ", yl2=" + yl2 +
        ", yl3=" + yl3 +
        ", yl4=" + yl4 +
        "}";
    }
}

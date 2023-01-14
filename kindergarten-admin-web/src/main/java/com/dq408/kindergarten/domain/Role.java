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
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @TableId(value = "rid", type = IdType.AUTO)
    private Long rid;

    /**
     * 角色名称
     */
    private String rname;

    /**
     * 角色状态
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


    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
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
        return "Role{" +
        "rid=" + rid +
        ", rname=" + rname +
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

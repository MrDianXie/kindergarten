package com.dq408.kindergarten.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author XieJinHong
 * @since 2023-01-19
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "uid", type = IdType.AUTO)
    private Long uid;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户助记码
     */
    private String mnemonicCode;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
     */
    private String gander;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户角色
     */
    private Long roleid;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

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


    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMnemonicCode() {
        return mnemonicCode;
    }

    public void setMnemonicCode(String mnemonicCode) {
        this.mnemonicCode = mnemonicCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGander() {
        return gander;
    }

    public void setGander(String gander) {
        this.gander = gander;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
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
        return "User{" +
        "uid=" + uid +
        ", username=" + username +
        ", avatar=" + avatar +
        ", mnemonicCode=" + mnemonicCode +
        ", password=" + password +
        ", gander=" + gander +
        ", phone=" + phone +
        ", email=" + email +
        ", roleid=" + roleid +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", yl1=" + yl1 +
        ", yl2=" + yl2 +
        ", yl3=" + yl3 +
        ", yl4=" + yl4 +
        "}";
    }
}

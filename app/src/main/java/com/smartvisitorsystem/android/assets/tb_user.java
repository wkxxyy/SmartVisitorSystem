package com.smartvisitorsystem.android.assets;

import org.litepal.crud.DataSupport;

/*
    管理员表

 */

public class tb_user extends DataSupport {
    private int id;//id
    private int user_id;
    private String name;//管理员姓名
    private String no;//管理员员身份证
    private String tel;//管理员联系方式
    private String position;//管理员职位
    private String password;//管理员密码
    private int authorization;//权限，0为系统管理员，1为管理员；

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAuthorization() {
        return authorization;
    }

    public void setAuthorization(int authorization) {
        this.authorization = authorization;
    }


}

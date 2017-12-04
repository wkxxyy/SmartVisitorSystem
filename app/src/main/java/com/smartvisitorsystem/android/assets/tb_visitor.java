package com.smartvisitorsystem.android.assets;

import org.litepal.crud.DataSupport;

/*
    访客表


 */

public class tb_visitor extends DataSupport {
    private int id;//id
    private int visitorId;
    private String name;//访客姓名
    private String no;//访客身份证
    private String tel;//访客联系方式



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(int visitorId) {
        this.visitorId = visitorId;
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

}

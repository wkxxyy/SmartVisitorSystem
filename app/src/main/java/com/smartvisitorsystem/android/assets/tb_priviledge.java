package com.smartvisitorsystem.android.assets;

import org.litepal.crud.DataSupport;

/**
 * Created by wkxxf on 2017/10/24.
 */
/*
    权限表


 */
public class tb_priviledge extends DataSupport {
    private int id;//id
    private int priviledge_id;
    private  String priviledge;//权限名称

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriviledge_id() {
        return priviledge_id;
    }

    public void setPriviledge_id(int priviledge_id) {
        this.priviledge_id = priviledge_id;
    }

    public String getPriviledge() {
        return priviledge;
    }

    public void setPriviledge(String priviledge) {
        this.priviledge = priviledge;
    }


}

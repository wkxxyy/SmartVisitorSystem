package com.smartvisitorsystem.android.assets;

import org.litepal.crud.DataSupport;

/**
 * Created by wkxxf on 2017/10/24.
 */
/*
    管理员权限表



        tb_priviledge1.setPriviledge_id(1);
        tb_priviledge1.setPriviledge("查询");


        tb_priviledge2.setPriviledge_id(2);
        tb_priviledge2.setPriviledge("修改");



        tb_priviledge3.setPriviledge_id(3);
        tb_priviledge3.setPriviledge("导出");


        tb_priviledge4.setPriviledge_id(4);
        tb_priviledge4.setPriviledge("删除");


        tb_priviledge5.setPriviledge("创建管理员");
        tb_priviledgesList.add(tb_priviledge5);


 */
public class tb_authorization extends DataSupport {
    private int id;//id
    private int user_id;//管理员id
    private int priviledge_id;//权限id

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

    public int getPriviledge_id() {
        return priviledge_id;
    }

    public void setPriviledge_id(int priviledge_id) {
        this.priviledge_id = priviledge_id;
    }
}

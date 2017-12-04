package com.smartvisitorsystem.android.assets;

import org.litepal.crud.DataSupport;

/**
 * Created by wkxxf on 2017/10/25.
 */

/*
    亲属表


 */
public class tb_relation extends DataSupport {
    private int id;//id
    private String name;//亲属姓名
    private String no;//亲属身份证
    private String tel;//亲属联系方式
    private int staff_id;//职员id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }
}

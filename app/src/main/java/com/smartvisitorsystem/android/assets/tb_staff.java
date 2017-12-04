package com.smartvisitorsystem.android.assets;

import org.litepal.crud.DataSupport;

/**
 * Created by wkxxf on 2017/10/25.
 */

public class tb_staff extends DataSupport {
    private int id;//id
    private int staff_id;
    private String name;//职员姓名
    private String no;//职员身份证号
    private String tel;//职员联系方式
    private String title;//zhiwei
    private String dept;//bumen

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}

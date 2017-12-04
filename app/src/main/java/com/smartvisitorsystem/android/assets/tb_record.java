package com.smartvisitorsystem.android.assets;

import org.litepal.crud.DataSupport;

/**
 * Created by wkxxf on 2017/10/25.
 */

/*
    来访记录表


 */
public class tb_record extends DataSupport {
    private int id;//id
    private int recordId;
    private int visitor_id_text;//访客id
    private int staff_id_text;//职员id
    private String fromtime;//来访时间
    private String totime;//签退时间
    private String motivations_text;//来访目的
    private boolean confirm_boolean=false;//来访确认


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVisitor_id_text() {
        return visitor_id_text;
    }

    public void setVisitor_id_text(int visitor_id_text) {
        this.visitor_id_text = visitor_id_text;
    }

    public int getStaff_id_text() {
        return staff_id_text;
    }

    public void setStaff_id_text(int staff_id_text) {
        this.staff_id_text = staff_id_text;
    }

    public String getFromtime() {
        return fromtime;
    }

    public void setFromtime(String fromtime) {
        this.fromtime = fromtime;
    }

    public String getTotime() {
        return totime;
    }

    public void setTotime(String totime) {
        this.totime = totime;
    }

    public String getMotivations_text() {
        return motivations_text;
    }

    public void setMotivations_text(String motivations_text) {
        this.motivations_text = motivations_text;
    }

    public boolean isConfirm_boolean() {
        return confirm_boolean;
    }

    public void setConfirm_boolean(boolean confirm_boolean) {
        this.confirm_boolean = confirm_boolean;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }
}

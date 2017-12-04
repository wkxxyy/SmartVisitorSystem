package com.smartvisitorsystem.android.Util;

import com.smartvisitorsystem.android.assets.*;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wkxxf on 2017/12/3.
 */

public class AddRecord {

    public static boolean addRecord(int visitor_id, int staffId, String motivations_text) {//访问动机message

        boolean isSucceed = false;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String time = formatter.format(curDate);


        boolean isFirstCrateRecordTable = DataSupport.findAll(tb_record.class).size() > 0 ? false : true;

        if (isFirstCrateRecordTable) {//是否是第一次visitor表
            tb_record tb_record = new tb_record();
            tb_record.setRecordId(1);
            tb_record.setFromtime(time);
            tb_record.setMotivations_text(motivations_text);
            tb_record.setStaff_id_text(staffId);
            tb_record.setVisitor_id_text(visitor_id);
            isSucceed = true;//添加访客表
        } else {
            tb_record tb_record = new tb_record();
            int recordId = DataSupport.findLast(tb_record.class).getRecordId() + 1;
            tb_record.setRecordId(recordId);
            tb_record.setFromtime(time);
            tb_record.setMotivations_text(motivations_text);
            tb_record.setStaff_id_text(staffId);
            tb_record.setVisitor_id_text(visitor_id);
            isSucceed = true;//添加访客表
        }


        return isSucceed;
    }
}

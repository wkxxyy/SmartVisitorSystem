package com.smartvisitorsystem.android.Util;

import com.smartvisitorsystem.android.assets.*;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 17-12-6.
 */

public class SignOutVisitor
{
    public static boolean SignOutVisitor(String visitorName) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String time = formatter.format(curDate);

        boolean isSucceed = false;


        List<tb_visitor> visitorList = DataSupport.select("name=?", visitorName).find(tb_visitor.class);

        if (visitorList.size() > 0) {

            tb_record tb_record = new tb_record();
            tb_record.setTotime(time);
            tb_record.updateAll("visitor_id_text=?", String.valueOf(visitorList.get(0).getVisitorId()));

            isSucceed = true;
        }


    return isSucceed;

    }




}

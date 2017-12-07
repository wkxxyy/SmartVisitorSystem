package com.smartvisitorsystem.android.Util;

import com.smartvisitorsystem.android.assets.tb_staff;
import com.smartvisitorsystem.android.assets.tb_user;
import com.smartvisitorsystem.android.assets.tb_visitor;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wkxxf on 2017/12/2.
 */

public class AddVisitor {

    public static boolean addVisitor(String visitorName,String visitorNoNum,String visitorTel,String visitorMessage,String visitorToStaffName){

        boolean isSucceed=false;

        boolean isFirstCratevisitorTable= DataSupport.findAll(tb_visitor.class).size()>0? false:true;//是否第一次

        List<tb_staff> tb_staffList=new ArrayList<tb_staff>();

        tb_staffList=DataSupport.where("name=?",visitorToStaffName).find(tb_staff.class);

        if (tb_staffList.size()==0){

            return isSucceed;
        }

        if (DataSupport.where("name=?",visitorName).find(tb_visitor.class).size()==0){//是否有这个记录
            if (isFirstCratevisitorTable){//是否是第一次visitor表
                tb_visitor tb_visitor=new tb_visitor();
                tb_visitor.setVisitorId(1);
                tb_visitor.setName(visitorName);
                tb_visitor.setNo(visitorNoNum);
                tb_visitor.setTel(visitorTel);
                tb_visitor.save();
                isSucceed=true&&AddRecord.addRecord(1,tb_staffList.get(0).getStaff_id(),visitorMessage);//添加访客记录表

            }else {
                tb_visitor tb_visitor=new tb_visitor();
                int visitorId=DataSupport.findLast(tb_visitor.class).getVisitorId()+1;
                tb_visitor.setVisitorId(visitorId);
                tb_visitor.setName(visitorName);
                tb_visitor.setNo(visitorNoNum);
                tb_visitor.setTel(visitorTel);
                tb_visitor.save();
                isSucceed=true&&AddRecord.addRecord(visitorId,tb_staffList.get(0).getStaff_id(),visitorMessage);//添加访客记录表表
            }

        }

        return isSucceed;
    }
}

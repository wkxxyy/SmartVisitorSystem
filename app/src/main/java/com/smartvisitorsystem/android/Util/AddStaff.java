package com.smartvisitorsystem.android.Util;

import com.smartvisitorsystem.android.assets.tb_staff;
import com.smartvisitorsystem.android.assets.tb_user;

import org.litepal.crud.DataSupport;

/**
 * Created by root on 17-12-4.
 */

public class AddStaff {

    public static boolean addStaff(String staffName,String staffNoNum,String staffTel,String staffTitle,String staffDept){

        boolean isSucceed=false;

        boolean isFirstCrateStaffTable= DataSupport.findAll(tb_staff.class).size()>0? false:true;

        if (DataSupport.where("name=?",staffName).find(tb_staff.class).size()==0){//是否有这个记录
            if (isFirstCrateStaffTable){//是否是第一次user表
                tb_staff tb_staff=new tb_staff();
                tb_staff.setStaff_id(1);
                tb_staff.setName(staffName);
                tb_staff.setNo(staffNoNum);
                tb_staff.setTel(staffTel);
                tb_staff.setTitle(staffTitle);
                tb_staff.setDept(staffDept);
                tb_staff.save();
                isSucceed=true;
            }else {
                tb_staff tb_staff=new tb_staff();
                int staffId=DataSupport.findLast(tb_staff.class).getStaff_id()+1;
                tb_staff.setStaff_id(staffId);
                tb_staff.setName(staffName);
                tb_staff.setNo(staffNoNum);
                tb_staff.setTel(staffTel);
                tb_staff.setTitle(staffTitle);
                tb_staff.setDept(staffDept);
                tb_staff.save();
                isSucceed=true;
            }

        }

        return isSucceed;
    }
}

package com.smartvisitorsystem.android.Util;

import android.content.SharedPreferences;
import android.util.Log;

import com.smartvisitorsystem.android.assets.tb_user;

import org.litepal.crud.DataSupport;

/**
 * Created by wkxxf on 2017/12/2.
 */

public class AddUser {

    public static boolean addUser(String userName,String userIdNum,String usetTel,String userPosition,int usetPriviledgeName){

        boolean isSucceed=false;

        boolean isFirstCrateUserTable=DataSupport.findAll(tb_user.class).size()>0? false:true;

        if (DataSupport.where("name=?",userName).find(tb_user.class).size()==0){//是否有这个记录
            if (isFirstCrateUserTable){//是否是第一次user表
                tb_user tb_user=new tb_user();
                tb_user.setUser_id(1);
                tb_user.setName(userName);
                tb_user.setNo(userIdNum);
                tb_user.setTel(usetTel);
                tb_user.setPosition(userPosition);
                tb_user.setAuthorization(usetPriviledgeName);
                tb_user.save();
                isSucceed=true;
            }else {
                tb_user tb_user=new tb_user();
                tb_user.setUser_id(DataSupport.findLast(tb_user.class).getUser_id()+1);
                tb_user.setName(userName);
                tb_user.setNo(userIdNum);
                tb_user.setTel(usetTel);
                tb_user.setPosition(userPosition);
                tb_user.setAuthorization(usetPriviledgeName);
                tb_user.save();
                isSucceed=true;
            }

        }

        return isSucceed;
    }
}

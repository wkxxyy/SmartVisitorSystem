package com.smartvisitorsystem.android.VisitorRegistration.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by wkxxf on 2017/8/21.
 */

public class JSON {

    private JSONObject jsonObject;

    public JSON(String responce){
        try{
            jsonObject=new JSONObject(responce);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public String getJSONText(String key){
        String text = null;
        try{
            JSONArray jsonArrayText=jsonObject.getJSONArray(key);
            text=jsonArrayText.get(0).toString();

        }catch (Exception e){
            e.printStackTrace();
        }

        return text;
    }
}

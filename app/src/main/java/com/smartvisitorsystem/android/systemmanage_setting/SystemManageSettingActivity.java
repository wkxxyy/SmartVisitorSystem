package com.smartvisitorsystem.android.systemmanage_setting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.smartvisitorsystem.android.R;
import com.smartvisitorsystem.android.systemmanage_setting.QueryVisitorMessage.QueryVisitorMessageActivity;
import com.smartvisitorsystem.android.systemmanage_setting.UpdateVisitorMessage.UpdateVisitorMessageActivity;

public class SystemManageSettingActivity extends AppCompatActivity implements View.OnClickListener{

    Button deleteVisitorMessage;
    Button queryVisitorMessage;
    Button exportVisitorMessage;
    Button updateVisitorMessage;
    Button createManage;
    Button createStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_manage_setting);


        deleteVisitorMessage=(Button)findViewById(R.id.delete_visitor_message);
        queryVisitorMessage=(Button)findViewById(R.id.query_visitor_message);
        exportVisitorMessage=(Button)findViewById(R.id.export_visitor_message);
        updateVisitorMessage=(Button)findViewById(R.id.update_visitor_message);
        createManage=(Button)findViewById(R.id.create_manage);
        createStaff=(Button)findViewById(R.id.create_staff_system_manage);

        deleteVisitorMessage.setOnClickListener(this);
        queryVisitorMessage.setOnClickListener(this);
        exportVisitorMessage.setOnClickListener(this);
        updateVisitorMessage.setOnClickListener(this);
        createManage.setOnClickListener(this);
        createStaff.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.delete_visitor_message:{
                Intent intent=new Intent(SystemManageSettingActivity.this,DeleteVisitorMessageActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.query_visitor_message:{
                Intent intent=new Intent(SystemManageSettingActivity.this,QueryVisitorMessageActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.export_visitor_message:{
                Intent intent=new Intent(SystemManageSettingActivity.this,ExportVisitorMessageActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.update_visitor_message:{
                Intent intent=new Intent(SystemManageSettingActivity.this,UpdateVisitorMessageActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.create_manage:{
                Intent intent=new Intent(SystemManageSettingActivity.this,CreateManageActivity.class);
                startActivity(intent);
                break;

            }
            case R.id.create_staff_system_manage:{
                Intent intent=new Intent(SystemManageSettingActivity.this,CreateStaffActivity.class);
                startActivity(intent);
                break;
            }


        }
    }
}

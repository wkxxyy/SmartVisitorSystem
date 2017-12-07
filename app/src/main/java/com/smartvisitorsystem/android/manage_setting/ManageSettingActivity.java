package com.smartvisitorsystem.android.manage_setting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.smartvisitorsystem.android.R;
import com.smartvisitorsystem.android.systemmanage_setting.CreateStaffActivity;
import com.smartvisitorsystem.android.systemmanage_setting.ExportVisitorMessageActivity;
import com.smartvisitorsystem.android.systemmanage_setting.QueryVisitorMessage.QueryVisitorMessageActivity;
import com.smartvisitorsystem.android.systemmanage_setting.UpdateVisitorMessage.UpdateVisitorMessageActivity;

public class ManageSettingActivity extends AppCompatActivity implements View.OnClickListener{
    Button queryVisitorMessage;
    Button exportVisitorMessage;
    Button updateVisitorMessage;
    Button createStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_setting);


        queryVisitorMessage=(Button)findViewById(R.id.query_visitor_message_manage);
        exportVisitorMessage=(Button)findViewById(R.id.export_visitor_message_manage);
        updateVisitorMessage=(Button)findViewById(R.id.update_visitor_message_manage);
        createStaff=(Button)findViewById(R.id.create_staff_manage);

        queryVisitorMessage.setOnClickListener(this);
        exportVisitorMessage.setOnClickListener(this);
        updateVisitorMessage.setOnClickListener(this);
        createStaff.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.query_visitor_message_manage:{
                Intent intent=new Intent(ManageSettingActivity.this,QueryVisitorMessageActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.export_visitor_message_manage:{
                Intent intent=new Intent(ManageSettingActivity.this,ExportVisitorMessageActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.update_visitor_message_manage:{
                Intent intent=new Intent(ManageSettingActivity.this,UpdateVisitorMessageActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.create_staff_manage:{
                Intent intent=new Intent(ManageSettingActivity.this,CreateStaffActivity.class);
                startActivity(intent);
                break;
            }


        }
    }
}

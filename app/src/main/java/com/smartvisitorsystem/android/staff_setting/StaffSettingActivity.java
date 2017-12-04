package com.smartvisitorsystem.android.staff_setting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.smartvisitorsystem.android.R;
import com.smartvisitorsystem.android.VisitorRegistration.IdcardResActivity;
import com.smartvisitorsystem.android.VisitorRegistration.VisitorRegistrationActivity;

public class StaffSettingActivity extends AppCompatActivity implements View.OnClickListener{

    Button staffGreenChannel;
    Button staffSecretary;
    Button staffHoliday;
    Button staffBlackList;
    Button staffWhiteList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_setting);

        staffGreenChannel=(Button)findViewById(R.id.staff_green_channel);
        staffSecretary=(Button)findViewById(R.id.staff_secretary);
        staffHoliday=(Button)findViewById(R.id.staff_holiday);
        staffBlackList=(Button)findViewById(R.id.staff_black_list);
        staffWhiteList=(Button)findViewById(R.id.staff_white_list);

        staffGreenChannel.setOnClickListener(this);
        staffSecretary.setOnClickListener(this);
        staffHoliday.setOnClickListener(this);
        staffBlackList.setOnClickListener(this);
        staffWhiteList.setOnClickListener(this);
    }




    public void onClick(View view) {
        switch (view.getId()){
            case R.id.staff_green_channel:{
                Intent intent=new Intent(StaffSettingActivity.this,StaffGreenChannelActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.staff_secretary:{
                Intent intent=new Intent(StaffSettingActivity.this,StaffSecretaryActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.staff_holiday:{
                Intent intent=new Intent(StaffSettingActivity.this,StaffHolidayActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.staff_black_list:{
                Intent intent=new Intent(StaffSettingActivity.this,StaffBlackListActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.staff_white_list:{
                Intent intent=new Intent(StaffSettingActivity.this,StaffWhiteListActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}

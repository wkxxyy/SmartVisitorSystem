package com.smartvisitorsystem.android.system_setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.smartvisitorsystem.android.R;

public class SystemSettingActivity extends AppCompatActivity implements View.OnClickListener{

    Button bootFromTheStart;
    Button automaticShutDown;
    Button emptyTheDatabaseVisitor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_setting);

        bootFromTheStart=(Button)findViewById(R.id.boot_from_the_start);
        automaticShutDown=(Button)findViewById(R.id.automatic_shut_down);
        emptyTheDatabaseVisitor=(Button)findViewById(R.id.empty_the_database_visitor);

        bootFromTheStart.setOnClickListener(this);
        automaticShutDown.setOnClickListener(this);
        emptyTheDatabaseVisitor.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.boot_from_the_start:{

                break;
            }
            case R.id.automatic_shut_down:{

                break;
            }
            case R.id.empty_the_database_visitor:{

                break;
            }
        }
    }
}

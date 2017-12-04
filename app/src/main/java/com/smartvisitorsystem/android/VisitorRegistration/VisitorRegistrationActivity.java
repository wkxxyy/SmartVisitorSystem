package com.smartvisitorsystem.android.VisitorRegistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.smartvisitorsystem.android.R;
import com.smartvisitorsystem.android.VisitorRegistration.SignOut.SignOutActivity;

public class VisitorRegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    Button idcardRes;
    Button bcardRes;
    Button manualRes;
    Button qrcodeRes;
    Button signOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_registration);

        idcardRes=(Button)findViewById(R.id.idcard_res);
        bcardRes=(Button)findViewById(R.id.bcard_res);
        manualRes=(Button)findViewById(R.id.manual_res);
        qrcodeRes=(Button)findViewById(R.id.qrcode_res);
        signOut=(Button)findViewById(R.id.sign_out);

        idcardRes.setOnClickListener(this);
        bcardRes.setOnClickListener(this);
        manualRes.setOnClickListener(this);
        qrcodeRes.setOnClickListener(this);
        signOut.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.idcard_res:{
                Intent intent=new Intent(VisitorRegistrationActivity.this,IdcardResActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.bcard_res:{
                Intent intent=new Intent(VisitorRegistrationActivity.this,BcardResActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.manual_res:{
                Intent intent=new Intent(VisitorRegistrationActivity.this,ManualResActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.qrcode_res:{
                Intent intent=new Intent(VisitorRegistrationActivity.this,QrcodeResActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.sign_out:{
                Intent intent=new Intent(VisitorRegistrationActivity.this,SignOutActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}

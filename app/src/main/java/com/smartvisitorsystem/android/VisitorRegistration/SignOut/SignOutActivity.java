package com.smartvisitorsystem.android.VisitorRegistration.SignOut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.smartvisitorsystem.android.R;

public class SignOutActivity extends AppCompatActivity {

    Button manualSignOut;
    Button idCardSignOut;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regis_sign_out);


        manualSignOut=(Button)findViewById(R.id.manual_sign_out_button);
        idCardSignOut=(Button)findViewById(R.id.idcard_sign_out_button);

        manualSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignOutActivity.this,ManualSignOutActivity.class);
                startActivity(intent);
            }
        });

        idCardSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignOutActivity.this,IdCardSignOutActivity.class);
                startActivity(intent);
            }
        });
    }
}

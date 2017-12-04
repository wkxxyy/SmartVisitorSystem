package com.smartvisitorsystem.android.systemmanage_setting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.smartvisitorsystem.android.LoginActivity;
import com.smartvisitorsystem.android.R;
import com.smartvisitorsystem.android.VisitorRegistration.VisitorRegistrationActivity;
import com.smartvisitorsystem.android.assets.tb_user;

import org.litepal.crud.DataSupport;

import java.util.List;

public class LoginSystemManageSettingActivity extends AppCompatActivity {

    private EditText systemManageAccountEdit;
    private EditText systemManagePasswordEdit;
    private Button systemManageLogin;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_system_manage_setting);

        systemManageAccountEdit=(EditText)findViewById(R.id.system_manage_login_account);
        systemManagePasswordEdit=(EditText)findViewById(R.id.system_manage_login_password);
        systemManageLogin=(Button)findViewById(R.id.system_manage_login_login);









        systemManageLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String account=systemManageAccountEdit.getText().toString();
                String password=systemManagePasswordEdit.getText().toString();

                List<tb_user> tb_usersList= DataSupport.where("name=?",account).find(tb_user.class);

                if (tb_usersList.size()>0){

                    String accountDataBase=tb_usersList.get(0).getName();
                    String passwordDataBase=tb_usersList.get(0).getPassword();
                    int authorizationDataBase=tb_usersList.get(0).getAuthorization();


                    if(account.equals(accountDataBase)&&password.equals(passwordDataBase)&&(authorizationDataBase==0))
                    {

                        Intent intent=new Intent(LoginSystemManageSettingActivity.this,SystemManageSettingActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(LoginSystemManageSettingActivity.this,"用户或者密码输入错误",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginSystemManageSettingActivity.this,"用户或者密码输入错误",Toast.LENGTH_SHORT).show();
                }

            }});

    }
}

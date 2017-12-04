package com.smartvisitorsystem.android.manage_setting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smartvisitorsystem.android.R;
import com.smartvisitorsystem.android.assets.tb_user;
import com.smartvisitorsystem.android.system_setting.SystemSettingActivity;

import org.litepal.crud.DataSupport;

import java.util.List;

public class LoginManageSettingActivity extends AppCompatActivity {

    private EditText manageAccountEdit;
    private EditText managePasswordEdit;
    private Button manageLogin;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_manage_setting);



        manageAccountEdit=(EditText)findViewById(R.id.manage_login_account);
        managePasswordEdit=(EditText)findViewById(R.id.manage_login_password);
        manageLogin=(Button)findViewById(R.id.manage_login_login);







        manageLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String account=manageAccountEdit.getText().toString();
                String password=managePasswordEdit.getText().toString();

                List<tb_user> tb_usersList= DataSupport.where("name=?",account).find(tb_user.class);

                if (tb_usersList.size()>0){

                    String accountDataBase=tb_usersList.get(0).getName();
                    String passwordDataBase=tb_usersList.get(0).getPassword();
                    int authorizationDataBase=tb_usersList.get(0).getAuthorization();


                    if(account.equals(accountDataBase)&&password.equals(passwordDataBase)&&(authorizationDataBase==1))
                    {

                        Intent intent=new Intent(LoginManageSettingActivity.this,ManageSettingActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(LoginManageSettingActivity.this,"用户或者密码输入错误",Toast.LENGTH_SHORT).show();
                    }

                }else {
                        Toast.makeText(LoginManageSettingActivity.this,"用户或者密码输入错误",Toast.LENGTH_SHORT).show();
                }

            }});
    }
}

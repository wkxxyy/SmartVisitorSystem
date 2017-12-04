package com.smartvisitorsystem.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.smartvisitorsystem.android.Util.AddUser;
import com.smartvisitorsystem.android.VisitorRegistration.VisitorRegistrationActivity;
import com.smartvisitorsystem.android.assets.*;
import com.smartvisitorsystem.android.manage_setting.LoginManageSettingActivity;
import com.smartvisitorsystem.android.manage_setting.ManageSettingActivity;
import com.smartvisitorsystem.android.staff_setting.StaffSettingActivity;
import com.smartvisitorsystem.android.system_setting.SystemSettingActivity;
import com.smartvisitorsystem.android.systemmanage_setting.LoginSystemManageSettingActivity;
import com.smartvisitorsystem.android.systemmanage_setting.SystemManageSettingActivity;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private DrawerLayout mdrawerLayout;//
    private NavigationView navigationView;//
    private SharedPreferences sharedPreferences;//
    private SharedPreferences.Editor editor;

    private CheckBox rememberPass;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences("share", MODE_PRIVATE);
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        editor = sharedPreferences.edit();

        if (isFirstRun){//如果是第一次运行就
            Log.d(TAG, "初始化权限表");
            initPriviledge();//初始化权限表

            tb_user root=new tb_user();
            root.setUser_id(1);
            root.setName("root");
            root.setNo("");
            root.setPassword("123456");
            root.setPosition("");
            root.setTel("");
            root.setAuthorization(0);//系统管理员
            root.save();

            tb_authorization tb_authorization=new tb_authorization();
            tb_authorization.setPriviledge_id(0);
            tb_authorization.setUser_id(1);
            tb_authorization.save();

            Log.d(TAG, "添加系统默认用户");
            editor.putBoolean("isFirstRun", false);
            editor.apply();
        }

        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        mdrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView=(NavigationView)findViewById(R.id.nav_view);
        rememberPass=(CheckBox)findViewById(R.id.login_remember_pass);
        accountEdit=(EditText)findViewById(R.id.login_account);
        passwordEdit=(EditText)findViewById(R.id.login_password);
        login=(Button)findViewById(R.id.login_login);

        boolean isRemeber=sharedPreferences.getBoolean("remember_password",false);

        if(isRemeber){
            String account=sharedPreferences.getString("account","");
            String password=sharedPreferences.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String account=accountEdit.getText().toString();
                String password=passwordEdit.getText().toString();

                List<tb_user> tb_usersList=DataSupport.where("name=?",account).find(tb_user.class);

                if (tb_usersList.size()>0){
                    String accountDataBase=tb_usersList.get(0).getName();
                    String passwordDataBase=tb_usersList.get(0).getPassword();


                    if(account.equals(accountDataBase)&&password.equals(passwordDataBase))
                    {
                        if(rememberPass.isChecked())
                        {

                            editor.putBoolean("remember_password",true);
                            editor.putString("account",accountDataBase);
                            editor.putString("password",passwordDataBase);
                            editor.apply();

                        }else {
                            editor.putBoolean("remember_password",false);
                            editor.apply();
                        }


                        Intent intent=new Intent(LoginActivity.this,VisitorRegistrationActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this,"用户或者密码输入错误",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this,"用户或者密码输入错误",Toast.LENGTH_SHORT).show();
                }

            }});

        navigationView.setCheckedItem(R.id.nav_login);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {//滑动栏里面的菜单
                switch (item.getItemId()){
                    case R.id.nav_login:{
                        mdrawerLayout.closeDrawers();
                        break;
                    }
                    case R.id.nav_manage_setting:{
                        Intent intent=new Intent(LoginActivity.this, LoginManageSettingActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.nav_systemmanage_setting:{
                        Intent intent=new Intent(LoginActivity.this, LoginSystemManageSettingActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.nav_system_setting:{
                        Intent intent=new Intent(LoginActivity.this, SystemSettingActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.nav_staff_setting:{
                        Intent intent=new Intent(LoginActivity.this, StaffSettingActivity.class);
                        startActivity(intent);
                        break;
                    }


                }
                return true;
            }
        });




    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mdrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }



    private void initPriviledge(){

        List<tb_priviledge> tb_priviledgesList=new ArrayList<tb_priviledge>();


        tb_priviledge tb_priviledge1=new tb_priviledge();
        tb_priviledge1.setPriviledge_id(1);
        tb_priviledge1.setPriviledge("查询");
        tb_priviledgesList.add(tb_priviledge1);

        tb_priviledge tb_priviledge2=new tb_priviledge();
        tb_priviledge2.setPriviledge_id(2);
        tb_priviledge2.setPriviledge("修改");
        tb_priviledgesList.add(tb_priviledge2);

        tb_priviledge tb_priviledge3=new tb_priviledge();
        tb_priviledge3.setPriviledge_id(3);
        tb_priviledge3.setPriviledge("导出");
        tb_priviledgesList.add(tb_priviledge3);

        tb_priviledge tb_priviledge4=new tb_priviledge();
        tb_priviledge4.setPriviledge_id(4);
        tb_priviledge4.setPriviledge("删除");
        tb_priviledgesList.add(tb_priviledge4);

        tb_priviledge tb_priviledge5=new tb_priviledge();
        tb_priviledge5.setPriviledge_id(5);
        tb_priviledge5.setPriviledge("创建管理员");
        tb_priviledgesList.add(tb_priviledge5);

        DataSupport.saveAll(tb_priviledgesList);

    }
}

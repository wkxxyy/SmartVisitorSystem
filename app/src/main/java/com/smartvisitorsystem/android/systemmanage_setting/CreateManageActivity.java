package com.smartvisitorsystem.android.systemmanage_setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smartvisitorsystem.android.R;
import com.smartvisitorsystem.android.Util.AddUser;
import com.smartvisitorsystem.android.Util.AddVisitor;
import com.smartvisitorsystem.android.VisitorRegistration.BcardResActivity;

public class CreateManageActivity extends AppCompatActivity {

    EditText createManagerNameText;
    EditText createManagerTelText;
    EditText createManagerNoText;
    EditText createManagerTitleText;
    EditText createManagerPassWordText;
    
    Button createManagerSaveMessage;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_manage);
        
        createManagerNameText=(EditText)findViewById(R.id.create_manager_name_text);
        createManagerTelText=(EditText)findViewById(R.id.create_manager_tel_text);
        createManagerNoText=(EditText)findViewById(R.id.create_manager_no_text);
        createManagerTitleText=(EditText)findViewById(R.id.create_manager_title_text);
        createManagerPassWordText=(EditText)findViewById(R.id.create_manager_password_text);
        
        createManagerSaveMessage=(Button)findViewById(R.id.create_manager_save_message);
        
        createManagerSaveMessage.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                String createManagerNameStrig=createManagerNameText.getText().toString();
                String createManagerTelString=createManagerTelText.getText().toString();
                String createManagerNoString=createManagerNoText.getText().toString();
                String createManagerTitleString=createManagerTitleText.getText().toString();
                String createManagerPassWordString=createManagerPassWordText.getText().toString();

                boolean isSucceed= AddUser.addUser(createManagerNameStrig,createManagerNoString,createManagerTelString,createManagerTitleString,createManagerPassWordString,1);
                if (isSucceed){
                    Toast.makeText(CreateManageActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(CreateManageActivity.this,"添加失败请重新添加",Toast.LENGTH_SHORT).show();
                }
            }
        });
        
    }
}

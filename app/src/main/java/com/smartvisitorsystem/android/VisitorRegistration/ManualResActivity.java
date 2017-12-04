package com.smartvisitorsystem.android.VisitorRegistration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smartvisitorsystem.android.R;
import com.smartvisitorsystem.android.Util.AddVisitor;

public class ManualResActivity extends AppCompatActivity {

    EditText manualCardNameText;
    EditText manualCardTelText;
    EditText manualCardNoText;
    EditText manualCardMessage;
    EditText manualCardStaff;



    Button manualCardSaveMessage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regis_manual_res);

        manualCardNameText=(EditText)findViewById(R.id.manual_name_text);
        manualCardTelText=(EditText)findViewById(R.id.manual_tel_text);
        manualCardNoText=(EditText)findViewById(R.id.manual_no_text);
        manualCardMessage=(EditText)findViewById(R.id.manual_message_text);
        manualCardStaff=(EditText)findViewById(R.id.manual_staff_text);

        manualCardSaveMessage=(Button)findViewById(R.id.manual_save_message);

        manualCardSaveMessage.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String manualCardNameStrig=manualCardNameText.getText().toString();
                String manualCardTelString=manualCardTelText.getText().toString();
                String manualCardNoString=manualCardNoText.getText().toString();
                String manualCardMessageString=manualCardMessage.getText().toString();
                String manualCardStaffNameString=manualCardStaff.getText().toString();

                boolean isSucceed= AddVisitor.addVisitor(manualCardNameStrig,manualCardNoString,manualCardTelString,manualCardMessageString,manualCardStaffNameString);

                if (isSucceed){
                    Toast.makeText(ManualResActivity.this,"提交成功，正在打印凭条，请稍等",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ManualResActivity.this,"添加失败请重新添加",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

}

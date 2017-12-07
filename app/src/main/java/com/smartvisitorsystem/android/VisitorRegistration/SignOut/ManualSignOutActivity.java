package com.smartvisitorsystem.android.VisitorRegistration.SignOut;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smartvisitorsystem.android.R;
import com.smartvisitorsystem.android.Util.SignOutVisitor;

public class ManualSignOutActivity extends AppCompatActivity {

    EditText manualSignOutNameText;

    Button manualSignOutConfirm;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_sign_out);

        manualSignOutNameText=(EditText)findViewById(R.id.manual_sign_out_name_text);

        manualSignOutConfirm=(Button)findViewById(R.id.manual_sign_out_confirm);

        manualSignOutConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String manualNameStrig=manualSignOutNameText.getText().toString();

                boolean isSucceed= SignOutVisitor.SignOutVisitor(manualNameStrig);

                if (isSucceed){
                    Toast.makeText(ManualSignOutActivity.this,"签退成功",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(ManualSignOutActivity.this,"签退失败",Toast.LENGTH_SHORT).show();

            }
            }
        });
    }
}

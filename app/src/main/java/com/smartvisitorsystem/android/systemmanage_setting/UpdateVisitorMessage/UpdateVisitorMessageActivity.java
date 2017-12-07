package com.smartvisitorsystem.android.systemmanage_setting.UpdateVisitorMessage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.smartvisitorsystem.android.R;

public class UpdateVisitorMessageActivity extends AppCompatActivity {

    EditText updateVisitorMessageText;

    Button updateVisitorMessageButton;
    Button updateVisitorMessageByDay;
    Button updateVisitorMessageByWeek;
    Button updateVisitorMessageByMouth;
    Button updateVisitorMessageByYear;
    
    
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_visitor_message);


        updateVisitorMessageText=(EditText)findViewById(R.id.update_visitor_message_text);

        updateVisitorMessageButton=(Button)findViewById(R.id.update_visitor_message_button);

        updateVisitorMessageByDay=(Button)findViewById(R.id.update_by_day_button);
        updateVisitorMessageByWeek=(Button)findViewById(R.id.update_by_week_button);
        updateVisitorMessageByMouth=(Button)findViewById(R.id.update_by_mouth_button);
        updateVisitorMessageByMouth=(Button)findViewById(R.id.update_by_mouth_button);
        updateVisitorMessageByYear=(Button)findViewById(R.id.update_by_year_button);
    
    
    
    
    
    
    
    
    
    }
}

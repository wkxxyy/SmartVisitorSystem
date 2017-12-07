package com.smartvisitorsystem.android.systemmanage_setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.smartvisitorsystem.android.R;

public class DeleteVisitorMessageActivity extends AppCompatActivity {


    EditText deleteVisitorMessageText;

    Button deleteVisitorMessageButton;
    Button deleteVisitorMessageByDay;
    Button deleteVisitorMessageByWeek;
    Button deleteVisitorMessageByMouth;
    Button deleteVisitorMessageByYear;
    
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_visitor_message);

        deleteVisitorMessageText=(EditText)findViewById(R.id.delete_visitor_message_text);

        deleteVisitorMessageButton=(Button)findViewById(R.id.delete_visitor_message_button);

        deleteVisitorMessageByDay=(Button)findViewById(R.id.delete_by_day_button);
        deleteVisitorMessageByWeek=(Button)findViewById(R.id.delete_by_week_button);
        deleteVisitorMessageByMouth=(Button)findViewById(R.id.delete_by_mouth_button);
        deleteVisitorMessageByMouth=(Button)findViewById(R.id.delete_by_mouth_button);
        deleteVisitorMessageByYear=(Button)findViewById(R.id.delete_by_year_button);


    }
}

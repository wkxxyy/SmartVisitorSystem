package com.smartvisitorsystem.android.systemmanage_setting.QueryVisitorMessage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.smartvisitorsystem.android.R;

public class QueryVisitorMessageActivity extends AppCompatActivity {

    EditText queryVisitorMessageText;

    Button queryVisitorMessageButton;
    Button queryVisitorMessageByDay;
    Button queryVisitorMessageByWeek;
    Button queryVisitorMessageByMouth;
    Button queryVisitorMessageByYear;

    ListView queryMessageList;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_visitor_message);


        queryVisitorMessageText=(EditText)findViewById(R.id.query_visitor_message_text);

        queryVisitorMessageButton=(Button)findViewById(R.id.query_visitor_message_button);

        queryVisitorMessageByDay=(Button)findViewById(R.id.query_by_day_button);
        queryVisitorMessageByWeek=(Button)findViewById(R.id.query_by_week_button);
        queryVisitorMessageByMouth=(Button)findViewById(R.id.query_by_mouth_button);
        queryVisitorMessageByMouth=(Button)findViewById(R.id.query_by_mouth_button);
        queryVisitorMessageByYear=(Button)findViewById(R.id.query_by_year_button);



    }
}

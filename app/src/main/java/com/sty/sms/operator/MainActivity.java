package com.sty.sms.operator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnDataList;
    private Button btnSmsSendActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setListeners();
    }

    private void initViews() {
        btnDataList = (Button) findViewById(R.id.btn_sms_data_activity);
        btnSmsSendActivity = (Button) findViewById(R.id.btn_sms_send_activity);
    }

    private void setListeners() {
        btnDataList.setOnClickListener(this);
        btnSmsSendActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sms_data_activity:
                Intent intent = new Intent(this, SmsDataActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_sms_send_activity:
                Intent intent2 = new Intent(this, SmsSendActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }
}

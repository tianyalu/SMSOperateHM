package com.sty.sms.operator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Shi Tianyi on 2017/11/19/0019.
 */

public class SmsSendActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etNumber;
    private EditText etSmsContent;
    private Button btnAdd;
    private Button btnSmsInsert;
    private Button btnSmsSend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_send);

        initViews();
        setListeners();
    }

    private void initViews(){
        etNumber = (EditText) findViewById(R.id.et_number);
        etSmsContent = (EditText) findViewById(R.id.et_sms_content);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnSmsInsert = (Button) findViewById(R.id.btn_sms_insert);
        btnSmsSend = (Button) findViewById(R.id.btn_sms_send);
    }

    private void setListeners(){
        btnAdd.setOnClickListener(this);
        btnSmsInsert.setOnClickListener(this);
        btnSmsSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                addContacts();
                break;
            case R.id.btn_sms_insert:
                insertSms();
                break;
            case R.id.btn_sms_send:
                sendSms();
                break;
            default:
                break;
        }
    }

    //点击+按钮 跳转到联系人页面
    private void addContacts(){
        Intent intent = new Intent(this, ContactActivity.class);
        //startActivity(intent);
        //如果一个页面开启另一个页面，需要另一个页面关闭时回传数据时，使用下面的方法
        startActivityForResult(intent, 1);
    }

    //当我们开启的Activity的页面关闭的时候这个方法会被调用
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(requestCode == 1){ //代表要请求ContactActivity的数据
//            String phone = data.getStringExtra("phone");
//            etNumber.setText(phone);
//        }else if(requestCode == 2){ //代表要请求SmsTemplateActivity的数据
//            String smsContent = data.getStringExtra("smsContent");
//            etSmsContent.setText(smsContent);
//        }

        if(resultCode == 10) { //说明该数据是由ContactActivity返回的
            String phone = data.getStringExtra("phone");
            etNumber.setText(phone);
        }else if(resultCode == 20){ //说明该数据是由SmsTemplateActivity返回的
            String smsContent = data.getStringExtra("smsContent");
            etSmsContent.setText(smsContent);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    //点击按钮 跳转到短信模板界面
    private void insertSms(){
        Intent intent = new Intent(this, SmsTemplateActivity.class);
        //开启Activity有两种方式
        //1.如果想使用开启的Activity界面的数据，用startActivityForResult();
        //2.如果仅仅是简简单单的跳转，就用startActivity();
        startActivityForResult(intent, 2);
    }

    //点击发送按钮实现发送短信的逻辑
    private void sendSms(){
        String number = etNumber.getText().toString().trim();
        String content = etSmsContent.getText().toString().trim();

        //1.获取smsManager的实例
        SmsManager smsManager = SmsManager.getDefault();
        //1.1如果短信内容过多，发不出去时分条发送
        ArrayList<String> divideMessages = smsManager.divideMessage(content);
        for(String div : divideMessages){
            //2.发送短信数据
            smsManager.sendTextMessage(number, null, div, null, null);
        }
    }
}

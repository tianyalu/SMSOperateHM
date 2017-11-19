package com.sty.sms.operator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Shi Tianyi on 2017/11/19/0019.
 */

public class SmsTemplateActivity extends AppCompatActivity {
    private ListView lvSmsTemplate;

    private String[] objects = {"我在开会，请稍后联系", "我在开会，请稍后联系", "我在敲代码，请稍后联系",
            "我在开车，请稍后联系", "我在约会，请稍后联系"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_template);

        //1.找到lv控件
        ListView lvSmsTemplate = (ListView) findViewById(R.id.lv_sms_template);
        //2.创建数据适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_item, objects);
        //3.把数据展示到listView上
        lvSmsTemplate.setAdapter(adapter);
        //4.给listView设置点击事件
        lvSmsTemplate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //5.获取我们点中条目的数据
                String smsContent = objects[position];
                //6.把这个数据返回给调用者
                Intent intent = new Intent();
                intent.putExtra("smsContent", smsContent);
                //7.通过这个方法把数据返回给调用者
                setResult(20, intent);
                //8.调用finish()方法结束当前Activity
                finish();
            }
        });
    }
}

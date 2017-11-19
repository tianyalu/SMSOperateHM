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
 * Created by Shi Tianyi on 2017/11/16/0016.
 */

public class SmsDataActivity extends AppCompatActivity {

    private ListView listView;

    //0.封装到了数组中
    private static String objects[] = {"今天的风儿轻柔无比，今天的花儿香飘万里；今天的鸟儿十分欢喜，今天的云儿满载笑意；今天的事儿万分顺利，今天的人儿如此甜蜜。所有美...",
            "丫头，生活是你自己的，你哭它就对你哭，你笑它就对你笑。转眼，又是一年，你的生日即将来到。今年，还是少不了我对你的祝福，我忍不住...",
            "世界上最动听的声音，是妈妈声声的呼唤；世界上最温暖的笑容，是妈妈温暖的笑脸。妈妈，原谅生日时我不能陪在您身边，在这个日子里，我...",
            "今天是你的生日，祝你：发财势头如快马加鞭，一日千里；发展速度如滔滔江水，势不可挡；好事发生如雨后春笋，络绎不绝；祝福发送如比赛..."};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_data);

        initViews();
        setListeners();
    }

    private void initViews(){
        //1.获取控件
        listView = (ListView) findViewById(R.id.list_view);
        //2.listView展示数据需要的适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_item, objects);
        //3.设置数据适配器
        listView.setAdapter(adapter);
    }

    private void setListeners() {
        //4.设置条目的监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //5.获取点击条目的数据
                String content = objects[position];
                //6.跳转到发送短信页面 用隐式意图
                 /*<intent-filter>
	               <action android:name="android.intent.action.SEND" />
	               <category android:name="android.intent.category.DEFAULT" />
	               <data android:mimeType="text/plain" />
	               </intent-filter>*/
                 //7.创建意图对象
                Intent intent = new Intent();
                intent.setAction("android.intent.action.SEND");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setType("text/plain");
                //8.把数据传递到短信的发送页面
                intent.putExtra("sms_body", content);
                //9.开启意图
                startActivity(intent);
            }
        });
    }
}

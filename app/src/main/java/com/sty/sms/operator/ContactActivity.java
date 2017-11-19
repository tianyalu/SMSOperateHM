package com.sty.sms.operator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sty.sms.operator.bean.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shi Tianyi on 2017/11/19/0019.
 */

public class ContactActivity extends AppCompatActivity {
    private ListView lvContact;
    List<Contact> contactLists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        lvContact = (ListView) findViewById(R.id.lv_contact);

        contactLists = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            Contact contact = new Contact();
            contact.setName("ZhangSan" + i);
            contact.setPhone("1388900" + i);
            contactLists.add(contact);
        }

        //展示数据，设置数据适配器
        lvContact.setAdapter(new MyAdapter());
        //给listView的条目设置点击事件
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //取出我们点中条目的数据
                String phone = contactLists.get(position).getPhone();
                //把phone返回个前一个页面
                Intent intent = new Intent();
                intent.putExtra("phone", phone);
                //把数据返回给调用者
                setResult(10, intent);
                //关闭当前Activity 调用这Activity的onActivityResult方法就会被执行
                finish();
            }
        });
    }

    //创建一个数据适配器
    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return contactLists.size();
        }

        @Override
        public Object getItem(int position) {
            return contactLists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if(convertView == null){
                view = View.inflate(getApplicationContext(), R.layout.contact_item, null);
            }else{ //复用历史缓存对象
                view = convertView;
            }

            //找到关心的控件
            TextView tvName = view.findViewById(R.id.tv_name);
            TextView tvPhone = view.findViewById(R.id.tv_phone);
            //设置数据
            tvName.setText(contactLists.get(position).getName());
            tvPhone.setText(contactLists.get(position).getPhone());

            return view;
        }
    }
}


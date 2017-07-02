package com.example.user.phonenumberlist;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {


    ListView listView;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listId);

        JsonParser parser = new JsonParser(this, pd, listView);
        parser.execute("http://api.androidhive.info/contacts/");



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);

                intent.putExtra("nameKey", JsonParser.usersNameArr.get(position));
                intent.putExtra("numberKey", JsonParser.phoneNumArr.get(position));
                intent.putExtra("mailKey", JsonParser.emailArr.get(position));
                intent.putExtra("genderKey", JsonParser.genderArr.get(position));
                intent.putExtra("addressKey", JsonParser.addressArr.get(position));
                intent.putExtra("imageKey", JsonParser.imgArray[position]);

                startActivity(intent);
            }
        });


    }

    //Log out Click
    public void logOutClick(View view) {
        SharedPreferences preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();

        LoginActivity.passwordEt.setText("");
        LoginActivity.emailEt.setText("");

        finish();

    }
}

package com.example.user.phonenumberlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class UserInfoActivity extends AppCompatActivity {

    TextView nameSurname, phoneNumber, gender, email, address;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        init();

        String name = getIntent().getStringExtra("nameKey");
        nameSurname.setText(name);

        String number = getIntent().getStringExtra("numberKey");
        phoneNumber.append(number);

        String emailStr = getIntent().getStringExtra("mailKey");
        email.append(emailStr);

        String genderStr = getIntent().getStringExtra("genderKey");
        gender.append(genderStr);

        String addressStr = getIntent().getStringExtra("addressKey");
        address.append(addressStr);

        int image = getIntent().getIntExtra("imageKey", 0);
        imageView.setImageResource(image);



    }

    public void init(){
        nameSurname = (TextView)findViewById(R.id.nameSurnameId);
        phoneNumber = (TextView)findViewById(R.id.phoneNumID);
        gender = (TextView)findViewById(R.id.genderId);
        email = (TextView)findViewById(R.id.emailID);
        address = (TextView)findViewById(R.id.addressID);
        imageView = (ImageView)findViewById(R.id.imageId);
    }
}

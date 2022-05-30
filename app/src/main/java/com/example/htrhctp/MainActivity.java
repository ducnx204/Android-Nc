package com.example.htrhctp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.htrhctp.lophoc.ActivitySubject;

public class MainActivity extends AppCompatActivity {
    ImageView imgstudent;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //anh xa

        imgstudent=findViewById(R.id.image1);


        imgstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, ActivitySubject.class);
                startActivity(intent);
            }
        });

    }
    private void DialogAuthor(){
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialoginformation);
        dialog.show();
    }
}

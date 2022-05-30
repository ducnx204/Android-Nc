package com.example.htrhctp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.htrhctp.database.database;
import com.example.htrhctp.lophoc.ActivitySubject;
import com.example.htrhctp.model.Subject;

public class ActivityAddSubject extends AppCompatActivity {
    Button buttonAddSubJect;
    EditText edtSubjectTitle,edtSubjectCredit,edtSubjectTime,edtSubjectplace;

    com.example.htrhctp.database.database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        buttonAddSubJect = findViewById(R.id.buttonAddSubject);
        edtSubjectCredit = findViewById(R.id.EditTextSubjectCredit);
        edtSubjectTitle = findViewById(R.id.EditTextSubjectTitle);
        edtSubjectTime = findViewById(R.id.EditTextSubjectTime);
        edtSubjectplace = findViewById(R.id.EditTextSubjectPlace);

        database = new database(this);

        edtSubjectCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSubjectCredit.setText("");
            }
        });
        edtSubjectTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSubjectTitle.setText("");
            }
        });
        edtSubjectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSubjectTime.setText("");
            }
        });
        edtSubjectplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSubjectplace.setText("");
            }
        });
        buttonAddSubJect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd();
            }
        });
    }

    private void DialogAdd() {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogadd);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYes);
        Button btnNo = dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjecttitle = edtSubjectTitle.getText().toString().trim();
                String credit =edtSubjectCredit.getText().toString().trim();
                String time = edtSubjectTime.getText().toString().trim();
                String place = edtSubjectplace.getText().toString().trim();

                //neu du lieu chua nhap day du
                if (subjecttitle.equals("")||credit.equals("")||time.equals("")||place.equals("")){
                    Toast.makeText(ActivityAddSubject.this,"bạn nhập chưa đủ dử liệu",Toast.LENGTH_SHORT).show();
                }
                else {

                    //gan gia tri cho doi tuong nhap vao

                    Subject subject =CreatSubject();

                    //add trong database
                    database.AddSubject(subject);
                    //them thanh cong chuyen giao dien
                    Intent intent= new Intent(ActivityAddSubject.this, ActivitySubject.class);
                    startActivity(intent);
                    dialog.show();
                    Toast.makeText(ActivityAddSubject.this,"Thêm thành công",Toast.LENGTH_SHORT).show();

                }


            }
        });
        //neu khong them nua
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
    private Subject CreatSubject(){

        String subjecttitle = edtSubjectTitle.getText().toString().trim();
        int credit = Integer.parseInt(edtSubjectCredit.getText().toString().trim());
        String time = edtSubjectTime.getText().toString().trim();
        String place = edtSubjectplace.getText().toString().trim();
        Subject subject = new Subject(subjecttitle,credit,time,place);
        return subject;
    }

}
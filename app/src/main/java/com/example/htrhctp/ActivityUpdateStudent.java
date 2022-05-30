package com.example.htrhctp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.htrhctp.database.database;
import com.example.htrhctp.lophoc.ActivityStudent;
import com.example.htrhctp.model.Student;

public class ActivityUpdateStudent extends AppCompatActivity {

    EditText edtTextUpdateName,edtTextUpdateCode,edtTextUpdateBirthday;
    RadioButton rdMale,rdFeMale;
    Button btnUpdateStudent;
    database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        edtTextUpdateBirthday = findViewById(R.id.EditTextStudentBirthdayUpdate);
        edtTextUpdateCode = findViewById(R.id.EditTextStudentCodeUpdate);
        edtTextUpdateName = findViewById(R.id.EditTextStudentNameUpdate);

        rdMale = findViewById(R.id.radiobuttonMaleUpdate);
        rdFeMale = findViewById(R.id.radiobuttonFeMaleUpdate);
        btnUpdateStudent = findViewById(R.id.buttonupdateStudent);
        edtTextUpdateName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTextUpdateName.setText("");
            }
        });
        edtTextUpdateCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTextUpdateCode.setText("");
            }
        });
        edtTextUpdateBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTextUpdateBirthday.setText("");
            }
        });
        Intent intent =getIntent();
        int id = intent.getIntExtra("id",0);
        String name = intent.getStringExtra("name");
        String sex = intent.getStringExtra("sex");
        String code = intent.getStringExtra("code");
        String birtday = intent.getStringExtra("birtday");
        int id_subject = intent.getIntExtra("id_subject",0);

        edtTextUpdateName.setText(name);
        edtTextUpdateCode.setText(code);
        edtTextUpdateBirthday.setText(birtday);

        if (sex.equals("Male")){
            rdMale.setChecked(true);
            rdFeMale.setChecked(false);
        }
        else {
            rdFeMale.setChecked(true);
            rdMale.setChecked(false);
        }
        database = new database(this);
        btnUpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUpdate(id,id_subject);
            }
        });

    }

    private void DialogUpdate(int id,int id_subject) {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogupdatestudent);

        dialog.setCanceledOnTouchOutside(false);

        Button btnyes = dialog.findViewById(R.id.buttonYesupdateStudent);
        Button btnno = dialog.findViewById(R.id.buttonNoupdateStudent);

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtTextUpdateName.getText().toString().trim();
                String code = edtTextUpdateCode.getText().toString().trim();
                String birthday = edtTextUpdateBirthday.getText().toString().trim();

                Student student =createstudent();

                if (name.equals("")||code.equals("")||birthday.equals("")){
                    Toast.makeText(ActivityUpdateStudent.this,"Bạn phải nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else {
                    database.updateStudent(student,id);
                    Intent intent= new Intent(ActivityUpdateStudent.this,ActivityStudent.class);
                    intent.putExtra("id_subject",id_subject);
                    startActivity(intent);
                    Toast.makeText(ActivityUpdateStudent.this,"Sửa thành công",Toast.LENGTH_SHORT).show();


                }
            }
        });
        btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    private Student createstudent(){
        String name = edtTextUpdateName.getText().toString().trim();
        String code = edtTextUpdateCode.getText().toString().trim();
        String birthday = edtTextUpdateBirthday.getText().toString().trim();
        String sex = "";
        if (rdMale.isChecked()){
            sex= "Nam";
        }else {
            sex = "Nữ";
        }
        Student student = new Student(name,sex,code,birthday);
        return student;
    }
}
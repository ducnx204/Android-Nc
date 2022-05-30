package com.example.htrhctp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.htrhctp.R;
import com.example.htrhctp.lophoc.ActivityStudent;
import com.example.htrhctp.model.Student;

import java.util.ArrayList;

public class  adapterstudent  extends BaseAdapter {

    private ActivityStudent context;
    private ArrayList<Student> ArraylistStudent;

    public adapterstudent(ActivityStudent context, ArrayList<Student> arraylistStudent) {
        this.context = context;
        ArraylistStudent = arraylistStudent;
    }

    @Override
    public int getCount() {
        return ArraylistStudent.size();
    }

    @Override
    public Object getItem(int i) {
        return ArraylistStudent.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.liststudent,null);

        TextView txtName = view.findViewById(R.id.tvstudentName);
        TextView txtcode = view.findViewById(R.id.tvcode);


        ImageButton imgdele = view.findViewById(R.id.studentdelete);
        ImageButton imginformation = view.findViewById(R.id.studentinformation);
        ImageButton imgupdate = view .findViewById( R.id.studentupdate);

        Student student = ArraylistStudent.get(i);

        txtName.setText(student.getStudent_name());
        txtcode.setText(student.getStudent_code());

        int id = student.getId_student();

        imgdele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.delete(id);
            }
        });

        imgupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            context.update(id);
            }
        });

        imginformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.information(id);
            }
        });
        return view;
    }
}

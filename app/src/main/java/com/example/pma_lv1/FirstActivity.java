package com.example.pma_lv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class FirstActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        mRecyclerView = (RecyclerView) findViewById(R.id.mainViewHolder);

        Storage StorageSingle = Storage.getInstance();
        List<Object> students = StorageSingle.getStudents();
        Student s = new Student("","","Trenutno nema studenata");
        if (students.size()==0){
            students.add("Studenti");
            students.add(s);
        }else{
            Student a = (Student) students.get(1);
            if (a.getSubject()=="Trenutno nema studenata") students
                    .remove(1);
        }

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new StudentsRecyclerAdapter(students);
        mRecyclerView.setAdapter(mAdapter);
    }
    public void OpenAddNewStudent(View view){
        Intent AddNewStudent = new Intent(FirstActivity.this, AddStudentActivity.class);
        startActivity(AddNewStudent);
    }
}
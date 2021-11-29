package com.example.pma_lv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pma_lv1.adapters.StudentsRecyclerAdapter;
import com.example.pma_lv1.models.Storage;
import com.example.pma_lv1.viewModels.StudentVM;

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
        StudentVM s = new StudentVM("","","Trenutno nema studenata");
        if (students.size()==0){
            students.add("Studenti");
            students.add(s);
        }else{
            StudentVM a = (StudentVM) students.get(1);
            if (a.getSubject()=="Trenutno nema studenata")
                students.remove(1);
        }

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new StudentsRecyclerAdapter(students);
        mRecyclerView.setAdapter(mAdapter);
    }
    public void OpenCreateNewRecord(View view){
        Intent CreateNewRecord = new Intent(FirstActivity.this, CreateNewRecordActivity.class);
        startActivity(CreateNewRecord);
    }
}
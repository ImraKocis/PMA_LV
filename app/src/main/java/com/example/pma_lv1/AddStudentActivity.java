package com.example.pma_lv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pma_lv1.models.Storage;
import com.example.pma_lv1.models.Student;
import com.example.pma_lv1.viewModels.StudentVM;
import com.google.android.material.textfield.TextInputEditText;

public class AddStudentActivity extends AppCompatActivity {

    private TextInputEditText etName;
    private TextInputEditText etSurname;
    private TextInputEditText etSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
    }

    public void AddNewStudent(View view){
        Storage StorageSingleton = Storage.getInstance();
        etName = (TextInputEditText) findViewById(R.id.etName);
        etSurname = (TextInputEditText) findViewById(R.id.etSurname);
        etSubject = (TextInputEditText) findViewById(R.id.etSubject);
        StudentVM student = new StudentVM(
                "Ime: " + (etName.getText() != null ? etName.getText().toString() : "N/A"),
                "Prezime: " + (etSurname.getText() != null ? etSurname.getText().toString() : "N/A"),
                "Predmet: " + (etSubject.getText() != null ? etSubject.getText().toString() : "N/A")
        );
        StorageSingleton.addStudent(student);
        Intent HomeAct = new Intent(AddStudentActivity.this, FirstActivity.class);
        HomeAct.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(HomeAct);
        finish();
    }
}
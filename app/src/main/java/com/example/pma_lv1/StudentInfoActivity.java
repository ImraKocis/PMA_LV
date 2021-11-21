package com.example.pma_lv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class StudentInfoActivity extends AppCompatActivity {

    private Button btnUnosPredmet;
    private TextInputEditText etPredmet;
    private TextInputEditText etProfesor;
    private TextInputEditText etAkGodina;
    private TextInputEditText etPredavanja;
    private TextInputEditText etLabosi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        btnUnosPredmet = (Button) findViewById(R.id.btnUnosPredmet);
        etPredmet = (TextInputEditText) findViewById(R.id.etUnosPredmet);
        etProfesor = (TextInputEditText) findViewById(R.id.unosIme);
        etAkGodina = (TextInputEditText) findViewById(R.id.unosAkademskaGodina);
        etPredavanja = (TextInputEditText) findViewById(R.id.unosSatiPredavanja);
        etLabosi = (TextInputEditText) findViewById(R.id.unosSatiLv);


        btnUnosPredmet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StudentInfoActivity.this, SummaryActivity.class);
                i.putExtra("predmet", etPredmet.getText().toString());
                i.putExtra("profesor", etProfesor.getText().toString());
                i.putExtra("akGodina", etAkGodina.getText().toString());
                i.putExtra("satiPredavanja", etPredavanja.getText().toString());
                i.putExtra("satiLabosa", etLabosi.getText().toString());
                Bundle extras = getIntent().getExtras();
                if (extras != null){
                    i.putExtra("ime", extras.getString("ime"));
                    i.putExtra("prezime", extras.getString("prezime"));
                    i.putExtra("datumRodenja", extras.getString("datumRodenja"));
                }
                startActivity(i);
            }
        });
    }
}
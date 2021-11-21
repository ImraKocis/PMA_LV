package com.example.pma_lv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;


public class MainActivity extends AppCompatActivity {

    private Button btnUnos;
    private TextInputEditText etUnosIme;
    private TextInputEditText etUnosPrezime;
    private TextInputEditText etUnosDatumRodenja;
    private ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUnos = (Button) findViewById(R.id.button);
        etUnosIme = (TextInputEditText) findViewById(R.id.unosIme);
        etUnosPrezime =(TextInputEditText) findViewById(R.id.unosPrezime);
        etUnosDatumRodenja =(TextInputEditText) findViewById(R.id.unosDatumRodenja);

        btnUnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, StudentInfoActivity.class);
                i.putExtra("ime" , etUnosIme.getText().toString());
                i.putExtra("prezime" , etUnosPrezime.getText().toString());
                i.putExtra("datumRodenja" , etUnosDatumRodenja.getText().toString());

                startActivity(i);
            }
        });
    }
}
package com.example.pma_lv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    private Button btnPovratak;
    private TextView tvIme;
    private TextView tvPredmet;
    private TextView tvPrezime;
    private TextView tvDatumRodenja;
    private TextView tvProfesor;
    private TextView tvAkGodina;
    private TextView tvBrojPredavanja;
    private TextView tvBrojLabosa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        btnPovratak = (Button) findViewById(R.id.btnPovratak);
        tvIme = (TextView) findViewById(R.id.tvIme);
        tvPredmet = (TextView) findViewById(R.id.tvPredmet);
        tvPrezime = (TextView) findViewById(R.id.tvPrezime);
        tvDatumRodenja = (TextView) findViewById(R.id.tvDatumRodenja);
        tvProfesor =(TextView)findViewById(R.id.tvProfesor);
        tvAkGodina = (TextView)findViewById(R.id.tvAkGodina);
        tvBrojPredavanja = (TextView)findViewById(R.id.tvSatiPredavanja);
        tvBrojLabosa = (TextView)findViewById(R.id.tvSatiLabosa);
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            tvIme.setText(extras.getString("ime"));
            tvPredmet.setText(extras.getString("predmet"));
            tvPrezime.setText(extras.getString("prezime"));
            tvDatumRodenja.setText(extras.getString("datumRodenja"));
            tvProfesor.setText(extras.getString("profesor"));
            tvAkGodina.setText(extras.getString("akGodina"));
            tvBrojPredavanja.setText(extras.getString("satiPredavanja"));
            tvBrojLabosa.setText(extras.getString("satiLabosa"));
        }
        btnPovratak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SummaryActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
            }
        });
    }
}
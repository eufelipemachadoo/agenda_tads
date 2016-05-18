package com.ftads.compromissos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class repetir extends AppCompatActivity {

    Spinner repeticao;
    String tipoRepeticao;
    String tempRepeticao;

    Spinner tempoRepeticao;
    String spnTempoRepeticao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repetir);

        repeticao = (Spinner)findViewById(R.id.spinnerRepeticao);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.repeticao, android.R.layout.simple_spinner_item);
        repeticao.setAdapter(adapter);
        tipoRepeticao = adapter.toString();


        tempoRepeticao = (Spinner)findViewById(R.id.spnTempoRepeticao);
        ArrayAdapter adapterTempoRepet = ArrayAdapter.createFromResource(this, R.array.tempoRepeticao, android.R.layout.simple_spinner_item);
        tempoRepeticao.setAdapter(adapterTempoRepet);
        tempRepeticao = adapterTempoRepet.toString();

    }
}

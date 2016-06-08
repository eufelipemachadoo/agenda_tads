package com.ftads.compromissos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class corrige_cadEvent extends AppCompatActivity {


        EditText cadData;
        EditText cadHora;
        EditText cadTermino;
        EditText cadLocal;
        EditText cadDescricao;
        EditText cadParticipantes;

        EditText bundleRepeticao;
        EditText bundleRepetCada;
        EditText bundleInicio;
        EditText bundleOpRepet;
        EditText bundleTRepet;
        EditText bundleOcorrencia;
        EditText bundleTerminaEm;


        Spinner spinnerTipoEvent;
        String tipoEvent;

        RadioButton rbRepetSim;


        String bundle_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corrige_cad_event);

        spinnerTipoEvent = (Spinner)findViewById(R.id.spinner_TipoEvento_corrigeCadEvent);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.tipoEvento, android.R.layout.simple_spinner_item);
        spinnerTipoEvent.setAdapter(adapter);
        tipoEvent = adapter.toString();

        cadData = (EditText)findViewById(R.id.etData_corrigeCadEvent);
        cadHora = (EditText)findViewById(R.id.etHora_corrigeCadEvent);
        cadTermino = (EditText)findViewById(R.id.etTermino_corrigeCadEvent);
        cadLocal = (EditText)findViewById(R.id.etLocal_corrigeCadEvent);
        cadDescricao = (EditText)findViewById(R.id.etDescricao_corrigeCadEvent);
        cadParticipantes = (EditText)findViewById(R.id.etParticipante_corrigeCadEvent);
        rbRepetSim = (RadioButton)findViewById(R.id.rbSim_corrigeCadEvent);


        Bundle valores = getIntent().getExtras();
        if ((valores != null) && (valores.containsKey("data"))) {
            String data = valores.getString("data");
            String hora = valores.getString("hora");
            String termino = valores.getString("termina");
            String local = valores.getString("local");
            String descricao = valores.getString("descricao");
            String participantes = valores.getString("participantes");
            String tipoEvento = valores.getString("tipoEvento");
            String repeticao = valores.getString("repeticao");
            String repetAcada = valores.getString("repetCada");
            String inicio = valores.getString("inicio");






            cadData.setText(data);
            cadHora.setText(hora);
            cadTermino.setText(termino);
            cadLocal.setText(local);
            cadDescricao.setText(descricao);
            cadParticipantes.setText(participantes);
            spinnerTipoEvent.setSelection(Integer.parseInt(tipoEvento));
        }


        rbRepetSim.setOnClickListener(new RadioButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent corrigeRepetir = new Intent(getBaseContext(), corrigeRepetir.class);

                startActivity(corrigeRepetir);
            }
        });





    }
}

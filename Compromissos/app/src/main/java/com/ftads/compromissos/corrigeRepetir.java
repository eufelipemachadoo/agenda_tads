package com.ftads.compromissos;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;


import com.ftads.compromissos.dataBase.DataBase;
import com.ftads.compromissos.dominio.entidades.Evento;
import com.ftads.compromissos.dominio.repositorioEventos;


public class corrigeRepetir extends AppCompatActivity {



    private DataBase dataBase;
    private SQLiteDatabase conn;
    private repositorioEventos RepositorioEventos;
    private Evento evento;


    Spinner Repeticao;
    String strRepeticao;

    Spinner spinnerRepetCada;
    String spnRepetCada;


    EditText inicio;
    RadioButton terminaSempre;
    RadioButton terminapos;
    RadioButton terminaEm;
    EditText ocorrencias;
    EditText strTerminaEm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corrige_repetir);



        Repeticao = (Spinner)findViewById(R.id.spinnerRepeticao_corrigeRepetir);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.repeticao, android.R.layout.simple_spinner_item);
        Repeticao.setAdapter(adapter);
        strRepeticao = adapter.toString();


        spinnerRepetCada = (Spinner)findViewById(R.id.spnTempoRepeticao);
        ArrayAdapter adapterRepetCada = ArrayAdapter.createFromResource(this, R.array.tempoRepeticao, android.R.layout.simple_spinner_item);
        spinnerRepetCada.setAdapter(adapterRepetCada);
        spnRepetCada = adapterRepetCada.toString();


        inicio = (EditText)findViewById(R.id.etInicioEm_corrigeRepetir);
        terminaSempre = (RadioButton)findViewById(R.id.rbTerminaSempre_corrigeRepetir);
        terminapos = (RadioButton)findViewById(R.id.rbTerminaApos_corrigeRepetir);
        terminaEm = (RadioButton)findViewById(R.id.rbTerminaEm_corrigeRepetir);
        ocorrencias = (EditText)findViewById(R.id.etOcorrencia_corrigeRepetir);
        strTerminaEm = (EditText)findViewById(R.id.etTerminaEm_corrigeRepetir);


        Bundle bundle = getIntent().getExtras();
        if ((bundle != null) && (bundle.containsKey("repeticao")))
        {
            String bRepeticao = bundle.getString("repeticao");
            Repeticao.setSelection(Integer.parseInt(bRepeticao));

            String bRepetCada = bundle.getString("repetCada");
            spinnerRepetCada.setSelection(Integer.parseInt(bRepetCada));

            String bInicio = bundle.getString("inicio");
            inicio.setText(bInicio);


            String bTermino = bundle.getString("termino");
            String bOcorrencias = bundle.getString("ocorrencias");
            String bEm = bundle.getString("terminaEm");
            if (bTermino.equals("Sempre"))
            {
                terminaSempre.setChecked(true);
            }
            else if (bTermino.equals("Após"))
            {
                terminapos.setChecked(true);
                ocorrencias.setText(bOcorrencias);
            }
            else if (bTermino.equals("Em"))
            {
                terminaEm.setChecked(true);
                strTerminaEm.setText(bEm);

            }




        }
    }
}

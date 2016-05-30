package com.ftads.compromissos;

import android.app.AlertDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.ftads.compromissos.dataBase.DataBase;
import com.ftads.compromissos.dominio.entidades.Evento;
import com.ftads.compromissos.dominio.repositorioEventos;
import java.util.Date;


public class cadEventRepet extends AppCompatActivity {

    private Evento evento;

    EditText cadData;
    EditText cadHora;
    EditText cadTermino;
    EditText cadLocal;
    EditText cadDescricao;
    EditText cadParticipantes;
    EditText cadInicio;

    TextView tvTestRepet;
    String spnTipoEvent;
    Spinner spinnerTipoEvent;

    String spnRepeticao;
    Spinner spinnerRepeticao;

    String spnRepetCada;
    Spinner spinnerRepetCada;


    RadioButton repetSim;
    RadioButton repetNao;


    EditText cadTermina;



    Button btAdc_cadEventRepet;
    String terminaSempre;
    String strRepetir;

    private DataBase dataBase;
    private SQLiteDatabase conn;
    private repositorioEventos RepositorioEventos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_event_repet);


        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            RepositorioEventos = new repositorioEventos(conn);



        }catch (SQLException ex)
        {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar banco" + ex.getMessage());
            dlg.setNeutralButton("Ok", null);
            dlg.show();
        }

        cadData = (EditText)findViewById(R.id.etData_cadEventRepet);
        cadHora = (EditText)findViewById(R.id.etHora_cadEventRepet);
        cadTermino = (EditText)findViewById(R.id.etTermino_cadEventRepet);
        cadLocal = (EditText)findViewById(R.id.etLocal_cadEventRepet);
        cadDescricao = (EditText)findViewById(R.id.etDescricao_cadEventRepet);
        cadParticipantes = (EditText)findViewById(R.id.etParticipante_cadEventRepet);
        cadInicio = (EditText)findViewById(R.id.et_cadInicio_cadEventRepet);
        cadTermina = (EditText)findViewById(R.id.et_termina_cadEventRepet);


        btAdc_cadEventRepet = (Button)findViewById(R.id.btAdc_cadEventRepet);




        spinnerTipoEvent = (Spinner)findViewById(R.id.spinner_TipoEvento_cadEventRepet);
        spinnerRepeticao = (Spinner)findViewById(R.id.spinner_Repeticao_cadEventRepet);
        spinnerRepetCada = (Spinner)findViewById(R.id.spinner_RepetCada_cadEventRepet);

        tvTestRepet = (TextView)findViewById(R.id.tvTestRepet);


        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.tipoEvento, android.R.layout.simple_spinner_item);
        spinnerTipoEvent.setAdapter(adapter);
        spnTipoEvent = adapter.toString();

        ArrayAdapter adapterRepeticao = ArrayAdapter.createFromResource(this, R.array.repeticao, android.R.layout.simple_spinner_item);
        spinnerRepeticao.setAdapter(adapterRepeticao);
        spnRepeticao = adapterRepeticao.toString();


        ArrayAdapter adapterRepetCada = ArrayAdapter.createFromResource(this, R.array.tempoRepeticao, android.R.layout.simple_spinner_item);
        spinnerRepetCada.setAdapter(adapterRepetCada);
        spnRepetCada = adapterRepetCada.toString();


        repetSim = (RadioButton)findViewById(R.id.rbSim_cadEventRepet);
        repetNao = (RadioButton)findViewById(R.id.rbNao_cadEventRepet);

        Bundle valores = getIntent().getExtras();
        if ((valores != null) && (valores.containsKey("devolveData")))
        {
            String data = valores.getString("devolveData");
            String hora = valores.getString("devolveHora");
            String termino = valores.getString("devolveTermino");
            String local = valores.getString("devolveLocal");
            String descricao = valores.getString("devolveDescricao");
            String participantes = valores.getString("devolveParticipantes");
            String tipoEvento = valores.getString("devolveTipoEvento");
            String repeticao = valores.getString("tRepeticao");
            String repetAcada = valores.getString("repetCada");
            String inicio = valores.getString("inicio");
            String opRepet = valores.getString("opRepet");
            String tRepet = valores.getString("tRepet");
            String ocorrencias = valores.getString("ocorrencias");
            String terminaEm = valores.getString("terminaEm");

            cadData.setText(data);
            cadHora.setText(hora);
            cadTermino.setText(termino);
            cadLocal.setText(local);
            cadDescricao.setText(descricao);
            cadParticipantes.setText(participantes);
            spinnerTipoEvent.setSelection(Integer.parseInt(tipoEvento));
            spinnerRepeticao.setSelection(Integer.parseInt(repeticao));
            spinnerRepetCada.setSelection(Integer.parseInt(repetAcada));
            cadInicio.setText(inicio);


             if (tRepet.equals("Sempre"))
            {
                cadTermina.setText(" " + tRepet);
                terminaSempre = tRepet;
            }

            else if (tRepet.equals("Após"))
            {
                cadTermina.setText(" " + tRepet + " " + ocorrencias + " ocorrências");
            }
            else if (tRepet.equals("Em"))
            {
                cadTermina.setText(" " + tRepet + " " + terminaEm);
            }


             if (opRepet.equals("Sim"))
             {
                 repetSim.setChecked(true);
                 strRepetir = String.valueOf(repetSim.getText().toString());
             }
            else
             {
                strRepetir = String.valueOf(repetNao.getText().toString());
             }

         tvTestRepet.setOnClickListener(new TextView.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (repetSim.isChecked())
                 {
                     tvTestRepet.setText("Sim");
                 }
                 else
                 {
                     tvTestRepet.setText("Não");
                 }
             }
         });






        }
        else
            evento = new Evento();






        btAdc_cadEventRepet.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                evento = new Evento();
                evento.setData(cadData.getText().toString());
                evento.setHora(cadHora.getText().toString());
                evento.setTermino(cadTermino.getText().toString());
                evento.setLocal(cadLocal.getText().toString());
                evento.setDescricao(cadDescricao.getText().toString());
                evento.setPariticipantes(cadParticipantes.getText().toString());
                evento.setTipoEvento(String.valueOf(spinnerTipoEvent.getSelectedItemPosition()));
                evento.setTipoRepeticao(String.valueOf(spinnerRepeticao.getSelectedItemPosition()));




                if (evento.getId() == 0)
                {RepositorioEventos.inserirEventos(evento);}
                else
                {RepositorioEventos.alterarEventos(evento);}



            }
        });
    }



}

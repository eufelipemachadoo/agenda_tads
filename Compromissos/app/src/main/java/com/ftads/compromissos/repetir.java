package com.ftads.compromissos;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

import com.ftads.compromissos.dataBase.DataBase;
import com.ftads.compromissos.dominio.entidades.Evento;
import com.ftads.compromissos.cadEvent;
import com.ftads.compromissos.dominio.repositorioEventos;


import javax.xml.validation.Validator;

public class repetir extends AppCompatActivity {

    private repositorioEventos RepositorioEventos;
    private DataBase dataBase;
    private SQLiteDatabase conn;

    Spinner Repeticao;
    String tempRepeticao;
    int tempRepet;

    String data;
    String dataTermino;


    TextView resumo;
    TextView resumoTitulo;
    EditText ocorrencias;
    String numOcorrencias;

    EditText terminaEm;



    String repetCada;
    String strTerminaEm;

    Spinner tempoRepeticao;

    TextView repetAcada;
    TextView inicioEm;
    String inicio;

    RadioGroup termina;
    RadioButton rbSempre, rbApos, rbEm,setRepetir;
    String tRepet;
    String rRepeticao;
    String tRepeticao;

    Button btConcluido;
    Button btCancelar;

    String devolveData;
    String devolveHora;
    String devolveTermino;
    String devolveLocal;
    String devolveDescricao;
    String devolveParticipantes;
    String devolveTipoEvent;
    String devolveOpRepetir;

    TextView etDevolveData;



    private Evento evento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repetir);

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



        etDevolveData = (TextView)findViewById(R.id.tvTest);

        btConcluido = (Button)findViewById(R.id.btConcluido_repetir);
        setRepetir = (RadioButton)findViewById(R.id.rbNao_cadEvent);

        btCancelar = (Button)findViewById(R.id.btCancelar_repetir);
        btCancelar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRepetir.setChecked(true);
                finish();


            }
        });

        Repeticao = (Spinner)findViewById(R.id.spinnerRepeticao);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.repeticao, android.R.layout.simple_spinner_item);
        Repeticao.setAdapter(adapter);


        rRepeticao = Repeticao.getSelectedItem().toString();

        













        repetAcada = (TextView)findViewById(R.id.tvRepetCada_repetir);


        inicioEm = (TextView)findViewById(R.id.etInicioEm_repetir);
        resumo = (TextView)findViewById(R.id.tvResumo_repetir);
        resumoTitulo = (TextView)findViewById(R.id.tvResumo);
        ocorrencias = (EditText)findViewById(R.id.etOcorrencia_repetir);


        terminaEm = (EditText)findViewById(R.id.etTerminaEm_repetir);

        final Calendar calendar1 = Calendar.getInstance();
        final int diaData = calendar1.get(calendar1.DAY_OF_MONTH);
        final int mesData = calendar1.get(calendar1.MONTH);
        final int anoData = calendar1.get(calendar1.YEAR);

        terminaEm.setOnClickListener(new EditText.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(repetir.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear +1;
                        terminaEm.setText(dayOfMonth + "/" + monthOfYear + "/" + year);

                    strTerminaEm = String.valueOf(terminaEm.getText().toString());
                    }
                }, diaData, mesData, anoData);
                datePickerDialog.setTitle("Selecione a data");
                datePickerDialog.show();

            }
        });


        tempoRepeticao = (Spinner)findViewById(R.id.spnTempoRepeticao);
        ArrayAdapter adapterTempoRepet = ArrayAdapter.createFromResource(this, R.array.tempoRepeticao, android.R.layout.simple_spinner_item);
        tempoRepeticao.setAdapter(adapterTempoRepet);
        tempRepeticao = adapterTempoRepet.toString();

        tRepeticao = String.valueOf(tempoRepeticao.getSelectedItemPosition());





        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Brazil/East"));
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        mes = mes+1;
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        inicioEm.setText(dia + "/" + mes + "/" + ano);
        data = (String.valueOf(dia)+"/"+String.valueOf(mes)+"/"+String.valueOf(ano));
        inicioEm.setEnabled(false);

        termina = (RadioGroup)findViewById(R.id.rgTermina);
        rbSempre = (RadioButton)findViewById(R.id.rbRepeteSempre_repetir);
        rbSempre.setChecked(true);

        rbApos = (RadioButton)findViewById(R.id.rbRepeteApos_repetir);
        rbEm = (RadioButton)findViewById(R.id.rbRepeteEm_repetir);

        ocorrencias.setEnabled(false);
        terminaEm.setEnabled(false);

        rbSempre.setOnClickListener(new RadioButton.OnClickListener() {
            @Override
            public void onClick(View v) {

                ocorrencias.setText("");
                ocorrencias.setEnabled(false);

                terminaEm.setText("");
                terminaEm.setEnabled(false);
            }
        });

        rbApos.setOnClickListener(new RadioButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                ocorrencias.setText("");
                ocorrencias.setEnabled(true);

                terminaEm.setText("");
                terminaEm.setEnabled(false);
            }
        });

        rbEm.setOnClickListener(new RadioButton.OnClickListener() {
            @Override
            public void onClick(View v) {

                ocorrencias.setText("");
                ocorrencias.setEnabled(false);

                terminaEm.setText("");
                terminaEm.setEnabled(true);
            }
        });




        Bundle valores = getIntent().getExtras();
        if ((valores != null) && (valores.containsKey("data")))
        {
            String fData = valores.getString("data");
            String fHora = valores.getString("hora");
            String fTermino = valores.getString("termino");
            String fLocal = valores.getString("local");
            String fDescricao = valores.getString("descricao");
            String fParticipantes = valores.getString("participantes");
            String fTipoEvento = valores.getString("tipoEvento");
            String fOpRepet = valores.getString("opRepet");




            devolveData = fData;
            devolveHora = fHora;
            devolveTermino = fTermino;
            devolveLocal = fLocal;
            devolveDescricao = fDescricao;
            devolveParticipantes = fParticipantes;
            devolveTipoEvent = String.valueOf(fTipoEvento);
            devolveOpRepetir = fOpRepet;


        }

    }



    /*public void result(View view)
    {
    }*/

    public void validar(View view)
    {



        switch (termina.getCheckedRadioButtonId())
        {
            case R.id.rbRepeteSempre_repetir:
                //Toast.makeText(repetir.this, "Sempre repetir",Toast.LENGTH_SHORT).show();
                tRepet = String.valueOf(rbSempre.getText().toString()) ;
                break;

            case R.id.rbRepeteApos_repetir:
                //Toast.makeText(repetir.this, "Repedir após",Toast.LENGTH_SHORT).show();
                tRepet = String.valueOf(rbApos.getText().toString());
                break;

            case R.id.rbRepeteEm_repetir:
                //Toast.makeText(repetir.this, "Repetir em",Toast.LENGTH_SHORT).show();
                tRepet = String.valueOf(rbEm.getText().toString());
                break;
        }


        repetCada = String.valueOf(String.valueOf(tempoRepeticao.getSelectedItem()));
        inicio = String.valueOf(inicioEm.getText().toString());
        numOcorrencias = String.valueOf(ocorrencias.getText().toString());


        if ((Repeticao.getSelectedItemPosition() != 1 ) && (Repeticao.getSelectedItemPosition() != 2) && (Repeticao.getSelectedItemPosition() != 3))
        {
            Toast.makeText(repetir.this, "Selecione um tipo de repetição", Toast.LENGTH_SHORT).show();
        }
        else if (tempoRepeticao.getSelectedItemPosition() < 1)
        {
            Toast.makeText(repetir.this, "Repete a cada ???", Toast.LENGTH_SHORT).show();
        }


       else if (rbApos.isChecked() && (TextUtils.isEmpty(ocorrencias.getText())))
       {
           Toast.makeText(repetir.this, "Após quantas ocorrências ???", Toast.LENGTH_SHORT).show();
       }

        else if ((rbEm.isChecked()) && (TextUtils.isEmpty(terminaEm.getText())))
        {
            Toast.makeText(repetir.this, "Data para o termino", Toast.LENGTH_SHORT).show();
        }








        else {



            resumo.setText(devolveData + " " + devolveHora + " " + devolveTermino + " " + devolveLocal + " " + " " + devolveDescricao
                    + " " + devolveParticipantes + " " + devolveTipoEvent + " a " + Repeticao.getSelectedItem().toString() + " a " +
                    tempoRepeticao.getSelectedItem().toString() + " " + tRepet) ;

            try {
                /*evento = new Evento();
                evento.setData(devolveData);
                evento.setHora(devolveHora);
                evento.setTermino(devolveTermino);
                evento.setLocal(devolveLocal);
                evento.setDescricao(devolveDescricao);
                evento.setPariticipantes(devolveParticipantes);
                evento.setTipoEvento(String.valueOf(devolveTipoEvent));






                if (evento.getId() == 0)
                {RepositorioEventos.inserirEventos(evento);}
                else
                {RepositorioEventos.alterarEventos(evento);}*/

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                // Seta o Titulo do Dialog
                alertDialogBuilder.setTitle("Atenção");

                // seta a mensagem
                alertDialogBuilder.setMessage("Todos os dados estão corretos?").setCancelable(false).setPositiveButton("Sim",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // SIM
                                Intent iCadEventRepet = new Intent(getBaseContext(), com.ftads.compromissos.cadEventRepet.class);

                                String bundleRepeticao;
                                bundleRepeticao = (String.valueOf(Repeticao.getSelectedItemPosition()));

                                String bundleRepetCada;
                                bundleRepetCada = (String.valueOf(tempoRepeticao.getSelectedItemPosition()));

                                if (rbSempre.isChecked())
                                {
                                    iCadEventRepet.putExtra("devolveData", devolveData.toString() );
                                    iCadEventRepet.putExtra("devolveHora", devolveHora.toString() );
                                    iCadEventRepet.putExtra("devolveTermino", devolveTermino.toString() );
                                    iCadEventRepet.putExtra("devolveLocal", devolveLocal.toString() );
                                    iCadEventRepet.putExtra("devolveDescricao", devolveDescricao.toString() );
                                    iCadEventRepet.putExtra("devolveParticipantes", devolveParticipantes.toString() );
                                    iCadEventRepet.putExtra("devolveTipoEvento", devolveTipoEvent);
                                    iCadEventRepet.putExtra("tRepeticao", bundleRepeticao.toString() );
                                    iCadEventRepet.putExtra("repetCada", bundleRepetCada.toString() );
                                    iCadEventRepet.putExtra("inicio", inicio.toString() );
                                    iCadEventRepet.putExtra("tRepet", tRepet.toString() );
                                    iCadEventRepet.putExtra("opRepet", devolveOpRepetir.toString());
                                }

                                else if (rbApos.isChecked())
                                {
                                    iCadEventRepet.putExtra("devolveData", devolveData.toString() );
                                    iCadEventRepet.putExtra("devolveHora", devolveHora.toString() );
                                    iCadEventRepet.putExtra("devolveTermino", devolveTermino.toString() );
                                    iCadEventRepet.putExtra("devolveLocal", devolveLocal.toString() );
                                    iCadEventRepet.putExtra("devolveDescricao", devolveDescricao.toString() );
                                    iCadEventRepet.putExtra("devolveParticipantes", devolveParticipantes.toString() );
                                    iCadEventRepet.putExtra("devolveTipoEvento", devolveTipoEvent);
                                    iCadEventRepet.putExtra("tRepeticao", bundleRepeticao.toString() );
                                    iCadEventRepet.putExtra("repetCada", bundleRepetCada.toString() );
                                    iCadEventRepet.putExtra("inicio", inicio.toString() );
                                    iCadEventRepet.putExtra("tRepet", tRepet.toString() );
                                    iCadEventRepet.putExtra("opRepet", devolveOpRepetir.toString());
                                    iCadEventRepet.putExtra("ocorrencias", ocorrencias.getText().toString());
                                }

                                else if (rbEm.isChecked())
                                {
                                    iCadEventRepet.putExtra("devolveData", devolveData.toString() );
                                    iCadEventRepet.putExtra("devolveHora", devolveHora.toString() );
                                    iCadEventRepet.putExtra("devolveTermino", devolveTermino.toString() );
                                    iCadEventRepet.putExtra("devolveLocal", devolveLocal.toString() );
                                    iCadEventRepet.putExtra("devolveDescricao", devolveDescricao.toString() );
                                    iCadEventRepet.putExtra("devolveParticipantes", devolveParticipantes.toString() );
                                    iCadEventRepet.putExtra("devolveTipoEvento", devolveTipoEvent);
                                    iCadEventRepet.putExtra("tRepeticao", bundleRepeticao.toString() );
                                    iCadEventRepet.putExtra("repetCada", bundleRepetCada.toString() );
                                    iCadEventRepet.putExtra("inicio", inicio.toString() );
                                    iCadEventRepet.putExtra("tRepet", tRepet.toString() );
                                    iCadEventRepet.putExtra("opRepet", devolveOpRepetir.toString());
                                    iCadEventRepet.putExtra("terminaEm", terminaEm.getText().toString());
                                    iCadEventRepet.putExtra("strTerminaEm", strTerminaEm.toString() );
                                }

                                startActivity(iCadEventRepet);

                                // Fecha o dialog
//                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("Não",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // NAO
                                        /*Intent iHome = new Intent(getBaseContext(), Home.class);
                                        startActivity(iHome);*/

                                        // Fecha o dialog
                                        //                                      dialog.cancel();
                                    }
                                });

                // Cria o alertDialog com o conteudo do alertDialogBuilder
                AlertDialog alertDialog = alertDialogBuilder.create();
                // Exibe o Dialog
                alertDialog.show();

            }catch (Exception ex)
            {
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setMessage("Erro ao inserir dados" + ex.getMessage());
                dlg.setNeutralButton("Ok", null);
                dlg.show();
            }





        }



    }














}

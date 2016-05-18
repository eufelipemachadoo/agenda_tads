package com.ftads.compromissos;



import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.database.sqlite.*;
import android.database.*;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ftads.compromissos.dataBase.DataBase;
import com.ftads.compromissos.dominio.entidades.Evento;
import com.ftads.compromissos.dominio.repositorioEventos;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class cadEvent extends AppCompatActivity {

    EditText etData;
    EditText etHora;
    EditText etTermino;
    EditText localEvent;
    EditText descricao;
    EditText participantes;
    Spinner spinnerTipoEvent;
    RadioButton repetirSim;
    RadioButton repetirNao;
    String tipoEvent;


    Button btVoltar;
    Button btAdc_cadEvent;
    Boolean repet;

    int horaTerminom1, minutoTermino1;

    static final int DIALOG_ID_DATA = 0;
    static final int DIALOG_ID_TERMINO = 0;





    private DataBase dataBase;
    private SQLiteDatabase conn;
    private repositorioEventos RepositorioEventos;
    private Evento evento;

    private TextWatcher dataMask;
    private TextWatcher horaMask;
    private TextWatcher terminoMask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_event);



        //Configura Botões

        btVoltar = (Button)findViewById(R.id.btVoltar_cadEvent);
        btAdc_cadEvent = (Button)findViewById(R.id.btAdc_cadEvent);

        //Fim congufigura Botões

        repetirSim = (RadioButton)findViewById(R.id.rbSim_cadEvent);
        repetirSim.setOnClickListener(new RadioButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iRepetir = new Intent(getBaseContext(), repetir.class);
                startActivity(iRepetir);
            }
        });

        repetirNao = (RadioButton)findViewById(R.id.rbNao_cadEvent);

        //Configura EditTexts
        etData = (EditText)findViewById(R.id.etData_cadEvent);
        etHora = (EditText)findViewById(R.id.etHora_cadEvent);
        etTermino = (EditText)findViewById(R.id.etTermino_cadEvent);
        localEvent = (EditText)findViewById(R.id.etLocal_cadEvent);
        descricao =(EditText)findViewById(R.id.etDescricao_cadEvent);
        participantes = (EditText)findViewById(R.id.etParticipante_cadEvent);
        //Fim Configura EditTexts


      /*  horaMask = Mask.insert("##:##", etHora);
        etHora.addTextChangedListener(horaMask);

        terminoMask = Mask.insert("##:##", etTermino);
        etTermino.addTextChangedListener(terminoMask);
      */



        //Configura SpinnerTipo Evento
        spinnerTipoEvent = (Spinner)findViewById(R.id.spinner_TipoEvento);
            ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.tipoEvento, android.R.layout.simple_spinner_item);
        spinnerTipoEvent.setAdapter(adapter);
        tipoEvent = adapter.toString();
        //Fim configura SpinnerTIpo Evento



        //recupera dados da act Home
        Bundle bundle = getIntent().getExtras();
        if ((bundle != null) && (bundle.containsKey("EVENTOS")))
        {
            evento = (Evento)bundle.getSerializable("EVENTOS");
            preencheDados();
        }
        else
        evento = new Evento();
        //fim de recupera dados da act Home


        //CRIAR DATABASE
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
        //FIM CRIAR DATABASE



        final Calendar calendar = Calendar.getInstance();
        final int diaData = calendar.get(calendar.DAY_OF_MONTH);
        final int mesData = calendar.get(calendar.MONTH);
        final int anoData = calendar.get(calendar.YEAR);


        final int hora = calendar.get(calendar.HOUR_OF_DAY);
        final int minuto = calendar.get(Calendar.MINUTE);

        final int horaTermino = calendar.get(calendar.HOUR_OF_DAY);
        final int minutoTermino = calendar.get(Calendar.MINUTE);




        etData.setOnClickListener(new EditText.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(cadEvent.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear , int dayOfMonth) {
                        monthOfYear = monthOfYear +1;
                    etData.setText(dayOfMonth + "/" + monthOfYear + "/" + year );
                    }
                }, diaData, mesData, anoData);
                datePickerDialog.setTitle("Selecione a data");
                datePickerDialog.show();
            }
        });






        etHora.setOnClickListener(new EditText.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(cadEvent.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        etHora.setText(hourOfDay+":"+minute);
                    }
                }, hora, minuto, true);
                timePickerDialog.setTitle("Selecione a hora do início");
                timePickerDialog.show();
            }
        });







        etTermino.setOnClickListener(new EditText.OnClickListener() {
        @Override
        public void onClick(View v) {
            TimePickerDialog timePickerDialog2 = new TimePickerDialog(cadEvent.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    etTermino.setText(hourOfDay+":"+minute);

                }
            }, horaTermino, minutoTermino, true);
            timePickerDialog2.setTitle("Selecione a hora de encerramento");
            timePickerDialog2.show();
        }
    });


    }






    //Captura o Tipo de Evento
    public void tipoEvent(View view)
    {
        btAdc_cadEvent.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tipoEvent = spinnerTipoEvent.getSelectedItem().toString();
                localEvent.setText(tipoEvent);
            }
        });
    }
    //Fim Captura Tipo Evento

    //Classe para Criar Mascaras
    public abstract static class Mask
    {
        public static String unmask(String s)
        {
            return  s.replaceAll("[.]", "").replaceAll("-", "")
                     .replaceAll("[/]", "").replaceAll("[(]","")
                     .replaceAll("[)]", "").replaceAll("[:]", "");
        }

       /* public static TextWatcher insert(final String mask, final EditText editText)
        {
            return new TextWatcher() {

                boolean isUpdating;
                String old;
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                    String str = Mask.unmask(s.toString());
                    String mascara = "";
                    if (isUpdating) {
                        old = str;
                        isUpdating = false;
                        return;
                    }
                    int i = 0;
                    for (char m : mask.toCharArray()) {
                        if (m != '#' && str.length() > old.length()) {
                            mascara += m;
                            continue;
                        }
                        try {
                            mascara += str.charAt(i);
                        } catch (Exception e) {
                            break;
                        }
                        i++;
                    }
                    isUpdating = true;
                    editText.setText(mascara);
                    editText.setSelection(mascara.length());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            };
        }*/
    }
    //Fim de Criar Mascaras

    private void preencheDados()
    {
        etData.setText(evento.getData());
        etHora.setText(evento.getHora());
        etTermino.setText(evento.getTermino());
        localEvent.setText(evento.getLocal());
        descricao.setText(evento.getDescricao());
        participantes.setText(evento.getPariticipantes());
        spinnerTipoEvent.setSelection(Integer.parseInt(evento.getTipoEvento()));

    }

        public void inserir(View view) {
            try {
                evento = new Evento();
                evento.setData(etData.getText().toString());
                evento.setHora(etHora.getText().toString());
                evento.setTermino(etTermino.getText().toString());
                evento.setLocal(localEvent.getText().toString());
                evento.setDescricao(descricao.getText().toString());
                evento.setPariticipantes(participantes.getText().toString());
                evento.setTipoEvento(String.valueOf(spinnerTipoEvent.getSelectedItemPosition()));
                evento.setRepetir(String.valueOf(repet));

                    if (evento.getId() == 0)
                    {RepositorioEventos.inserirEventos(evento);}
                    else
                    {RepositorioEventos.alterarEventos(evento);}

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                // Seta o Titulo do Dialog
                alertDialogBuilder.setTitle("Atenção");

                // seta a mensagem
                alertDialogBuilder.setMessage("Cadastrar outro evento?").setCancelable(false).setPositiveButton("Sim",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // SIM
                                Intent iCadEvent = new Intent(getBaseContext(), com.ftads.compromissos.cadEvent.class);
                                startActivity(iCadEvent);

                                // Fecha o dialog
//                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("Não",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // NAO
                                        Intent iHome = new Intent(getBaseContext(), Home.class);
                                        startActivity(iHome);

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

    public void salvar() {
        try {

            RepositorioEventos.excluir(evento.getId());
            evento = new Evento();
            evento.setData(etData.getText().toString());
            evento.setHora(etHora.getText().toString());
            evento.setTermino(etTermino.getText().toString());
            evento.setLocal(localEvent.getText().toString());
            evento.setDescricao(descricao.getText().toString());
            evento.setPariticipantes(participantes.getText().toString());
            evento.setTipoEvento(String.valueOf(spinnerTipoEvent.getSelectedItemPosition()));
            evento.setRepetir(String.valueOf(repet));

            if (evento.getId() == 0)
            RepositorioEventos.inserirEventos(evento);
            else
            RepositorioEventos.alterarEventos(evento);
        }catch (Exception ex)
        {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao inserir dados" + ex.getMessage());
            dlg.setNeutralButton("Ok", null);
            dlg.show();
        }
    }





    public void setBtVoltar(View view)
    {
                Intent iHome = new Intent(getBaseContext(), Home.class);
                startActivity(iHome);
    }

}

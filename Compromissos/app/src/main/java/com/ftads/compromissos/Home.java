package com.ftads.compromissos;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ftads.compromissos.dataBase.DataBase;
import com.ftads.compromissos.dominio.entidades.Evento;
import com.ftads.compromissos.dominio.repositorioEventos;



public class Home extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private DataBase dataBase;
    private SQLiteDatabase conn;
    private repositorioEventos RepositorioEventos;


    ListView lista;
    EditText pesquisa;
    Button cadEvent;
    ArrayAdapter<Evento> adpEventos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cadEvent = (Button)findViewById(R.id.btCadEvent);
        lista = (ListView)findViewById(R.id.lvLista);
        pesquisa = (EditText)findViewById(R.id.etBuscarEvent_home);
        lista.setOnItemClickListener(this);




        //CRIAR DATABASE
        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            RepositorioEventos = new repositorioEventos(conn);



            adpEventos = RepositorioEventos.buscaEventos(this);
            lista.setAdapter(adpEventos);

            filtraDados FiltraDados = new filtraDados(adpEventos);
            pesquisa.addTextChangedListener(FiltraDados);

           /* AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Sucesso");
            dlg.setNeutralButton("Ok", null);
            dlg.show();
            */
        }catch (SQLException ex)
        {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar banco" + ex.getMessage());
            dlg.setNeutralButton("Ok", null);
            dlg.show();
        }
        //FIM CRIAR DATABASE

    }

    public void setCadEvent(View view)
    {
                Intent iCadEvent = new Intent(getBaseContext(), com.ftads.compromissos.cadEvent.class);
                startActivity(iCadEvent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Evento evento = adpEventos.getItem(position);

        Intent iCadEvent = new Intent(getBaseContext(), detalhesEvento.class);
        iCadEvent.putExtra("EVENTOS", evento);

        startActivity(iCadEvent);

    }


    private class filtraDados implements TextWatcher
    {

        private ArrayAdapter<Evento> arrayAdapter;

        private filtraDados(ArrayAdapter<Evento> arrayAdapter)
        {
            this.arrayAdapter = arrayAdapter;
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
                arrayAdapter.getFilter().filter(s);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}

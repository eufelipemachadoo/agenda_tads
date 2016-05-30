package com.ftads.compromissos.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.widget.ArrayAdapter;

import com.ftads.compromissos.EventoArrayAdapter;
import com.ftads.compromissos.R;
import com.ftads.compromissos.dominio.entidades.Evento;

/**
 * Created by Felipe Machado on 14/05/2016.
 */
public class repositorioEventos {

    private SQLiteDatabase conn;

    public repositorioEventos(SQLiteDatabase conn)
    {
        this.conn = conn;
    }

    public ContentValues preencheCampos(Evento evento)
    {
        ContentValues values = new ContentValues();
        values.put("DATA", evento.getData());
        values.put("HORA", evento.getHora());
        values.put("TERMINO", evento.getTermino());
        values.put("LOCAL", evento.getLocal());
        values.put("DESCRICAO", evento.getDescricao());
        values.put("PARTICIPANTES", evento.getPariticipantes());
        values.put("TIPOEVENT", evento.getTipoEvento());
        //values.put("REPETIR", evento.getRepetir());
        //values.put("REPETICAO", evento.getTipoRepeticao());
        //values.put("REPETICADA", evento.getRepetCada());
        //values.put("INICIO", evento.getInicio());
        //values.put("SEMPRE", evento.getSempre());
       // values.put("OCORRENCIAS", evento.getOcorrencias());
        //values.put("TERMINAEM", evento.getTerminaEm());
        return values;
    }

    public void inserirEventos(Evento evento)
    {
        ContentValues values = preencheCampos(evento);
        conn.insertOrThrow("EVENTOS", null, values);
    }


    public void alterarEventos(Evento evento)
    {

        ContentValues values = preencheCampos(evento);
        conn.update("EVENTOS",values, "_id = ?", new String[]{String.valueOf(evento.getId())} );
    }


    public void excluir(long id)
    {
        conn.delete("EVENTOS", " _id = ? ", new String[]{String.valueOf(id)});
    }




    public ArrayAdapter<Evento> buscaEventos(Context context)
    {
        EventoArrayAdapter adpEventos = new EventoArrayAdapter(context, R.layout.item_eventos);

        Cursor cursor = conn.query("EVENTOS", null, null, null, null, null, null);
        if (cursor.getCount()>0)
        {

            cursor.moveToFirst();

            do {

                Evento evento = new Evento();

                evento.setId(cursor.getLong(0));
                evento.setData(cursor.getString(1));
                evento.setHora(cursor.getString(2));
                evento.setTermino(cursor.getString(3));
                evento.setLocal(cursor.getString(4));
                evento.setDescricao(cursor.getString(5));
                evento.setPariticipantes(cursor.getString(6));
                evento.setTipoEvento(cursor.getString(7));
                evento.setRepetir(cursor.getString(8));
//                evento.setTipoRepeticao(cursor.getString(9));



//
                adpEventos.add(evento);
            }
            while (cursor.moveToNext());

        }

        return adpEventos;
    }
}

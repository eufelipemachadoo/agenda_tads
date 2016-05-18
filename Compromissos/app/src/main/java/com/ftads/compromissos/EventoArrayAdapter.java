package com.ftads.compromissos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ftads.compromissos.dominio.entidades.Evento;

/**
 * Created by Felipe Machado on 16/05/2016.
 */
public class EventoArrayAdapter extends ArrayAdapter <Evento>{

    private int resource = 0;
    private LayoutInflater inflater;

    public EventoArrayAdapter(Context context, int resource)
    {
        super(context, resource);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = null;
        ViewHolder viewHolder = null;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            view = inflater.inflate(resource, parent, false);

            viewHolder.tvCor = (TextView)view.findViewById(R.id.tvCor);
            viewHolder.tvData= (TextView)view.findViewById(R.id.tvData);
            viewHolder.tvDescricao= (TextView)view.findViewById(R.id.tvDescricao);
            viewHolder.tvLocal= (TextView)view.findViewById(R.id.tvLocal);

            view.setTag(viewHolder);
            convertView = view;
        }
        else
        {
            viewHolder = (ViewHolder)convertView.getTag();
            view = convertView;
        }

        Evento evento = getItem(position);
        //viewHolder.tvCor
        viewHolder.tvData.setText(evento.getData());
        viewHolder.tvLocal.setText(evento.getLocal());
        viewHolder.tvDescricao.setText(evento.getDescricao());
        return view;
    }

    static class ViewHolder
    {
        TextView tvCor;
        TextView tvData;
        TextView tvLocal;
        TextView tvDescricao;
    }
}

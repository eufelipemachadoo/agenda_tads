package com.ftads.compromissos.dominio.entidades;

import java.io.Serializable;

/**
 * Created by Felipe Machado on 14/05/2016.
 */
public class Evento implements Serializable {

    private long id;
    private String data;
    private String hora;
    private String termino;
    private String local;
    private String descricao;
    private String pariticipantes;
    private String tipoEvento;
    private String repetir;


    public Evento()
    {

    }





//METODOS GETS E SETS

    //ID
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    //FIM ID



    //DATA
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    //FIM DATA


    //HORA
    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    //FIM HORA



    //TERMINO
    public String getTermino() {
        return termino;
    }

    public void setTermino(String termino) {
        this.termino = termino;
    }
    //FIM TERMINO


    //LOCAL
    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
    //FIM LOCAL



    //DESCRICAO
    public String getDescricao() {
        return descricao;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    //FIM DESCRICAO



    //PARTICIPANTES
    public String getPariticipantes() {
        return pariticipantes;
    }

    public void setPariticipantes(String pariticipantes) {
        this.pariticipantes = pariticipantes;
    }
    //FIM PARTICIPANTES


    //TIPO EVENTO
    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }
    //FIM TIPO EVENTO


    //REPETIR
    public String getRepetir() {
        return repetir;
    }

    public void setRepetir(String repetir) {
        this.repetir = repetir;
    }
    //FIM REPETIR

    //FIM METODOS GETS SETS



    @Override
    public String toString()
    {
        return "Data: "+ data+ " \nLocal: " + " " + local + " \nHora: " + hora;
    }
}

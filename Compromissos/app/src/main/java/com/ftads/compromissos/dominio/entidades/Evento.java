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


    private long idTEv;
    private String repeticao;
    private String repeteCada;
    private String terminaEm;







//METODOS GET SET

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


    //FIM METODOS GETS SETS


    public long getIdTEv() {
        return idTEv;
    }

    public void setIdTEv(long idTEv) {
        this.idTEv = idTEv;
    }

    public String getRepeticao() {
        return repeticao;
    }

    public void setRepeticao(String repeticao) {
        this.repeticao = repeticao;
    }

    public String getRepeteCada() {
        return repeteCada;
    }

    public void setRepeteCada(String repeteCada) {
        this.repeteCada = repeteCada;
    }

    public String getTerminaEm() {
        return terminaEm;
    }

    public void setTerminaEm(String terminaEm) {
        this.terminaEm = terminaEm;
    }




    @Override
    public String toString()
    {
        return "Data: "+ data+ " \nLocal: " + " " + local + " \nHora: " + hora;
    }
}

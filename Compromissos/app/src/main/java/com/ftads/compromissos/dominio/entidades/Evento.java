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
    private String tipoRepeticao;
    private String repetCada;;
    private String inicio;
    private String sempre;
    private String ocorrencias;
    private String terminaEm;


    public Evento()
    {

    }





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


    //REPETIR
    public String getRepetir() {
        return repetir;
    }

    public void setRepetir(String repetir) {
        this.repetir = repetir;
    }
    //FIM REPETIR

    public String getTipoRepeticao() {
        return tipoRepeticao;
    }

    public void setTipoRepeticao(String tipoRepeticao) {
        this.tipoRepeticao = tipoRepeticao;
    }

    public String getRepetCada() {
        return repetCada;
    }

    public void setRepetCada(String repetCada) {
        this.repetCada = repetCada;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getSempre() {
        return sempre;
    }

    public void setSempre(String sempre) {
        this.sempre = sempre;
    }

    public String getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(String ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    public String getTerminaEm() {
        return terminaEm;
    }

    public void setTerminaEm(String terminaEm) {
        this.terminaEm = terminaEm;
    }


    //FIM METODOS GETS SETS





    @Override
    public String toString()
    {
        return "Data: "+ data+ " \nLocal: " + " " + local + " \nHora: " + hora;
    }
}

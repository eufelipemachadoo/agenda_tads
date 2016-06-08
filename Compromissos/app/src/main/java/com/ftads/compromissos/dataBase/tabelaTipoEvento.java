package com.ftads.compromissos.dataBase;

/**
 * Created by Felipe Machado on 01/06/2016.
 */
public class tabelaTipoEvento {

    public static String getCreateTipoEventos()
    {
        StringBuilder sqlBuilder2 = new StringBuilder();
        sqlBuilder2.append("CREATE TABLE IF NOT EXISTS EVENTOS(" );
        sqlBuilder2.append(" _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder2.append(" REPETICAO VARCHAR(15), ");
        sqlBuilder2.append(" REPETECADA VARCHAR(15), ");
        sqlBuilder2.append(" TERMINA VARCHAR(15), ");
        sqlBuilder2.append(" CONSTRAINT FK_id FOREIGN KEY(evento_id) REFERENCES getCreateEventos); ");
        sqlBuilder2.append(" ); ");


        return sqlBuilder2.toString();

    }
}

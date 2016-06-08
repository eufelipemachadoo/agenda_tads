package com.ftads.compromissos.dataBase;

/**
 * Created by Felipe Machado on 14/05/2016.
 */
public class ScriptSQL {

    public static String getCreateEventos()
    {
        StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("CREATE TABLE IF NOT EXISTS EVENTOS(" );
            sqlBuilder.append(" _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
            sqlBuilder.append(" DATA VARCHAR(10), ");
            sqlBuilder.append(" HORA VARCHAR(5), ");
            sqlBuilder.append(" TERMINO VARCHAR(5), ");
            sqlBuilder.append(" LOCAL VARCHAR(50), " );
            sqlBuilder.append(" DESCRICAO VARCHAR(100), ");
            sqlBuilder.append(" PARTICIPANTES VARCHAR(200), ");
            sqlBuilder.append(" TIPOEVENTO VARCHAR(20) ");
            sqlBuilder.append(" ); ");


        return sqlBuilder.toString();

    }


    public static String getCreateTipoEventos()
    {
        StringBuilder sqlBuilder2 = new StringBuilder();
        sqlBuilder2.append("CREATE TABLE IF NOT EXISTS EVENTOS(" );
        sqlBuilder2.append(" _id INTEGER NOT NULL, ");
        sqlBuilder2.append(" id_eventos INTEGER NOT NULL, ");
        sqlBuilder2.append(" REPETICAO VARCHAR(15), ");
        sqlBuilder2.append(" REPETECADA VARCHAR(15), ");
        sqlBuilder2.append(" TERMINA VARCHAR(15), ");
        sqlBuilder2.append(" FOREIGN KEY(id_eventos) REFERENCES EVENTOS (_id); ");
        sqlBuilder2.append(" ); ");


        return sqlBuilder2.toString();

    }
}

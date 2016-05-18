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
            sqlBuilder.append(" TIPOEVENT VARCHAR(20), ");
            sqlBuilder.append(" REPETIR VARCHAR(3) ");
            sqlBuilder.append(" ); ");


        return sqlBuilder.toString();

    }
}

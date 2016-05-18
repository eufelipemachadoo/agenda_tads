package com.ftads.compromissos.dataBase;

/**
 * Created by Felipe Machado on 14/05/2016.
 */

import android.content.Context;
import android.database.sqlite.*;


public class DataBase extends SQLiteOpenHelper {
    public DataBase(Context context) {
        super(context, "AGENDA", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ScriptSQL.getCreateEventos());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

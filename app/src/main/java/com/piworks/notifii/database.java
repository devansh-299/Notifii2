package com.piworks.notifii;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class database extends SQLiteOpenHelper {

    static  final  private  String Db_name = "time-reminder";
    static  final private  String Db_table = "table";
    static final private int Db_Version  = 1 ;
    Context ctx;
    SQLiteDatabase mydb;

    public database(Context ct){
        super(ct,Db_name,null,Db_Version);
        ctx = ct;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+Db_table+" (id primary key autoincrement ,time_epoch text ,reminder text);");
        Log.i("Database","Table created");


    }
              //   DON'T KNOW ABOUT UPGRADING    JUST DID AS TOLD IN LECTURE!
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+Db_table);
        onCreate(db);
    }

    public void insertData (String timestring , String reminder){
        mydb = getWritableDatabase();
        mydb.execSQL("insert into "+Db_table+" (time_epoch,reminder) values ('"+timestring+"','"+reminder+"')");
        Log.i("Databased_Saved","Data was saved");
    }
    public String getreminder (String timestring){
        Long timeLong = Long.getLong(timestring);
        Long lowerlimit = timeLong - (1000)*60;
        String lowerstring = Long.toString(lowerlimit);
        Long upperlimit = timeLong + (1000)*60;
        String upperString = Long.toString(upperlimit);
        mydb = getReadableDatabase();
        Cursor cr = mydb.rawQuery("Select reminder from "+Db_table+" reminder between "+lowerstring+" and "+upperString,null);
        StringBuilder str  = new StringBuilder();
        while (cr.moveToNext()){
            String reminder = cr.getString(2);
            str.append(reminder);
        }
        return str.toString();


    }
}

package com.bilal.socialbuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;

/**
 * Created by bilalarshad on 3/16/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="buddydata.db";
    public static final String FILE_DIR ="myDataaaaa";

    public static final String TABLE_NAME1 ="user_info";
    public static final String colu1 ="user_id";
    public static final String colu2 ="home_lat";
    public static final String colu3 ="home_long";
    public static final String colu4 ="work_lat";
    public static final String colu5 ="work_long";
    public static final String colu6 ="progress";

    /////////////////////////////////////////////////////////

    public static final String TABLE_NAME2 ="friend_info";
    public static final String colf1 ="frnd1_lat";
    public static final String colf2 ="frnd1_long";
    public static final String colf3 ="frnd2_lat";
    public static final String colf4 ="frnd2_long";
    public static final String colf5 ="frnd3_lat";
    public static final String colf6 ="frnd3_long";



    ///////////////////////////////////////////////////////////

    public static final String TABLE_NAME3 ="activity";
    public static final String cola1 ="act_id";
    public static final String cola2 ="act_lat";
    public static final String cola3 ="act_long";
    public static final String cola4 ="duration";
    public static final String cola5 ="source";                  //Fb or GPS?
    public static final String cola6 ="points";                  //How many points for activity?


    /////////////////////////////////////////////////////////

    public static final String TABLE_NAME4 ="infer";
    public static final String colt1 ="type";                       //Restaurant, Friends, Library, Sports????
    public static final String colt2 ="points";


    /////////////////////////////////////////////////////////


    public DatabaseHelper(Context context) {
        super(context, Environment.getExternalStorageDirectory()
                + File.separator + FILE_DIR
                + File.separator + DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        sqLiteDatabase.execSQL("create table " + TABLE_NAME1 +  "(user_id  integer primary key, home_lat double," +
                "home_long double,work_lat double, work_long double, progress integer  )");

        sqLiteDatabase.execSQL("create table " + TABLE_NAME2 +  "(frnd1_lat  double, frnd1_long double," +
                "frnd2_lat  double, frnd2_long double, frnd3_lat  double, frnd3_long double )");

        sqLiteDatabase.execSQL("create table " + TABLE_NAME3 +  "(act_id  integer primary key, act_lat double," +
                "act_long  double, duration double, source  TEXT, points integer )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);


        onCreate(sqLiteDatabase);

    }

    public boolean insertData1(int id, double home_lt, double home_ln, double work_lt, double work_ln, int prog )
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(colu1, id);
        contentValues.put(colu2, home_lt);
        contentValues.put(colu3, home_ln);
        contentValues.put(colu4, work_lt);
        contentValues.put(colu5, work_ln);
        contentValues.put(colu6, prog);

        long result = sqLiteDatabase.insert(TABLE_NAME1,null, contentValues);

        if (result ==-1)
        {
            return false;
        }
        else
            return  true;

    }




    public boolean insertData2( double frnd1_lat, double frnd1_long, double frnd2_lat, double frnd2_long, double frnd3_lat, double frnd3_long)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(colf1, frnd1_lat);
        contentValues.put(colf2, frnd1_long);
        contentValues.put(colf3, frnd2_lat);
        contentValues.put(colf4, frnd2_long);
        contentValues.put(colf5, frnd3_lat);
        contentValues.put(colf6, frnd3_long);

        long result = sqLiteDatabase.insert(TABLE_NAME2,null, contentValues);

        if (result ==-1)
        {
            return false;
        }
        else
            return  true;

    }



    public boolean insertData3(int id, double act_lt, double act_ln, double dur, String source, int point )
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(cola1, id);
        contentValues.put(cola2, act_lt);
        contentValues.put(cola3, act_ln);
        contentValues.put(cola4, dur);
        contentValues.put(cola5, source);
        contentValues.put(cola6, point);

        long result = sqLiteDatabase.insert(TABLE_NAME2,null, contentValues);

        if (result ==-1)
        {
            return false;
        }
        else
            return  true;

    }




}

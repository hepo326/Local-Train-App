package com.example.mohebbadawy.localtrain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Moheb Badawy on 5/17/2016.
 */
public class MyDataBase extends SQLiteOpenHelper {

    public static final String CREATE_CMD_1 = "CREATE TABLE user (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT  ," +
            "NAME TEXT NOT NULL ," +
            "ADDRESS TEXT ," +
            "MOBILE TEXT  ," +
            "AGE INTEGER CHECK (AGE BETWEEN 4 AND 120)," +
            "PASSWORD TEXT  ," +
            "BALANCE REAL CHECK (BALANCE > 0) ," +
            "GENDER TEXT ) ";

    public static final String CREATE_CMD_2 = "CREATE TABLE journey (" +
            "ID INTEGER PRIMARY KEY ,"+
            "TRAIN_ID INTEGER  ," +
            "TRAIN_TYPE TEXT  ," +
            "FIRST_DATE TEXT ," +
            "SECOND_DATE TEXT ," +
            "FIRST_DEGREE_COST REAL ," +
            "SECOND_DEGREE_COST REAL ," +
            "ARRIVAL TEXT ," +
            "DESTINATION TEXT ) " ;


    public static final String CREATE_CMD_3 = "CREATE TABLE ticket (" +
            "TICKET_ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "USER_ID INTEGER  ," +
            "TRAIN_ID INTEGER  ," +
            "CONSTRAINT ID_FKEY FOREIGN KEY (USER_ID) REFERENCES user(ID) ON DELETE CASCADE ," +
            "CONSTRAINT TRAIN_ID_FKEY FOREIGN KEY (TRAIN_ID) REFERENCES journey(ID) ON DELETE CASCADE )";


    public static final String []INSERT_CMD =new String[]{
            "INSERT INTO journey   VALUES( 1  ,  981  , 'Air conditioned' , '05:00' , '14:00' ,  85.96  ,  48.64  , 'Aswan'      , 'Assiut');" ,
            "INSERT INTO journey   VALUES( 2  , 983  , 'Air conditioned' , '07:00' , '16:00' ,  85.96  ,  48.64  , 'Aswan'      , 'Assiut');" ,
            "INSERT INTO journey   VALUES( 3  , 887  , 'Fast'            , '15:00' , '22:55' ,  76.96  ,  42.64  , 'Aswan'      , 'Assiut');" ,
            "INSERT INTO journey   VALUES( 4  , 1903 , 'Fast'            , '16:15' , '00:20' ,  76.96  ,  42.64  , 'Aswan'      , 'Assiut');" ,
            "INSERT INTO journey   VALUES( 5  , 997  , 'Fast'            , '19:45' , '03:50' ,  76.96  ,  42.64  , 'Aswan'      , 'Assiut');" ,
            "INSERT INTO journey   VALUES( 6  , 89   , 'Fast'            , '20:00' , '05:00' ,  76.96  ,  42.64  , 'Aswan'      , 'Assiut');" ,
            "INSERT INTO journey   VALUES( 7  , 983  , 'Air conditioned' , '22:30' , '05:30' ,  80.96  ,  47.64  , 'Aswan'      , 'Assiut');" ,
            "INSERT INTO journey   VALUES( 8  , 89   , 'Fast'            , '20:00' , '13:40' , 153.92  ,  80.08  , 'Aswan'      , 'Alexandria');" ,
            "INSERT INTO journey   VALUES( 9  , 981  , 'Air conditioned' , '05:00' , '08:35' ,  50.64  ,  30.00  , 'Aswan'      , 'Luxor');" ,
            "INSERT INTO journey   VALUES( 10  , 983  , 'Air conditioned' , '07:00' , '10:25' ,  50.64  ,  30.00  , 'Aswan'      , 'Luxor');" ,
            "INSERT INTO journey   VALUES( 11  , 887  , 'Fast'            , '15:00' , '18:10' ,  42.64  ,  26.00  , 'Aswan'      , 'Luxor');" ,
            "INSERT INTO journey   VALUES( 12  , 1903 , 'Fast'            , '16:15' , '19:35' ,  42.64  ,  26.00  , 'Aswan'      , 'Luxor');" ,
            "INSERT INTO journey   VALUES( 13  , 997  , 'Fast'            , '19:45' , '23:05' ,  42.64  ,  26.00  , 'Aswan'      , 'Luxor');" ,
            "INSERT INTO journey   VALUES( 14  , 89   , 'Fast'            , '20:00' , '23:35' ,  42.64  ,  26.00  , 'Aswan'      , 'Luxor');" ,
            "INSERT INTO journey   VALUES( 15  , 989  , 'Fast'            , '22:30' , '00:50' ,  42.64  ,  26.00  , 'Aswan'      , 'Luxor');" ,
            "INSERT INTO journey   VALUES( 16  , 981  , 'Air conditioned' , '05:00' , '19:10' , 130.36  ,  65.20  , 'Aswan'      , 'Cairo');" ,
            "INSERT INTO journey   VALUES( 17  , 983  , 'Air conditioned' , '07:00' , '22:20' , 130.36  ,  65.20  , 'Aswan'      , 'Cairo');" ,
            "INSERT INTO journey   VALUES( 18  , 887  , 'Fast'            , '15:00' , '04:15' , 113.36  ,  57.20  , 'Aswan'      , 'Cairo');" ,
            "INSERT INTO journey   VALUES( 19  , 1903 , 'Fast'            , '16:15' , '05:40' , 113.36  ,  57.20  , 'Aswan'      , 'Cairo');" ,
            "INSERT INTO journey   VALUES( 20 , 997  , 'Fast'            , '19:45' , '09:15' , 113.36  ,  57.20  , 'Aswan'      , 'Cairo');" ,
            "INSERT INTO journey   VALUES( 21 , 89   , 'Fast'            , '20:00' , '13:40' , 113.36  ,  57.20  , 'Aswan'      , 'Alexandria');" ,
            "INSERT INTO journey   VALUES( 22 , 989  , 'Fast'            , '22:30' , '10:45' , 113.36  ,  57.20  , 'Aswan'      , 'Cairo');" ,
            "INSERT INTO journey   VALUES( 23 , 981  , 'Air conditioned' , '05:00' , '15:50' ,  95.36  ,  50.76  , 'Aswan'      , 'Minya');" ,
            "INSERT INTO journey   VALUES( 24 , 983  , 'Air conditioned' , '07:00' , '17:55' ,  95.36  ,  50.76  , 'Aswan'      , 'Minya');" ,
            "INSERT INTO journey   VALUES( 25 , 887  , 'Fast'            , '15:00' , '00:45' ,  87.36  ,  45.76  , 'Aswan'      , 'Minya');" ,
            "INSERT INTO journey   VALUES( 26 , 997  , 'Fast'            , '19:45' , '05:40' ,  87.36  ,  45.76  , 'Aswan'      , 'Minya');" ,
            "INSERT INTO journey   VALUES( 27 , 89   , 'Fast'            , '20:00' , '06:55' ,  87.36  ,  45.76  , 'Aswan'      , 'Minya');" ,
            "INSERT INTO journey   VALUES( 28 , 989  , 'Fast'            , '22:30' , '07:20' ,  87.36  ,  45.76  , 'Aswan'      , 'Minya');" ,
            "INSERT INTO journey   VALUES( 29 , 981  , 'Air conditioned' , '05:00' , '17:27' , 120.84  ,  60.00  , 'Aswan'      , 'Beni Suef');" ,
            "INSERT INTO journey   VALUES( 30 , 983  , 'Air conditioned' , '05:00' , '17:27' , 120.84  ,  60.00  , 'Aswan'      , 'Beni Suef');" ,
            "INSERT INTO journey   VALUES( 31 , 887  , 'Fast'            , '15:00' , '02:20' ,  99.84  ,  52.00  , 'Aswan'      , 'Beni Suef');" ,
            "INSERT INTO journey   VALUES( 32 , 997  , 'Fast'            , '19:45' , '07:20' ,  99.84  ,  52.00  , 'Aswan'      , 'Beni Suef');" ,
            "INSERT INTO journey   VALUES( 33 , 89   , 'Fast'            , '20:00' , '08:37' ,  99.84  ,  52.00  , 'Aswan'      , 'Beni Suef');" ,
            "INSERT INTO journey   VALUES( 34  , 989  , 'Fast'            , '22:30' , '09:00' ,  99.84  ,  52.00  , 'Aswan'      , 'Beni Suef');" ,
            "INSERT INTO journey   VALUES( 35 , 981  , 'Air conditioned' , '05:00' , '12:35' ,  80.60  ,  40.40  , 'Aswan'      , 'Sohag');" ,
            "INSERT INTO journey   VALUES( 36 , 983  , 'Air conditioned' , '07:00' , '14:25' ,  80.60  ,  40.40  , 'Aswan'      , 'Sohag');" ,
            "INSERT INTO journey   VALUES( 37 , 887  , 'Fast'            , '15:00' , '22:40' ,  67.60  ,  36.40  , 'Aswan'      , 'Sohag');" ,
            "INSERT INTO journey   VALUES( 38 , 1903 , 'Fast'            , '16:15' , '22:55' ,  67.60  ,  36.40  , 'Aswan'      , 'Sohag');" ,
            "INSERT INTO journey   VALUES( 39 , 997  , 'Fast'            , '19:45' , '02:30' ,  67.60  ,  36.40  , 'Aswan'      , 'Sohag');" ,
            "INSERT INTO journey   VALUES( 40 , 89   , 'Fast'            , '20:00' , '03:35' ,  67.60  ,  36.40  , 'Aswan'      , 'Sohag');" ,
            "INSERT INTO journey   VALUES( 41 , 989  , 'Fast'            , '22:30' , '04:15' ,  67.60  ,  36.40  , 'Aswan'      , 'Sohag');" ,
            "INSERT INTO journey   VALUES( 42 , 981  , 'Air conditioned' , '05:00' , '09:50' ,  75.00  ,  35.16  , 'Aswan'      , 'Qena');" ,
            "INSERT INTO journey   VALUES( 43 , 983  , 'Air conditioned' , '07:00' , '11:40' ,  75.00  ,  35.16  , 'Aswan'      , 'Qena');" ,
            "INSERT INTO journey   VALUES( 44 , 887  , 'Fast'            , '15:00' , '19:15' ,  52.00  ,  30.16  , 'Aswan'      , 'Qena');" ,
            "INSERT INTO journey   VALUES( 45 , 1903 , 'Fast'            , '16:15' , '20:40' ,  52.00  ,  30.16  , 'Aswan'      , 'Qena');" ,
            "INSERT INTO journey   VALUES( 46 , 997  , 'Fast'            , '19:45' , '00:15' ,  52.00  ,  30.16  , 'Aswan'      , 'Qena');" ,
            "INSERT INTO journey   VALUES( 47 , 89   , 'Fast'            , '20:00' , '00:50' ,  52.00  ,  30.16  , 'Aswan'      , 'Qena');" ,
            "INSERT INTO journey   VALUES( 48 , 989  , 'Fast'            , '22:30' , '01:55' ,  52.00  ,  30.16  , 'Aswan'      , 'Qena');" ,
            "INSERT INTO journey   VALUES( 49 , 988  , 'Fast'            , '00:01' , '07:55' ,  76.96  ,  42.64  , 'Assiut'     , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 50 , 88   , 'Sleep'           , '01:20' , '10:05' ,  95.96  ,  60.64  , 'Assiut'     , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 51 , 996  , 'Fast'            , '03:00' , '11:10' ,  76.96  ,  42.64  , 'Assiut'     , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 52 , 886  , 'Fast'            , '04:30' , '12:20' ,  76.96  ,  42.64  , 'Assiut'     , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 53 , 1902 , 'Fast'            , '04:55' , '13:15' ,  76.96  ,  42.64  , 'Assiut'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 54 , 980  , 'Sleep'           , '13:00' , '22:00' ,  93.96  ,  61.64  , 'Assiut'     , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 55 , 982  , 'Fast'            , '17:10' , '01:55' ,  76.96  ,  42.64  , 'Aswan'      , 'Assiut');" ,
            "INSERT INTO journey   VALUES( 56 , 88   , 'Sleep'           , '16:45' , '10:05' , 180.92  , 100.08  , 'Alexandria' , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 57 , 988  , 'Fast'            , '04:40' , '07:55' ,  42.64  ,  26.00  , 'Luxor'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 58 , 88   , 'Sleep'           , '06:30' , '10:05' ,  55.64  ,  35.00  , 'Luxor'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 59 , 996  , 'Fast'            , '07:50' , '11:10' ,  42.64  ,  26.00  , 'Luxor'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 60 , 886  , 'Fast'            , '09:10' , '12:20' ,  42.64  ,  26.00  , 'Luxor'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 61 , 1902 , 'Fast'            , '10:00' , '13:15' ,  42.64  ,  26.00  , 'Aswan'      , 'Luxor');" ,
            "INSERT INTO journey   VALUES( 62 , 980  , 'Sleep'           , '18:25' , '22:00' ,  62.64  ,  37.00  , 'Luxor'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 63 , 982  , 'Fast'            , '22:30' , '01:55' ,  42.64  ,  26.00  , 'Luxor'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 64 , 1902 , 'Fast'            , '00:05' , '13:15' , 113.36  ,  57.20  , 'Cairo'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 65  , 980  , 'Sleep'           , '08:00' , '22:00' , 136.36  ,  69.20  , 'Cairo'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 66  , 982  , 'Fast'            , '12:00' , '01:55' , 113.36  ,  57.20  , 'Cairo'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 67  , 988  , 'Fast'            , '19:00' , '07:55' , 113.36  ,  57.20  , 'Cairo'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 68  , 88   , 'Sleep'           , '20:00' , '10:05' , 180.92  , 100.08  , 'Alexandria' , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 69 , 996  , 'Fast'            , '22:00' , '11:10' , 113.36  ,  57.20  , 'Cairo'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 70 , 886  , 'Fast'            , '23:15' , '12:20' , 113.36  ,  57.20  , 'Cairo'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 71 , 996  , 'Fast'            , '01:10' , '11:10' ,  87.36  ,  45.76  , 'Minya'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 72 , 886  , 'Fast'            , '02:35' , '12:20' ,  87.36  ,  45.76  , 'Minya'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 73 , 980  , 'Sleep'           , '11:10' , '22:00' ,  99.36  ,  64.76  , 'Minya'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 74  , 982  , 'Fast'            , '15:15' , '01:55' ,  87.36  ,  45.76  , 'Minya'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 75  , 988  , 'Fast'            , '22:10' , '07:55' ,  87.36  ,  45.76  , 'Minya'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 76  , 88   , 'Sleep'           , '23:25' , '10:05' ,  87.36  ,  45.76  , 'Minya'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 77  , 886  , 'Fast'            , '01:00' , '12:20' ,  99.84  ,  52.00  , 'Beni Suef'  , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 78  , 980  , 'Sleep'           , '09:40' , '22:00' , 127.84  ,  68.00  , 'Beni Suef'  , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 79 , 982  , 'Fast'            , '13:45' , '01:55' ,  99.84  ,  52.00  , 'Beni Suef'  , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 80  , 988  , 'Fast'            , '20:40' , '07:55' ,  99.84  ,  52.00  , 'Beni Suef'  , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 81  , 88   , 'Sleep'           , '22:45' , '10:05' ,  99.84  ,  52.00  , 'Beni Suef'  , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 82  , 996  , 'Fast'            , '23:40' , '11:10' ,  99.84  ,  52.00  , 'Beni Suef'  , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 83  , 988  , 'Fast'            , '01:15' , '07:55' ,  67.60  ,  36.40  , 'Sohag'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 84  , 88   , 'Sleep'           , '02:45' , '10:05' ,  67.60  ,  36.40  , 'Sohag'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 85  , 996  , 'Fast'            , '04:20' , '11:10' ,  67.60  ,  36.40  , 'Sohag'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 86  , 886  , 'Fast'            , '05:45' , '12:20' ,  67.60  ,  36.40  , 'Sohag'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 87  , 1902 , 'Fast'            , '06:30' , '13:15' ,  67.60  ,  36.40  , 'Sohag'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 88  , 980  , 'Sleep'           , '14:30' , '22:00' ,  80.60  ,  46.40  , 'Sohag'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 89  , 982  , 'Fast'            , '18:45' , '01:55' ,  67.60  ,  36.40  , 'Sohag'      , 'Aswan');" ,
            "INSERT INTO journey   VALUES( 90  , 988  , 'Fast'            , '03:30' , '07:55' ,  52.00  ,  30.16  , 'Aswan'      , 'Qena');" ,
            "INSERT INTO journey   VALUES( 91  , 88   , 'Sleep'           , '05:15' , '10:05' ,  52.00  ,  30.16  , 'Aswan'      , 'Qena');" ,
            "INSERT INTO journey   VALUES( 92  , 996  , 'Fast'            , '06:40' , '11:10' ,  52.00  ,  30.16  , 'Aswan'      , 'Qena');" ,
            "INSERT INTO journey   VALUES( 93  , 886  , 'Fast'            , '08:05' , '12:20' ,  52.00  ,  30.16  , 'Aswan'      , 'Qena');" ,
            "INSERT INTO journey   VALUES( 94  , 1902 , 'Fast'            , '08:50' , '13:15' ,  52.00  ,  30.16  , 'Aswan'      , 'Qena');",
            "INSERT INTO journey   VALUES( 95  , 980  , 'Sleep'           , '17:10' , '22:00' ,  70.00  ,  45.10  , 'Aswan'      , 'Qena');" ,
            "INSERT INTO journey   VALUES( 96  , 982  , 'Fast'            , '22:15' , '01:55' ,  52.00  ,  30.16  , 'Aswan'      , 'Qena');"};





    public static final String TABLE_NAME_1 = "user";
    public static final String TABLE_NAME_2 = "journey";
    public static final String TABLE_NAME_3 = "ticket";

    public static final String DB_NAME = "Train.db";
    public static final String user_Col_1 = "ID";
    public static final String user_Col_2 = "NAME";
    public static final String user_Col_3 = "ADDRESS";
    public static final String user_Col_4 = "MOBILE";
    public static final String user_Col_5 = "AGE";
    public static final String user_Col_6 = "PASSWORD";
    public static final String user_Col_7 = "BALANCE";
    public static final String user_Col_8 = "GENDER";


    public static final String journey_Col_1 = "TRAIN_ID";
    public static final String journey_Col_2 = "TRAIN_TYPE";
    public static final String journey_Col_3 = "FIRST_DATE";
    public static final String journey_Col_4 = "SECOND_DATE";
    public static final String journey_Col_5 = "FIRST_DEGREE_COST";
    public static final String journey_Col_6 = "SECOND_DEGREE_COST";
    public static final String journey_Col_7 = "ARRIVAL";
    public static final String journey_Col_8 = "DESTINATION";


    public static final String ticket_Col_1 = "TICKET_ID";
    public static final String ticket_Col_2 = "USER_ID";
    public static final String ticket_Col_3 = "TRAIN_ID";


    public MyDataBase(Context context) {
        super(context, DB_NAME, null, 1);

        SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_CMD_1);
        db.execSQL(CREATE_CMD_2);
        db.execSQL(CREATE_CMD_3);
        insertJourneys();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_3);


        onCreate(db);


    }

    public Cursor query(String table, String[] columns, String selection) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(table, columns, selection, null, null, null, null);

        return cursor;
    }



    public boolean user_insertData(String name, String address, String mobile, String age, String password, String balance, String gender) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValue = new ContentValues();

        contentValue.put(user_Col_2, name);
        contentValue.put(user_Col_3, address);
        contentValue.put(user_Col_4, mobile);
        contentValue.put(user_Col_5, age);
        contentValue.put(user_Col_6, password);
        contentValue.put(user_Col_7, balance);
        contentValue.put(user_Col_8, gender);


        long Result = db.insert(TABLE_NAME_1, null, contentValue);

        if (Result == -1) {
            return false;
        } else {
            return true;
        }

    }


    public boolean ticket_insertData(String user_id, String journey_Col_1) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValue = new ContentValues();

        contentValue.put(ticket_Col_2, user_id);
        contentValue.put(ticket_Col_3, journey_Col_1);


        long Result = db.insert(TABLE_NAME_3, null, contentValue);

        if (Result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean insertJourneys(){
        SQLiteDatabase db = this.getWritableDatabase();

        long Result=-1;

        for (int i=0 ; i<INSERT_CMD.length;i++) {
             db.execSQL(INSERT_CMD[i]);
            Result++;
        }

        if (Result == -1) {
            return false;
        } else {
            return true;
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void execute(String sql){

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(sql);


    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean updateBalance(String id , String balance)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValue = new ContentValues();

        contentValue.put(user_Col_7, balance);



        long Result = db.update(TABLE_NAME_1, contentValue, "ID="+id , null);

        if (Result == -1) {
            return false;
        } else {
            return true;
        }
    }


}

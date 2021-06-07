package sg.edu.np.madweek3practical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class dbHandler extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "users.db";
    public static String TABLE_USER = "user";
    public static int DATABASE_VERSION = 1;

    public dbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_USER_TABLE = ("CREATE TABLE " + TABLE_USER + " (name TEXT, description TEXT, id INTEGER PRIMARY KEY AUTOINCREMENT, followed INTEGER)");




        db.execSQL(CREATE_USER_TABLE);



        for (int i = 0; i < 20; i++) {
            ContentValues values = new ContentValues();

            /*User u = new User();
            u.name = "Name" + generateRandNum();
            u.description = "Description" + generateRandNum();

            u.followed = generateRandbool() ;*/

            boolean followed = generateRandbool();



            values.put("name","Name" + generateRandNum() );
            values.put("description","Description" + generateRandNum());

            values.put("followed", convertbooltoInt(followed));

            db.insert(TABLE_USER,null,values);



        }





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }




    private int generateRandNum() {

        Random ran = new Random();
        int ranVal = ran.nextInt();
        return ranVal;

    }

    private boolean generateRandbool() {

        Random ran = new Random();
        boolean ranbool = ran.nextBoolean();
        return ranbool;


    }

    public int convertbooltoInt(boolean tf){

        if (generateRandbool() == true){

            return 1;




        }
        return 0;

    }


    public ArrayList<User> getUsers(){
        String query = "SELECT * FROM " + TABLE_USER ;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query,null);

        ArrayList<User> list = new ArrayList<User> ();

        while (cursor.moveToNext()){

            //User u =  new User();
            User userdata =  new User();

            userdata.name = cursor.getString(0);
            userdata.description = cursor.getString(1);
            userdata.id = cursor.getInt(2);
            userdata.followed = cursor.getInt(3)==0?false:true;
            list.add(userdata);

           


        }
         cursor.close();
        db.close();
         return list;
    }

    public void updateUser(User u)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("followed", u.followed);
        int count = db.update("user", values, "id = ?", new String[]{ "" + u.id });

        db.close();
    }



}






















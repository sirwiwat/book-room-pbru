package pbru.permsook.wiwat.bookroompbru;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.security.PublicKey;

/**
 * Created by addroid on 24/5/2559.
 */
public class Mymanage {
    //ประกาศตัวแปร
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public static final String user_table = "userTABLE";
    public static final String column__id = "_id";
    public static final String column_Name = "Name";
    public static final String column_Surname = "Surname";
    public static final String column_IDcard = "IDcard";
    public static final String column_Office = "Office";
    public static final String column_User = "User";
    public static final String column_password = "Password";


    public Mymanage(Context context) {
        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();

    }//ลูก

    public long addUser(String strName,
                        String strSurname,
                        String strIDcard,
                        String strOffice,
                        String strUser,
                        String strPassword) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_Name, strName);
        contentValues.put(column_Surname, strSurname);
        contentValues.put(column_IDcard, strIDcard);
        contentValues.put(column_Office, strOffice);
        contentValues.put(column_User, strUser);
        contentValues.put(column_password, strPassword);
        return sqLiteDatabase.insert(user_table,null,contentValues);

    }

}//แม่

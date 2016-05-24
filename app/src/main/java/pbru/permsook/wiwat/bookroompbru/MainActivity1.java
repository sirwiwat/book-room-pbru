package pbru.permsook.wiwat.bookroompbru;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity1 extends AppCompatActivity {
    //ประกาศตัวแปร
    private Mymanage mymanage;
    private String strURL = "http://swiftcodingthai.com/pbru/get_user.php";
    private String strLogo = "http://swiftcodingthai.com/pbru/Image/logo_pbru.png";
    private ImageView imageView;
    private EditText userEdittext, passwordEdittext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        mymanage = new Mymanage(this);

        imageView = (ImageView) findViewById(R.id.imageView);
        userEdittext = (EditText) findViewById(R.id.editText6);
        passwordEdittext = (EditText) findViewById(R.id.editText7);


        Picasso.with(this).load(strLogo).into(imageView);

        //ทดสอบป้อนข้อมูล
       // mymanage.addUser("name","sur","card","off","user","pass");

        //ลบทั้งหมดในฐานข้อมูลบนมือถือ SQllite
        deleteAllSQLite();

     //
        synJSON();

    } //ลูก

    private void synJSON() {
        SynUser synUser = new SynUser();
        synUser.execute();
    }


    //สร้างอินเนอร์คลาส
    public class SynUser extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strURL).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                return null;
            }

        }//คลาสเบื้องหลัง

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("pbruV1", "strJSON ==>" + s);

            try {
                JSONArray jsonArray = new JSONArray(s);

                for (int i=0;i<jsonArray.length();i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String strName = jsonObject.getString(Mymanage.column_Name);
                    String strSurname = jsonObject.getString(Mymanage.column_Surname);
                    String strIDcard = jsonObject.getString(Mymanage.column_IDcard);
                    String strOffice = jsonObject.getString(Mymanage.column_Office);
                    String strUser = jsonObject.getString(Mymanage.column_User);
                    String strPassword = jsonObject.getString(Mymanage.column_password);

                    mymanage.addUser(strName, strSurname, strIDcard, strOffice, strUser, strPassword);

                }//วนลูปตัด Json



            } catch (Exception e) {
                e.printStackTrace();
            }


        }//onPost
    }// อินเนอร์คลาส คลาสเบื้องหน้า (คลาสซ้อนคลาส)

    private void deleteAllSQLite() {
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        sqLiteDatabase.delete(Mymanage.user_table, null, null);
    }

    public void clickSingUpMain(View view) {
        startActivity(new Intent(MainActivity1.this,SingUpActivity2.class)); //เคลื่อนย้ายจากหน้าหนึ่งไปหน้าหนึ่ง
    }
}   // แม่


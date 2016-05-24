package pbru.permsook.wiwat.bookroompbru;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class MainActivity1 extends AppCompatActivity {
    //ประกาศตัวแปร
    private Mymanage mymanage;
    private String strURL = "http://swiftcodingthai.com/pbru/get_user.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        mymanage = new Mymanage(this);

        //ทดสอบป้อนข้อมูล
       // mymanage.addUser("name","sur","card","off","user","pass");

        //ลบทั้งหมดในฐานข้อมูลบนมือถือ SQllite
        deleteAllSQLite();
//---------------
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


package pbru.permsook.wiwat.bookroompbru;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SingUpActivity2 extends AppCompatActivity {

    //ประกาศตัวแปรส่ง
    private EditText nameEditText, surnameEditText, idcaedEditText,
            userEditText, passwordEditText;
    private RadioGroup radioGroup;
    private RadioButton officeRadioButton, outofficeRadioButton;
    //ตัวแปรรับ
    private String nameString, surnameString, idcaedString,
            userString, passwordString, officeString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up2);

        //ผูกตัวแปร
        bindwiget();

        //ควบคุมเรดิโอButton
        radiocontroller();


    }//methon ลูก สมัครสมาชิก

    private void radiocontroller() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                switch (checkedId) {
                    case R.id.radioButton:
                        officeString = "0";
                        break;
                    case R.id.radioButton2:
                        officeString = "1";
                        break;
                    default:
                        officeString = "0";
                        break;
                }
            }
        });
    }

    private void bindwiget() {
        nameEditText = (EditText) findViewById(R.id.editText);
        surnameEditText = (EditText) findViewById(R.id.editText2);
        idcaedEditText = (EditText) findViewById(R.id.editText3);
        userEditText = (EditText) findViewById(R.id.editText4);
        passwordEditText = (EditText) findViewById(R.id.editText5);
        radioGroup = (RadioGroup) findViewById(R.id.ragoffice);
        officeRadioButton = (RadioButton) findViewById(R.id.radioButton);
        outofficeRadioButton = (RadioButton) findViewById(R.id.radioButton2);
    }//ผูกตัวแปร

    public void clickSignUp2(View view) {
        nameString = nameEditText.getText().toString().trim();
        surnameString = surnameEditText.getText().toString().trim();
        idcaedString = idcaedEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //ตรวจสอบช่องว่าง
        if (checkSpace()) {
            MyError myError = new MyError();
            myError.myDialog(this, "กรอกข้อมูลไม่ครบ", "กรุณากรอกข้อมูลให้ครบ");
        }//แจ้งเตือนมีช่องว่าง
        else if (idcaedString.length() != 13) {
            MyError myError = new MyError();
            myError.myDialog(this, "รหัสบัตรประชาชนไม่ถูกต้อง", "กรุณากรอกบัตรประชาชนให้ถูกต้อง");
        }//ตรวจสอบเลขบัตรประชาชน
        else if (checkradiochoose()) {
            MyError myError = new MyError();
            myError.myDialog(this, "ไม่มีการเลือกสถานะ", "กรุณาเลือกสถานะ");

        } else if (checkUser()) {
            MyError myError = new MyError();
            myError.myDialog(this, "มี Uer นี้อยู่แล้ว", "กรุณากรอก User ใหม่");

        } //เช็ค radiobutton
        else if (checkIDcard()) {
            MyError myError = new MyError();
            myError.myDialog(this, "รหัสบัตรประชาชนถูกใช้แล้ว", "กรุณากรอกบัตรประชาชนให้ถูกต้อง");
        } else {
            uploadtoserver();
        }

    }//คลิกสมัครสมาชิก

    private boolean checkIDcard() {
        try {

            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                    MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE WHERE IDcard = "+"'"+ idcaedString +"'", null);
            cursor.moveToFirst();

            Log.d("25May", "ID ==>" + idcaedString);
            Log.d("25May", "ID ==>" + cursor.getString(3));
            return true;

        } catch (Exception e) {
            return false;
        }

    }


    private boolean checkUser() {
        try {

            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                    MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE WHERE User = "+"'"+ userString +"'", null);
            cursor.moveToFirst();
            Log.d("25May", "ID ==>" + userString);
            Log.d("25May", "ID ==>" + cursor.getString(5));
            return true;

        } catch (Exception e) {
            return false;
        }


    }//

    private void uploadtoserver() {

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("Name", nameString)
                .add("Surname", surnameString)
                .add("IDcard", idcaedString)
                .add("Office", officeString)
                .add("User", userString)
                .add("Password", passwordString)
                .build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url("http://swiftcodingthai.com/pbru/add_user_master.php").post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                finish();
            }
        });

    }//การอัพโหลดข้อมูลไปฐานข้อมูล

    private boolean checkradiochoose() {

        boolean result = true;

        result = officeRadioButton.isChecked() || outofficeRadioButton.isChecked();

        return !result;
    }

    private boolean checkSpace() {
        boolean result = true;
        result = nameString.equals("") ||
                surnameString.equals("") ||
                idcaedString.equals("") ||
                userString.equals("") ||
                passwordString.equals("");

        return result;
    }//โค้ดตรวจสอบช่องว่าง

}//main แม่ สมัครสมาชิก

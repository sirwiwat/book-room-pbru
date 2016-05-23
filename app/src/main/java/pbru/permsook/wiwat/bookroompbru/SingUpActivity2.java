package pbru.permsook.wiwat.bookroompbru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
            myError.myDialog(this,"กรอกข้อมูลไม่ครบ","กรุณากรอกข้อมูลให้ครบ");
        }//แจ้งเตือนมีช่องว่าง
        else if (idcaedString.length() != 13) {
            MyError myError = new MyError();
            myError.myDialog(this, "รหัสบัตรประชาชนไม่ถูกต้อง", "กรุณากรอกบัตรประชาชนให้ถูกต้อง");
        }//ตรวจสอบเลขบัตรประชาชน
        else if (checkradiochoose()) {
            MyError myError = new MyError();
            myError.myDialog(this,"ไม่มีการเลือกสถานะ","กรุณาเลือกสถานะ");

        } else {
            //uploadtoserver

        } //เช็ค radiobutton

    }//คลิกสมัครสมาชิก

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

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


    }//methon ลูก สมัครสมาชิก

    private void bindwiget() {
        nameEditText = (EditText) findViewById(R.id.editText);
        surnameEditText = (EditText) findViewById(R.id.editText2);
        idcaedEditText = (EditText) findViewById(R.id.editText3);
        userEditText = (EditText) findViewById(R.id.editText4);
        passwordEditText = (EditText) findViewById(R.id.editText5);
        radioGroup = (RadioGroup) findViewById(R.id.ragoffice);
        officeRadioButton = (RadioButton) findViewById(R.id.radioButton);
        outofficeRadioButton = (RadioButton) findViewById(R.id.radioButton2);
    }

    public void clickSignUp2(View view) {

    }

}//main แม่ สมัครสมาชิก

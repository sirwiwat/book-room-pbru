package pbru.permsook.wiwat.bookroompbru;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
    } //method

    public void clickSingUpMain(View view) {
        startActivity(new Intent(MainActivity1.this,SingUpActivity2.class)); //เคลื่อนย้ายจากหน้าหนึ่งไปหน้าหนึ่ง
    }
}   // Class


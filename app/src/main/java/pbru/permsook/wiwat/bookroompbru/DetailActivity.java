package pbru.permsook.wiwat.bookroompbru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView nameRoomTextView, nameBuildTextView, sizeTextView, priceDayTextView, priceHoliTextView;
    private ImageView imageView;
    private  String nameRoomString,nameBuildString,sizeString,priceDayString, priceHoliString,
    image1String,image2String,image3String,image4String,image5String;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        bindwidget();

        receiveValue();

        showtextviwe();


    }//ลูก

    private void showtextviwe() {
        nameRoomTextView.setText(nameRoomString);
        nameBuildTextView.setText(nameBuildString);
        sizeTextView.setText("ความจุ" + " " +sizeString);
        priceDayTextView.setText("วันธรรมดา" + " " +priceDayString);
        priceHoliTextView.setText("วันหยุด" + " " +priceHoliString);

    }

    private void receiveValue() {
        nameRoomString = getIntent().getStringExtra("NameRoom");
        nameBuildString = getIntent().getStringExtra("NameBuild");
        sizeString = getIntent().getStringExtra("Size");
        priceDayString = getIntent().getStringExtra("PriceDay");
        priceHoliString = getIntent().getStringExtra("PriceHoliday");
        image1String = getIntent().getStringExtra("Image1");
        image2String = getIntent().getStringExtra("Image2");
        image3String = getIntent().getStringExtra("Image3");
        image4String = getIntent().getStringExtra("Image4");
        image5String = getIntent().getStringExtra("Image5");

    }

    private void bindwidget() {

        nameRoomTextView = (TextView) findViewById(R.id.textView13);
        nameBuildTextView = (TextView) findViewById(R.id.textView14);
        sizeTextView = (TextView) findViewById(R.id.textView15);
        priceDayTextView = (TextView) findViewById(R.id.textView16);
        priceHoliTextView = (TextView) findViewById(R.id.textView17);
        imageView =(ImageView) findViewById(R.id.imageView3);


    }
}//แม่

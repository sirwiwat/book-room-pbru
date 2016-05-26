package pbru.permsook.wiwat.bookroompbru;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.preference.DialogPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

public class CalendaActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private Spinner spinner;
    private RadioGroup radioGroup;
    private RadioButton beforenoonRadioButton, afternoonRadioButton, fulldayRadioButton;
    private String idcardString, nameRoomString, dateString, timeString;
    private String[] userloginStrings;
    private int DayAnInt, monthAnInt, yearAnInt, loopdayAnInt = 1;
    private String urlJSON = "http://swiftcodingthai.com/pbru/get_order.php";


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calenda);

        bindWidget();

        getValue();

        createSpinner();

        calendacontoller();

        SetupDate();

        radiocontroller();
        SynOrderTABLE synOrderTABLE = new SynOrderTABLE();
        synOrderTABLE.execute();
    }//ลูก

    public class SynOrderTABLE extends AsyncTask<Void, Void, String> {


        @Override
        protected String doInBackground(Void... params) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJSON).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                return null;
            }


        }//doIn

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("26May", "JSON ==>" + s);

            try {

                JSONArray jsonArray = new JSONArray(s);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String strIDcard = jsonObject.getString(Mymanage.column_IDcard);
                    String strNameRoom = jsonObject.getString(Mymanage.column_nameRoom);
                    String strDate = jsonObject.getString(Mymanage.column_date);
                    String strTime = jsonObject.getString(Mymanage.column_time);

                    Mymanage mymanage = new Mymanage(CalendaActivity.this);
                    mymanage.addorder(strIDcard, strNameRoom, strDate, strTime);


                }

            } catch (Exception e) {
                e.printStackTrace();

            }
        }//onPost
    }//syn class

    private void calendacontoller() {


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {

                SetupDate();


                if (year < yearAnInt) {
                    MyError myError = new MyError();
                    myError.myDialog(CalendaActivity.this, "คุณเลือกย้อนเวลาไม่ได้", "โปรดเลือกให้ถูกต้อง");

                } else {
                    if (year == yearAnInt) {


                        if ((month + 1) < monthAnInt) {
                            MyError myError = new MyError();
                            myError.myDialog(CalendaActivity.this, "คุณเลือกย้อนเวลาไม่ได้", "โปรดเลือกให้ถูกต้อง");


                        } else {

                            if ((month + 1) == monthAnInt) {

                                if ((day <= DayAnInt)) {
                                    MyError myError = new MyError();
                                    myError.myDialog(CalendaActivity.this, "คุณเลือกย้อนเวลาไม่ได้", "โปรดเลือกให้ถูกต้อง");

                                } else {
                                    DayAnInt = day;
                                    monthAnInt = month + 1;
                                    yearAnInt = year;
                                }

                            } else {

                                DayAnInt = day;
                                monthAnInt = month + 1;
                                yearAnInt = year;

                            }

                        }
                    }

                    DayAnInt = day;
                    monthAnInt = month + 1;
                    yearAnInt = year;




                }


            }    //onSelectDay
        });

    }   // calendar


    private void SetupDate() {
            Calendar calendar = Calendar.getInstance();

            DayAnInt = calendar.get(Calendar.DATE);
            monthAnInt = calendar.get(Calendar.MONTH) + 1;
            yearAnInt = calendar.get(calendar.YEAR);


    }

    private void radiocontroller() {

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton3:
                        timeString = "0";
                        break;
                    case R.id.radioButton4:
                        timeString = "1";
                        break;
                    case R.id.radioButton5:
                        timeString = "2";
                        break;

                }

            }
        });
    }


    private void getValue() {
        userloginStrings = getIntent().getStringArrayExtra("User");
        idcardString = userloginStrings[3];
        nameRoomString = getIntent().getStringExtra("NameRoom");

    }

    private void createSpinner() {
        String[] strAmount = {"1 วัน", "2 วัน", "3 วัน", "4 วัน", "5 วัน", "6 วัน", "7 วัน", "8 วัน", "9 วัน", "10 วัน"};
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strAmount);
        spinner.setAdapter(stringArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loopdayAnInt = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                loopdayAnInt = 1;
            }
        });
    }//create Spinner

    private void bindWidget() {
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        spinner = (Spinner) findViewById(R.id.spinner);
        radioGroup = (RadioGroup) findViewById(R.id.ragtime);
        beforenoonRadioButton = (RadioButton) findViewById(R.id.radioButton3);
        afternoonRadioButton = (RadioButton) findViewById(R.id.radioButton4);
        fulldayRadioButton = (RadioButton) findViewById(R.id.radioButton5);

    }


    public void clickOrdercalenda(View view) {
        if (checkRadioButton()) {
            MyError myError = new MyError();
            myError.myDialog(this, "คุณไม่ได้เลือกเวลา", "โปรดเลือกเวลาที่ต้องการ");

        } else if (checkroom()) {
            MyError myError = new MyError();
            myError.myDialog(this, "ห้องไม่ว่างในวันดังกล่าว", "โปรดเลือกวันและเวลาอีกครั้ง");

        } else {

            updatetoserver();
        }

    }//click order

    private boolean checkroom() {


        try {
            dateString = CreateDate(DayAnInt);
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                    MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM orderTABLE WHERE NameRoom = "+"'"+ nameRoomString + "'"+" AND Date = " + "'" + dateString + "'", null);
            cursor.moveToFirst();

            Log.d("26MayV1", "Room ==>" + cursor.getString(2));
            Log.d("26MayV1", "Date ==>" + cursor.getString(3));

            return true;

        } catch (Exception e) {
            return false;
        }


    }

    private void updatetoserver() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.icon_myaccount);
        builder.setCancelable(false);
        builder.setTitle("ตรวจสอบข้อมูล");
        builder.setMessage("ชื่อผู้จอง =" + userloginStrings[1] + " " + userloginStrings[2] + "\n" +
                "รหัสบัตรประชาชน =" + idcardString + "\n" +
                "ห้องที่จอง = " + nameRoomString + "\n" +
                "วันที่เข้าใช้ = " + CreateDate(DayAnInt) + "\n" +
                "จำนวนวัน = " + Integer.toString(loopdayAnInt) + "\n" +
                "เวลาที่เข้าใช้ = " + showtime(timeString));
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("ConFirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                updateOrderTABLE();
                dialog.dismiss();

            }

        });
        builder.show();


    }//updatetoserver

    private void updateOrderTABLE() {
        String strURL = "http://swiftcodingthai.com/pbru/add_order.php";
        int intDay = DayAnInt;
        for (int i = 0; i < loopdayAnInt; i++) {
            intDay = intDay + i;
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("IDcard", idcardString)
                    .add("NameRoom", nameRoomString)
                    .add("Date", CreateDate(intDay))
                    .add("Time", timeString)
                    .build();

            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strURL).post(requestBody).build();
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


        }//for
    }//updateOrderTABLE


    private String CreateDate(int intDay) {
        String strResult = null;
        strResult = Integer.toString(intDay) + "/" +
                Integer.toString(monthAnInt) + "/" +
                Integer.toString(yearAnInt);


        return strResult;
    }

    private String showtime(String timeString) {
        int inttime = Integer.parseInt(timeString);
        String strResult = "8:00 - 12:00";
        switch (inttime) {
            case 0:
                strResult = "8:00 - 12:00";
                break;
            case 1:
                strResult = "13:00 - 16:00";
                break;
            case 2:
                strResult = "8:00 - 16:00";
                break;
        }


        return strResult;
    }

    private boolean checkRadioButton() {

        if (beforenoonRadioButton.isChecked() ||
                afternoonRadioButton.isChecked() ||
                fulldayRadioButton.isChecked()) {

            return false;
        } else {
            return true;
        }

    }
}//แม่

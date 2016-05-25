package pbru.permsook.wiwat.bookroompbru;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by addroid on 25/5/2559.
 */
public class MyAdapter extends BaseAdapter {

    //ประกาศตัวแปร
    private Context context;
    private String[] nameRoomStrings, nameBuildStrings, sizeStrings, priceDayStrings,
            priceHolidayStrings, iconStrings;

    public MyAdapter(Context context,
                     String[] nameRoomStrings,
                     String[] nameBuildStrings,
                     String[] sizeStrings,
                     String[] priceDayStrings,
                     String[] priceHolidayStrings,
                     String[] iconStrings) {
        this.context = context;
        this.nameRoomStrings = nameRoomStrings;
        this.nameBuildStrings = nameBuildStrings;
        this.sizeStrings = sizeStrings;
        this.priceDayStrings = priceDayStrings;
        this.priceHolidayStrings = priceHolidayStrings;
        this.iconStrings = iconStrings;
    }

    @Override
    public int getCount() {
        return nameRoomStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.my_listview, viewGroup, false);

        TextView nameroomTextView = (TextView) view1.findViewById(R.id.textView8);
        nameroomTextView.setText(nameRoomStrings[i]);
        TextView nameBuildtView = (TextView) view1.findViewById(R.id.textView9);
        nameBuildtView.setText(nameBuildStrings[i]);
        TextView sizeTextView = (TextView) view1.findViewById(R.id.textView10);
        sizeTextView.setText("ความจุ"+" " + sizeStrings[i]);
        TextView priceDayTextView = (TextView) view1.findViewById(R.id.textView11);
        priceDayTextView.setText("วันธรรมดา" +" "+ priceDayStrings[i]);
        TextView priceHolidayTextView = (TextView) view1.findViewById(R.id.textView12);
        priceHolidayTextView.setText("วันหยุด" +" " + priceHolidayStrings[i]);
        ImageView imageView = (ImageView) view1.findViewById(R.id.imageView2);
        Picasso.with(context).load(iconStrings[i]).resize(120,100).into(imageView);
        return view1;
    }
}//แม่

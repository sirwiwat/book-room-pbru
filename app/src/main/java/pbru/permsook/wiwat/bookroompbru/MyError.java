package pbru.permsook.wiwat.bookroompbru;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by addroid on 23/5/2559.
 */
public class MyError {
    public void myDialog(Context context,
                         String strtitle,
                         String strMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.danger);
        builder.setCancelable(false);
        builder.setTitle(strtitle);
        builder.setMessage(strMessage);
        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }//โค้ดแจ้งเตือน

}//แม่ error

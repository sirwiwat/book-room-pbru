package pbru.permsook.wiwat.bookroompbru;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by addroid on 23/5/2559.
 */
public class MyError {
    public void myDialog(Context context,
                         String strtitle,
                         String strMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.danger);
    }

}//แม่ error

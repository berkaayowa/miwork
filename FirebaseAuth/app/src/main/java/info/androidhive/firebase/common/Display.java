package info.androidhive.firebase.common;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by Berka on 9/23/2016.
 */
public class Display {
    private static ProgressDialog progress;
    public static void console(String value)
    {
        System.out.println(value);
    }

    public static  void toast(String msg, Context context)
    {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void toastLong (String msg, Context context)
    {
       Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static  void errorToast(String msg, Context context)
    {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void startLoading(String message, Activity context){
        progress = new ProgressDialog(context);
        progress.setCancelable(false);
        progress.setMessage(message + "...");
        progress.show();
    }

    public static void endLoading() {
        if(progress != null){
            progress.hide();
        }
        else {

        }
    }
}

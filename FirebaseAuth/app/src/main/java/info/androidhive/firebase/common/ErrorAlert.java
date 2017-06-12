package info.androidhive.firebase.common;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.TextView;

import info.androidhive.firebase.R;

/**
 * Created by berka on 6/10/17.
 */

public class ErrorAlert extends AlertDialog.Builder {
    //private TextView _title, message;
    public ErrorAlert(Context context, Activity activity) {
        super(context);
        LayoutInflater inflater = (activity).getLayoutInflater();
        setCancelable(true);
        setView(inflater.inflate(R.layout.alert_error_style, null));

    }
}

package info.androidhive.firebase.common;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.TextView;

import info.androidhive.firebase.R;

/**
 * Created by berka on 6/10/17.
 */

public class ErrorAlert extends AlertDialog.Builder {
    private TextView alertTitle, alertMessage;
    private String alert;
    private Activity activity;

    public ErrorAlert(Context context, Activity activity, String alert) {
        super(context);

        this.activity = activity;
        this.alert = alert;

        LayoutInflater inflater = (activity).getLayoutInflater();
        setCancelable(true);

        if (alert.equalsIgnoreCase("emptyField")) {
            setView(inflater.inflate(R.layout.alert_empty_field_style, null));
        } else if(alert.equalsIgnoreCase("successReg")) {
            setView(inflater.inflate(R.layout.alert_style, null));
        } else  if(alert.equalsIgnoreCase("error")) {
            setView(inflater.inflate(R.layout.alert_error_style, null));
        } else if(alert.equalsIgnoreCase("invalidUser")) {
            setView(inflater.inflate(R.layout.alert_invalid_user_style, null));
        } else if(alert.equalsIgnoreCase("default")) {
            setView(inflater.inflate(R.layout.alert_confrim_error_style, null));
        } if(alert.equalsIgnoreCase("successVerification")) {
            setView(inflater.inflate(R.layout.alert_success_verification_style, null));
        }


    }

    public void setAlertMessage(String title, String message) {
        if (alert.equalsIgnoreCase("default")) {
//
//            alertTitle = (TextView) activity.findViewById(R.id.txtErrorTitle);
//            alertMessage = (TextView) activity.findViewById(R.id.txtErrorMs);
//
//           // if(alertTitle != null && alertMessage != null) {
//                alertTitle.setText(title);
//                alertMessage.setText(message);
//          //  }
        }
    }

    public void close() {
        setNegativeButton("button4", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface d, int arg1) {
                d.cancel();
            }
        });
    }
}

package info.androidhive.firebase.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import info.androidhive.firebase.R;

public class ConfirmPhone extends AppCompatActivity {

    TextView txtMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_phone);

        txtMessage = (TextView) findViewById(R.id.txtMessage);

        Bundle extras = getIntent().getExtras();
        if (!extras.get("message").toString().isEmpty()) {
            txtMessage.setText(extras.get("message").toString());
        }
    }
}

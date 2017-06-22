package info.androidhive.firebase.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import info.androidhive.firebase.R;
import info.androidhive.firebase.domain.User;

public class Myprofile extends AppCompatActivity {

    private User user;
    private EditText edtName, edtSurname, edtEmail, edtPassword, edtPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

        edtName = (EditText) findViewById(R.id.edtProName);
        edtSurname = (EditText) findViewById(R.id.edtProSurname);
        edtEmail = (EditText) findViewById(R.id.edtProEmail);
        edtPhone = (EditText) findViewById(R.id.edtProPhone);
        edtPassword = (EditText) findViewById(R.id.edtProPw);

        Bundle extras = getIntent().getExtras();
        if (extras.get("type").equals("user")) {

            user = (User) getIntent().getSerializableExtra("user");
            edtName.setText(user.getName());
            edtSurname.setText(user.getSurname());
            edtEmail.setText(user.getContact().getEmailAddress());
            edtPhone.setText(user.getContact().getCellPhone());
            edtPassword.setText("*********");

            setTitle(user.getName() + " Profile");

        }
    }
}

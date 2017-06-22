package info.androidhive.firebase.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import info.androidhive.firebase.R;
import info.androidhive.firebase.domain.User;

public class MainMenu extends AppCompatActivity {

    private User user;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        title = (TextView) findViewById(R.id.txtMenuTitle);

        Bundle extras = getIntent().getExtras();
        if (extras.get("type").equals("user")) {
            user = (User) getIntent().getSerializableExtra("user");
            title.setText("Hi " + user.getName() + " " + user.getSurname() + " | " + user.getContact().getCellPhone());
        }

    }

    public void newLoan(View view) {
        Intent viewNewLoan = new Intent(this, MainActivity.class);
        viewNewLoan.putExtra("options","");
        this.startActivity(viewNewLoan);
    }

    public void LoanHistory(View view) {
        Intent viewLoanHistory = new Intent(this, LoanHistory.class);
        viewLoanHistory.putExtra("options","");
        this.startActivity(viewLoanHistory);
    }

    public void userProfile(View view) {
        Intent viewProfile = new Intent(this, Myprofile.class);
        viewProfile.putExtra("user", user);
        viewProfile.putExtra("type", "user");
        this.startActivity(viewProfile);
    }
}

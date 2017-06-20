package info.androidhive.firebase.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import info.androidhive.firebase.R;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
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
        viewProfile.putExtra("options","");
        this.startActivity(viewProfile);
    }
}

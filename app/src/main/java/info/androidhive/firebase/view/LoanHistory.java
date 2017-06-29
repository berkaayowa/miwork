package info.androidhive.firebase.view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.VolleyError;

import org.json.JSONException;

import java.util.ArrayList;

import info.androidhive.firebase.R;
import info.androidhive.firebase.common.adapter.LoanAdapter;
import info.androidhive.firebase.controller.LoanController;
import info.androidhive.firebase.controller.callback.LoanCallBack;
import info.androidhive.firebase.domain.Loan;
import info.androidhive.firebase.domain.User;

public class LoanHistory extends AppCompatActivity {

    private ListView lvLoan;
    private LoanAdapter loanAdapter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loanhistory);
        setTitle("Loan History");

        lvLoan = (ListView) findViewById(R.id.lvLoan);

        Bundle extras = getIntent().getExtras();

        if (extras.get("type").equals("user")) {
            user = (User) getIntent().getSerializableExtra("user");
            displayLoansHistory(this);
        }

    }

    public void displayLoansHistory(final Activity activity) {

        LoanController loanController = new LoanController(this);

        loanController.getLoans(user.getUserKey(), user.getContact().getEmailAddress(), new LoanCallBack() {
            @Override
            public void onSuccess(ArrayList<Loan> loans) {
                loanAdapter = new LoanAdapter(activity, loans);
                lvLoan.setAdapter(loanAdapter);
                Log.e("OK:::", "Ff");
            }

            @Override
            public void onJsonError(JSONException e) {
                Log.e("JSON:::", "Ff");
            }

            @Override
            public void onConnectingError(VolleyError error) {
                Log.e("CON:::", "Ff");
            }
        });
    }
}

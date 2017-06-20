package info.androidhive.firebase.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import info.androidhive.firebase.R;
import info.androidhive.firebase.common.adapter.LoanAdapter;
import info.androidhive.firebase.domain.Loan;

public class LoanHistory extends AppCompatActivity {

    private ListView lvLoan;
    private LoanAdapter loanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loanhistory);

        lvLoan = (ListView) findViewById(R.id.lvLoan);
        loanAdapter = new LoanAdapter(this, getLoans());
        lvLoan.setAdapter(loanAdapter);

        setTitle("Loan History");

    }

    public ArrayList<Loan> getLoans() {

        ArrayList<Loan> loans = new ArrayList<>();

        loans.add(new Loan(200.0, "10/10/2014", "received"));
        loans.add(new Loan(200.0, "10/20/2014", "received"));
        loans.add(new Loan(200.0, "10/20/2014", "received"));
        loans.add(new Loan(200.0, "10/20/2014", "received"));
        loans.add(new Loan(200.0, "10/20/2014", "received"));
        loans.add(new Loan(200.0, "10/20/2014", "received"));
        loans.add(new Loan(200.0, "10/20/2014", "received"));
        loans.add(new Loan(200.0, "10/20/2014", "received"));
        loans.add(new Loan(200.0, "10/10/2014", "received"));
        loans.add(new Loan(200.0, "10/20/2014", "received"));
        loans.add(new Loan(200.0, "10/20/2014", "received"));
        loans.add(new Loan(200.0, "10/20/2014", "received"));
        loans.add(new Loan(200.0, "10/20/2014", "received"));
        loans.add(new Loan(200.0, "10/20/2014", "received"));
        loans.add(new Loan(200.0, "10/20/2014", "received"));
        loans.add(new Loan(200.0, "10/20/2014", "received"));
        loans.add(new Loan(200.0, "10/10/2014", "received"));
        loans.add(new Loan(200.0, "10/20/2014", "received"));
        loans.add(new Loan(200.0, "10/20/2014", "received"));
        loans.add(new Loan(200.0, "10/20/2014", "received"));
        loans.add(new Loan(200.0, "10/20/2014", "received"));
        loans.add(new Loan(200.0, "10/20/2014", "received"));
        loans.add(new Loan(200.0, "10/20/2014", "received"));
        loans.add(new Loan(200.0, "10/20/2014", "received"));

        return loans;
    }
}

package info.androidhive.firebase.common.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import info.androidhive.firebase.R;
import info.androidhive.firebase.domain.Loan;

/**
 * Created by berka on 6/15/17.
 */

public class LoanAdapter extends ArrayAdapter<Loan> {

    private ArrayList<Loan> loans;
    private final Activity context;

    public LoanAdapter(Activity context, ArrayList<Loan> loans) {
        super(context, R.layout.loanhistory_list_style, loans);
        this.context = context;
        this.loans = loans;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.loanhistory_list_style, null,true);

        TextView txtDate = (TextView) rowView.findViewById(R.id.txtDate);
        TextView txtAmount = (TextView) rowView.findViewById(R.id.txtAmount);
        TextView txtStatus = (TextView) rowView.findViewById(R.id.txtStatus);
        LinearLayout lyLoan = (LinearLayout) rowView.findViewById(R.id.lyLoan);

        txtDate.setText(loans.get(position).getDate());
        txtAmount.setText(String.valueOf(loans.get(position).getLoanAmount()));
        txtStatus.setText(loans.get(position).getStatus());

        if (position % 2 == 0) {
            lyLoan.setBackgroundColor(Color.rgb(220,220,220));
        }

        final Loan currentLoan = getCurrentLoan(position);


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rowView;
    }

    public Loan getCurrentLoan(int position) {
        return loans.get(position);
    }

}
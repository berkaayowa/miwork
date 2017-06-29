package info.androidhive.firebase.auth.entityparse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import info.androidhive.firebase.domain.Contact;
import info.androidhive.firebase.domain.Loan;
import info.androidhive.firebase.domain.User;
import info.androidhive.firebase.factory.Factory;

/**
 * Created by berka on 6/16/17.
 */

public class EntityParse {

    public static User jsonToUser(JSONObject jobObject) throws JSONException {

        JSONArray jsonArray = jobObject.getJSONArray("user");
        User user = null;
        Contact contact ;

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String userId = jsonObject.getString("user_id");
            String userName = jsonObject.getString("user_name");
            String userSurname = jsonObject.getString("user_surname");

            String userEmailAddress = jsonObject.getString("user_email");
            String userCellPhone = jsonObject.getString("user_phone");
            String userPhoneVerified = jsonObject.getString("user_verification");
            String userKey = jsonObject.getString("user_key");

            contact = Factory.createContactObject(userCellPhone, userEmailAddress);
            user = Factory.createUserObject(userId, userName, userSurname);
            user.setContact(contact);
            user.setUserKey(userKey);

            if (userPhoneVerified.equals("1")) {
                user.setPhoneVerified(true);
            } else {
                user.setPhoneVerified(false);
            }
        }

        return user;
    }

    public static ArrayList<Loan> jsonToLoan(JSONObject jobObject) throws JSONException {

        JSONArray jsonArray = jobObject.getJSONArray("loans");
        ArrayList<Loan> loans = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int loanId = Integer.parseInt(jsonObject.getString("loan_id"));
            Double loanAmount = Double.parseDouble(jsonObject.getString("amount"));
            String loanTerm = jsonObject.getString("term_month");

            String loanStatus = jsonObject.getString("status_name");
            String loanDate = jsonObject.getString("loan_date");

            Loan loan = Factory.createLoanObject(loanAmount, loanDate, loanStatus);
            loan.setLoanId(loanId);
            loan.setPaymentTerms(loanTerm);

            loans.add(loan);
        }

        return loans;
    }
}

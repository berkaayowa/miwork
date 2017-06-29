package info.androidhive.firebase.factory;

import info.androidhive.firebase.domain.Contact;
import info.androidhive.firebase.domain.Loan;
import info.androidhive.firebase.domain.User;

/**
 * Created by berka on 6/16/17.
 */

public class Factory {

    public static User createUserObject(String userID, String name, String surname) {
        return new User(userID, name, surname);
    }

    public static Contact createContactObject(String cellPhone, String emailAddress) {
        return new Contact(cellPhone, emailAddress);
    }

    public static Loan createLoanObject(double loanAmount, String date, String status) {
        return new Loan(loanAmount, date, status);
    }
}

package info.androidhive.firebase.domain;

import java.io.Serializable;

/**
 * Created by berka on 6/10/17.
 */

public class Loan implements Serializable{

    private int loanId;
    private String employmentStatus, paymentTerms;
    private double monthlyIncome, loanAmount;
    private String date;
    private String status;

    public Loan(String paymentTerms, double loanAmount) {
        this.paymentTerms = paymentTerms;
        this.loanAmount = loanAmount;
    }

    public Loan(double loanAmount, String date, String status) {
        this.loanAmount = loanAmount;
        this.date = date;
        this.status = status;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }
}

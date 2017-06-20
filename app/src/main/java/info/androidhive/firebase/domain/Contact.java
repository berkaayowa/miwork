package info.androidhive.firebase.domain;

/**
 * Created by berka on 6/10/17.
 */

public class Contact {

    private int contactId;
    private String cellPhone, emailAddress;

    public Contact(String cellPhone, String emailAddress) {
        this.cellPhone = cellPhone;
        this.emailAddress = emailAddress;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}

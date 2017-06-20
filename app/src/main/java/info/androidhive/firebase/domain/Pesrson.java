package info.androidhive.firebase.domain;

/**
 * Created by berka on 6/10/17.
 */

public abstract class Pesrson {

    private String name, surname;
    private Contact contact;

    public Pesrson(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}

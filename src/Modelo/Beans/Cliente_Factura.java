package Modelo.Beans;

import javafx.beans.property.StringProperty;

/**
 * Created by Casa on 24/01/2015.
 */
public class Cliente_Factura {
    public Cliente_Factura(StringProperty name, StringProperty address, StringProperty id) {
        this.name = name;
        this.address = address;
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getContact() {
        return contact.get();
    }

    public StringProperty contactProperty() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact.set(contact);
    }

    private StringProperty name;
    private StringProperty address;
    private StringProperty id;
    private StringProperty contact;
}

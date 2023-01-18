package Model;

import java.util.ArrayList;

public class InvoiceHeader {
    private String id;
    private String date;
    private String customer;
    private String total;

    public InvoiceHeader(String id, String date, String customer, String total) {
        this.id = id;
        this.date = date;
        this.customer = customer;
        this.total = total;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getCustomer() {
        return customer;
    }

    public String getTotal() {
        return total;
    }

    public String[] getModelRow() {
        String [] res = {id, date, customer, total};
        return res;
    }
}

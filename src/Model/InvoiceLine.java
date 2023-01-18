package Model;

public class InvoiceLine {
    private String id;
    private String price;
    private String itemName;
    private String quantity;
    private String subTotal;

    public InvoiceLine(String id, String price, String itemName, String quantity, String subTotal) {
        this.id = id;
        this.price = price;
        this.itemName = itemName;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public String getItemName() {
        return itemName;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public String[] getModelRow() {
        String [] res = {id, price, itemName, quantity, subTotal};
        return res;
    }
}

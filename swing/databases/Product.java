package dbeshop;

public class Product {

    private String title;
    private String price;
    private String description;
    private String weight;
    private int qty;

    public Product() {
    }

    public Product(String title, String price, String description, String weight) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.weight = weight;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getWeight() {
        return weight;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

}

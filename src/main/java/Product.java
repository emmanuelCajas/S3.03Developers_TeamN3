import org.bson.Document;

public class Product {

    private Integer id;
    private String type;
    private String name;
    private double price;
    private Integer quantity;
    private String feature;

    public Product() {
    }

    public Product(Integer id, String type, String name, double price, Integer quantity, String feature) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.feature = feature;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", feature='" + feature + '\'' +
                '}';
    }
}

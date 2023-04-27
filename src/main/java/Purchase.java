public class Purchase {

    private Product product;
    private Integer quantity;

    public Purchase(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {

        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {

        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        Double total;
        total = product.getPrice()*this.quantity;

        return total;
    }


    @Override
    public String toString() {
        return "Purchase{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}

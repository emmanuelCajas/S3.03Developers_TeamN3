import java.util.*;

public class Store {
    private String name;
    private double totalStockValue;
    private ArrayList<Product> products;
    private ArrayList<Ticket> tickets;

    public Store(String nombre) {
        this.name = nombre;
        this.totalStockValue = 0;
        this.products = new ArrayList<>();
        this.tickets = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalStockValue() {
        return totalStockValue;
    }

    public void setTotalStockValue(double totalStockValue) {
        this.totalStockValue = totalStockValue;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }
}

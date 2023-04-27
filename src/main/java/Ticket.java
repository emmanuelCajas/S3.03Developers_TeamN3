import java.util.Date;
import java.util.List;

public class Ticket {

    private int id;
    private List<Purchase> purchases;
    private double totalTicketAmount;
    private Date date;

    public Ticket() {
    }

    public Ticket(int id, List<Purchase> purchases, double totalTicketAmount, Date date) {
        this.id = id;
        this.purchases = purchases;
        this.totalTicketAmount = totalTicketAmount;
        this.date = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setProducts(List<Purchase> purchases) {
        this.purchases = purchases;
    }
    public double getTotalTicketAmount() {
        return totalTicketAmount;
    }

    public void setTotalTicketAmount(double totalTicketAmount) {
        this.totalTicketAmount = totalTicketAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", purchases=" + purchases +
                ", totalTicketAmount=" + totalTicketAmount +
                ", date=" + date +
                '}';
    }
}

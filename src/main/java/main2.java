import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.Date;
import java.util.List;

import com.mongodb.client.model.Filters;

public class main2 {

    static MongoDBConexion db = new MongoDBConexion();

    public static void main(String[] args) {

        Menu.menu();
        /*
        //MOSTRAR LISTA DE PRODUCTOS
        showCollections("Product");
        showTicket();


        //AGREGAR PRODUCTOS
        Product product = new Product(5,"decoration","jor",3.4,45,"plastic");
        addProduct(product);

        //ELIMINAR PRODUCTO
        removeProduct(5);


        //AGREGAR PURCHASE A UNA LISTA --> PARA AGREGAR LA LISTA A TICKET

        Double totalAmount;
        List<Purchase> purchases = new ArrayList<>();
        purchases.add(new Purchase(getProductById(1),3));
        purchases.add(new Purchase(getProductById(2),2));
        purchases.add(new Purchase(getProductById(3),1));

        totalAmount = purchases.stream().mapToDouble(s->s.getTotal()).sum();

        //CREANDO TICKET
        Ticket ticket = new Ticket(1,purchases,totalAmount,new Date());

        //CONVIRTIENDO LISTA<PURCHASE> A LISTA<DOCUMENT> PARA AGREGAR EL NUEVO DOCUMENTO AL COLLECTION "TICKET"
        ArrayList<Document> sales =new ArrayList<>();
        for (int i=0;i<purchases.size();i++){
            sales.add(i,new  Document("productId",purchases.get(i).getProduct().getId())
                    .append("name",purchases.get(i).getProduct().getName())
                    .append("price",purchases.get(i).getProduct().getPrice())
                    .append("quantity",purchases.get(i).getQuantity()));
        }
        System.out.println(sales.toString());

        //AGREGANDO TICKET
        addTicket(ticket,sales);
    */


    }


    public static void showTicket() {

        MongoCollection<Document> collection = db.getDatabase().getCollection("Ticket");

        FindIterable<Document> iterable = collection.find();

        System.out.println("");
        System.out.println("LISTA DE TICKETS");
        System.out.println("");

        for (Document doc : iterable) {
            int ticketID= doc.getInteger("ticketID");
            doc = collection.find(Filters.eq("ticketID", ticketID)).first();
            //List<Object> products = doc.getList("product", );
            Double totalAmount = doc.getDouble("totalAmount");
            Date date = doc.getDate("date");

            //IMPRIMIENDO DATOS DE TICKET
            System.out.println("   id:" + ticketID);
            System.out.println("   Date " + date);
            doc.getList("product", Document.class).forEach((product) -> {
                System.out.println("     productId: " + product.getInteger("productId")
                        + ", name: " + product.getString("name")
                        + ", price: " + product.getDouble("price")
                        + ", quantity: " + product.getInteger("quantity"));
            });
            System.out.println("   totalAmount:" + totalAmount);
            System.out.println("_________________________________________________________");


        }
    }

    public static void showTicketsbyId(Integer id){
        String cadena="";
        MongoCollection<Document> collection = db.getDatabase().getCollection("Ticket");

        Document doc = collection.find(Filters.eq("ticketID", id)).first();
        if (doc != null) {

            System.out.println("ticketID: " + doc.getInteger("ticketID")
                    + ", totalAmount: " + doc.getDouble("totalAmount")
                    + ", date: " + doc.getDate("date"));

            doc.getList("product", Document.class).forEach((product) -> {


                System.out.println("productId: " + product.getInteger("productId")
                        + ", name: " + product.getString("name")
                        + ", price: " + product.getDouble("price")
                        + ", quantity: " + product.getInteger("quantity"));

            });
        }

    }

    public static void removeProduct(Integer id) {
        MongoCollection<Document> collection = db.getDatabase().getCollection("Product");
        Document findDocument = new Document("productId", id);
        collection.findOneAndDelete(findDocument);
    }

    public static void addTicket(Ticket ticket, List<Document> sales) {

        MongoCollection<Document> collection = db.getDatabase().getCollection("Ticket");
        Document doc = new Document("ticketID", ticket.getId())
                .append("product",sales)
                .append("totalAmount", ticket.getTotalTicketAmount())
                .append("date",ticket.getDate());

        collection.insertOne(doc);
    }

    public static void addProduct(Product product) {

        MongoCollection<Document> collection = db.getDatabase().getCollection("Product");
        Document doc = new Document("productId", product.getId())
                .append("name", product.getName())
                .append("type", product.getType())
                .append("price", product.getPrice())
                .append("quantity", product.getQuantity())
                .append("feature", product.getFeature());

        collection.insertOne(doc);
    }

    public static void showCollections(String collectionName) {

        MongoCollection<Document> collection = db.getDatabase().getCollection(collectionName);

        FindIterable<Document> iterable = collection.find();

        for (Document doc : iterable) {
            int productId= doc.getInteger("productId");
            String name = doc.getString("name");
            Double price = doc.getDouble("price");
            Integer quantity = doc.getInteger("quantity");
            System.out.println("id:" + productId + " name:" + name + " price:" + price + " quantity " + quantity);
        }
    }

    public static Document getDocumentByProductId(Integer id) {

            MongoCollection<Document> collection = db.getDatabase().getCollection("Product");
            Document filter = new Document("productId", id);
            Document product = collection.find(filter).first();
            return product;
    }

    public static Product getProductById(Integer id) {

        Document documentProduct = getDocumentByProductId(id);

        Integer productId = documentProduct.getInteger("productId");
        String type = documentProduct.getString("type");
        String nameProduct = documentProduct.getString("name");
        Double price = documentProduct.getDouble("price");
        Integer quantity = documentProduct.getInteger("quantity");
        String feature = documentProduct.getString("feature");

        Product product = new Product(productId,type,nameProduct,price,quantity,feature);
        return product;
    }

    public static void showTotalProfit(){

        MongoCollection<Document> collection = db.getDatabase().getCollection("Ticket");

        double totalProfit=0;

        FindIterable<Document> iterable = collection.find();
        for (Document doc : iterable) {
            Double totalAmount = doc.getDouble("totalAmount");
            totalProfit += totalAmount;
        }
        System.out.println("el precio total de las ventas es: " +"€"+ totalProfit + " euros");
    }

    public static void showTotalValue() {

        MongoCollection<Document> collection = db.getDatabase().getCollection("Product");
        FindIterable<Document> iterable = collection.find();

        double totalValue = 0;
        for (Document doc : iterable) {
            Double price = doc.getDouble("price");
            Integer quantity = doc.getInteger("quantity");
            totalValue += (price*quantity);
        }

        System.out.println("the total value of the florist :" + "€" + totalValue + " euros");
    }

    public static void upDateQuantityProductById(Integer id) {

        Document documentProduct = getDocumentByProductId(id);

        Integer quantity = documentProduct.getInteger("quantity");

    }

}

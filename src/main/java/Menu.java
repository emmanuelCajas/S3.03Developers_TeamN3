import org.bson.Document;

import java.util.*;

public class Menu {
    public static Scanner sc = new Scanner(System.in);
    public static Store store;

    static {

    }
    public static void menu() {
        boolean continueExecution=true;
        int option;
        while(continueExecution) {
            menuHeader();
            option = Integer.parseInt(sc.nextLine());
            switch (option) {
                case 1: {
                    menuAddProduct();
                    break;
                }
                case 2: {
                    menuProductRemove();
                    break;
                }
                case 3: {
                    printAllProduct();
                    break;
                }
                case 4: {
                    menuAddTicket();
                    break;
                }
                case 5: {
                    printAllTicket();
                    break;
                }
                case 6: {
                    printTotalProfit();
                    break;
                }
                case 7: {
                    printTotalValue();
                    break;
                }
                case 0: {
                    continueExecution=false;
                    sc.close();
                }
            }
        }
    }
    public static void menuHeader() {

        System.out.println("Choose and option: ");
        System.out.println("1: Adding product");
        System.out.println("2: Remove product");
        System.out.println("3: Printing stock");
        System.out.println("4: Adding Ticket");
        System.out.println("5: Printing total Tickets");
        System.out.println("6: Show Total Profit");
        System.out.println("7: Show florist total value");

    }

    public static void menuAddProduct() {
        System.out.println("Type product id");
        Integer id=Integer.parseInt(sc.nextLine());
        System.out.println("Type  product type");
        String type=sc.nextLine();
        System.out.println("Type product name");
        String name=sc.nextLine();
        System.out.println("Type  product price");
        Double price=Double.parseDouble(sc.nextLine());
        System.out.println("Type product quantity");
        Integer quantity=Integer.parseInt(sc.nextLine());
        System.out.println("Type product feature: flower(colour), tree(height), decoration(material)");
        String feature=sc.nextLine();

        //AGREGAR PRODUCTO
        Product product = new Product(id,type,name,price,quantity,feature);
        main2.addProduct(product);
    }

    public static void menuAddTicket() {
        System.out.println("Type ticket id");
        Integer id=Integer.parseInt(sc.nextLine());
        System.out.println(" Type products ");
        Double totalAmount;
        String end = "Y";

        List<Purchase> purchases = new ArrayList<>();
        while (end.equalsIgnoreCase("Y")){
            System.out.println("Type products id");
            Integer productId=Integer.parseInt(sc.nextLine());
            System.out.println(" Type products quantity");
            Integer quantity=Integer.parseInt(sc.nextLine());
            purchases.add(new Purchase(main2.getProductById(productId),quantity));
            System.out.println(" add next product? (Y/N)");
            end=sc.nextLine();


        }



        totalAmount = purchases.stream().mapToDouble(s->s.getTotal()).sum();

        //CREANDO TICKET
        Ticket ticket = new Ticket(id,purchases,totalAmount,new Date());



        //CONVIRTIENDO LISTA<PURCHASE> A LISTA<DOCUMENT> PARA AGREGAR EL NUEVO DOCUMENTO AL COLLECTION "TICKET"
        ArrayList<Document> sales =new ArrayList<>();
        for (int i=0;i<purchases.size();i++){
            sales.add(i,new  Document("productId",purchases.get(i).getProduct().getId())
                    .append("name",purchases.get(i).getProduct().getName())
                    .append("price",purchases.get(i).getProduct().getPrice())
                    .append("quantity",purchases.get(i).getQuantity()));
        }

        //AGREGANDO TICKET
        main2.addTicket(ticket,sales);
        System.out.println("Added ticket...");

    }

    public static void menuProductRemove() {
        System.out.println("Which product do you want to remove? Type its id");
        Integer id=Integer.parseInt(sc.nextLine());
        main2.removeProduct(id);
    }

    public static void printAllProduct(){
        main2.showCollections("Product");
    }
    public static void printAllTicket(){
        main2.showTicket();
    }
    public static  void printTotalProfit(){main2.showTotalProfit();}
    public static  void printTotalValue(){main2.showTotalValue();}

}

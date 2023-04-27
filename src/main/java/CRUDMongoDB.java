import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import org.bson.Document;

import java.util.ArrayList;

public class CRUDMongoDB {

    public static void main(String[] args) {

        MongoClient mongo = crearConexion();

        // SI NO EXISTE LA BASE DE DATOS LA CREAMOS

        if (mongo != null) {
            DB db = mongo.getDB("FlowerWorld");

            //addFlower(db, "Flower",4,"tulip",2.3,4,"yelllow");
            //addFlower(db, "Flower",5,"dalia",2.5,20,"blue");

            //addTree(db, "Tree",4,"ash",2.3,4,2.4);
            //addTree(db, "Tree",5,"maple",2.5,20,3.4);

            //addDecorations(db,"Decorations",3,"jor",2.3,4,"plastic");
            // addTicket(db, "Ticket",4,2.3,"02-03-2023");
            /*
            ArrayList<Document> adderesses =new ArrayList<>();
            adderesses.add(new Document("FlowerId",1).append("quantity",2));
            adderesses.add(new Document("treeId",1).append("quantity",1)
            );
            */

            showCollections(db, "Flower");
            showCollections(db, "Tree");
            showCollections(db, "Decorations");
            showTicket(db, "Ticket");

            //removeDocument(db,"Tree","oak");

        }
    }

    public static MongoClient crearConexion() {
        System.out.println("PRUEBA CONEXION MONGODB");

        MongoClient mongo = null;

        mongo = new MongoClient("localhost", 27017);

        return mongo;
    }

    public static void addFlower(DB db, String collection, int flowerId,String name, double price ,int quantity, String colour ) {
        DBCollection colec = db.getCollection(collection);

        // CREA EL DOCUMENTO(REGISTRO) E INSERTA LA INFORMACION RECIBIDA
        BasicDBObject document = new BasicDBObject();
        document.put("flowerId", flowerId);
        document.put("name", name);
        document.put("price", price);
        document.put("quantity", quantity);
        document.put("colour", colour);

        colec.insert(document);

    }

    public static void addTree(DB db, String collection, int treeId,String name, double price ,int quantity, double height ) {
        DBCollection colec = db.getCollection(collection);

        // CREA EL DOCUMENTO(REGISTRO) E INSERTA LA INFORMACION RECIBIDA
        BasicDBObject document = new BasicDBObject();
        document.put("treeId", treeId);
        document.put("name", name);
        document.put("price", price);
        document.put("quantity", quantity);
        document.put("height", height);

        colec.insert(document);

    }

    public static void addTicket(DB db, String collection, int ticketID ,double totalAmount, String date,
                                 ArrayList<Document> adderesses) {
        DBCollection colec = db.getCollection(collection);

        // CREA EL DOCUMENTO(REGISTRO) E INSERTA LA INFORMACION RECIBIDA
        BasicDBObject document = new BasicDBObject();
        document.put("ticketID", ticketID);
        document.put("product", adderesses);
        document.put("date", date);

        colec.insert(document);
    }

    public static void addDecorations(DB db, String collection,int decorationId, String name, double price ,int quantity,
                                      String material ) {
        DBCollection colec = db.getCollection(collection);

        // CREA EL DOCUMENTO(REGISTRO) E INSERTA LA INFORMACION RECIBIDA
        BasicDBObject document = new BasicDBObject();
        document.put("decorationId", decorationId);
        document.put("name", name);
        document.put("price", price);
        document.put("quantity", quantity);
        document.put("material", material);

        colec.insert(document);

    }

    // METODO PARA ELIMINAR UN DOCUMENTO (REGISTRO)
    public static void removeDocument(DB db, String collection, String name) {
        DBCollection colec = db.getCollection(collection);

        colec.remove(new BasicDBObject().append("name", name));
    }


    public static void showCollections(DB db, String collection) {
        DBCollection colec = db.getCollection(collection);

        DBCursor cursor = colec.find();

        while(cursor.hasNext()) {
            System.out.println(collection+" --> "+ cursor.next().get("name") + " - " + cursor.curr().get("quantity"));
        }
    }

    public static void showTicket(DB db, String collection) {
        DBCollection colec = db.getCollection(collection);

        DBCursor cursor = colec.find();

        while(cursor.hasNext()) {
            System.out.println(collection+" --> "+ cursor.next().get("ticketID") + " - " + cursor.curr().get("product"));
        }
    }

    // MUESTRA TODOS LOS DOCUMENTOS DE LA COLECCION USUARIOS QUE COINCIDAN CON EL NOMBRE
    public static void buscarPorNombre(DB db, String collection, String nombre) {
        DBCollection colect = db.getCollection(collection);

        // CREAMOS LA CONSULTA CON EL CAMPO NOMBRE
        BasicDBObject consulta = new BasicDBObject();
        consulta.put("nombre", nombre);

        // BUSCA Y MUESTRA TODOS LOS DOCUMENTOS QUE COINCIDAN CON LA CONSULTA
        DBCursor cursor = colect.find(consulta);
        while(cursor.hasNext()) {

            System.out.println("-- " + cursor.next().get("nombre") + " - " + cursor.curr().get("pais"));
        }
    }
}

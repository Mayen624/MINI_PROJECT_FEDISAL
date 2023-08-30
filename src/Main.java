import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        //Create an inventory simulating a db using arrays.
        //Show the items with a table format.
        Scanner sc = new Scanner(System.in);

        System.out.println("Escoja la acción a realizar:");
        System.out.println("1. Añadir articulo");
        System.out.println("2. Mostrar articulos");
        System.out.println("3. Editar articulo");
        System.out.println("4. Eliminar articulo.");
        int option = sc.nextInt();

        ArrayList<Items> itemsList = null;
        switch (option) {
            case 1:

                itemsList = new ArrayList<Items>();

                String input;

                do {

                    System.out.println("¿Desea añadir un nuevo articulo? (S/N)");
                    input = sc.next();

                    if (input.equals("S")) {

                        try {
                            System.out.println("Ingrese el nombre del articulo - (valor texto)");
                            String name = sc.next();
                            System.out.println("Ingrese el proveedor - (valor texto)");
                            String provider = sc.next();
                            System.out.println("Ingrese la cantidad de existencias - (valor numerico)");
                            int quantity = sc.nextInt();
                            System.out.println("Ingrese el precio del articulo - (valor decimal)");
                            double price = sc.nextDouble();

                            Items newItem = new Items(name, provider, quantity, price);
                            newItem.addItem(itemsList);

                            saveToFile(itemsList);

                        } catch (Exception e) {
                            System.out.println("Ha ocurrido un error inesperado, asegurate de ingresar bien los datos. Intentalo de nuevo.");
                            System.out.println("FIN DEL PROGRAMA.");
                        }
                    } else {
                        System.out.println("FIN DEL PROGRAMA.");
                    }
                } while (input.equals("S"));
                sc.close();
            break;
            case 2 :

                ArrayList<Items> savedItems = readFromFile();
                System.out.println("Lista de artículos guardados:");
                for (Items item : savedItems) {
                    System.out.println("----------------------------------");
                    System.out.println("Nombre: " + item.getName());
                    System.out.println("Proveedor: " + item.getProvider());
                    System.out.println("Cantidad: " + item.getQuantity());
                    System.out.println("Precio: " + item.getPrice());
                    System.out.println("----------------------------------");
                }
            break;
            default:
                System.out.println("FIN DEL PROGRAMA.");
            break;
        }
    }

    private static void saveToFile(ArrayList<Items> itemList){
        ArrayList<Items> existingItems = readFromFile(); // Cargar datos existentes

        existingItems.addAll(itemList); // Agregar nuevos datos

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("items.dat"))) {
            outputStream.writeObject(existingItems); // Escribir todos los datos
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Items> readFromFile(){
        ArrayList<Items> savedItems = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("items.dat"))) {
            savedItems = (ArrayList<Items>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return savedItems;
    }
}
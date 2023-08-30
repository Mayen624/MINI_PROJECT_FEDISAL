import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
public class Items implements Serializable {

    private String name;
    private String provider;
    private int quantity;
    private double price;

    //Constructor
    public Items(String name, String provider, int quantity, double price){
        this.name = name;
        this.provider = provider;
        this.quantity = quantity;
        this.price = price;
    }

    //Getters
    public String getName(){
        return name;
    }
    public String getProvider(){
        return provider;
    }
    public int getQuantity(){
        return quantity;
    }
    public double getPrice(){
        return price;
    }

    //Setters

    public void setName(String name){
        this.name = name;
    }
    public void setProvider(String provider){
        this.provider = provider;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public void setPrice(double price){
        this.price = price;
    }


    public void addItem(ArrayList<Items> list){
        list.add(this);
    }
}

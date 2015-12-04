package Lesson4.entities;

/**
 * Created by Rapid Blaze on 09.10.2015.
 */
public class Order {
    int id;
    int customerId;
    int salesPersonId;


    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setCustomerId(int id){
        this.customerId = id;
    }

    public int getCustomerId(){
        return customerId;
    }

    public void setSalesPersonId(int id){
        this.salesPersonId = id;
    }

    public int getSalesPersonId(){
        return salesPersonId;
    }

    @Override
    public String toString(){
        return id + " " + customerId + " " + salesPersonId;
    }
}

package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {
        Integer HH=Integer.parseInt(deliveryTime.substring(0,2));
        Integer MM=Integer.parseInt(deliveryTime.substring(3));
        this.deliveryTime=HH*60+MM;
        this.id=id;

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
    public String toString(){
        return "[ id"+id+" -> DeleveryTime"+deliveryTime+"]";
    }

}

package com.driver;

import io.swagger.models.auth.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;

@org.springframework.stereotype.Repository
public class Repository {
    HashMap<String, List<Order>> OrderHashMap=new HashMap<>();
    HashMap<String, List<DeliveryPartner>> PartnerMap=new HashMap<>();
    HashMap<String,List<Order>> OrderPartnerPair=new HashMap<>();
    public void addOrder(Order order){
        String primaryKey=order.getId();
        List<Order> currOrder=OrderHashMap.getOrDefault(primaryKey,new ArrayList<>());
        currOrder.add(order);
        OrderHashMap.put(primaryKey,currOrder);

    }
    public void addPartner(String partnerId){
        DeliveryPartner newPartner=new DeliveryPartner(partnerId);
        String primaryKey = newPartner.getId();
        List<DeliveryPartner> currPartner=PartnerMap.getOrDefault(primaryKey,new ArrayList<>());
        currPartner.add(newPartner);
        PartnerMap.put(primaryKey,currPartner);

    }
    public void addOrderPartnerPair(String orderId,String partnerId){
        List<Order> order=OrderHashMap.getOrDefault(orderId,new ArrayList<>());
        OrderPartnerPair.put(partnerId,order);

    }
    public List<Order> getOrderById(String orderId){
        List<Order> currOrder=OrderHashMap.getOrDefault(orderId,new ArrayList<>());
        return currOrder;

    }
    public List<DeliveryPartner> getPartnerById(String partnerId){
        List<DeliveryPartner> currPartner=PartnerMap.getOrDefault(partnerId,new ArrayList<>());
        return currPartner;

    }
    public List<DeliveryPartner> getOrderCountByPartnerId(String partnerId){
        List<DeliveryPartner> OrderCountByPartnerId=PartnerMap.getOrDefault(partnerId,new ArrayList<>());
        return OrderCountByPartnerId;

    }
    public List<Order> getOrdersByPartnerId(String partnerId){
        return OrderPartnerPair.getOrDefault(partnerId,new ArrayList<>());

    }
    public List<String> getAllOrders(){
        List<String> allOrder=new ArrayList<>();
        for(Map.Entry m : OrderHashMap.entrySet()){
             allOrder.add(m.getValue().toString());
        }
        return allOrder;

    }
    public Integer getCountOfUnassignedOrders(){
        List<String> allOrder=new ArrayList<>();
        for(Map.Entry m : OrderHashMap.entrySet()){
            allOrder.add(m.getValue().toString());
        }
        Integer allOrders=allOrder.size();


        List<String> partnerOrder=new ArrayList<>();
        for(Map.Entry m : OrderPartnerPair.entrySet()){
            allOrder.add(m.getValue().toString());
        }
        Integer partnerAllOrders=partnerOrder.size();


        Integer unassignedOrder = allOrders - partnerAllOrders;
        return unassignedOrder;

    }
    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time,String partnerId){
        List<Order> currDelPartnerOrder=OrderPartnerPair.getOrDefault(partnerId,new ArrayList<>());
        Integer HH=Integer.parseInt(time.substring(0,2));
        Integer MM=Integer.parseInt((time.substring(3)));

        Integer limitTime=HH*60+MM;

        Integer count=0;
        for(Order currOrder : currDelPartnerOrder){
            if(currOrder.getDeliveryTime() > limitTime){
                count++;
            }
        }
        return count;

    }
    public String getLastDeliveryTimeByPartnerId(String partnerId){
        List<Order> currDelPartnerOrder=OrderPartnerPair.getOrDefault(partnerId,new ArrayList<>());
        List<Integer> time=new ArrayList<>();
        for(Order currOrder : currDelPartnerOrder){
            time.add(currOrder.getDeliveryTime());
        }
        Integer lastDeliveryTime=time.get(time.size()-1);
        String lastDelivery=String.valueOf(lastDeliveryTime);
        return lastDelivery;

    }
    public void deletePartnerById(String partnerId){
        OrderPartnerPair.remove(partnerId);

    }
    public void deleteOrderById(String orderId){
        OrderHashMap.remove(orderId);

    }
}

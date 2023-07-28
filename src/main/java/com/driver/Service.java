package com.driver;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private Repository repositoryObj;
    public void addOrder(Order order){
         repositoryObj.addOrder(order);

    }
    public void addPartner(String partnerId){
         repositoryObj.addPartner(partnerId);

    }
    public void addOrderPartnerPair(String orderId,String partnerId){
         repositoryObj.addOrderPartnerPair(orderId,partnerId);

    }
    public Order getOrderById(String orderId){
        List<Order>currOrder = repositoryObj.getOrderById(orderId);
        //Every OrderId is Unique
        Order NewOrder=null;
        for(Order OID : currOrder){
            NewOrder=OID;
        }
        return NewOrder;

    }
    public DeliveryPartner getPartnerById(String partnerId){
        List<DeliveryPartner>currPartner = repositoryObj.getPartnerById(partnerId);
        DeliveryPartner NewDeliveryPartner=null;
        for(DeliveryPartner DID : currPartner){
            NewDeliveryPartner=DID;
        }
        return NewDeliveryPartner;

    }
    public Integer getOrderCountByPartnerId(String partnerId){
        List<DeliveryPartner> currOrderByPartnerId = repositoryObj.getOrderCountByPartnerId(partnerId);
        DeliveryPartner NewcurrOrderByPartnerId=null;
        for(DeliveryPartner COBPI : currOrderByPartnerId){
            NewcurrOrderByPartnerId=COBPI;
        }
        return NewcurrOrderByPartnerId.getNumberOfOrders();

    }
    public List<String > getOrdersByPartnerId(String partnerId){
        List<String > GOBPI=new ArrayList<>();
        List<Order> currOrder = repositoryObj.getOrdersByPartnerId(partnerId);
        for(Order CO : currOrder){
            GOBPI.add(CO.toString());
        }
        return GOBPI;

    }
    public List<String > getAllOrders(){
        return repositoryObj.getAllOrders();

    }
    public Integer getCountOfUnassignedOrders(){
        return repositoryObj.getCountOfUnassignedOrders();

    }
    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time,String partnerId){
        return repositoryObj.getOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);

    }
    public String getLastDeliveryTimeByPartnerId(String partnerId){
        return repositoryObj.getLastDeliveryTimeByPartnerId(partnerId);

    }
    public void deletePartnerById(String partnerId){
        repositoryObj.deletePartnerById(partnerId);

    }
    public void deleteOrderById(String orderId){
        repositoryObj.deleteOrderById(orderId);

    }
}

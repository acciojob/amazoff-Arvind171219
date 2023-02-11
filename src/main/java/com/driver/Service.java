package com.driver;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@org.springframework.stereotype.Service

public class Service {


    @Autowired
    Repository repository;//=new Repository();

    public void addOrder(Order order)
    {
        repository.addOrder(order);
    }

    public  void addPatner(String patnerid)
    {
        repository.addPatner(patnerid);
    }

    public Order getOrderById(String orderId)
    {
        return  repository.getOrderById(orderId);
    }

    public  DeliveryPartner getPartnerById(String id)
    {
        return repository.getPartnerById(id);
    }

    public Integer getOrderCountByPartnerId(String id)
    {
        return repository.getOrderCountByPartnerId(id);
    }

    public void deleteOrderById(String id)
    {
        repository.deleteOrderById(id);
    }

    public void deletePartnerById(String id)
    {
        repository.deletePartnerById(id);
    }

    public void  addOrderPartnerPair(String orderid, String patnerId) {repository.addOrderPartnerPair(orderid,patnerId);}

    public List<String> getOrdersByPartnerId(String id)
    {
        return  repository.getOrdersByPartnerId(id);
    }

    public List<String> getAllOrders()
    {
        return repository.getAllOrders();
    }

    public String getLastDeliveryTimeByPartnerId(String id)
    {
        return  repository.getLastDeliveryTimeByPartnerId(id);
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String patnerId)
    {
        return repository.getOrdersLeftAfterGivenTimeByPartnerId(time,patnerId);
    }
    public Integer getCountOfUnassignedOrders()
    {
        return repository.getCountOfUnassignedOrders();
    }

}
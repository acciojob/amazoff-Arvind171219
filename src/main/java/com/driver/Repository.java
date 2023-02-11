package com.driver;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@org.springframework.stereotype.Repository
public class Repository {

    HashMap<String, Order> ordersHashMap=new HashMap<>();

    HashMap<String, Integer> unassignedOrders=new HashMap<>();

    HashMap<String, List<String>> pair=new HashMap<>();

    public  void addOrder(Order order)
    {

        ordersHashMap.put(order.getId(),order);
        unassignedOrders.put(order.getId(),0);
    }

    public void addPatner(String patnerId)
    {
        List<String> list=new ArrayList<>();
        pair.put(patnerId, list);
    }
    public Order getOrderById(String orderId)
    {
        return ordersHashMap.get(orderId);
    }
    public DeliveryPartner getPartnerById(String Id)
    {
        DeliveryPartner deliveryPartner=new DeliveryPartner(Id);
        deliveryPartner.setNumberOfOrders(pair.get(Id).size());
        return  deliveryPartner;
    }
    public Integer getOrderCountByPartnerId(String id)
    {

        return  (Integer) pair.get(id).size();
    }
    public void deleteOrderById(String id)
    {
        ordersHashMap.remove(id);
    }
    public  void deletePartnerById(String id)
    {
        pair.remove(id);
    }
    public void addOrderPartnerPair(String orderId, String patnerId)
    {
        if ( unassignedOrders.get(orderId) == 0) {
            List<String> listOfOrders = pair.get(patnerId);
            listOfOrders.add(orderId);
            pair.replace(patnerId, listOfOrders);

            unassignedOrders.replace(orderId, 1);
        }
    }
    public List<String> getOrdersByPartnerId(String id)
    {
        List<String> ordersOfPatner= pair.get(id);
        return ordersOfPatner;
    }
    public List<String> getAllOrders()
    {
        List<String> orders=new ArrayList<>();
        for(String key: ordersHashMap.keySet())
        {
            orders.add(ordersHashMap.get(key).getId());
        }
        return orders;
    }

    public Integer getCountOfUnassignedOrders()
    {
        int count=0;
        if(!ordersHashMap.isEmpty()) {
            for (String key : ordersHashMap.keySet()) {
                if (unassignedOrders.get(key) == 0) {
                    count++;
                }
            }
        }
        return  (Integer) count;
    }
    public String getLastDeliveryTimeByPartnerId(String patnerId)
    {
        String lastDeliveryTime="";
        //list of all the ordersId than need to be delivered by this patner
        List<String> allOrdersOfPatner=pair.get(patnerId);

        for (int i=0; i<allOrdersOfPatner.size(); i++)
        {
//            log.info()
            Order order=ordersHashMap.get(allOrdersOfPatner.get(i));

            int deliveryTime=order.getDeliveryTime();  //which is in int -> we have to convert in into string;


            int hour=deliveryTime/60;

            int minute=deliveryTime % 60;


            lastDeliveryTime=""+hour+":";
            if (minute < 10)
            {
                lastDeliveryTime=lastDeliveryTime+"0"+minute;
            }
            else
            {
                lastDeliveryTime=lastDeliveryTime+minute;
            }


        }
        return lastDeliveryTime;
    }
    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time,String patnerId)
    {
        int hour=0;
        int minute=0;
        for (int i=0; i<2; i++)
        {
            hour=(hour*10)+ (int)time.charAt(i) -'0';
        }
        for (int i=3; i<5; i++)
        {
            minute=(minute*10)+ (int)time.charAt(i) -'0';
        }
        System.out.println(hour+" "+minute);

        int currentTime=hour*60 + minute;
        System.out.println(currentTime);


        List<String> listOfOrders=pair.get(patnerId);

        int count=0;
        //traverse throung list of orders
        for (int i=0; i<listOfOrders.size(); i++)
        {
            Order order=ordersHashMap.get(listOfOrders.get(i));
            System.out.println(order.getDeliveryTime()+" "+currentTime);
            if (order.getDeliveryTime()> currentTime)
            {
                count++;
            }

        }
        System.out.println(count);
        return  (Integer) count;
    }


}
package com.mycompany.assigmentweb.service;

import com.mycompany.assigmentweb.model.OrderPr;
import java.util.List;

public interface OrderPrService {
    
    List<OrderPr> findAllOrder();
    
    OrderPr finndById(String id);
    
    void saveOrderPr (OrderPr orderPr);
    
    void deleteOrderPr (OrderPr orderPr);
    
    void updateOrderPr(OrderPr orderPr);
    
    List<OrderPr> finfdAllUserOrders(String userName);
    
}

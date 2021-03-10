package com.mycompany.assigmentweb.dao;

import com.mycompany.assigmentweb.model.OrderPr;
import java.util.List;


public interface OrderPrDao {
    
    List<OrderPr> findAllOrder();
    
    OrderPr finndById(String id);
    
    void saveOrderPr (OrderPr orderPr);
    
    void deleteOrderPr (OrderPr orderPr);
    
    void updateOrderPr(OrderPr orderPr);
    
    
}

package com.mycompany.assigmentweb.dao;

import com.mycompany.assigmentweb.model.OrderDetails;
import java.util.List;


public interface OrderDetailsDao {
    
   // List<OrderDetails> findAllOrderDetails();
    
    List<OrderDetails> findOrderDetails(String orderNumber);
    
    void saveOrderDetails (OrderDetails orderDetails);
    
    void deleteOrderDetails(OrderDetails orderDetails);
    
}

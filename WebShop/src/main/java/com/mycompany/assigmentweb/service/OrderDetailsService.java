
package com.mycompany.assigmentweb.service;

import com.mycompany.assigmentweb.model.OrderDetails;
import java.util.List;


public interface OrderDetailsService {
    
  //  List<OrderDetails> findAllOrderDetails();
    
    List<OrderDetails> findOrderDetails(String orderNumber);
    
    void saveOrderDetails (OrderDetails orderDetails);
    
    void deleteOrderDetails(OrderDetails orderDetails);
    
}

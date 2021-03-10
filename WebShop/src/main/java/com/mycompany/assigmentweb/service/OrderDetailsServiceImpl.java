package com.mycompany.assigmentweb.service;

import com.mycompany.assigmentweb.dao.OrderDetailsDao;
import com.mycompany.assigmentweb.model.OrderDetails;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("orderDetailsService")
@Transactional
public class OrderDetailsServiceImpl implements OrderDetailsService{

    @Autowired
    OrderDetailsDao dao;
    
   /* @Override
    public List<OrderDetails> findAllOrderDetails() {
        return dao.findAllOrderDetails();
    }*/

    @Override
    public List<OrderDetails> findOrderDetails(String orderNumber) {
        return dao.findOrderDetails(orderNumber);
    }

    @Override
    public void saveOrderDetails(OrderDetails orderDetails) {
        dao.saveOrderDetails(orderDetails);
    }

    @Override
    public void deleteOrderDetails(OrderDetails orderDetails) {
        dao.deleteOrderDetails(orderDetails);
    }
    
}

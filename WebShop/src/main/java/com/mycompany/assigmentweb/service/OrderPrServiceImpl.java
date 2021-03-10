package com.mycompany.assigmentweb.service;

import com.mycompany.assigmentweb.dao.OrderPrDao;
import com.mycompany.assigmentweb.model.OrderPr;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("orderPrService")
@Transactional
public class OrderPrServiceImpl implements OrderPrService{
    
    @Autowired
    OrderPrDao dao;

    @Override
    public List<OrderPr> findAllOrder() {
        return dao.findAllOrder();
    }

    @Override
    public OrderPr finndById(String id) {
        return dao.finndById(id);
    }

    @Override
    public void saveOrderPr(OrderPr orderPr) {
        dao.saveOrderPr(orderPr);
    }

    @Override
    public void deleteOrderPr(OrderPr orderPr) {
        dao.deleteOrderPr(orderPr);
    }

    @Override
    public void updateOrderPr(OrderPr orderPr) {
       dao.updateOrderPr(orderPr);

    }

    @Override
    public List<OrderPr> finfdAllUserOrders(String userName) {
        
        List<OrderPr> allorders = findAllOrder();
        List<OrderPr> ordersUser = new ArrayList<OrderPr>();
        for (OrderPr order : allorders) {
            if (order.getUser().getUserName().equals(userName)) {
                ordersUser.add(order);
            }
        }
        return ordersUser;
    }
    
}

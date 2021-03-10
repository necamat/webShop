package com.mycompany.assigmentweb.dao;

import com.mycompany.assigmentweb.model.OrderDetails;
import com.mycompany.assigmentweb.model.OrderPr;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("orderDetailsDao")
public class OrderDetailsDaoImpl extends AbstractDao<Integer, OrderDetails> implements OrderDetailsDao{

   
    
 

    @Override
    @SuppressWarnings("unchecked")
    public List<OrderDetails> findOrderDetails(String orderNumber) {
        
        Criteria crti = createEntityCriteria().addOrder(Order.asc("id"));
        crti.createAlias("orderNumber", "orderNumber");
        crti.add(Restrictions.eq("orderNumber.id", orderNumber));
        crti.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// Avoid duplicates.
        List<OrderDetails> orderDetailses = (List<OrderDetails>) crti.list();



        return orderDetailses;
    }

    @Override
    public void saveOrderDetails(OrderDetails orderDetails) {
        persist(orderDetails);
    }

    @Override
    public void deleteOrderDetails(OrderDetails orderDetails) {
        delete(orderDetails);
    }
    
}

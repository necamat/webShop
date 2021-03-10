package com.mycompany.assigmentweb.dao;

import static com.mycompany.assigmentweb.dao.UserDaoImpl.logger;
import com.mycompany.assigmentweb.model.OrderPr;
import com.mycompany.assigmentweb.model.User;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("orderPrDao")
public class OrderPrDaoImpl extends AbstractDao<String, OrderPr> implements OrderPrDao {
    
    final static Logger logger = LoggerFactory.getLogger(OrderPrDaoImpl.class);
    
    @Override
    @SuppressWarnings("unchecked")
    public List<OrderPr> findAllOrder() {
        Criteria crti = createEntityCriteria().addOrder(Order.asc("id"));
        crti.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// Avoid duplicates.
        List<OrderPr> orders = (List<OrderPr>) crti.list();
        // ******* From showing OrderDetails implemnt
          for (OrderPr order : orders) {
            Hibernate.initialize(order.getUser().getUserName());
            Hibernate.initialize(order.getDetailses());
            
        }
        
        return orders;
    }
    
    @Override
    public OrderPr finndById(String id) {
        logger.info("Order number : {} ", id);
        OrderPr orderPr = getByKey(id);
        return orderPr;
    }
    
    @Override
    public void saveOrderPr(OrderPr orderPr) {
        persist(orderPr);
    }
    
    @Override
    public void deleteOrderPr(OrderPr orderPr) {
        delete(orderPr);
    }
    
    @Override
    public void updateOrderPr(OrderPr orderPr) {
        update(orderPr);
    }

    
    
}

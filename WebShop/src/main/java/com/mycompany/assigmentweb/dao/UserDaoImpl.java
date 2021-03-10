package com.mycompany.assigmentweb.dao;

import com.mycompany.assigmentweb.model.User;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao{
    
    final static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Criteria crti = createEntityCriteria().addOrder(Order.asc("firstName"));
        crti.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// Avoid duplicates.
        List<User> users = (List<User>) crti.list();
        // From showing userProfile since
        /*
        for (User user : users) {
            Hibernate.initialize(user.getUserProfiles());
        }*/
        return users;
    }

    @Override
    public User findById(int id) {
        
        User user = getByKey(id);
        if (user != null) {
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
    }

    @Override
    public User findByUserName(String userName) {
       logger.info("User Name : {} ", userName);
       Criteria crti = createEntityCriteria();
       crti.add(Restrictions.eq("userName", userName));
       User user = (User) crti.uniqueResult();
       if (user != null) {
           Hibernate.initialize(user.getUserProfiles());   
           Hibernate.initialize(user.getOrderPrs());
           
        }
       
       return user;
    }

    @Override
    public void save(User user) {
        persist(user);
    }

    @Override
    public void deleteByUserName(String userName) {
        Criteria crti = createEntityCriteria();
        crti.add(Restrictions.eq("userName", userName));
        User user = (User) crti.uniqueResult();
        delete(user);
    }

    
    
}

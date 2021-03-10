package com.mycompany.assigmentweb.dao;

import com.mycompany.assigmentweb.model.UserProfile;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer, UserProfile> implements UserProfileDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<UserProfile> findAll() {
        Criteria crti = createEntityCriteria();
        crti.addOrder(Order.asc("type"));
        return (List<UserProfile>) crti.list();
    }

    @Override
    public UserProfile findByType(String type) {
        Criteria crti = createEntityCriteria();
        crti.add(Restrictions.eq("type", type));
        return (UserProfile) crti.uniqueResult();
    }

    @Override
    public UserProfile findById(int id) {
        return getByKey(id);
    }

}

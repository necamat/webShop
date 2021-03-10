
package com.mycompany.assigmentweb.service;


import com.mycompany.assigmentweb.dao.UserProfileDao;
import com.mycompany.assigmentweb.model.UserProfile;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService{
    
    @Autowired
    UserProfileDao dao;

    @Override
    public List<UserProfile> findAll() {
        return dao.findAll();
    }

    @Override
    public UserProfile findById(int id) {
        return dao.findById(id);
    }

    @Override
    public UserProfile findByType(String type) {
        return  dao.findByType(type);
    }

   
    
}

package com.mycompany.assigmentweb.dao;

import com.mycompany.assigmentweb.model.UserProfile;
import java.util.List;


public interface UserProfileDao {
    
    List<UserProfile> findAll();
    
    UserProfile findByType(String type);
    
    UserProfile findById(int id);
    
}


package com.mycompany.assigmentweb.service;


import com.mycompany.assigmentweb.model.UserProfile;
import java.util.List;


public interface UserProfileService {
    
    List<UserProfile> findAll();
    
    UserProfile findById(int id);
    
    UserProfile findByType(String type);
    
}

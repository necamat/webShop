
package com.mycompany.assigmentweb.dao;

import com.mycompany.assigmentweb.model.User;
import java.util.List;


public interface UserDao {
    
    List<User> findAllUsers();
    
    User findById(int id);
    
    User findByUserName(String userName);
    
    void save(User user);
    
    void deleteByUserName(String userName);
    
  
    
}

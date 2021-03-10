package com.mycompany.assigmentweb.service;

import com.mycompany.assigmentweb.dao.UserDao;
import com.mycompany.assigmentweb.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }

    @Override
    public User findById(int id) {
        User user = dao.findById(id);
        return user;
    }

    @Override
    public User findByUserName(String userName) {
        User user = dao.findByUserName(userName);
        return user;
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
    }

    /*
      * The method is running with Transaction, No need to call hibernate update explicitly.
     * It will be updated in db once transaction ends
     */
    @Override
    public void updateUser(User user) {
       
        User entity = dao.findById(user.getId());
        
        if (entity != null) {
            entity.setUserName(user.getUserName());
            if (!user.getPassword().equals(entity.getPassword())) {
                entity.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            entity.setFirstName(user.getFirstName());
            entity.setLastName(user.getLastName());
            entity.setEmail(user.getEmail());
            entity.setUserProfiles(user.getUserProfiles());
            entity.setState(user.getState());
        }
    }

    @Override
    public void deleteByUserName(String userName) {
        dao.deleteByUserName(userName);
    }

    @Override
    public boolean isUserNameUnique(Integer id, String userName) {
        User user = findByUserName(userName);
        return (user == null || ((id != null) && (user.getId() == id)));
    }

}

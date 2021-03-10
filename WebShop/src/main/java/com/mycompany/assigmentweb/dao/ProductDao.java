package com.mycompany.assigmentweb.dao;

import com.mycompany.assigmentweb.model.Product;
import java.util.List;

 
public interface ProductDao {
    
    List<Product> findAllProducts();
    
    Product finndById(String id);
    
    Product findByName(String name);
    
    void save(Product product);
    
    void deleteById(String id);
    
    void updateProduct (Product product);        
}

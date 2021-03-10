
package com.mycompany.assigmentweb.service;

import com.mycompany.assigmentweb.model.Product;
import java.util.List;

public interface ProductService {
    
    List<Product> findAllProducts();
    
    Product finndById(String id);
    
    Product findByName(String name);
    
    void saveProduct(Product product);
    
    void updateProduct(Product product);
    
    void deleteById(String id);
    

    
}

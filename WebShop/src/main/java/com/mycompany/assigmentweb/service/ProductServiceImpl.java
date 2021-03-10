package com.mycompany.assigmentweb.service;

import com.mycompany.assigmentweb.dao.ProductDao;
import com.mycompany.assigmentweb.model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    ProductDao dao;
    
    @Override
    public List<Product> findAllProducts() {
        return dao.findAllProducts();
    }
    
    @Override
    public Product finndById(String id) {
        return dao.finndById(id);
    }
    
    @Override
    public Product findByName(String name) {
        return dao.findByName(name);
    }
    
    @Override
    public void saveProduct(Product product) {
        dao.save(product);
    }
    
    @Override
    public void deleteById(String id) {
        dao.deleteById(id);
    }
    
    @Override
    public void updateProduct(Product product) {
        Product entity = dao.finndById(product.getId());
        if (entity != null) {
            entity.setName(product.getName());
            entity.setQuantity(product.getQuantity());
            entity.setPrice(product.getPrice());
            entity.setDescription(product.getDescription());
            if (entity.getPhotoName() == null) {
                entity.setPhotoName(product.getPhotoName());
                
            } else {
                entity.setPhotoName(entity.getPhotoName());
            }
            
        }
    }
    
    
    
}

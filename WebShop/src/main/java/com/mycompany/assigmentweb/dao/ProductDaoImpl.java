package com.mycompany.assigmentweb.dao;

import com.mycompany.assigmentweb.model.Product;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDao<String, Product> implements ProductDao{

    final static Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);
    
    @Override
    public List<Product> findAllProducts() {
        
        Criteria crti = createEntityCriteria().addOrder(Order.asc("name"));
        crti.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//Avoid duplicates.
        List<Product> products =(List<Product>) crti.list();
        
        
        return products;
    }

    @Override
    public Product finndById(String id) {
        
        Product product = getByKey(id);
        Hibernate.initialize(product.getDetailses());
        return product;
    }

    @Override
    public Product findByName(String name) {
       logger.info("Product Name : {} ", name);
       Criteria crti = createEntityCriteria();
       crti.add(Restrictions.eq("name", name));
       Product product = (Product) crti.uniqueResult();
       
       return product;
    }

    @Override
    public void save(Product product) {
        persist(product);
    }

    @Override
    public void deleteById(String id) {
        Criteria crti = createEntityCriteria();
        crti.add(Restrictions.eq("id", id));
        Product product = (Product)crti.uniqueResult();
        delete(product);
    }

    @Override
    public void updateProduct(Product product) {
        update(product);
    }

    
    
    
}

package com.mycompany.assigmentweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_DETAILS")
public class OrderDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "QUANTITY", nullable = false)
    private int quantity;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "ORDER_NUM_ID")
    private OrderPr orderNumber;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "PRODUCT_NUM_ID")
    private Product product;
    
    

    public OrderDetails() {
    }

    public OrderDetails(int quantity) {
        this.quantity = quantity;
    }

    public OrderDetails(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public OrderDetails(int quantity, OrderPr orderNumber, Product product) {
        this.quantity = quantity;
        this.orderNumber = orderNumber;
        this.product = product;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderPr getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(OrderPr orderNumber) {
        this.orderNumber = orderNumber;
    }

    

    @Override
    public int hashCode() {

        return 15;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderDetails other = (OrderDetails) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return "OrderDetails{" + "id=" + id + ", orderNumber=" + orderNumber + ", product=" + product + ", quantity=" + quantity +  '}';
    }

   
        
   
   
    
}

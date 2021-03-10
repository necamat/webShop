package com.mycompany.assigmentweb.model;

public class ItemCart {

    private Product product;

    private int quantity;
    
    public ItemCart(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    

    public ItemCart() {

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}

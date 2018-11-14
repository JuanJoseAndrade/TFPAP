/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuse.appweb.shop.services;

import fuse.appweb.shop.model.Order;
import fuse.appweb.shop.model.Product;
import fuse.appweb.shop.model.User;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Juan José Andrade
 */
public interface OrderServices {
    Map<String, User> getAllUsers();
    Set<String> getAvailableProductNames();
    Product getProductByName(String product) throws OrderServicesException;
    int calculateBill(String email) throws OrderServicesException;
    void cleanUserOrder(String email) throws OrderServicesException;
    Map<String, Product> getAllProducts();
    Map<String, Product> getProductsByNameOrType(String busqueda);
    Map<String, Product> getOrderedProducts();
    void addUser(User user) throws OrderServicesException;
    void logIn(String email, String contr) throws OrderServicesException;
    void logOut() throws OrderServicesException;
    User getUser(String email) throws OrderServicesException;
    User getLoggedUser() throws OrderServicesException;
    void addProduct(Product prod) throws OrderServicesException ;
    void setProductQuantity(String name, int quantity) throws OrderServicesException;
    void deleteProduct(String name) throws OrderServicesException;
    void addProductToOrder(String email,Product prod) throws OrderServicesException ;
    void setProductQuantityOfOrder(String email,String name, int quantity) throws OrderServicesException;
    void deleteProductFromOrder(String email, String name) throws OrderServicesException;
    
}

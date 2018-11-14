package fuse.appweb.shop.model;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Order {


    private Map<String, Integer> orderAmountsMap;

    public Map<String, Integer> getOrderAmountsMap() {
        return orderAmountsMap;
    }

    public void setOrderAmountsMap(Map<String, Integer> orderAmountsMap) {
        this.orderAmountsMap = orderAmountsMap;
    }
    private String userEmail;

    public Order() {
    }
   
  
    public Order(String userEmail) {
        orderAmountsMap = new ConcurrentHashMap<>();
        this.userEmail = userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void addProduct(String p, int amount) {
        if (!orderAmountsMap.containsKey(p)){
            orderAmountsMap.put(p, amount);
        }
        else{
            int previousAmount=orderAmountsMap.get(p);
            orderAmountsMap.put(p, previousAmount+amount);
        }
    }

    public Set<String> getProducts() {
        return orderAmountsMap.keySet();
    }

    public int getProductAmount(String p) {
        if (!orderAmountsMap.containsKey(p)) {
            return 0;
        } else {
            return orderAmountsMap.get(p);
        }
    }
    public void deleteProduct(String product){
        orderAmountsMap.remove(product);
    }
    

}

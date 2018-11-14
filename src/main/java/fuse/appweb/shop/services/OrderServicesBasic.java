package fuse.appweb.shop.services;


import fuse.appweb.shop.model.Order;
import fuse.appweb.shop.model.Product;
import fuse.appweb.shop.bill.BillCalculator;
import fuse.appweb.shop.model.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("Basic")
public class OrderServicesBasic implements OrderServices {

    @Autowired
    @Qualifier("WithTaxes")
    BillCalculator calc ;

    public OrderServicesBasic() {
    }

    public void setBillCalculator(BillCalculator calc) {
        this.calc = calc;
    }
    private static final Map<String, Product> productsMap;
    private static final Map<String, User> users;
    private static User loggedUser=null;

    static {
        productsMap = new ConcurrentHashMap<>();
        users = new ConcurrentHashMap<>(); 
        productsMap.put("Omega Men's Seamaster 300M Chronometer", new Product("Omega Men's Seamaster 300M Chronometer", 8870000,"Watch", "Precise automatic swiss Movement, Sapphire crystal; Brushed and polished stainless steel case and bracelet.",10 ));
        productsMap.put("Beats By Dre Solo 2 Luxe Edition", new Product("Beats By Dre Solo 2 Luxe Edition", 380000, "Headphone", "On-Ear Headphones | Black (WIRED, Not Wireless), Custom-fit feeling with curved frame, Ergonomic earcups, Refined glossy finish.",10));
        productsMap.put("Gibson Les Paul Custom Electric Guitar", new Product("Gibson Les Paul Custom Electric Guitar", 6500300,"Music", "The body of the Les Paul Signature has the modern weight relieved Mahogany body with 3 ply W/B/W binding and a AA figured Maple top. It also has a Mahogany neck with a 50's Round Profile",10));
        productsMap.put("Alienware 17 ANW17-2136SLV 17 Inch Laptop", new Product("Alienware 17 ANW17-2136SLV 17 Inch Laptop", 6100000, "Laptop", "Intel Core i7-4710HQ Processor (Quad-Core, 6MB Cache, up to 3.5GHz w/ Turbo Boost), 8 GB DDR3 RAM, 1000 GB 5400 rpm Hard Drive, 17.3-Inch Screen, NVIDIA GeForce GTX 970M with 3GB GDDR5",10));
        productsMap.put("Samsung Chromebook Plus Convertible Touch Laptop (XE513C24-K01US)", new Product("Samsung Chromebook Plus Convertible Touch Laptop (XE513C24-K01US)", 1600000, "Laptop","Flexible 360° profile. Enables you to use upright as a computer to reply to emails or finish a paper and recline for a tablet view ideal for surfing and viewing,This Device comes with the pen out of the box. Power to personalize your communications by writing directly on screen. Take notes, make a sketch, capture part of the screen, etc, Speed (GHz):2Ghz.",10));
        productsMap.put("Dell Inspiron 13 7000 Series 13.3-Inch Convertible", new Product("Dell Inspiron 13 7000 Series 13.3-Inch Convertible", 2999000, "Laptop","Intel Core i5-5200U Processor (3M Cache, 2.2 GHz), 8 GB DDR3, 13.3 in Full HD IPS LED touchscreen (1920 x 1080), 10-finger multi-touch support",10));
        
        User usradm= new User("Juan José Andrade Pardo","juan.andrade96@hotmail.com","asduyasdku=asd","int");
        Order o = new Order("juan.andrade96@hotmail.com");
        usradm.setOrder(o);
        o.addProduct("Omega Men's Seamaster 300M", 1);
        o.addProduct("Beats By Dre Solo 2 Luxe Edition", 2);
        o.addProduct("Gibson Les Paul Custom Electric Guitar", 3);
        
        users.put("juan.andrade96@hotmail.com", usradm);
        User usradm2= new User("Juan José Andrade Pardo","juan.andrade95@hotmail.com","","ext");
        Order o2 = new Order("juan.andrade95@hotmail.com");
        o2.addProduct("Omega Men's Seamaster 300M", 1);
        o2.addProduct("Beats By Dre Solo 2 Luxe Edition", 2);
        users.put("juan.andrade95@hotmail.com", usradm2);
    }



    @Override
    public Set<String> getAvailableProductNames() {
        return productsMap.keySet();
    }


    @Override
    public Product getProductByName(String product) throws OrderServicesException {
        if (!productsMap.containsKey(product)) {
            throw new OrderServicesException("Producto no existente:" + product);
        } else {
            return productsMap.get(product);
        }
    }






    @Override
    public int calculateBill(String email) throws OrderServicesException {
        if (!users.containsKey(email)) {
            throw new OrderServicesException("El usuario no está creado");
        } else {
            return calc.calculateBill(users.get(email).getOrder(), productsMap);
        }
    }
    @Override
    public Map<String, User> getAllUsers(){
        return users;
        
    }
    @Override
    public Map<String, Product> getAllProducts(){
        return productsMap;
        
    }
    
    



    @Override
    public void addUser(User user) throws OrderServicesException {
        if (users.containsKey(user.getCorreo())) {
            throw new OrderServicesException("El usuario ya está registrado");
        } else {
            users.put(user.getCorreo(), user);
        }
    }

    @Override
    public void logIn(String email, String contr) throws OrderServicesException {
        try{
            User tempUser= users.get(email);
            if(contr.equals(tempUser.getContrasena())|| tempUser.getCategoria().contentEquals("ext")){
                loggedUser=tempUser;
            }
            else{
                throw new OrderServicesException("");
            }
        }
        catch(Exception e){
            throw new OrderServicesException("El usuario no existe o la contraseña es incorrecta");
        }
    }

    @Override
    public void logOut() throws OrderServicesException {
        loggedUser=null;
    }

    @Override
    public User getUser(String email) throws OrderServicesException {
        if (users.containsKey(email)) {
            return users.get(email);
        } else {
            throw new OrderServicesException("El usuario no se encuentra");
        }
    }

    @Override
    public Map<String, Product> getProductsByNameOrType(String busqueda) {
        Map<String, Product> searchproducts = new ConcurrentHashMap<>();
        for (String p:productsMap.keySet()){
                    if(p.contains(busqueda)||productsMap.get(p).getType().contains(busqueda)){
                        searchproducts.put(p, productsMap.get(p));
                    }                    
		}
        return searchproducts;
    }

    @Override
    public Map<String, Product> getOrderedProducts() {
        List<String> keys = new ArrayList<>(productsMap.keySet());
        Collections.sort(keys);
        Map<String, Product> searchproducts = new ConcurrentHashMap<>();
        for (String p:keys){
            searchproducts.put(p, productsMap.get(p));
	}
        return searchproducts;
    }

    @Override
    public void addProduct(Product prod) throws OrderServicesException {
        if (!productsMap.containsKey(prod.getName())){
            productsMap.put(prod.getName(), prod);
        }
        else{
            productsMap.get(prod.getName()).addCantidad(prod.getCantidad());
        }
    
    }

    @Override
    public void setProductQuantity(String name,int quantity) throws OrderServicesException {
       if (productsMap.get(name)!=null && quantity>0) {
            productsMap.get(name).setCantidad(quantity);
        } else {
            throw new OrderServicesException("Ese producto no existe o la cantidad es incorrecta");
        }
    }

    @Override
    public void deleteProduct(String name) throws OrderServicesException {
         if (!productsMap.containsKey(name)) {
            throw new OrderServicesException("Ese producto no existe");
        } else {
            productsMap.remove(name);
        }
    }

    @Override
    public User getLoggedUser() throws OrderServicesException {
        if (loggedUser!=null) {
            return loggedUser;
        } else {
            throw new OrderServicesException("No hay usuario activo");
        }
    }

    @Override
    public void cleanUserOrder(String email) throws OrderServicesException {
        if (!users.containsKey(email)) {
            throw new OrderServicesException("No existe ese usuario");
        } else {
            users.get(email).setOrder(new Order(email));
        }
    }

    @Override
    public void addProductToOrder(String email, Product prod) throws OrderServicesException {
        try{
            users.get(email).getOrder().addProduct(prod.getName(), prod.getCantidad());
            setProductQuantity(prod.getName(),productsMap.get(prod.getName()).getCantidad()-prod.getCantidad());
        }
        catch(Exception e){
            throw new OrderServicesException("El usuario no existe o no hay suficiente cantidad de productos"+e);
        }

    }

    @Override
    public void setProductQuantityOfOrder(String email, String name, int quantity) throws OrderServicesException {
        try{
            users.get(email).getOrder().getOrderAmountsMap().put(name, quantity);
            setProductQuantity(name,productsMap.get(name).getCantidad()+users.get(email).getOrder().getOrderAmountsMap().get(name)-quantity);
         }
        catch(Exception e){
            throw new OrderServicesException("Error al modificar la cantidad del producto"+e);
        }
    }

    @Override
    public void deleteProductFromOrder(String email, String name) throws OrderServicesException {
        try{
            users.get(email).getOrder().getOrderAmountsMap().remove(name);
            setProductQuantity(name,productsMap.get(name).getCantidad()+users.get(email).getOrder().getOrderAmountsMap().get(name));
         }
        catch(Exception e){
            throw new OrderServicesException("Error al eliminar  el producto"+e);
        }
    }
    


}

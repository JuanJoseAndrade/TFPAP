/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fuse.appweb.shop.restcontroller;
import fuse.appweb.shop.services.OrderServices;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author Juan José Andrade Pardo
 */

@RestController
@RequestMapping(value = "/shopapi") 
public class ShopController { 
    @Autowired
    @Qualifier("Basic")
    OrderServices services;
/*
    @GetMapping("/{id}")
        public ResponseEntity<?> getOrder(@PathVariable int id) {
            if(restaurantorderservices.getTableOrder(id)!=null){
                return new ResponseEntity<>(restaurantorderservices.getTableOrder(id),HttpStatus.ACCEPTED);
            }
            else{	
                return new ResponseEntity<>("Error 404, esa orden no existe en el sistema",HttpStatus.NOT_FOUND);
            }

        }
    @GetMapping("/{id}/total")    
        public ResponseEntity<?> getOrderTotal(@PathVariable int id) {
            try {
                return new ResponseEntity<>(restaurantorderservices.calculateTableBill(id),HttpStatus.ACCEPTED);
            } catch (OrderServicesException ex) {
                Logger.getLogger(OrdersAPIController.class.getName()).log(Level.SEVERE, null, ex);
                return new ResponseEntity<>("Error 404, esa orden no existe en el sistema",HttpStatus.NOT_FOUND);
            }
        }
*/
//GET PRODUCT -----------------------------------------------------------------------------  
    @RequestMapping(method = RequestMethod.GET , value = "/products")
    public ResponseEntity<?> GetProducts(){

            try {
                    //obtener datos que se enviarán a través del API
                    return new ResponseEntity<>(services.getAllProducts(),HttpStatus.ACCEPTED);
            } catch (Exception ex) {

                    return new ResponseEntity<>("Error 404"+ex,HttpStatus.NOT_FOUND);
            }  
    }
    @RequestMapping(method = RequestMethod.GET , value = "/sortproducts")
    public ResponseEntity<?> GetSortProducts(){


            try {
                    //obtener datos que se enviarán a través del API
                    return new ResponseEntity<>(services.getOrderedProducts(),HttpStatus.ACCEPTED);
            } catch (Exception ex) {

                    return new ResponseEntity<>("Error 404"+ex,HttpStatus.NOT_FOUND);
            }  
    }
    @RequestMapping(method = RequestMethod.GET , value = "/searchproducts/{search}")
    public ResponseEntity<?> GetSearchProducts(@PathVariable String search){

            try {
                    //obtener datos que se enviarán a través del API
                    return new ResponseEntity<>(services.getProductsByNameOrType(search),HttpStatus.ACCEPTED);
            } catch (Exception ex) {

                    return new ResponseEntity<>("Error 404"+ex,HttpStatus.NOT_FOUND);
            }  
    }
    
    @RequestMapping(method = RequestMethod.GET , value = "/products/{product}")
    public ResponseEntity<?> GetProduct(@PathVariable String product){

            try {
                    //obtener datos que se enviarán a través del API
                    return new ResponseEntity<>(services.getProductByName(product),HttpStatus.ACCEPTED);
            } catch (Exception ex) {

                    return new ResponseEntity<>("Error 404"+ex,HttpStatus.NOT_FOUND);
            }  
    }
    //GET USER
    @RequestMapping(method = RequestMethod.GET , value = "/active")
    public ResponseEntity<?> GetActiveUser(){


            try {
                    //obtener datos que se enviarán a través del API
                    return new ResponseEntity<>(services.getLoggedUser(),HttpStatus.ACCEPTED);
            } catch (Exception ex) {

                    return new ResponseEntity<>("Error 404"+ex,HttpStatus.NOT_FOUND);
            }  
    }
    //Get Value
        @RequestMapping(method = RequestMethod.GET , value = "/factura/{mail}")
    public ResponseEntity<?> GetFact(@PathVariable String mail){
            try {
                    //obtener datos que se enviarán a través del API
                    return new ResponseEntity<>(services.calculateBill(mail),HttpStatus.ACCEPTED);
            } catch (Exception ex) {

                    return new ResponseEntity<>("Error 404"+ex,HttpStatus.NOT_FOUND);
            }  
    }
    
    /*
    @RequestMapping(method = RequestMethod.POST)	
    public ResponseEntity<?> manejadorPostRecursoXX(@RequestBody Order newOrder){
            try {
                    restaurantorderservices.addNewOrderToTable(newOrder);
                    return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (Exception ex) {
                    Logger.getLogger(OrdersAPIController.class.getName()).log(Level.SEVERE, null, ex);
                    return new ResponseEntity<>("Error al intentar subir la orden",HttpStatus.FORBIDDEN);            
            }        

    }
    //Se ingresa una orden con el identificador de la mesa a la que se le quieren agregar cosas y los productos
    //Ejemplo de como usar Put con Json : Dentro van los nuevos productos que se quieran agregar{"orderAmountsMap":{"PIZZA":5},"tableNumber":1}
    //http://localhost:8080/orders Este es el path para modificar determinados datos sea la mesa que sea
    // Ejemplo para modificar orden: curl -i -X PUT -HContent-Type:application/json -HAccept:application/json http://localhost:8080/orders -d '{"orderAmountsMap":{"PIZZA":3,"HOTDOG":1,"COKE":4,"HAMBURGER":4},"tableNumber":1}'

    @RequestMapping(method = RequestMethod.PUT)	
    public ResponseEntity<?> manejadorPutxOrder(@RequestBody  Order newOrder){
            try {   
                    restaurantorderservices.addOrderToOrder(newOrder);
                    return new ResponseEntity<>(HttpStatus.ACCEPTED);
            } catch (Exception ex) {
                    Logger.getLogger(OrdersAPIController.class.getName()).log(Level.SEVERE, null, ex);
                    return new ResponseEntity<>("No se pudo hacer el proceso Put de los productos",HttpStatus.FORBIDDEN);            
            }        

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{numOrder}")
    public ResponseEntity<?> manejadorDeleteOrder(@PathVariable String numOrder){
            try {
                    restaurantorderservices.releaseTable(Integer.parseInt(numOrder));
                    return new ResponseEntity<>(HttpStatus.ACCEPTED);
            } catch (Exception ex) {
                    Logger.getLogger(OrdersAPIController.class.getName()).log(Level.SEVERE, null, ex);
                    return new ResponseEntity<>("Mesa inexistente o ya liberada, No se pudo eliminar el elemento",HttpStatus.FORBIDDEN);            
            }        

    }
    @RequestMapping(method = RequestMethod.DELETE)	
    public ResponseEntity<?> manejadorDeleteProduct(@RequestBody Order order){
            try {
                    restaurantorderservices.deleteOrderFromOrder(order);
                    return new ResponseEntity<>(HttpStatus.ACCEPTED);
            } catch (Exception ex) {
                    Logger.getLogger(OrdersAPIController.class.getName()).log(Level.SEVERE, null, ex);
                    return new ResponseEntity<>("El producto o la orden no existe",HttpStatus.FORBIDDEN);            
            }        
    }
    */

}

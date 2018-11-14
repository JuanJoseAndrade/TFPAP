/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuse.appweb.shop.model;

/**
 *
 * @author JuanJoAndrade
 */
public class User {
    private String nombre;
    private String correo;
    private String contrasena;
    private String categoria;
    private Order carritodecompras;
    
    public User(String nombre, String correo, String contrasena, String categoria){
        this.nombre=nombre;
        this.contrasena=contrasena;
        this.correo=correo;
        this.categoria=categoria;
    }
    public void setOrder(Order neworder){
        carritodecompras=neworder;
    }
    public Order getOrder(){
        return carritodecompras;
    }
    public void setContrasena(String contrasena){
        this.contrasena=contrasena;
    }
    public String getContrasena(){
        return contrasena;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public String getNombre(){
        return nombre;
    }
    public void setCorreo(String correo){
        this.correo=correo;
    }
    public String getCorreo(){
        return correo;
    }
    public void setCategoria(String categoria){
        this.categoria=categoria;
    }
    public String getCategoria(){
        return categoria;
    }
            
            
}

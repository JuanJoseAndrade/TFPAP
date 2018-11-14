package fuse.appweb.shop.model;

import fuse.appweb.shop.services.OrderServicesException;

public class Product  {

	private float price;

	private String name;
        
        private String type;
        
        private String description;
        
        private int cantidad;

        public Product(){
            
        }
        
        
	public Product(String nombre, float precio, String type, String description,int cantidad) {
		super();
                this.description=description;
		this.price = precio;
		this.name = nombre;
                this.type=type;
                this.cantidad=cantidad;
	}
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }

        public int getCantidad() {
            return cantidad;
        }
        public void setCantidad(int cantidad) throws OrderServicesException {
            if (cantidad>0){
            this.cantidad = cantidad;
            }
            else{
                throw new OrderServicesException("Las unidades del producto no pueden nulas o negativas");
            }
        }
        public void addCantidad(int cantidad) throws OrderServicesException{
            if((this.cantidad+cantidad)>0){
                this.cantidad+=cantidad;
            }
            else{
                throw new OrderServicesException("Solo quedan:"+Integer.toString(this.cantidad)+" unidades del producto");
            }
        }
        
        public void setPrice(int precio) {
            this.price = precio;
        }

    
        public void setName(String nombre) {
            this.name = nombre;
        }
        
        
    
	public String getName() {
		return name;
	}
	

	public float getPrice() {
		return price;
	}
	
}

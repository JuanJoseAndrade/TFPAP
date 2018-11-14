package fuse.appweb.shop.taxes;

import fuse.appweb.shop.model.Product;

public interface TaxesCalculator {

	public float getProductTaxes(Product p);
	
}

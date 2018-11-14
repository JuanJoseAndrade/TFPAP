package fuse.appweb.shop.taxes;

import org.springframework.stereotype.Service;
import fuse.appweb.shop.model.Product;
@Service("Standar")
public class StandardTaxesCalculator implements TaxesCalculator {

	@Override
	public float getProductTaxes(Product p) {
		return 0.19f;
	}

}

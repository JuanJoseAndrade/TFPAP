package fuse.appweb.shop.bill;

import fuse.appweb.shop.model.Order;
import fuse.appweb.shop.model.Product;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("Base")
public class BaseBillCalculator implements BillCalculator {

	@Override
	public int calculateBill(Order o,Map<String,Product> productsMap) {
		int total=0;
		for (String p:o.getProducts()){
                    Product rp=productsMap.get(p);
                    total+=o.getProductAmount(p)*rp.getPrice();                    
		}
		return total;
	}

}

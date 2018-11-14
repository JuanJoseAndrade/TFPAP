package fuse.appweb.shop.bill;

import fuse.appweb.shop.model.Order;
import fuse.appweb.shop.model.Product;
import fuse.appweb.shop.taxes.TaxesCalculator;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("WithTaxes")
public class BillWithTaxesCalculator implements BillCalculator {
    @Autowired
    @Qualifier("Standar")
    TaxesCalculator taxescalc;

    @Override
    public int calculateBill(Order o, Map<String, Product> productsMap) {
        int total = 0;
        for (String p : o.getProducts()) {
            Product rp=productsMap.get(p);
            total += (o.getProductAmount(p) * (rp.getPrice() * (1 + taxescalc.getProductTaxes(rp))));
        }
        return total;

    }

}

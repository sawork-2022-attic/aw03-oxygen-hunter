package com.example.webpos.biz;

import com.example.webpos.db.PosDB;
import com.example.webpos.model.Cart;
import com.example.webpos.model.Item;
import com.example.webpos.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class PosServiceImp implements PosService {

    private PosDB posDB;

    private final BigDecimal tax;

    private final BigDecimal discount;

    @Autowired
    public void setPosDB(PosDB posDB) {
        this.posDB = posDB;
    }

    @Autowired
    PosServiceImp(@Value("${tax}") String tax, @Value("${discount}") String discount) {
        this.tax = new BigDecimal(tax);
        this.discount = new BigDecimal(discount);
    }

    @Override
    public Cart getCart() {
        Cart cart = posDB.getCart();
        if (cart == null){
            cart = this.newCart();
        }
        return cart;
    }

    @Override
    public Cart newCart() {
        return posDB.saveCart(new Cart());
    }

    @Override
    public boolean addProduct(String productId, int amount) {
        Product product = posDB.getProduct(productId);
        if (product == null) return false;
        this.getCart().addItem(new Item(product, amount));
        return true;
    }

    @Override
    public boolean subProduct(int itemIndex) {
        return this.getCart().subProduct(itemIndex);
    }

    @Override
    public boolean removeProduct(int itemIndex) {
        return this.getCart().removeProduct(itemIndex);
    }

    @Override
    public boolean addProduct(int itemIndex) {
        return this.getCart().addProduct(itemIndex);
    }

    @Override
    public void emptyCart(Cart cart) {
        this.getCart().emptyCart();
    }

    @Override
    public void checkout(Cart cart) {
        this.getCart().checkout();
    }

    @Override
    public List<Product> products() {
        return posDB.getProducts();
    }

    @Override
    public BigDecimal getTax() {
        return tax.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getDiscount() {
        return discount.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getSubTotal() {
        BigDecimal cartTotal = this.getCart().getTotal();
        BigDecimal subTotal = cartTotal.subtract(cartTotal.multiply(discount));
        return subTotal.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getTotal() {
        BigDecimal subTotal = getSubTotal();
        BigDecimal total = subTotal.multiply(BigDecimal.valueOf(1).add(tax));
        return total.setScale(2, RoundingMode.HALF_UP);
    }
}

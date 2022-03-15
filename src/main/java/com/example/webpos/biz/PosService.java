package com.example.webpos.biz;

import com.example.webpos.model.Cart;
import com.example.webpos.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface PosService {
    public Cart getCart();

    public Cart newCart();

    public boolean addProduct(String productId, int amount);

    public boolean subProduct(int itemIndex);

    public boolean removeProduct(int itemIndex);

    public boolean addProduct(int itemIndex);

    public void emptyCart(Cart cart);

    public void checkout(Cart cart);

    public List<Product> products();

    BigDecimal getTax();

    BigDecimal getDiscount();

    BigDecimal getSubTotal();

    BigDecimal getTotal();
}

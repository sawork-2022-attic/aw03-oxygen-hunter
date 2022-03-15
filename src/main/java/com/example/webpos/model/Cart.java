package com.example.webpos.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private List<Item> items = new ArrayList<>();

    public boolean addItem(Item item) {
        return items.add(item);
    }

    public boolean addProduct(int itemIndex) {
        if (itemIndex >= items.size()) {
            return false;
        }
        Item itemToAdd = items.get(itemIndex);
        int oldQuantity = itemToAdd.getQuantity();
        itemToAdd.setQuantity(oldQuantity + 1);
        return true;
    }

    public boolean subProduct(int itemIndex) {
        if (itemIndex >= items.size()) {
            return false;
        }
        Item itemToSub = items.get(itemIndex);
        int oldQuantity = itemToSub.getQuantity();
        if (oldQuantity == 1) {
            items.remove(itemIndex);
        } else {
            itemToSub.setQuantity(oldQuantity - 1);
        }
        return true;
    }

    public boolean removeProduct(int itemIndex) {
        if (itemIndex >= items.size()) {
            return false;
        }
        items.remove(itemIndex);
        return true;
    }

    public void emptyCart() {
        items.clear();
    }

    public void checkout() {
        emptyCart();
    }

    public BigDecimal getTotal() {
        BigDecimal total = new BigDecimal(0);
        for (Item item : items) {
            BigDecimal price = BigDecimal.valueOf(item.getProduct().getPrice());
            BigDecimal quantity = BigDecimal.valueOf(item.getQuantity());
            total = total.add(price.multiply(quantity));
        }
        return total;
    }

    @Override
    public String toString() {
        if (items.size() ==0){
            return "Empty Cart";
        }
        double total = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cart -----------------\n"  );

        for (int i = 0; i < items.size(); i++) {
            stringBuilder.append(items.get(i).toString()).append("\n");
            total += items.get(i).getQuantity() * items.get(i).getProduct().getPrice();
        }
        stringBuilder.append("----------------------\n"  );

        stringBuilder.append("Total...\t\t\t" + total );

        return stringBuilder.toString();
    }
}

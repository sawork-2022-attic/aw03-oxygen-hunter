package com.example.webpos.web;

import com.example.webpos.biz.PosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PosController {

    private PosService posService;

    @Autowired
    public void setPosService(PosService posService) {
        this.posService = posService;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("products", posService.products());
        model.addAttribute("cart", posService.getCart());
        model.addAttribute("tax", posService.getTax());
        model.addAttribute("discount", posService.getDiscount());
        model.addAttribute("subTotal", posService.getSubTotal());
        model.addAttribute("total", posService.getTotal());
    }

    @GetMapping("/")
    public String pos(Model model) {
        return "index";
    }

    @GetMapping("/subItem")
    public String subProdcut(@RequestParam("itemIndex") int itemIndex) {
        posService.subProduct(itemIndex);
        return "redirect:/";
    }

    @GetMapping("/addItem")
    public String addProdcut(@RequestParam("itemIndex") int itemIndex) {
        posService.addProduct(itemIndex);
        return "redirect:/";
    }

    @GetMapping("/addProduct")
    public String addProduct(@RequestParam("productId") String productId) {
        posService.addProduct(productId, 1);
        return "redirect:/";
    }

    @GetMapping("/removeItem")
    public String removeProdcut(@RequestParam("itemIndex") int itemIndex) {
        posService.removeProduct(itemIndex);
        return "redirect:/";
    }

    @GetMapping("/cancel")
    public String emptyCart() {
        posService.emptyCart(posService.getCart());
        return "redirect:/";
    }

    @GetMapping("/charge")
    public String charge() {
        posService.checkout(posService.getCart());
        return "redirect:/";
    }


}

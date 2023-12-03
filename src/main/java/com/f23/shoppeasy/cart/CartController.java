package com.f23.shoppeasy.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService service;

    @GetMapping("/list")
    public String getAllProducts(Model model) {
        model.addAttribute("productList",
                service.getAllProducts());

        return "cart/cart";
    }

    @GetMapping("/add-to-cart/listing={listingId}&user={userId}")
    public String addItemToCart(Model model, @PathVariable long listingId, @PathVariable long userId) {
        CartEntry cart = new CartEntry();
        cart.setItemId(listingId);
        cart.setUserId(userId);
        cart.setDestination("Placeholder");

        service.addItemToCart(cart);

        return "redirect:/listings/search";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable long id, Model model) {
        service.deleteProduct(id);
        return "redirect:/cart";
    }

    @GetMapping("/clear-cart")
    public String clearCart(Model model) {
        service.clearCart();
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        System.out.println(service.checkout(1).size());
        model.addAttribute("productList",
                service.checkout(1));
        return "cart/checkout";
    }

    @GetMapping("/confirmation")
    public String paid(Model model) {
        service.paid(1, 123.43);
        return "cart/confirmation";
    }

//    @PostMapping("/update/{id}")
//    public String updateCart(@PathVariable long id, @RequestParam("quantity") int quantity, Model model) {
//        service.updateCart(id, quantity);
//        return "cart/cart";
//    }
}

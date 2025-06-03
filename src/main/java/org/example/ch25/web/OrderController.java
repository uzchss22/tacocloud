package org.example.ch25.web;

import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.example.ch25.TacoOrder;
import org.example.ch25.data.OrderRepository;
import org.example.ch25.User;

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo){
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String orderForm(Model model, @AuthenticationPrincipal User user){
        TacoOrder tacoOrder = new TacoOrder();
        if (user != null) {
            tacoOrder.setDeliveryName(user.getFullname());
            tacoOrder.setDeliveryStreet(user.getStreet());
            tacoOrder.setDeliveryCity(user.getCity());
            tacoOrder.setDeliveryState(user.getState());
            tacoOrder.setDeliveryZip(user.getZip());
        }
        model.addAttribute("tacoOrder", tacoOrder);
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder tacoOrder, Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        tacoOrder.setUser(user);
        orderRepo.save(tacoOrder);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}

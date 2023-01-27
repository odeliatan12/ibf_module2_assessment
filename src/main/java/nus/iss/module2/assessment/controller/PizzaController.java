package nus.iss.module2.assessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import nus.iss.module2.assessment.model.OrderDetails;
import nus.iss.module2.assessment.service.PizzaService;

@Controller
@RequestMapping(path = "/")
public class PizzaController {
    
    @Autowired
    private PizzaService pizzaService;

    // Validations
    @GetMapping(path="pizza")
    public String getPizza(Model model){
        System.out.println("====== hellloooooo");
        model.addAttribute("pizza", new OrderDetails()); 
        return "index";
    }

    @PostMapping(path = "pizza", consumes = "application/x-www-form-urlencoded", produces = "text/html")
    public String getPizza(@Valid OrderDetails pizza, BindingResult bindingResult, Model model){
        System.out.println("helllooooooo");
        if(bindingResult.hasErrors()){
            return "index";
        }
        // System.out.println(pizza.getSize());
        model.addAttribute("pizza", pizza);
        return "deliveryDetails";
    }

    // validations
    @GetMapping(path = "pizza/order")
    public String getOrder(Model model){
        System.out.println("============");
        model.addAttribute("orderDetails", new OrderDetails());
        return "deliveryDetails";
    }

    // Validations
    @PostMapping(path = "pizza/order", consumes = "application/x-www-form-urlencoded", produces = "text/html")
    public String placeOrder(@Valid OrderDetails orderDetails, BindingResult binding, Model model, HttpServletResponse response) {

        System.out.println("hellloooooo");
        System.out.println(orderDetails.getName());
        if (binding.hasErrors()) {
            return "deliveryDetails";
        }

        // saving the delivery details--- can only save order id, name, address, phone, rush and comments
        pizzaService.saveOrderDetails(orderDetails);
        model.addAttribute( "orderDetails", orderDetails);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return "orderConfirmation";
    }


    // Obtaining the order details from redis
    // @GetMapping(path="pizza/order/{pizzaId}")
    // public String getOrderInfoById(Model model, @PathVariable(value="pizzaId") String pizzaId){
    //     OrderDetails ps = pizzaService.findById(pizzaId);
    //     ps.setId(pizzaId);
    //     model.addAttribute("pizzas", ps);
    //     return "orderConfirmation";
    // }
}

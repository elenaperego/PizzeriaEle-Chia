package Classes.Pizza;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import Classes.Exceptions.Exception404;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PizzaController {

    private final AtomicLong counter = new AtomicLong();
    ArrayList<Pizza> pizzas = new ArrayList<>();

    @GetMapping("/create")
    public Pizza pizza(@RequestParam(value = "name", defaultValue = "niente") String name, @RequestParam(value = "vegetarian", defaultValue = "false") boolean veg) {
        Pizza newPizza = new Pizza((int) counter.incrementAndGet(), name, veg);
        pizzas.add(newPizza);
        return newPizza;
    }

    @GetMapping("/pizza")
    public ArrayList<Pizza> getAllPizzas(){ return pizzas; }

    @GetMapping("/pizzaID")
    public Pizza getPizza(@RequestParam(value = "id", defaultValue = "-1") long id) {
        Pizza pizza = null;
        boolean found = false;
        for (int i = 0; i < pizzas.size(); i++) {
            if (pizzas.get(i).getId() == id) {
                pizza = pizzas.get(i);
                found = true;
            }
        }

        if (!found) {
            throw new Exception404("404: Order_ID not found");
        }
        return pizza;
    }
}
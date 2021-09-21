package Mappers;

import com.PizzaAPI.PizzaAPI.Pizza.Pizza;

import java.util.Optional;

public interface PizzaDataMapper {
    Optional<Pizza> find(int pizzaId);
    void insert(Pizza pizza);
    void update(Pizza pizza);
    void delete(Pizza pizza);

}

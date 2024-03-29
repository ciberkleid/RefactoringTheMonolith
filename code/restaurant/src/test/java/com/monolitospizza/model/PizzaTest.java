package com.monolitospizza.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Matt Stine
 */
public class PizzaTest {

    private Topping sausage;
    private Size large;
    private Crust handTossed;
    private Pizza pizza;
    private Sauce light;

    @Before
    public void setUp() throws Exception {
        sausage = new Topping("Sausage", BigDecimal.valueOf(0.5));
        large = new Size("Large", BigDecimal.valueOf(12.99));
        handTossed = new Crust("Hand Tossed");
        light = new Sauce("Light");

        pizza = new Pizza(large, handTossed, light);
    }

    @Test
    public void mustSetSizeAndCrustAndSauce() {
        assertThat(large, equalTo(pizza.getSize()));
        assertThat(handTossed, equalTo(pizza.getCrust()));
        assertThat(light, equalTo(pizza.getSauce()));
    }

    @Test
    public void newPizzaHasTwoHalves() {
        assertThat(pizza.getLeftHalf(), notNullValue());
        assertThat(pizza.getRightHalf(), notNullValue());
    }


    @Test
    public void canAddLeftTopping() {
        pizza.addLeftTopping(sausage);

        assertTrue(pizza.getLeftHalf().getToppings().contains(sausage));
    }

    @Test
    public void canRemoveLeftTopping() {
        pizza.addLeftTopping(sausage);
        pizza.removeLeftTopping(sausage);

        assertFalse(pizza.getLeftHalf().getToppings().contains(sausage));
    }

    @Test
    public void canAddRightTopping() {
        pizza.addRightTopping(sausage);

        assertTrue(pizza.getRightHalf().getToppings().contains(sausage));
    }

    @Test
    public void canRemoveRightTopping() {
        pizza.addRightTopping(sausage);
        pizza.removeRightTopping(sausage);

        assertFalse(pizza.getRightHalf().getToppings().contains(sausage));
    }

    @Test
    public void canAddWholeTopping() {
        pizza.addTopping(sausage);

        assertTrue(pizza.getLeftHalf().getToppings().contains(sausage));
        assertTrue(pizza.getRightHalf().getToppings().contains(sausage));
    }

    @Test
    public void canRemoveWholeTopping() {
        pizza.addTopping(sausage);
        pizza.removeTopping(sausage);

        assertFalse(pizza.getLeftHalf().getToppings().contains(sausage));
        assertFalse(pizza.getRightHalf().getToppings().contains(sausage));
    }

    @Test
    public void pizzaWithToppingsIncludesThemInPrice() {
        pizza.addLeftTopping(sausage);
        pizza.addRightTopping(new Topping("Onion", BigDecimal.valueOf(0.5)));

        BigDecimal expectedPrice =
                large.getPrice()
                        .add(sausage.getPrice())
                        .add(new Topping("Onion", BigDecimal.valueOf(0.5)).getPrice());

        assertThat(pizza.getPrice(), equalTo(expectedPrice));
    }

}

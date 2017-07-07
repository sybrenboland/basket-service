package org.shboland.model;

import org.junit.Test;
import org.shboland.model.basket.Basket;

import static org.junit.Assert.assertNotNull;

public class BasketTest {

    @Test
    public void testConstructor() {
        assertNotNull("Constructor does not create object", new Basket());
    }
}

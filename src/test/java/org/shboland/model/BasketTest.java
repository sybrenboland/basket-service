package org.shboland.model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class BasketTest {

    @Test
    public void testConstructor() {
        assertNotNull("Constructor does not create object", new Basket("1", new ArrayList<>()));
    }
}

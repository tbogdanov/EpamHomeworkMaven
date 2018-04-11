package org.tbogdanov.epamhw.module2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tbogdanov.epamhw.module2.Submarine.AtomicSubmarine;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by Timur Bogdanov on 11.04.18.
 */
public class AtomicSubmarineTest {

    AtomicSubmarine submarine;

    @Test
    public void startWithoutFuelTest() {
        submarine = new AtomicSubmarine(0.0);
        assertThrows(Exception.class, () -> {
            submarine.startEngine();
        });
    }

    @Test
    public void startNormallyTest() {
        submarine = new AtomicSubmarine(50.0);
        try {
            submarine.startEngine();
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void throttleWithEngineStopped() {
        submarine = new AtomicSubmarine(50.0);
        assertThrows(Exception.class, () -> {
            submarine.throttle();
        });
    }

    @Test
    public void throttleWithoutFuel() {
        submarine = new AtomicSubmarine(50.0);
        try {
            submarine.startEngine();
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
        assertThrows(Exception.class, () -> {
            submarine.throttle();
        });
    }

    @Test
    public void throttleNormally() {
        submarine = new AtomicSubmarine(500.0);
        try {
            submarine.startEngine();
            submarine.turnByAngleXYInDegrees(90);
            submarine.turnByAngleZInDegrees(-90);
            submarine.throttle();
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
        assertEquals(1.0, submarine.getXY().getValue().doubleValue());
        assertEquals(1.0, submarine.getDepth());
    }

}

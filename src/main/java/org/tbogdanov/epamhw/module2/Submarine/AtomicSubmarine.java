package org.tbogdanov.epamhw.module2.Submarine;

import javafx.util.Pair;

/**
 * Created by Timur Bogdanov on 11.04.18.
 */
public class AtomicSubmarine {

    private class AtomicSubmarineEngine {

        private final double MIN_FUEL_TO_START = 50.0;
        private final double MAX_FUEL = 1000.0;
        private final double FUEL_PER_1_SPEED = 1.0;

        private boolean isStarted;
        private double fuel;

        public AtomicSubmarineEngine(double startingFuel) {
            addFuel(startingFuel);
            isStarted = false;
            speed = 1.0;
        }

        public boolean startEngine() {
            isStarted = (fuel >= MIN_FUEL_TO_START);
            if (isStarted) {
                fuel -= MIN_FUEL_TO_START;
            }
            return isStarted;
        }

        public boolean isStarted() {
            return isStarted;
        }

        public boolean hasFuel(double fuel) {
            return this.fuel >= fuel;
        }

        public void addFuel(double fuel) {
            this.fuel += fuel;
            if (this.fuel > MAX_FUEL) {
                this.fuel = MAX_FUEL;
            }
        }

        public boolean dispenseFuelForMove(double speed) {
            if (!hasFuel(speed*FUEL_PER_1_SPEED)) {
                return false;
            }
            else {
                this.fuel -= speed*FUEL_PER_1_SPEED;
                return true;
            }
        }

        public void stopEngine() {
            isStarted = false;
            speed = 0.0;
        }
    }

    private AtomicSubmarineEngine engine;
    private double speed;
    private double angleXY;
    private double angleZ;
    private double x;
    private double y;
    private double depth;

    public AtomicSubmarine(double startingFuel) {
        engine = new AtomicSubmarineEngine(startingFuel);
    }

    public void startEngine() throws Exception {
        if (!engine.startEngine()) {
            throw new Exception(String.format("Not enough fuel to start with! Add %f more fuel!",
                    engine.MAX_FUEL-engine.fuel));
        }
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void turnByAngleXYInDegrees(double angle) {
        angleXY += Math.toRadians(angle);
    }

    public void turnByAngleZInDegrees(double angle) {
        angleZ += Math.toRadians(angle);
    }

    public void throttle() throws Exception {
        if (!engine.isStarted()) {
            throw new Exception("Start the engine first!");
        }
        if (!engine.dispenseFuelForMove(speed)) {
            throw new Exception(String.format("Not enough fuel to do that! Add %f more fuel!",
                    engine.FUEL_PER_1_SPEED*speed-engine.fuel));
        }
        x += speed * Math.cos(angleXY);
        y += speed * Math.sin(angleXY);
        depth -= speed * Math.sin(angleZ);
        if (depth <= 0) {
            depth = 0;
        }
    }

    public Pair<Double, Double> getXY() {
        return new Pair<>(x, y);
    }

    public double getDepth() {
        return depth;
    }

}

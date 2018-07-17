package com.example.bridge;

public class Bridge {
    private int northboundCars;
    private int southboundCars;
    private boolean movingNorth;

    public Bridge(int northboundCars, int southboundCars) {
        this.northboundCars = northboundCars;
        this.southboundCars = southboundCars;
    }

    public void process() throws SolutionComplete {
        determineDirection();
        crossBridge();
    }

    private void crossBridge() throws SolutionComplete {
        if (movingNorth) {
            northboundCars--;
            System.out.println(southboundCars + " southbound cars remain, " + northboundCars + " northbound cars");
            if (northboundCars == 0) throw new SolutionComplete();
        } else {
            southboundCars--;
            System.out.println(southboundCars + " southbound cars remain, " + northboundCars + " northbound cars");
            if (southboundCars == 0) throw new SolutionComplete();
        }
    }

    private void determineDirection() {
        if (southboundCars > northboundCars) {
            System.out.println("southbound > northbound : Priority is southbound");
            movingNorth = false;
        } else if (northboundCars > southboundCars) {
            System.out.println("northbound > southbound: Priority is northbound");
            movingNorth = true;
        } else {
            if (movingNorth) {
                System.out.println("northbound = southbound: Priority is southbound");
            } else {
                System.out.println("northbound = southbound: Priority is northbound");
            }
            movingNorth = !movingNorth;
        }
    }

    public void addCars() {
        northboundCars++;
        southboundCars++;
    }
}

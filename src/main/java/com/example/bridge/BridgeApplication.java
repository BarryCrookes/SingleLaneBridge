package com.example.bridge;

class BridgeApplication {

    public static void main(String[] args) {
        int northboundCars = 5;
        int southboundCars = 4;
        boolean running = true;
        int timer = 0;

        Bridge bridge = new Bridge(northboundCars, southboundCars);

        while (running) {
            try {
                bridge.process();
                Thread.sleep(1 * 1000);
                timer++;
                if (timer % 3 == 0 && timer > 0) bridge.addCars();
            } catch (SolutionComplete solutionComplete) {
                System.out.println("Solution complete");
                running = false;
            } catch (InterruptedException e) {
                // do nothing for sleep exception
            }
        }
    }
}

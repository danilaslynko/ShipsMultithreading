package ru.shipsmultithreading.ship.waypoints;

import ru.shipsmultithreading.ships.GoodsType;
import ru.shipsmultithreading.ships.Ship;

public class Pier extends Thread {

    private Tunnel tunnel;
    private final GoodsType pierType;
    private Ship currentShip;

    public Pier(Tunnel tunnel, GoodsType pierType) {
        this.tunnel = tunnel;
        this.pierType = pierType;
    }

    @Override
    public void run() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!tunnel.isEmpty()) {
            if (currentShip == null) {
                currentShip = tunnel.getShipByType(pierType);
                System.out.println("Ship was taken to " + pierType.name() + " pier");
            }

            while (!currentShip.isFull()) {
                currentShip.load(10);

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(pierType.name() + " ship was loaded");
            currentShip = null;
        }
    }
}

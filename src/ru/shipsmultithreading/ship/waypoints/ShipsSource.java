package ru.shipsmultithreading.ship.waypoints;

import ru.shipsmultithreading.ships.Ship;

import java.util.LinkedList;
import java.util.Queue;

public class ShipsSource extends Thread {

    private Tunnel tunnel;
    private Queue<Ship> waitingShips;
    private final int maxShips;

    public ShipsSource(int maxWaitingShips, Tunnel tunnel) {
        this.tunnel = tunnel;
        waitingShips = new LinkedList<>();
        maxShips = maxWaitingShips;
    }

    @Override
    public void run() {
        while (waitingShips.size() < maxShips) {
            waitingShips.add(new Ship());

            if (!tunnel.isFull()) {
                tunnel.launchShip(waitingShips.poll());
            }
            else {
                System.out.println("Ship was added to the waiting queue");
            }

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (!waitingShips.isEmpty()) {
            tunnel.launchShip(waitingShips.poll());
        }
    }
}

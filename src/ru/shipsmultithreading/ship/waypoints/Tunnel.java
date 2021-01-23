package ru.shipsmultithreading.ship.waypoints;

import ru.shipsmultithreading.ships.GoodsType;
import ru.shipsmultithreading.ships.Ship;

import java.util.LinkedList;
import java.util.Queue;

public class Tunnel {

    private Queue<Ship> ships;
    private int maxShips;

    public Tunnel(int maxShips) {
        this.maxShips = maxShips;
        ships = new LinkedList<>();
    }

    public synchronized Ship getShipByType(GoodsType type) {
        while (isEmpty() || type != ships.peek().type()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Ship ship = ships.poll();

        notifyAll();
        return ship;
    }

    public synchronized void launchShip(Ship ship) {
        while (isFull()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ships.add(ship);
        System.out.println("Ship was launched in the tunnel");
        notify();
    }

    public synchronized Ship peekShip() {
        return ships.peek();
    }

    public boolean isFull() {
        return ships.size() >= maxShips;
    }

    public boolean isEmpty() {
        return ships.isEmpty();
    }
}

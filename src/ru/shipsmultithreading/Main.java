package ru.shipsmultithreading;

import ru.shipsmultithreading.ship.waypoints.Pier;
import ru.shipsmultithreading.ship.waypoints.ShipsSource;
import ru.shipsmultithreading.ship.waypoints.Tunnel;
import ru.shipsmultithreading.ships.GoodsType;

public class Main {

    public static void main(String[] args) {
        Tunnel tunnel = new Tunnel(5);
        Pier coalPier = new Pier(tunnel, GoodsType.COAL);
        Pier fruitPier = new Pier(tunnel, GoodsType.FRUIT);
        Pier passengerPier = new Pier(tunnel, GoodsType.PASSENGER);
        ShipsSource source = new ShipsSource(20, tunnel);

        source.start();
        coalPier.start();
        fruitPier.start();
        passengerPier.start();
    }
}

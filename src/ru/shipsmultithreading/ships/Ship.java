package ru.shipsmultithreading.ships;

import java.util.Random;

public class Ship {

    private GoodsType goodsType;
    private int capacity;
    private int goods;

    public Ship() {
        Random rndm = new Random();
        int determinant = rndm.nextInt(3);
        goodsType = determinant == 0 ? GoodsType.COAL :
                determinant == 1 ? GoodsType.FRUIT :
                        GoodsType.PASSENGER;

        determinant = rndm.nextInt(3);
        capacity = determinant == 0 ? 10 :
                determinant == 1 ? 50 :
                        100;

        goods = 0;
    }

    public boolean isFull() {
        return goods >= capacity;
    }

    public void load(int goods) {
        this.goods += goods;
        if (this.goods > capacity) {
            this.goods = capacity;
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public GoodsType type() {
        return goodsType;
    }
}

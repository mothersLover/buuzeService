package com.simple;

import org.springframework.stereotype.Component;

@Component(value = "startUpProperties")
public class StartUpProperties {
    private float containerPrice;

    private int containerCapacity;

    private float buuzePrice;

    private int deliveryPrice;

    public float getContainerPrice() {
        return containerPrice;
    }

    public void setContainerPrice(float containerPrice) {
        this.containerPrice = containerPrice;
    }

    public void setBuuzePrice(float buuzePrice) {
        this.buuzePrice = buuzePrice;
    }

    public float getBuuzePrice() {
        return buuzePrice;
    }

    public int getContainerCapacity() {
        return containerCapacity;
    }

    public void setContainerCapacity(int containerCapacity) {
        this.containerCapacity = containerCapacity;
    }

    public int getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(int deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }
}

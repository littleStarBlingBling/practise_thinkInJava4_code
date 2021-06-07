package com.practise.enumerated;

enum Signal {GREEN, YELLOW, RED,}

public class TrafficLight {
    private Signal color = Signal.RED;

    // 红绿灯：红-黄-绿
    public void change() {
        switch (color) {
            case RED:
                color = Signal.YELLOW;
                break;
            case GREEN:
                color = Signal.RED;
                break;
            case YELLOW:
                color = Signal.GREEN;
                break;
        }
    }

    @Override
    public String toString() {
        return "The traffic light is " + color;
    }

    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();
        for (int i = 0; i < 8; i++) {
            System.out.println(trafficLight);
            trafficLight.change();
        }
    }
}

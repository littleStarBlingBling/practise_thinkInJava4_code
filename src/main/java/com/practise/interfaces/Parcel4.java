package com.practise.interfaces;

public class Parcel4 {

    private void internalTracking(boolean b) {
        // 在控制语句中创建了一个内部类
        if (b) {
            class TrackingSlip {
                private String id;

                TrackingSlip(String s) {
                    id = s;
                }

                String getSlip() {
                    return id;
                }
            }

            TrackingSlip trackingSlip = new TrackingSlip("slip");
            String s = trackingSlip.getSlip();
            System.out.println(s);
        }

    }

    public void track() {
        internalTracking(true);
    }

    public static void main(String[] args) {
        Parcel4 parcel4 = new Parcel4();
        parcel4.track();
    }
}

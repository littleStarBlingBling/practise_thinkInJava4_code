package com.practise.enumerated;

public enum  OverrideConstantSpecific {
    NUT, BOLT,
    WASHER{
        @Override
        void f(){
            System.out.println("Overridden method");
        }
    };

    void f(){
        System.out.println("default behavior");
    }

    public static void main(String[] args) {
        for(OverrideConstantSpecific specific : values()){
            System.out.print(specific + "： ");
            specific.f();
        }
    }
}

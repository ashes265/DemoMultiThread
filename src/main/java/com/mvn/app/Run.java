package com.mvn.app;

public class Run implements java.lang.Runnable {
    @Override
    public void run() {
        try{
            Thread.sleep(12000);
            System.out.println("Error after 12s display");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

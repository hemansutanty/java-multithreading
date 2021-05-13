package com.threads.fundamentals.HandleException;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("Intentional exception");
            }
        });
        thread.setName("Misbehaving Thread");

        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Critical Error:"+t.getName()+"\t"+e.getMessage());
            }
        });

        thread.start();
    }
}

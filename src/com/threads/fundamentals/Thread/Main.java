package com.threads.fundamentals.Thread;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("We are in:"+Thread.currentThread().getName()+" thread");
                System.out.println("Thread Priority:"+Thread.currentThread().getPriority());
            }
        });
        thread.setName("New Worker Thread");
        thread.setPriority(Thread.MAX_PRIORITY);
        System.out.println("Current Thread Before thread start: "+Thread.currentThread().getName());
        thread.start();
        System.out.println("Current Thread After thread start: "+Thread.currentThread().getName());

        Thread.sleep(1000);
    }
}

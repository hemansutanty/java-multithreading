package com.threads.race.condition;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementCounter incrementingThread = new IncrementCounter(inventoryCounter);
        DecrementCounter decrementingThread = new DecrementCounter(inventoryCounter);

        incrementingThread.start(); decrementingThread.start();
        incrementingThread.join(); decrementingThread.join();

        System.out.println( inventoryCounter.getItems());
    }
    public static class IncrementCounter extends Thread{
        private InventoryCounter inventoryCounter;

        public IncrementCounter(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for(int i=0;i<10000;i++){
                inventoryCounter.increment();
            }
        }
    }
    public static class DecrementCounter extends Thread{
        private InventoryCounter inventoryCounter;

        public DecrementCounter(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for(int i=0;i<10000;i++){
                inventoryCounter.decrement();
            }
        }
    }
    private static class InventoryCounter{
        private int items = 0;
        public synchronized void increment(){
            items++;
        }
        public synchronized void decrement(){
            items--;
        }
        public synchronized int getItems(){
            return items;
        }
    }
}

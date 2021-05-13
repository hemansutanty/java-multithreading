package com.threads.coordination.join.power;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        BigInteger base1 = new BigInteger("2");
        BigInteger power1 = new BigInteger("10");

        BigInteger base2 = new BigInteger("3");
        BigInteger power2 = new BigInteger("10");

        PowerCalculatingThread thread1 = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread thread2 = new PowerCalculatingThread(base2, power2);
        thread1.setDaemon(true);thread2.setDaemon(true);
        thread1.start(); thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread1.getResult().add(thread2.getResult()));

    }
    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
           /*
           Implement the calculation of result = base ^ power
           */
            this.result = power(base, power);
        }

        public BigInteger power(BigInteger base, BigInteger power) {
            BigInteger tempResult = BigInteger.ONE;
            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) < 0; i = i.add(BigInteger.ONE)) {
                tempResult = tempResult.multiply(base);
            }
            return tempResult;
        }

        public BigInteger getResult() {
            return result;
        }

    }
}

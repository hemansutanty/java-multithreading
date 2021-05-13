package com.threads.coordination.join.factorial;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Long> numbers = Arrays.asList(10000000L, 3435L, 35435L, 2324L, 4656L, 23L, 5556L);
        List<FactorialThread> threads = new ArrayList<>();
        for(long input: numbers){
            threads.add(new FactorialThread(input));
        }
        for(Thread thread: threads){
            thread.setDaemon(true);
            thread.start();
        }
        for(Thread thread: threads){
            thread.join(2000);
        }
        for(int i=0;i<numbers.size();i++){
            if(threads.get(i).isfinished()){
                System.out.println("Factorial : "+ threads.get(i).getResult());
            }else{
                System.out.println("Processing Factorial");
            }
        }

    }
    private static class FactorialThread extends Thread{
        private long input;
        private BigInteger result;
        private boolean isfinished;

        public FactorialThread(long input) {
            this.input = input;
            this.result = result= BigInteger.ZERO;
            this.isfinished = false;
        }

        public void run(){
            this.result = factorial(input);
            this.isfinished = true;
        }

        public BigInteger factorial(long n){
            BigInteger tempResult = BigInteger.ONE;
            for(long i=n;i>0;i--){
                tempResult=tempResult.multiply(new BigInteger(Long.toString(i)));
            }
            return tempResult;
        }

        public BigInteger getResult() {
            return result;
        }

        public boolean isfinished() {
            return isfinished;
        }
    }
}

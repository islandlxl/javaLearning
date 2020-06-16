package com.islandlxl.javalearning.tuling.FromBook;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/*
@project:com.tuling.FromBook
@Title:TimeLock
@Auther:lxl
@create:2020/1/14,22:38
*/public class TimeLock implements Runnable{
   public static ReentrantLock lock=new ReentrantLock();
    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)){
                Thread.sleep(5000);
            }else{
                System.out.println("get lock fail");
            }

        }catch (InterruptedException e){
            e.printStackTrace();

        }finally {
            if (lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TimeLock timeLock=new TimeLock();
        Thread t1=new Thread(timeLock);
        Thread t2=new Thread(timeLock);
        t1.start();
        t2.start();
    }
}

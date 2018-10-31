package com;

public class Main {
    public static void main(String[] args) throws Exception {
        Resourse.i = 5;
        MyThread myThread = new MyThread();
        myThread.setName("one");
        MyThread myThread2 = new MyThread();
        myThread.start();
        myThread2.start();
        myThread.join();
        myThread2.join();
        System.out.println(Resourse.i);
    }
}

class MyThread extends Thread {
    Resourse resourse;

    @Override
    public void run() {
        Resourse.changeStaticI();
        new Resourse().changeI();
    }
}

class Resourse {
    static int i;

    public synchronized static void changeStaticI() {
        synchronized (Resourse.class) {
            int i = Resourse.i;
            if (Thread.currentThread().getName().equals("one")) {
                Thread.yield();
            }
            i++;
            Resourse.i = i;
        }
    }

    public void changeI() {
        synchronized (this) {
            int i = Resourse.i;
            if (Thread.currentThread().getName().equals("one")) {
                Thread.yield();
            }
            i++;
            Resourse.i = i;
        }
    }
}
package ru.nlvovski;

import ru.nlvovski.threading.MyThread;

import java.util.concurrent.Semaphore;

public class Main {
    public static synchronized void main(String[] args) throws InterruptedException {
        Semaphore sem0 = new Semaphore(1, true);
        Semaphore sem1 = new Semaphore(3, true);
        Semaphore sem2 = new Semaphore(3, true);
        Semaphore sem3 = new Semaphore(3, true);
        MyThread threadA = new MyThread("A", sem0);
        MyThread threadB = new MyThread("B", sem1);
        MyThread threadC = new MyThread("C", sem1);
        MyThread threadD = new MyThread("D", sem2);
        MyThread threadE = new MyThread("E", sem2);
        MyThread threadF = new MyThread("F", sem2);
        MyThread threadG = new MyThread("G", sem3);
        MyThread threadH = new MyThread("H", sem3);
        MyThread threadK = new MyThread("K", sem3);
        MyThread threadJ = new MyThread("J", sem1);
        MyThread threadI = new MyThread("I", sem3);

        threadA.start();

        Thread.sleep(100);
        System.out.println(" ");
        // Занимаем семафор 3, дабы поток I не смог войти в него.
        for(int i = 0; i < 3; i++) sem3.acquire();
        threadB.start();
        threadC.start();
        threadJ.start();
        threadI.start();

        Thread.sleep(100);
        System.out.println(" ");
        threadD.start();
        threadE.start();
        threadF.start();

        Thread.sleep(100);
        System.out.println(" ");
        // Освобождаем семафор 3.
        for(int i = 0; i < 3; i++) sem3.release();
        threadG.start();
        threadH.start();

        Thread.sleep(100);
        System.out.println(" ");
        threadK.start();
    }
}

package ru.nlvovski.threading;

import java.util.concurrent.Semaphore;

public class MyThread extends Thread{
    Semaphore sem;

    public MyThread(String name, Semaphore sem){
        super(name);
        this.sem = sem;
    }

    @Override
    public void run(){
        System.out.printf("Поток %s начался и ожидает семафор.\n", getName());
        try {
            // Захват семафора.
            // Если семафор занят, то поток "спит", пока не освободится место.
            sem.acquire();
            System.out.printf("Поток %s захватывает семафор.\n", getName());
            System.out.printf("Переменная семафора равна: %d. Поток %s выходит из семафора.\n",
                    (sem.drainPermits()), getName());
            // Выход из семафора.
            sem.release();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

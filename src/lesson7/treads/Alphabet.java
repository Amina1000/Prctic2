package lesson7.treads;

/**
 * homework lesson7.treads
 *
 * @author Amina
 * 10.05.2021
 */
/*
 Создать три потока,
 каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС).
 Используйте wait/notify/notifyAll.
 */
public class Alphabet {

    private char letter='A';
    private final Object mon = new Object();

    public void printA() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (letter != 'A') {
                        mon.wait();
                    }
                    System.out.print("A");
                    letter = 'B';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (letter != 'B') {
                        mon.wait();
                    }
                    System.out.print("B");
                    letter = 'C';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void printC() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (letter != 'C') {
                        mon.wait();
                    }
                    System.out.print("C");
                    letter = 'A';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

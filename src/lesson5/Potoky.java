package lesson5;

import java.util.Arrays;

/**
 * homework lesson5
 *
 * @author Amina
 * 24.03.2021
 */
public class Potoky {

    static final int size = 10000000;
    static final int h = size / 2;
    float[] arr = new float[size];
    long a;

    public void Massive1(){

        Arrays.fill(arr, 1);
        a = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(System.currentTimeMillis() - a);
    }
    public void Massive2(){

        Arrays.fill(arr, 1);
        a = System.currentTimeMillis();

        float[] a1 = new float[h];
        float[] a2 = new float[h];

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < a1.length; i++) {
                a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }});
        Thread t2 = new Thread(() ->{
        for (int i = 0; i < a2.length; i++) {
            a2[i] = (float)(a2[i] * Math.sin(0.2f + (i+h)/ 5) * Math.cos(0.2f + (i+h)/ 5) * Math.cos(0.4f + (i+h)/ 2));
        }});

       t1.start();
       t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.out.println(System.currentTimeMillis() - a);

    }
}

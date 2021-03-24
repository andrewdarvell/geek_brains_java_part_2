package lessons.five;

public class Main {

    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {
        methodOne();

        methodTwo();
        System.out.println("finish main");

    }

    public static void methodOne() {
        float[] arr = genArray(size);

        long startTime = System.currentTimeMillis();
        calculate(arr);
        long endTime = System.currentTimeMillis();
        System.out.println("Method one works : " + (endTime - startTime) + "ms");
    }

    public static void methodTwo() {

        float[] arr = genArray(size);
        float[] a1 = new float[h];
        float[] a2 = new float[h];

        long startTime = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Thread t1 = new Thread(()->calculate(a1));
        t1.start();
        Thread t2 = new Thread(()->calculate(a2));
        t2.start();

        //Ждём, когда потоки закончат считать
        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        long endTime = System.currentTimeMillis();

        System.out.println("Method two works : " + (endTime - startTime) + "ms");

    }

    public static void calculate(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));;
        }
        System.out.println("finish calculate "+ Thread.currentThread().getName());
    }

    public static float[] genArray(int size) {
        float[] result = new float[size];
        for (int i = 0; i < result.length; i++) {
            result[i] = 1;
        }
        return result;
    }
}

import java.sql.Time;

public class Main {
    static final int size = 10000000;
    static final int h = size / 2;
    static float[] arr = new float[size];

    public static void main(String[] args) {
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
m1(arr);

    }

    private static long m1(float[] arr) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long end = System.currentTimeMillis();
        System.out.printf("время 1 метода",end-start);
        return start;
    }

    private static long m2(float[] arr) {
        float[] arr1 = new float[h];
        float[] arr2 = new float[h];


        long start = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);
        Mthread t1 = new Mthread("arr1", arr1);
        Mthread t2 = new Mthread("arr2", arr2);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        arr1 = t1.getArr();
        arr2 = t2.getArr();

        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, arr1.length, arr2.length);

        long end = System.currentTimeMillis();

        System.out.printf("время 2 метода", end-start);

        return start;
    }

}


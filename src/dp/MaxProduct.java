package dp;

/**
 * Created by huanchen on 10/22/14.
 */
public class MaxProduct {

    public float maxProduct(float[] A) {
        float max = A[0];
        float min = A[0];
        float maxProduct = A[0];

        for(int i = 1; i < A.length; i ++) {
            float temp = max;
            max = Math.max(Math.max(A[i], max*A[i]), min*A[i]);
            min = Math.min(Math.min(A[i], min*A[i]), temp*A[i]);
            if(max > maxProduct)
                maxProduct = max;
        }
        return maxProduct;
    }

    public static void main(String[] args) {
        MaxProduct test = new MaxProduct();
        float[] A = new float[]{0.1f, 9, 0.2f, 0.3f, 10, 23, 2, 0.16f};
        float max = test.maxProduct(A);
        System.out.println(max);
    }
}

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] data = new int[n];

        for (int i = 0; i < n; i++) {
            data[i] = sc.nextInt();
        }

        int swapCount = 0;
        boolean canPrint = false;

        for (int i = n; i > 0; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    swapCount++;
                }
                if (swapCount == k) {
                    for (int t = 0; t < n; t++) {
                        System.out.printf("%d ", data[t]);
                    }
                    canPrint = true;
                    return;
                }
            }
        }

        if (!canPrint) {
            System.out.println(-1);
        }
    }
}

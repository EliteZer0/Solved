import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static final long MOD = 1_000_000_007L;
    static final int SIZE = 2;

    // 2x2 행렬 곱
    static long[][] mul(long[][] A, long[][] B) {
        long[][] C = new long[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                long sum = 0;
                for (int k = 0; k < SIZE; k++) {
                    sum = (sum + (A[i][k] * B[k][j]) % MOD) % MOD;
                }
                C[i][j] = sum;
            }
        }
        return C;
    }

    // 2x2 항등행렬 I
    static long[][] identity() {
        long[][] I = new long[SIZE][SIZE];
        I[0][0] = 1;
        I[1][1] = 1;
        return I;
    }

    // 분할정복 거듭제곱 M^exp
    static long[][] pow(long[][] M, long exp) {
        // exp == 0이면 I (곱셈의 항등원)
        if (exp == 0) return identity();
        // exp == 1이면 M 자체
        if (exp == 1) return M;

        // exp를 반으로 쪼개서 계산
        long[][] half = pow(M, exp / 2);

        // half * half 로 exp의 절반 결과를 제곱해 exp로 복원
        long[][] result = mul(half, half);

        // exp가 홀수면 M을 한 번 더 곱해야 M^exp가 됨
        if (exp % 2 == 1) result = mul(result, M);

        return result;
    }

    // F(n) 반환 (F0=0, F1=1)
    static long fib(long n) {
        if (n == 0) return 0;

        // 피보나치 변환 행렬
        long[][] M = {
            {1, 1},
            {1, 0}
        };

        // M^n = [[F_{n+1}, F_n], [F_n, F_{n-1}]]
        long[][] Mn = pow(M, n);
        return Mn[0][1];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        System.out.println(fib(n));
    }
}

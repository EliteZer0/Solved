import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] weight;
    static int count = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        weight = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }
        
        // 각 운동키트의 실제 효과 = 증가량 - 감소량
        int[] effects = new int[N];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            effects[i] = weight[i] - K;
            sum += effects[i];
        }
        
        if(sum<0){
            System.out.print(0);
            return;
        }
        
        boolean[] used = new boolean[N];
        int[] permutation = new int[N];
        generatePermutations(effects, used, permutation, 0);
        
        sb.append(count);
        System.out.print(sb.toString());
    }
    
    static void generatePermutations(int[] effects, boolean[] used, int[] perm, int depth) {
        if (depth == N) {
            // 현재 순열이 조건을 만족하는지 확인
            if (isValid(perm)) {
                count++;
            }
            return;
        }
        
        for (int i = 0; i < N; i++) {
            if (!used[i]) {
                used[i] = true;
                perm[depth] = effects[i];
                generatePermutations(effects, used, perm, depth + 1);
                used[i] = false;
            }
        }
    }
    
    static boolean isValid(int[] arr) {
        int currentWeight = 500;
        
        for (int i = 0; i < N; i++) {
            // i일차에 운동키트 사용 후 하루가 지난 후의 중량
            currentWeight += arr[i];
            
            // 중량이 500 미만이 되면 조건 위반
            if (currentWeight < 500) {
                return false;
            }
        }
        return true;
    }
}
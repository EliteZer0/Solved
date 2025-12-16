import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] pos = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            pos[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(pos);

        // 배열의 구간을 K개로 나눠서 그 차의 합이 가장 작은 경우를 구해야함.
        // 끊는 지점을 골라야하기 때문에 두점 사이의 간격 중에서 고른다고 생각하면 됨.
        // 가장 큰 간격들에서 끊으면 최솟값을 찾을 수 있음
        // 간격을 먼저 구하고 간격을 정렬한 뒤 N-K 개 만큼 앞에서 선택하면 됨

        int[] gap = new int[N - 1];
        
        for (int i = 0; i < N - 1; i++) {
            gap[i] = pos[i + 1] - pos[i];
        }

        Arrays.sort(gap);

        int sum = 0;
        for (int i = 0; i < N - K; i++) {
            sum += gap[i];
        }

        System.out.println(sum);
    }
}
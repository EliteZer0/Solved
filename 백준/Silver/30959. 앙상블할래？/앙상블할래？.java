import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] ans = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<M; i++){
            ans[i] = Integer.parseInt(st.nextToken());
        }

        int[][] models = new int[N][M];
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                 models[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 단일 모델 최고 정확도 계산
        int bestSingle = 0;
        for (int i = 0; i<N; i++) {
            int correct = 0;
            for (int j = 0; j<M; j++) {
                if (models[i][j] == ans[j]) correct++;
            }
            // 굳이 나누기 연산을 한 번 더 할 이유가 없음
            // 어차피 결과는 1,0으로 출력할 거니까
            if (correct > bestSingle) bestSingle = correct;
        }

        // 홀수 개(>=3) 모델 조합으로 하드 보팅 앙상블 검사
        // 어떤 조합이라도 bestSingle보다 정확도가 크면 1 출력 후 종료
        // 0부터 2^N - 1까지의 정수를 이용해서 모든 부분집합 표현
        int limit = 1 << N;
        for (int mask = 1; mask<limit; mask++) {
            int k = Integer.bitCount(mask);
            // 짝수개를 골랐거나 1개면 스킵
            if ((k&1) == 0 || k<3) continue;
            int correct = 0;
            
            for (int j = 0; j < M; j++) {
                int sum = 0;
                for (int i = 0; i < N; i++) {
                    if ((mask & (1 << i)) != 0) {
                        sum += models[i][j];
                    }
                }
                // 각 항목 j마다, 선택된 모델들의 합계를 구해 과반이면 1, 아니면 0
                int vote = (sum > k / 2) ? 1 : 0;
                if (vote == ans[j]) correct++;
            }

            if (correct > bestSingle) {
                System.out.println(1);
                return;
            }
        }

        System.out.println(0);
    }
}
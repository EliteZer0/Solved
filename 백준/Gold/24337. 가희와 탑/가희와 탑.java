import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        if (a+b-1 > N) {
            System.out.println(-1);
            return;
        }

        int max = Math.max(a, b);
        int extra = N-(a+b-1);

        // a가 1이면 제일 왼쪽 건물이 최고 높이여야 다른 건물이 안 보임
        if(a == 1){
            sb.append(max).append(" ");
            // 사전순으로 작은거 출력하니까 나머지 1 채워넣음
            for (int i = 0; i < extra; i++) sb.append(1).append(" ");
            // b 조건을 위해 나머지 오른쪽에 1씩 감소하면서 배치
            for (int x = b - 1; x >= 1; x--) sb.append(x).append(" ");
        }
        
        else {
            // 사전순 최소를 위해 extra개의 1을 최대한 앞에 배치
            sb.append(1).append(' ');
            for (int i = 0; i < extra; i++) sb.append(1).append(' ');
            
            // 평범하게 1부터 증가해서 a 조건 1 전까지 채워넣음
            for (int x = 2; x <= a - 1; x++) sb.append(x).append(" ");

            // a의 마지막 개수를 만족하기 위한 최고 높이 건물 배치
            sb.append(max).append(" ");

            // b 조건을 위해 나머지 오른쪽에 1씩 감소하면서 배치
            for (int x = b - 1; x >= 1; x--) sb.append(x).append(" ");
        }
        
        System.out.println(sb.toString());
    }
}
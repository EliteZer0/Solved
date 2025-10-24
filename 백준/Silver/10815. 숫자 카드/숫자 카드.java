import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // 상근이가 가진 카드 개수
        int N = Integer.parseInt(br.readLine());
        
        // HashSet에 상근이의 카드 저장
        HashSet<Integer> cards = new HashSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards.add(Integer.parseInt(st.nextToken()));
        }
        
        // 확인할 숫자 개수
        int M = Integer.parseInt(br.readLine());
        
        // 각 숫자가 있는지 확인
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (cards.contains(num)) {
                sb.append(1).append(" ");
            } else {
                sb.append(0).append(" ");
            }
        }
        
        System.out.println(sb.toString());
    }
}
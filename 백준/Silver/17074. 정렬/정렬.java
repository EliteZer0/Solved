import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        // 각 위치까지 비내림차순인지 체크
        boolean[] leftSorted = new boolean[n];
        leftSorted[0] = true;
        for (int i = 1; i < n; i++) {
            leftSorted[i] = leftSorted[i-1] && arr[i-1] <= arr[i];
        }
        
        // 각 위치부터 끝까지 비내림차순인지 체크
        boolean[] rightSorted = new boolean[n];
        rightSorted[n-1] = true;
        for (int i = n-2; i >= 0; i--) {
            rightSorted[i] = rightSorted[i+1] && arr[i] <= arr[i+1];
        }
        
        int cnt = 0;
        
        // 각 위치의 원소를 제거해보기
        for (int i = 0; i < n; i++) {
            boolean valid = true;
            
            // i번째 원소를 제거했을 때
            if (i == 0) {
                // 첫 번째 원소 제거: 오른쪽만 확인
                valid = rightSorted[1];
            } else if (i == n-1) {
                // 마지막 원소 제거: 왼쪽만 확인
                valid = leftSorted[n-2];
            } else {
                // 중간 원소 제거: 왼쪽, 오른쪽, 그리고 연결 확인
                valid = leftSorted[i-1] && rightSorted[i+1] && arr[i-1] <= arr[i+1];
            }
            
            if (valid) {
                cnt++;
            }
        }
        
        sb.append(cnt);
        System.out.println(sb.toString());
    }
}
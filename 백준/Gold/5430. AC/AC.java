import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int t = Integer.parseInt(br.readLine());
        
        while (t-- > 0) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String arrayStr = br.readLine();

            Deque<Integer> deque = new ArrayDeque<>();
            if (n > 0) {
                // [1,2,3,4] 형태에서 숫자만 추출
                String numbers = arrayStr.substring(1, arrayStr.length() - 1);
                String[] nums = numbers.split(",");
                for (String num : nums) {
                    deque.add(Integer.parseInt(num));
                }
            }
            
            boolean isReversed = false; // 뒤집힌 상태인지 확인
            boolean error = false;
            
            // 함수 실행
            for (char c : p.toCharArray()) {
                if (c == 'R') {
                    isReversed = !isReversed; // 실제로 뒤집지 않고 상태만 변경
                } else if (c == 'D') {
                    if (deque.isEmpty()) {
                        error = true;
                        break;
                    }
                    // 뒤집힌 상태에 따라 앞/뒤에서 제거
                    if (isReversed) {
                        deque.removeLast();
                    } else {
                        deque.removeFirst();
                    }
                }
            }
            
            if (error) {
                sb.append("error\n");
            } else {
                sb.append("[");
                if (!deque.isEmpty()) {
                    List<Integer> result = new ArrayList<>(deque);
                    if (isReversed) {
                        Collections.reverse(result);
                    }
                    for (int i = 0; i < result.size(); i++) {
                        if (i > 0) sb.append(",");
                        sb.append(result.get(i));
                    }
                }
                sb.append("]\n");
            }
        }
        
        System.out.print(sb.toString());
    }
}
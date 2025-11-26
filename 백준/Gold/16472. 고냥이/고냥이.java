import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        String s = br.readLine();
        
        int[] count = new int[26];
        int left = 0;
        int maxLength = 0;
        int distinctCount = 0;
        
        for (int right = 0; right < s.length(); right++) {
            // 오른쪽 포인터의 문자 추가
            int rightChar = s.charAt(right) - 'a';
            if (count[rightChar] == 0) {
                distinctCount++;
            }
            count[rightChar]++;
            
            // 알파벳 종류가 N개를 초과하면 왼쪽 포인터 이동
            while (distinctCount > N) {
                int leftChar = s.charAt(left) - 'a';
                count[leftChar]--;
                if (count[leftChar] == 0) {
                    distinctCount--;
                }
                left++;
            }
            
            // 최대 길이 갱신
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        System.out.println(maxLength);
    }
}
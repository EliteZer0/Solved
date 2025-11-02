import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        Map<String, Integer> wordCnt = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            if (word.length() >= M) {
                wordCnt.put(word, wordCnt.getOrDefault(word, 0) + 1);
            }
        }
        
        List<String> words = new ArrayList<>(wordCnt.keySet());

        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String w1, String w2) {
                // 빈도수 내림차순
                int cnt1 = wordCnt.get(w1);
                int cnt2 = wordCnt.get(w2);
                if (cnt1 != cnt2) {
                    return cnt2 - cnt1;
                }
                
                // 길이 내림차순
                if (w1.length() != w2.length()) {
                    return w2.length() - w1.length();
                }
                
                // 사전순 오름차순
                return w1.compareTo(w2);
            }
        });
        
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word).append("\n");
        }
        System.out.println(sb.toString());
    }
}
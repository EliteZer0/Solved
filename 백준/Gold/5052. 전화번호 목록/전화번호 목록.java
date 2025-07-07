import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEnd; // 이 노드에서 번호가 끝나는가

        public TrieNode() {
            this.isEnd = false;
        }
    }

    static class Trie {
        TrieNode root = new TrieNode();

        // 번호 삽입 중 접두어 문제 발생 시 false 리턴
        public boolean insert(String number) {
            TrieNode node = root;

            for (int i = 0; i < number.length(); i++) {
                char c = number.charAt(i);

                if (!node.children.containsKey(c)) {
                    node.children.put(c, new TrieNode());
                }

                node = node.children.get(c);

                // 삽입 도중 누군가의 번호가 여기서 끝났다면 => 그 번호가 현재 번호의 접두어임
                if (node.isEnd) {
                    return false;
                }
            }

            // 삽입이 끝난 후 자식이 남아있으면 => 현재 번호가 다른 번호의 접두어임
            if (!node.children.isEmpty()) {
                return false;
            }

            node.isEnd = true;
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] numbers = new String[n];
            for (int i = 0; i < n; i++) {
                numbers[i] = br.readLine();
            }

            Arrays.sort(numbers); // 문자열 정렬: 짧은 것부터 정렬되어 접두어 먼저 삽입되도록

            Trie trie = new Trie();
            boolean isConsistent = true;

            for (String number : numbers) {
                if (!trie.insert(number)) {
                    isConsistent = false;
                    break;
                }
            }

            sb.append(isConsistent ? "YES" : "NO").append("\n");
        }

        System.out.print(sb.toString());
    }
}

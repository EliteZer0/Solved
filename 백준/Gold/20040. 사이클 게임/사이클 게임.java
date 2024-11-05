import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//양방향 그래프
//사이클 판단 -> 크루스칼은 안됨
//DFS로도 가능함 -> 시간 초과
//유니온 파인드
public class Main {
    static int[] parent;
    
    private static byte[] buffer = new byte[1 << 16];
    private static int bufferPointer = 0, bytesRead = 0;

    private static int read() throws IOException {
        if (bufferPointer == bytesRead) {
            bufferPointer = 0;
            bytesRead = System.in.read(buffer);
            if (bytesRead == -1) return -1;
        }
        return buffer[bufferPointer++];
    }

    private static int nextInt() throws IOException {
        int result = 0;
        int c = read();
        
        // 공백 문자 건너뛰기
        while (c <= ' ') c = read();
        
        // 양수 또는 음수 판별
        boolean negative = (c == '-');
        if (negative) c = read();

        // 숫자 부분 읽기
        do {
            result = result * 10 + (c - '0');
            c = read();
        } while (c >= '0' && c <= '9');
        
        return negative ? -result : result;
    }
    
    // 부모 노드를 찾는 함수
    static int find(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    // 두 노드를 합치는 함수
    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB)
            return true; // 사이클 발견
        parent[rootB] = rootA;
        return false;
    }

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int m = nextInt();

        // 부모 배열 초기화
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int result = 0;
        for (int i = 1; i <= m; i++) {
            int a = nextInt();
            int b = nextInt();

            if (union(a, b)) {
                result = i;
                break;
            }
        }

        System.out.println(result);
    }
}
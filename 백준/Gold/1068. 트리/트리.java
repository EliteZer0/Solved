import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        
        // 각 노드의 부모 정보
        int[] parent = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int root = -1;
        for (int i = 0; i < N; i++) {
            parent[i] = Integer.parseInt(st.nextToken());
            if (parent[i] == -1) {
                root = i;
            }
        }
        
        int deleteNode = Integer.parseInt(br.readLine());
        
        // 삭제할 노드가 루트인 경우
        if (deleteNode == root) {
            sb.append(0);
            System.out.println(sb.toString());
            return;
        }
        
        // 삭제된 노드 체크
        boolean[] deleted = new boolean[N];
        markDeleted(deleteNode, parent, deleted, N);
        
        // 각 노드의 자식 수 계산
        int[] childCount = new int[N];
        for (int i = 0; i < N; i++) {
            if (!deleted[i] && parent[i] != -1 && !deleted[parent[i]]) {
                childCount[parent[i]]++;
            }
        }
        
        // 리프 노드 개수 세기
        int leafCount = 0;
        for (int i = 0; i < N; i++) {
            if (!deleted[i] && childCount[i] == 0) {
                leafCount++;
            }
        }
        
        sb.append(leafCount);
        System.out.println(sb.toString());
    }
    
    // 삭제할 노드와 그 자손들을 모두 표시
    static void markDeleted(int node, int[] parent, boolean[] deleted, int N) {
        deleted[node] = true;
        
        // 해당 노드를 부모로 가지는 모든 노드를 찾아 재귀적으로 삭제
        for (int i = 0; i < N; i++) {
            if (!deleted[i] && parent[i] == node) {
                markDeleted(i, parent, deleted, N);
            }
        }
    }
}
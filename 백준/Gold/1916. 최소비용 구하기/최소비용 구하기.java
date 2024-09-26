import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//다익스트라
class Node implements Comparable<Node> {
    int end;
    int weight;
 
    Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
 
    @Override
    public int compareTo(Node o) {
        return weight - o.weight;
    }
}

public class Main {
	static int N, M;
    static ArrayList<ArrayList<Node>> arr;
    static int[] dis;
    static boolean[] check;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
 
        arr = new ArrayList<>();
        dis = new int[N + 1];
        check = new boolean[N + 1];
 
        Arrays.fill(dis, Integer.MAX_VALUE);
 
        for (int i = 0; i <= N; i++) {
            arr.add(new ArrayList<>());
        }
 
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
 
            arr.get(start).add(new Node(end, weight));
        }
 
        st = new StringTokenizer(br.readLine());
        int startPos = Integer.parseInt(st.nextToken());
        int endPos = Integer.parseInt(st.nextToken());
 
        System.out.println(dijkstra(startPos, endPos));
    }
 
    public static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] check = new boolean[N + 1];
        pq.offer(new Node(start, 0));
        dis[start] = 0;
 
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.end;
 
            if (!check[cur]) {
                check[cur] = true;
 
                for (Node node : arr.get(cur)) {
                    if (!check[node.end] && dis[node.end] > dis[cur] + node.weight) {
                        dis[node.end] = dis[cur] + node.weight;
                        pq.add(new Node(node.end, dis[node.end]));
                    }
                }
            }
        }
        return dis[end];
    }
}
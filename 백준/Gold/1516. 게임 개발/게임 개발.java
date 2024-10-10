import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        // 길이가 N인 이유 : 인덱스를 1부터 사용하기 위해
        int[] edgeCount =new int[N+1];

        // 위상정렬에 사용할 그래프 2차원 리스트로 구현
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < N+1; i++) {
            graph.add(new ArrayList<Integer>());
        }
        
        int[] time = new int[N+1];
        int[] totalTime = new int[N+1];
        // 그래프 각 노드별 인접한 노드정보 초기화
        for (int i = 1; i < N+1; i++) {
        	st = new StringTokenizer(br.readLine());
        	//소요 시간 입력
        	int buildTime = Integer.parseInt(st.nextToken());
        	time[i] = buildTime;
        	totalTime[i] = buildTime;
//        	System.out.println("time : " + time[i]);
        	
        	while(st.hasMoreTokens()) {
        		//인접 노드 입력
        		int adj = Integer.parseInt(st.nextToken());
        		//입력된 인접 노드 값이 -1이면 탈출
        		if(adj == -1) break;
//        		System.out.println("adj : " + adj);
        		graph.get(adj).add(i);//실수로 i, adj로 써서 그래프 방향 바뀌는 바람에 아주 우당탕이었음... 주의합시다
        		//진입차수 증가
        		edgeCount[i]++;
//        		System.out.println("edgeCount: " + edgeCount[i]);
        	}
		}

        // 위상정렬에 사용할 큐
        Queue<Integer> que = new LinkedList<>();

        // 진입차수가 0인 값 큐에 넣기
        for (int i = 1; i < edgeCount.length; i++) {
            if (edgeCount[i] == 0) {
                que.offer(i);
            }
        }
        // 큐가 빌 때까지 반복
        while (!que.isEmpty()) {
            // 큐에서 노드번호 꺼내기
            int nodeNo = que.poll();
//            System.out.println("node NO : " + nodeNo);
            // 꺼낸 노드의 인접한 노드들 찾기
            ArrayList<Integer> adjNodes = graph.get(nodeNo);
            // 인접한 노드의 개수만큼 반복문 실행
            for (int adjNode : adjNodes) {
                // 인접한 노드 진입차수 갱신
//            	System.out.println("인접 노드 : " + ajdNodes.get(i));
                edgeCount[adjNode] -- ;
                // 인접 노드 시간에 시간 더하기. DP로 비교함.
                totalTime[adjNode] = Math.max(totalTime[adjNode], totalTime[nodeNo] + time[adjNode]);
                // 갱신된 노드의 진입차수가 0이면 큐에 노드 넣기
                if (edgeCount[adjNode] == 0) {
                    que.offer(adjNode);
                }
            }
        }

        for (int i = 1; i < time.length; i++) {
			sb.append(totalTime[i]).append("\n");
		}
        
        System.out.print(sb.toString());
    }
}
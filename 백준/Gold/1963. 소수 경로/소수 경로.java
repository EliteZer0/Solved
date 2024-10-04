import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[] notPrime = new boolean[10000];
	static boolean[] visited;
	static int[] count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		
		int T = Integer.parseInt(br.readLine());
		
		findPrimeNumber();
		
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			visited = new boolean[10000];
			count = new int[10000];
			
			bfs(a, b);
			
			System.out.println(count[b]);
		}
	}
	
    public static void bfs(int a, int b) {
    	Queue<Integer> que = new LinkedList<>();
    	que.offer(a);
    	visited[a] = true;
    	while(!que.isEmpty()) {
    		int start = que.poll();
    		for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 10; j++) {
					if(i == 0 && j == 0)continue;
					
					int k = change(start, i, j);	
					
					if(visited[k]) continue;
					if(notPrime[k]) continue;
					
					que.offer(k);
					visited[k] = true;
					count[k] = count[start]+1;
				}
			}
    	}
	}


	private static int change(int start, int i, int j) {
		StringBuilder sb = new StringBuilder(String.valueOf(start));
		sb.setCharAt(i, (char) (j + '0'));
		return Integer.parseInt(sb.toString());
	}

	public static void findPrimeNumber() {
        int N = 9999;
        
        notPrime[0] = notPrime[1] = true;
        
        for(int i=2; i*i<=N; i++){
            if(!notPrime[i]){
            	for(int j=i*i; j<=N; j+=i) notPrime[j] = true;                
            }        
        }            
    }
}
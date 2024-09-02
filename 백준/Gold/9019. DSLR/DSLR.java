import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited;
	static String[] command;
	static int current;
	static Queue<Integer> que;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int answer = Integer.parseInt(st.nextToken());
			visited = new boolean[10000];
			command = new String[10000];
			check(n, answer);
			System.out.println(command[answer]);
		}
	}

	static void check(int n, int answer) {
		que = new LinkedList<>();
		visited[n] = true;
		command[n] = "";
		que.add(n);
		
		while(!que.isEmpty() && !visited[answer]) {
			current = que.poll();
			int d = D(current);
			int s = S(current);
			int l = L(current);
			int r = R(current);
			
			if(!visited[d]) {
				que.add(d);
				visited[d] = true;
				command[d] = command[current]+"D";
			}
			if(!visited[s]) {
				que.add(s);
				visited[s] = true;
				command[s] = command[current]+"S";
			}
			if(!visited[l]) {
				que.add(l);
				visited[l] = true;
				command[l] = command[current]+"L";
			}
			if(!visited[r]) {
				que.add(r);
				visited[r] = true;
				command[r] = command[current]+"R";
			}
		}
	}

	static int D(int n) {
		return (n*2)%10000;
	}
	
	static int S(int n) {
		if(n == 0) {
			return 9999;
		}else {
			return n-1;
		}
	}
	
	static int L(int n) {
		return (n/1000) + (n%1000/100)*1000 + (n%100/10)*100 + (n%10)*10;		
	}
	
	static int R(int n) {
		return (n/1000)*100 + (n%1000/100)*10 + (n%100/10) + (n%10)*1000;
	}
}
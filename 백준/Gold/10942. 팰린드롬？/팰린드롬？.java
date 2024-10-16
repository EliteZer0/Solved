import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Question {
	int s;
	int e;
	
	public Question(int s, int e) {
		this.s = s;
		this.e = e;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N+1; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		
		Queue<Question> q = new LinkedList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			q.offer(new Question(start, end));
		}
		
		boolean isPal = true;
		
		while(!q.isEmpty()) {
			Question cur = q.poll();
			int start = cur.s;
			int end = cur.e;
			
			if((end-start) == 0) {
				isPal = true;
			}
			else if((end-start) % 2 == 0) {
				for (int i = 0; i <(end-start)/2 ; i++) {
					if(nums[start+i] != nums[end-i]) {
						isPal = false;
						break;
					}
					if(nums[start+i] == nums[end-i]) {
						isPal = true;
					}
				}
			}
			else if((end-start) % 2 != 0) {
				for (int i = 0; i <(end-start+1)/2 ; i++) {
					if(nums[start+i] != nums[end-i]) {
						isPal = false;
						break;
					}
					if(nums[start+i] == nums[end-i]) {
						isPal = true;
					}
				}
			}
			
			if(isPal) {
				sb.append(1).append("\n");
			}else {
				sb.append(0).append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}
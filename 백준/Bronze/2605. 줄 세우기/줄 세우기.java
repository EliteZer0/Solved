import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] line = new int[N];
		int[] ticketNum = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {	
			ticketNum[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			if(line[N-1 - ticketNum[i]] != 0) {
				for(int j = 1; j<N-1 - ticketNum[i]+1; j++) {
					if(line[j] != 0) {
						line [j-1] = line[j];
					}
				}
				line[N-1 - ticketNum[i]] = i+1;
			}else if(line[N-1 - ticketNum[i]] == 0){
				line[N-1 - ticketNum[i]] = i+1;
			}
		}
		
		for (int i = 0; i < N; i++) {
			System.out.printf("%d ", line[i]);
		}
	}
}
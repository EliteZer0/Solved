import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = 9;
		int[] height = new int[n];
		for (int i = 0; i < n; i++) {
			height[i] = Integer.parseInt(br.readLine());
		}
		
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum+=height[i];
		}
		
		//9개 합에서 2개 빼기
		loop :
		for (int i = 0; i < n-1; i++) {
			for(int j = 1; j<n; j++) {
				sum -= (height[i]+height[j]);
				if(sum == 100) {
					height[i] = 0;
					height[j] = 0;
					break loop;
				}else {
					sum += (height[i]+height[j]);
				}
			}
		}
		
		List<Integer> sevens = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if(height[i] != 0) {
				sevens.add(height[i]);
			}
		}
		
		Collections.sort(sevens);
		for (int i = 0; i < sevens.size(); i++) {
			System.out.printf("%d\n",sevens.get(i));
		}
	}
}

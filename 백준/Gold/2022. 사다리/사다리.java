import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		double x = Double.parseDouble(st.nextToken());
		double y = Double.parseDouble(st.nextToken());
		double c = Double.parseDouble(st.nextToken());
		
		//이분 탐색을 돌리기 위해 범위를 설정해야하는데 밑변은 무조건 빗변보다 짧음. 그래서 right 값을 빗변의 길이 중 가장 짧은 것으로 설정.
		double left = 0;
		double right = Math.min(x, y);
		
		while(right - left >= 0.001) {
			double width = (left+right)/2;
			double h1 = Math.sqrt(Math.pow(x, 2) - Math.pow(width, 2));
			double h2 = Math.sqrt(Math.pow(y, 2) - Math.pow(width, 2));
			double temp = (h1*h2)/(h1+h2);
			//temp의 길이가 c보다 크거나 같게 나왔다는 이야기는 h1, h2의 값이 더 작아야한다는 이야기. 그러므로 width의 값이 증가해야함.
			if(temp>=c) left = width;
			else right = width;
		}
		
		System.out.println(String.format("%.3f", right));
	}
}
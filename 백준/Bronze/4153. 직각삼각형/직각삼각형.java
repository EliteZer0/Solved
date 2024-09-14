import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int a, b, c;
	static boolean isRight;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while(true) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			if(end()) break;
			isRight = false;
			pythagoras();
			
			if(isRight) {
				System.out.println("right");
			}else {
				System.out.println("wrong");
			}
		}
	}
	
	static boolean end() {
		return a == 0 || b == 0 || c == 0;
	}

	static void pythagoras() {
		int hypotenuse = Math.max(a, Math.max(b, c));
		if(hypotenuse == a) {
			if(Math.pow(a, 2) == Math.pow(b, 2)+ Math.pow(c, 2)) isRight = true;
		}
		if(hypotenuse == b) {
			if(Math.pow(b, 2) == Math.pow(a, 2)+ Math.pow(c, 2)) isRight = true;
		}
		if(hypotenuse == c) {
			if(Math.pow(c, 2) == Math.pow(a, 2)+ Math.pow(b, 2)) isRight = true;
		}
	}	
}
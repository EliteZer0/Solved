import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static double min = Double.MAX_VALUE;
	static coordinate A;
	static coordinate B;
	static coordinate C;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int ax = Integer.parseInt(st.nextToken());
		int ay = Integer.parseInt(st.nextToken());
		int az = Integer.parseInt(st.nextToken());
		int bx = Integer.parseInt(st.nextToken());
		int by = Integer.parseInt(st.nextToken());
		int bz = Integer.parseInt(st.nextToken());
		int cx = Integer.parseInt(st.nextToken());
		int cy = Integer.parseInt(st.nextToken());
		int cz = Integer.parseInt(st.nextToken());
		
		A = new coordinate(ax, ay, az);
		B = new coordinate(bx, by, bz);
		C = new coordinate(cx, cy, cz);
		
		Search(A, B);
		
		System.out.println(min);		
	}
	
	static void Search(coordinate start, coordinate end) {
		double temp = Math.sqrt(Math.pow(start.x - end.x, 2) + Math.pow(start.y - end.y, 2) + Math.pow(start.z - end.z, 2));		

		if(-1*1e-6 <= temp && temp <= 1e-6) return;
		
		coordinate key = new coordinate((start.x+end.x)/2, (start.y+end.y)/2, (start.z+end.z)/2);
		double length = Math.sqrt(Math.pow(key.x - C.x, 2) + Math.pow(key.y - C.y, 2) + Math.pow(key.z - C.z, 2));
		
		min = Math.min(min, length);
		
		double startC = Math.sqrt(Math.pow(start.x - C.x, 2) + Math.pow(start.y - C.y, 2) + Math.pow(start.z - C.z, 2));
		double endC = Math.sqrt(Math.pow(end.x - C.x, 2) + Math.pow(end.y - C.y, 2) + Math.pow(end.z - C.z, 2));
	
		if(startC > endC) {
			Search(key, end);
		}else {
			Search(start, key);
		}
	}
}

class coordinate {
	double x;
	double y;
	double z;
	public coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int i = 0; i<T; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            int distancePow = (int)(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));	// 중점간 거리 distance의 제곱 
 
 
    		// case 1 : 중점이 같으면서 반지름도 같을 경우
    		if(x1 == x2 && y1 == y2 && r1 == r2) sb.append(-1);
    		// case 2-1 : 두 원의 반지름 합보다 중점간 거리가 더 길 때 
    		else if(distancePow > Math.pow(r1 + r2, 2)) sb.append(0);
    		// case 2-2 : 원 안에 원이 있으나 내접하지 않을 때 
    		else if(distancePow < Math.pow(r2 - r1, 2)) sb.append(0);
    		// case 3-1 : 내접할 때 
    		else if(distancePow == Math.pow(r2 - r1, 2)) sb.append(1);
    		// case 3-2 : 외접할 때 
    		else if(distancePow == Math.pow(r1 + r2, 2)) sb.append(1);
    		else sb.append(2);
            
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
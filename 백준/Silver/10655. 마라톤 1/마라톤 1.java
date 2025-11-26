import java.util.*;
import java.lang.*;
import java.io.*;

class Point {
    int x, y;
    
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Main {  
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        Point[] points = new Point[N];
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }
        
        int totalDis = 0;
        for (int i = 0; i < N - 1; i++) {
            totalDis += dis(points[i], points[i + 1]);
        }
        
        int maxSaved = 0;
        for (int i = 1; i < N - 1; i++) {
            int original = dis(points[i - 1], points[i]) + dis(points[i], points[i + 1]);
            int skipped = dis(points[i - 1], points[i + 1]);
            int saved = original - skipped;
            
            maxSaved = Math.max(maxSaved, saved);
        }
        
        System.out.println(totalDis - maxSaved);
    }

    static int dis(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
}
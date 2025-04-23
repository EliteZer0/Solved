import java.util.*;
import java.io.*;

public class Main {
    static class Cordinates implements Comparable<Cordinates>{
        int x;
        int y;
        public Cordinates(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Cordinates o){
            if(this.y != o.y) return Integer.compare(this.y, o.y);
            return Integer.compare(this.x, o.x);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        List<Cordinates> cordinates = new ArrayList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            cordinates.add(new Cordinates(x, y));
        }

        Collections.sort(cordinates);

        StringBuilder sb = new StringBuilder();
        for(Cordinates c : cordinates){
            sb.append(c.x).append(" ").append(c.y).append("\n");
        }
        System.out.print(sb.toString());
    }
}
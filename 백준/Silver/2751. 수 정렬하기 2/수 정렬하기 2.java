import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> nums = new PriorityQueue<>();
        for(int i = 0; i<N; i++){
            nums.offer(Integer.parseInt(br.readLine()));
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++){
            sb.append(nums.poll()).append("\n");
        }
        System.out.println(sb.toString());
    }
}
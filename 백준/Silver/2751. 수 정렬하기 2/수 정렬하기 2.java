import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] nums;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        for(int i = 0; i<N; i++){
            nums[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(nums);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++){
            sb.append(nums[i]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
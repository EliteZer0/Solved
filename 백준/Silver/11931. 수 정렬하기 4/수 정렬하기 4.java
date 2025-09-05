import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        
        for(int i = 0; i<N; i++){
            nums[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(nums);

        StringBuilder sb = new StringBuilder();

        for(int i = N-1; i>=0; i--){
            sb.append(nums[i]).append("\n");
        }
        
        System.out.println(sb.toString());
    }
}
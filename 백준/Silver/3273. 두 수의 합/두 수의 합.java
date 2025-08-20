import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);
        
        int x = Integer.parseInt(br.readLine());
        int cnt = 0;
        int left = 0, right = n - 1;
        
        while(left < right) {
            int sum = nums[left] + nums[right];
            if(sum == x) {
                cnt++;
                left++;
                right--;
            } else if(sum < x) {
                left++;
            } else {
                right--;
            }
        }
        
        System.out.print(cnt);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] data = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0;
        boolean isPrintable = false;
        int maxIndex = 0;
        int firstNum = 0;
        int secondNum = 0;

        for (int i = n-1; i > 0; i--) {
            maxIndex = i;
            
            for (int j = 0; j < i+1; j++) {
                if(data[maxIndex]<data[j]){
                    maxIndex = j;
                }
            }

            if(maxIndex != i){
                int temp = data[i];
                data[i] = data[maxIndex];
                data[maxIndex] = temp;
                firstNum = data[maxIndex];
                secondNum = data[i];
                count ++; 
            }

            if(count == k){
                isPrintable = true;
                break;
            }
        }

        if(isPrintable){
            System.out.println(firstNum+" "+secondNum);
        }else{
            System.out.println(-1);
        }
    }
}
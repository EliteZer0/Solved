import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] alphabetNum;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        alphabetNum = new int[26];
        int answer =0;
        int k=9;
        for(int i=0; i<n; i++){
            alphabetToNumber(br.readLine());
        }
        Arrays.sort(alphabetNum);

        for(int i=alphabetNum.length-1; i>=0; i--){
            if(alphabetNum[i] == 0) break;
            else{
                answer += alphabetNum[i]*k;
                k--;
            }
        }
        System.out.println(answer);
    }
    static void alphabetToNumber(String input){
        for(int i=0; i<input.length(); i++){
        	alphabetNum[(int)input.charAt(i)-65] += Math.pow(10,input.length()-(i+1));
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        String input = br.readLine().trim();
        char[] inequality = input.replace(" ", "").toCharArray();
        
        int[] maxArr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] minArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        //max 값 정렬 로직
        for (int i = 0; i < k; i++) {
            if (inequality[i] == '<') {
                for (int j = i + 1; j <= k; j++) {
                    if (j == k || inequality[j] == '>') {
                        Arrays.sort(maxArr, i, j + 1);
                        break;
                    }
                }
            }
        }
        //min 값 정렬 로직
        for (int i = 0; i < k; i++) {
            if (inequality[i] == '>') {
                for (int j = i + 1; j <= k; j++) {
                    if (j == k || inequality[j] == '<') {
                    	int temp = minArr[j];
						for (int l = i; l <=j; l++) {
							minArr[l] = temp--;
						}
						i = j;
                        break;
                    }
                }
            }
        }
        StringBuilder max = new StringBuilder();
        for (int i = 0; i <= k; i++) {
            max.append(maxArr[i]);
        }
        System.out.println(max.toString());
        
        StringBuilder min = new StringBuilder();
        for (int i = 0; i <= k; i++) {
            min.append(minArr[i]);
        }
        System.out.println(min.toString());
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());
		
		//최댓값 저장할 변수
		int max = 0;
        int[][] stromMap = new int[r2-r1+1][c2-c1+1];
        for(int i=r1; i<r2+1; i++){
            for(int j=c1; j<c2+1; j++){
            	stromMap[i-r1][j-c1] = print(i, j);
                max = Math.max(stromMap[i-r1][j-c1], max);
            }
        }
        
        // 오른쪽 정렬 위한 최댓값 길이 찾기
        int maxLength = String.valueOf(max).length();
        
        for(int i=0; i<r2-r1+1; i++){
            for(int j=0; j<c2-c1+1; j++){
                System.out.printf("%"+maxLength+"d ", stromMap[i][j]);
            }
            System.out.println();
        }
    }
	
	//테두리 n = |boardIdx| (2n-1)^2 +1 ~ (2n+1)^2 -> 2~9 10~25 26~49...
	static int print(int row, int col) {
		int boardIdx = Math.max(Math.abs(row), Math.abs(col));
		
		int min = (int)Math.pow(2*boardIdx-1, 2) + 1;
		//(0, boardIdx) (-boardIdx, 0) (0, -boardIdx) (boardIdx, 0) 규칙 Min+idx-1 Min+3idx-1 Min+5idx-1 Min+7idx-1
		if(row == boardIdx) {
			return min + 7*boardIdx -1 + col;
		}
		if(col == -boardIdx) {
			return min + 5*boardIdx -1 + row;
		}
		if(row == -boardIdx) {
			return min + 3*boardIdx -1 - col;
		}
		if(col == boardIdx) {
			return min + boardIdx -1 -row;
		}
		
		return 0;
	}
	
}
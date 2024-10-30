import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//2차원으로 맵 만들기 시도 -> 역시나 메모리 초과
//대각선 시작 수를 배열에 저장해놓고 규칙으로 숫자 꺼내오기
// 내 로직이 틀린 것 같지 않은데 나 왜 통과를 못할까
public class Main {
	static int N;
	static long[] startNum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

        startNum = new long[2*N];

        // 각 줄의 시작 숫자를 저장. 여기서 줄이란 대각선을 의미한다. 1부터 시작한다 가정
        startNum[1] = 1;

        int idx = 1;

        // n+1번째 대각선까지 시작 숫자는 전 숫자에 순차적으로 증가하는 idx값 만큼 증가
        for(int i=2 ; i <= N;i++){
            startNum[i] = startNum[i-1] + idx++;
        }

        // n+2번째 대각선부터 끝까지는 idx값이 반대로 감소
        for(int i=N+1; i <= 2*N-1; i++){
            startNum[i] = startNum[i-1] + idx--;
        }
		//시작 수 배열 로직 확인용 코드
//		System.out.println();
//		System.out.println(Arrays.toString(startNum));
        // 토끼 이동 커맨드 입력
        char[] move = br.readLine().toCharArray();
        
        int[] dr = {-1, 1, 0, 0};//상하좌우
        int[] dc = {0, 0, -1, 1};
        
        int r = 0;
        int c = 0;
        long ans = getNum(r, c);
        
        for (int i = 0; i < K; i++) {
        	int cr = r;
            int cc = c;
            if (move[i] == 'U') {
                cr += dr[0];
                cc += dc[0];
            } else if (move[i] == 'D') {
                cr += dr[1];
                cc += dc[1];
            } else if (move[i] == 'L') {
                cr += dr[2];
                cc += dc[2];
            } else if (move[i] == 'R') {
                cr += dr[3];
                cc += dc[3];
            }

            // 위치 이동 및 값 더하기
            r = cr;
            c = cc;
            // 좌표 이동 확인 및 이동 위치 수 가져오는 로직 확인용 코드
//            System.out.println("(r, c) : " + r+", "+c);
//            System.out.println("getNum : " + getNum(r,c));
            ans += getNum(r, c);
		}
        
        System.out.println(ans);
	}
	
	private static long getNum(int r, int c) {
	    // 대각선의 번호
	    int sum = r + c;
	    
	    // 해당 대각선의 시작 숫자
	    long curStart = startNum[sum+1];	    

	    // 짝수 대각선인 경우
	    if (sum % 2 == 0) {
	        if (sum < N) {
	            // 짝수 대각선이며, 대각선 번호가 n보다 작은 경우
	            // 좌하단에서 우상단으로 증가하므로 curStart에 열 인덱스 c를 더함
	            return curStart + c;
	        }	        
	        else {
	            // 짝수 대각선이며, 대각선 번호가 n보다 크거나 같은 경우
	            // 좌하단에서 우상단으로 진행하므로 curStart에 (N-r-1)을 더함
	        	return curStart + (N-r-1);	            
	        }
	    }
	    // 홀수 대각선인 경우
	    else {
	        if (sum < N) {
	            // 홀수 대각선이며, 대각선 번호가 n보다 작은 경우
	            // 우상단에서 좌하단으로 증가하므로 curStart에 행 인덱스 'r'을 더함
	            return curStart + r;
	        }
	        else {
	            // 홀수 대각선이며, 대각선 번호가 n보다 크거나 같은 경우
	        	// 우상단에서 좌하단으로 진행하므로 curStart에 (N-c-1)을 더함
	            return curStart + (N-c-1);
	        }
	    }
	}
}
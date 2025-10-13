import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // M, N 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        
        // U, L, R, D 입력
        st = new StringTokenizer(br.readLine());
        int U = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        
        // 크로스워드 퍼즐 입력
        String[] puzzle = new String[M];
        for (int i = 0; i < M; i++) {
            puzzle[i] = br.readLine();
        }
        
        // 전체 크기 계산
        int totalRows = U + M + D;
        int totalCols = L + N + R;
        
        // 장식된 퍼즐 생성
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                // 원본 퍼즐 영역인지 확인
                if (i >= U && i < U + M && j >= L && j < L + N) {
                    // 원본 퍼즐의 글자 출력
                    sb.append(puzzle[i - U].charAt(j - L));
                } else {
                    // 장식 영역 - 체스판 패턴
                    if ((i + j) % 2 == 0) {
                        sb.append('#');
                    } else {
                        sb.append('.');
                    }
                }
            }
            sb.append('\n');
        }
        
        System.out.print(sb.toString());
    }
}
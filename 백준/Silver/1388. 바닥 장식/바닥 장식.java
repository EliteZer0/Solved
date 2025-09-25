import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]); // 세로 크기
        int M = Integer.parseInt(input[1]); // 가로 크기
        
        char[][] floor = new char[N][M];
        
        // 바닥 장식 입력 받기
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                floor[i][j] = line.charAt(j);
            }
        }
        
        int plankCount = 0;
        
        // 가로 판자 개수 계산 ('-' 문자)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (floor[i][j] == '-') {
                    // 새로운 가로 판자의 시작점인지 확인
                    if (j == 0 || floor[i][j-1] != '-') {
                        plankCount++;
                    }
                }
            }
        }
        
        // 세로 판자 개수 계산 ('|' 문자)
        for (int j = 0; j < M; j++) {
            for (int i = 0; i < N; i++) {
                if (floor[i][j] == '|') {
                    // 새로운 세로 판자의 시작점인지 확인
                    if (i == 0 || floor[i-1][j] != '|') {
                        plankCount++;
                    }
                }
            }
        }
        
        sb.append(plankCount);
        System.out.println(sb.toString());
    }
}
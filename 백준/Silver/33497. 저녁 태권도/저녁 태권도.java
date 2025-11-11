import java.util.*;
import java.lang.*;
import java.io.*;

class Main {   
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = new int[M];
        int[] B = new int[M];
        
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());

            // 한 날에 필요한 총 인원이 N을 초과하면 불가능
            if(A[i] + B[i] > N) {
                System.out.println("NO");
                return;
            }
        }

        char[][] schedule = new char[N][M];
        int[] dayCnt = new int[M];
        int[] nightCnt = new int[M];
        for(int student = 0; student < N; student++){
            // 결석일 선택: 결석자가 N - (A_i + B_i)보다 적은 날 중 선택
            int absentDay = -1;
            for(int d = 0; d < M; d++) {
                int maxAbsent = N - (A[d] + B[d]);
                int currentAbsent = student - (dayCnt[d] + nightCnt[d]);
                if(currentAbsent < maxAbsent) {
                    absentDay = d;
                    break;
                }
            }

            // 결석일 없으면 안됨
            if(absentDay == -1) {
                System.out.println("NO");
                return;
            }

            schedule[student][absentDay] = 'X';

            for(int d = 0; d < M; d++) {
                if(d == absentDay) continue;
                
                if(dayCnt[d] < A[d]) {
                    schedule[student][d] = '+';
                    dayCnt[d]++;
                } else if(nightCnt[d] < B[d]) {
                    schedule[student][d] = '-';
                    nightCnt[d]++;
                } else {
                    schedule[student][d] = '+';
                    dayCnt[d]++;
                }
            }
        }
        
        for(int d = 0; d < M; d++) {
            if(dayCnt[d] < A[d] || nightCnt[d] < B[d]) {
                System.out.println("NO");
                return;
            }
        }
        
        sb.append("YES\n");
        for(int i = 0; i < N; i++) {
            sb.append(schedule[i]).append('\n');
        }
        
        System.out.print(sb.toString());
    }
}
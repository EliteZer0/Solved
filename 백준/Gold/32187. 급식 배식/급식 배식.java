import java.io.*;
import java.util.*;

public class Main {
    // 문제 해석을 잘못함. 같은 음식에 대해서 연속 학생 금지인 거지 1번이 고르면 3,4,5번은 고를 수 있음
    // static class Food {
    //     int food;
    //     int happiness;
        
    //     Food(int food, int happiness) {
    //         this.food = food;
    //         this.happiness = happiness;
    //     }
    // }

    static class Food {
        int j;       // 학생 번호
        long happiness;      // 행복도
        
        Food(int j, long happiness) { 
            this.j = j;
            this.happiness = happiness;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        List<Food>[] foods = new List[N+1];
        for (int i = 1; i <= N; i++) {
            foods[i] = new ArrayList<>();
        }
        
        // 각 학생의 음식 정보 입력
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int amount = Integer.parseInt(st.nextToken());
            
            for (int j = 0; j < amount; j++) {
                int foodIdx = Integer.parseInt(st.nextToken());
                int happiness = Integer.parseInt(st.nextToken());
                foods[foodIdx].add(new Food(i, happiness));
            }
        }
        
        long answer = 0;
        
        for (int i = 1; i <= N; i++) {
            List<Food> list = foods[i];
            if (list.isEmpty()) continue;

            // take = 마지막 항목을 선택한 최댓값, skip = 마지막 항목을 선택하지 않은 최댓값
            long take = 0;
            long skip = 0;
            int lastJ = -1;

            for (Food cur : list) {
                long newTake, newSkip;
                if (cur.j == lastJ + 1) {
                    // 인접 학생이면 바로 이전을 선택할 수 없음
                    newTake = skip + cur.happiness; // 이전에 스킵한 값에 현재 행복도 더하기
                    newSkip = Math.max(skip, take); // 이번에 안 고르면 이전 최댓값 유지
                } else {
                    // 인접하지 않으면 이전 최댓값에 그냥 더하면 됨
                    long preBest = Math.max(skip, take);
                    newTake = preBest + cur.happiness;
                    newSkip = preBest;
                }
                take = newTake;
                skip = newSkip;
                lastJ = cur.j;
            }

            answer += Math.max(take, skip);
        }

        System.out.println(answer);
    }
}
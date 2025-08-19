import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        br.close();
        
        sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
        printRecursion(0, n);
        
        System.out.print(sb.toString());
    }
    
    public static void printRecursion(int depth, int maxDepth) {
        String indent = getIndent(depth);
        
        // 질문 추가
        sb.append(indent).append("\"재귀함수가 뭔가요?\"\n");
        
        // 기저 조건: 최대 깊이에 도달했을 때
        if (depth == maxDepth) {
            sb.append(indent).append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
            sb.append(indent).append("라고 답변하였지.\n");
            return;
        }
        
        // 재귀 조건: 스토리 추가 후 재귀 호출
        sb.append(indent).append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
        sb.append(indent).append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
        sb.append(indent).append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
        
        // 재귀 호출
        printRecursion(depth + 1, maxDepth);
        
        // 재귀 호출 후 돌아와서 마무리 추가
        sb.append(indent).append("라고 답변하였지.\n");
    }
    
    // 들여쓰기 생성 (언더스코어 4개씩) - StringBuilder로 최적화
    public static String getIndent(int depth) {
        StringBuilder indentSb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indentSb.append("____");
        }
        return indentSb.toString();
    }
}
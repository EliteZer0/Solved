import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int P = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < P; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            
            List<Integer> line = new ArrayList<>();
            int totalSteps = 0;
            
            for (int j = 0; j < 20; j++) {
                int height = Integer.parseInt(st.nextToken());
                int insertPos = line.size();
                
                for (int k = 0; k < line.size(); k++) {
                    if (line.get(k) > height) {
                        insertPos = k;
                        break;
                    }
                }
                
                totalSteps += line.size() - insertPos;
                line.add(insertPos, height);
            }
            
            sb.append(T).append(" ").append(totalSteps).append("\n");
        }
        
        System.out.print(sb.toString());
    }
}
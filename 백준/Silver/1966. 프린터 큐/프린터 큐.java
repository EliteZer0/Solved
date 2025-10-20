import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Document {
        int index;
        int priority;
        
        Document(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            Queue<Document> queue = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            
            for (int i = 0; i < N; i++) {
                int priority = Integer.parseInt(st.nextToken());
                queue.offer(new Document(i, priority));
            }
            
            int printOrder = 0;
            
            while (!queue.isEmpty()) {
                Document current = queue.poll();
                boolean hasHigherPriority = false;
                
                for (Document doc : queue) {
                    if (doc.priority > current.priority) {
                        hasHigherPriority = true;
                        break;
                    }
                }
                
                if (hasHigherPriority) {
                    queue.offer(current);
                } else {
                    printOrder++;
                    if (current.index == M) {
                        sb.append(printOrder).append('\n');
                        break;
                    }
                }
            }
        }
        
        System.out.print(sb.toString());
    }
}
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(br.readLine());
        
        int[] queue = new int[n];
        int front = 0;
        int back = 0;
        
        for (int i = 0; i < n; i++) {
            String[] command = br.readLine().split(" ");
            
            switch (command[0]) {
                case "push":
                    queue[back++] = Integer.parseInt(command[1]);
                    break;
                    
                case "pop":
                    if (front == back) {
                        sb.append(-1).append('\n');
                    } else {
                        sb.append(queue[front++]).append('\n');
                    }
                    break;
                    
                case "size":
                    sb.append(back - front).append('\n');
                    break;
                    
                case "empty":
                    sb.append(front == back ? 1 : 0).append('\n');
                    break;
                    
                case "front":
                    if (front == back) {
                        sb.append(-1).append('\n');
                    } else {
                        sb.append(queue[front]).append('\n');
                    }
                    break;
                    
                case "back":
                    if (front == back) {
                        sb.append(-1).append('\n');
                    } else {
                        sb.append(queue[back - 1]).append('\n');
                    }
                    break;
            }
        }
        
        System.out.print(sb.toString());
    }
}
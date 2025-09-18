import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        char[][] board = new char[N][N];
        
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = line.charAt(j);
            }
        }
        
        int heartRow = -1, heartCol = -1;
        
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == '*') {
                    // 머리를 찾았으면 심장은 바로 아래
                    heartRow = i + 1;
                    heartCol = j;
                    break;
                }
            }
            if (heartRow != -1) break;
        }
        
        int leftArm = 0;
        for (int j = heartCol - 1; j >= 0; j--) {
            if (board[heartRow][j] == '*') {
                leftArm++;
            } else {
                break;
            }
        }
        
        int rightArm = 0;
        for (int j = heartCol + 1; j < N; j++) {
            if (board[heartRow][j] == '*') {
                rightArm++;
            } else {
                break;
            }
        }
        
        int waist = 0;
        int waistEnd = heartRow;
        for (int i = heartRow + 1; i < N; i++) {
            if (board[i][heartCol] == '*') {
                waist++;
                waistEnd = i;
            } else {
                break;
            }
        }
        
        int leftLeg = 0;
        for (int i = waistEnd + 1; i < N; i++) {
            if (board[i][heartCol - 1] == '*') {
                leftLeg++;
            } else {
                break;
            }
        }
        
        int rightLeg = 0;
        for (int i = waistEnd + 1; i < N; i++) {
            if (board[i][heartCol + 1] == '*') {
                rightLeg++;
            } else {
                break;
            }
        }
        
        sb.append((heartRow + 1)).append(" ").append((heartCol + 1)).append("\n");
        sb.append(leftArm).append(" ").append(rightArm).append(" ").append(waist)
          .append(" ").append(leftLeg).append(" ").append(rightLeg);
        
        System.out.print(sb.toString());
    }
}
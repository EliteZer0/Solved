import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        while(true){
            String input = br.readLine();
            if(input.equals("end")) break;

            char[][] board = new char[3][3];
            for(int i = 0; i < 9; i++){
                board[i / 3][i % 3] = input.charAt(i);
            }
            
            boolean xWin = checkWin(board, 'X');
            boolean oWin = checkWin(board, 'O');
            
            int xCnt = 0;
            int oCnt = 0;
            for(int i = 0; i<9; i++){
                if(input.charAt(i) == 'X') xCnt++;
                if(input.charAt(i) == 'O') oCnt++;
            }

            boolean canTictactoe = true;
            
            // O가 후순이기 때문에 개수가 많을 수 없음
            if(oCnt>xCnt) canTictactoe = false;
            // 선공 후공 개수 차이가 1보다 클 수 없음
            else if(xCnt - oCnt>1) canTictactoe = false;
            // 진행 중인 경우
            if(!xWin && !oWin && xCnt+oCnt<9) canTictactoe = false;
            // 둘 다 이길 수 없음
            if(xWin && oWin) canTictactoe = false;
            // X가 이겼는데 O가 둔 경우는 없음
            if(xWin && xCnt == oCnt) canTictactoe = false;
            // O가 이겼는데 X가 둔 경우는 없음
            if(oWin && xCnt == oCnt + 1) canTictactoe = false;
            if(!canTictactoe) sb.append("invalid");
            else sb.append("valid");
            sb.append("\n");
        }
        
        System.out.println(sb.toString());
    }

    public static boolean checkWin(char[][] board, char player){
        // 가로 체크
        for(int i = 0; i < 3; i++){
            if(board[i][0] == player && board[i][1] == player && board[i][2] == player){
                return true;
            }
        }
        
        // 세로 체크
        for(int j = 0; j < 3; j++){
            if(board[0][j] == player && board[1][j] == player && board[2][j] == player){
                return true;
            }
        }
        
        // 대각선 체크
        if(board[0][0] == player && board[1][1] == player && board[2][2] == player){
            return true;
        }
        
        if(board[0][2] == player && board[1][1] == player && board[2][0] == player){
            return true;
        }
        
        return false;
    }
}
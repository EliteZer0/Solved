import java.util.Arrays;

class Solution {
    public int solution(int a, int b, int c, int d) {
        int answer = 0;
        int[] dice = {a, b, c, d};
        Arrays.sort(dice);
        
        if(dice[0] == dice[3]){
            answer = 1111*dice[0];
        }
        else if(dice[0]==dice[2]){
            answer = threeDice(dice[0], dice[3]);
        }
        else if(dice[1]==dice[3]){
            answer = threeDice(dice[1], dice[0]);
        }
        else if(dice[0]==dice[1] && dice[2]==dice[3]){
            answer = doubleDice(dice[0], dice[2]);
        }
        else if(dice[0]==dice[1]){
            answer = twoDice(dice[2], dice[3]);
        }
        else if(dice[1]==dice[2]){
            answer = twoDice(dice[0], dice[3]);
        }
        else if(dice[2]==dice[3]){
            answer = twoDice(dice[0], dice[1]);
        }
        else{
            answer = dice[0];
        }
        return answer;
    }
        
    public int threeDice(int p, int q){
        return (int)Math.pow(10*p + q, 2);
    }    
    public int doubleDice(int p, int q){
        return (p+q) * Math.abs(p-q);
    }    
    public int twoDice(int p, int q){
        return p*q;
    }
}
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int[] blockHeight = new int[w];

        for(int i = 0; i<w; i++){
            blockHeight[i] = sc.nextInt();
        }

        int[][] blockMap = new int[h][w];
        
        for(int i = 1; i<h+1; i++){
            for(int j = 0; j<w; j++){
                if(blockHeight[j]>0){
                    blockMap[h-i][j] = 1;
                }
                blockHeight[j] --;
            }
        }

        int waterCount = 0;

        for(int i = 0; i<h; i++){
            for(int j = 0; j<w; j++){
                if(blockMap[i][j]==0){
                    boolean isLeftBlock = false;
                    boolean isRightBlock = false;
                    int left = j-1;
                    int right = j+1;

                    while(left >= 0){
                        if (blockMap[i][left]==1){
                            isLeftBlock = true;
                            break;
                        }else{
                            left --;
                        }
                    }

                    while(right < w){
                        if (blockMap[i][right]==1){
                            isRightBlock = true;
                            break;
                        }else{
                            right ++;
                        }
                    }
                    
                    if(isLeftBlock&&isRightBlock) {
                        waterCount++ ;
                    }
                }
            }
        }
        System.out.println(waterCount);
    }
}
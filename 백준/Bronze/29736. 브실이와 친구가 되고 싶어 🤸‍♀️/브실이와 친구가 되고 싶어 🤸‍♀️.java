import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int friends = 0;
        String ab = br.readLine();
        StringTokenizer st1 = new StringTokenizer(ab);
        int a = Integer.parseInt(st1.nextToken());
        int b = Integer.parseInt(st1.nextToken());
        String kx = br.readLine();
        StringTokenizer st2 = new StringTokenizer(kx);
        int k = Integer.parseInt(st2.nextToken());
        int x = Integer.parseInt(st2.nextToken());

        for(int i =a; i<=b; i++){
            if(Math.abs(k-i)<=x){
                friends ++;
            }
        }

        if(friends == 0){
            System.out.println("IMPOSSIBLE");
        }else{
            System.out.println(friends);
        }
        
    }
}

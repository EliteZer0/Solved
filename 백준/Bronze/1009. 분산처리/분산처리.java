import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int t = 0; t < T; t++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int data = 1;
			int count = 0;

            while(count<b){
                data *= a;
                data %= 10;
                count ++;
            }

            if(data == 0){
                System.out.println(10);
            }else {
            	System.out.println(data);
            }
		}
	}
}
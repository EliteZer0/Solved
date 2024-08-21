import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a;
        int b;
		while((a=sc.nextInt())!=0 && (b= sc.nextInt())!=0) {
			System.out.println(a+b);
		}
	}
}
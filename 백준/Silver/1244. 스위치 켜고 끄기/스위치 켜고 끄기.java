import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int switchCounts = Integer.parseInt(br.readLine());
		int[] switchsState = new int[switchCounts];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < switchCounts; i++) {
			switchsState[i] = Integer.parseInt(st.nextToken());
		}
		int studentCounts = Integer.parseInt(br.readLine());
		int[][] studentsState = new int[studentCounts][2];//row : gender col: getNumber
		
		for (int i = 0; i < studentCounts; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				studentsState[i][j] = Integer.parseInt(st.nextToken());	
			}
		}
		
		for (int i = 0; i < studentCounts; i++) {
			int gender = studentsState[i][0];
			int getNum = studentsState[i][1];
			//남학생 로직
			if(gender == 1) {
				for (int j = 0; j < switchCounts; j++) {
					if((j+1)%getNum == 0) {
						if(switchsState[j] == 0) switchsState[j] = 1;
						else switchsState[j] = 0;
					}
				}
			}
			//여학생 로직 -> 좌우가 대칭이면 대칭인 곳까지 모두 전환 아니면 본인 스위치만 전환
			else {
				if(switchsState[getNum-1] == 0) switchsState[getNum-1] = 1;
				else switchsState[getNum-1] = 0;
				
				for (int j = 1; j < switchCounts; j++) {			
					if(getNum-1-j>=0 && getNum-1+j<switchCounts && switchsState[getNum-1-j] == switchsState[getNum-1+j]) {
						
						if(switchsState[getNum-1-j] == 0) {
							switchsState[getNum-1-j] = 1;
							switchsState[getNum-1+j] = 1;
						}
						else {
							switchsState[getNum-1-j] = 0;
							switchsState[getNum-1+j] = 0;
						}	
					}else {
						break;
					}
				}
			}
		}
		if(switchCounts/20 > 0) {
			for(int i = 0; i<(switchCounts/20)+1; i++) {
				for (int j = 20*i; j < 20*(i+1); j++) {
					if(j<switchCounts) {
						System.out.printf("%d ", switchsState[j]);
					}else {
						break;
					}
				}
				System.out.println();
			}
		}else {
			for (int i = 0; i < switchCounts; i++) {
				System.out.printf("%d ", switchsState[i]);
			}
		}
	}
}

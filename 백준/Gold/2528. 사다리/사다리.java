import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 메모리 초과 -> 불필요한 객체 생성을 제거해서 해결
// 시간 초과 -> 매 시간마다 사다리를 옮겨서 발생. 시간을 먼저 계산하고 그 시간만큼 한번에 사다리 이동
class Ladder{
	int l, s, e, d;

	public Ladder(int l, int s, int e, int d) {
		this.l = l;
		this.s = s;
		this.e = e;
		this.d = d;
	}
}

public class Main {
	static int N, L, time;
	static Ladder[] ladderList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		ladderList = new Ladder[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			if(l == L) {
				ladderList[i] = new Ladder(l, 0, l-1, -1);
			}
			else if(d == 0) {
				ladderList[i] = new Ladder(l, 0, l-1, d);
			}else {
				ladderList[i] = new Ladder(l, L-l, L-1, d);
			}
		}
		
		time = 0;
		
		for (int i = 0; i < N-1; i++) {
			while(!canMeet(i, i+1)) {
				time++;
			}
		}
		
		System.out.println(time);
	}

	private static boolean canMeet(int cur, int next) {
		Ladder curLadder = ladderList[cur];
		Ladder nextLadder = ladderList[next];
		
		// 현재 시간에서 사다리 위치
		int[] curLoca = getLocation(curLadder, time);
		int[] nextLoca = getLocation(nextLadder, time);
		return isOverlap(curLoca[0], curLoca[1], nextLoca[0], nextLoca[1]);
	}

	private static boolean isOverlap(int s1, int e1, int s2, int e2) {
		return !(e1<s2-1 || s1>e2+1);
	}

	private static int[] getLocation(Ladder ladder, int t) {
		if(ladder.d == -1) {
			return new int[] {ladder.s, ladder.e};
		}
		
		int period = 2*(L-ladder.l);//왕복 거리
		
		//사다리 이동은 주기적임.
		//왕복 거리로 나누면 그 나머지의 시간과 위치가 동일함.
		t = t%period;
		
		int start, end;
		
		//왼쪽 시작
		if(ladder.d == 0) {
			// 오른쪽으로 이동 중
			if(t < L-ladder.l) {
				start = t;
                end = start + ladder.l-1;
            }
			//오른쪽 벽과 만났을 때
			else if(t == L-ladder.l) {
				start = L-ladder.l;
				end = start + ladder.l-1;
			}
			// 왼쪽으로 이동 중
			else { 
                start = L-ladder.l-(t-(L-ladder.l));
                end = start + ladder.l-1;
            }
		}
		//오른쪽 시작
		else {
			if(t < L-ladder.l) { // 왼쪽으로 이동 중
                start = L-ladder.l-t;
                end = start+ladder.l-1;
            } 
            else if(t == L-ladder.l) {
				start = 0;
				end = start+ladder.l-1;
			}
            else { // 오른쪽으로 이동 중
                start = t-(L-ladder.l);
                end = start+ladder.l-1;
            }
        }
		
		return new int[]{start, end};
	}
}
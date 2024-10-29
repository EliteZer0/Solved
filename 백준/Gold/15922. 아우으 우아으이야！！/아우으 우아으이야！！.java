import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
//중복되는 선분 길이 생각하기 귀찮아서 두가지 방법을 고민함
//1. boolean 배열로 있니 없니 체크 -> 음수가 나올 수도 있어서 고려해야하는 요소가 많음.
//2. 셋으로 받아서 중복값 제거 -> 이 경우 정렬이 필요해서 트리셋 사용
//연결이 끊어지면 우선 길이를 1 줄임. 앞 선분의 마지막 수와 뒷 선분의 시작하는 수를 빼서 전체 길이에서 빼면 됨
//이렇게 진행하니 수의 범위가 커버가 안됨.
//선분의 시작점과 끝점을 받는 클래스를 트리셋으로 받아서 진행.
//트리셋이 생각했던 것만큼 빠르게 진행이 안 돼서 리스트로 바꿔서 진행
//선분 병합 로직 자체를 단순화 해봄

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		int start = Integer.MIN_VALUE;
		int end = Integer.MIN_VALUE;
		int length = 0;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int curStart = Integer.parseInt(st.nextToken());
			int curEnd = Integer.parseInt(st.nextToken());
			
			//새로 뽑은 선분과 이전 선분의 범위가 겹치면
			if(curStart <= end) {
				//이전 선분 끝나는 지점과 새로 뽑은 선분 끝나는 지점 중에서 더 큰 거
				end = Math.max(end, curEnd);
			} 
			//겹치지 않으면
			else {
				if(start != Integer.MIN_VALUE) {
					length += end-start;
				}
				//시작 위치 새로 뽑은 선분의 시작위치로 갱신
				start = curStart;
				//끝나는 위치 새로 뽑은 선분의 끝나는 위치로 갱신
				end = curEnd;
			}
		}
		
		//마지막 선분 길이까지 더해주기
		if(start != Integer.MIN_VALUE) {
			length += end-start;
		}
		
		System.out.println(length);
	} 
}
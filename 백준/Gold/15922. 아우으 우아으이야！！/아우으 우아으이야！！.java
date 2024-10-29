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
class Segment implements Comparable<Segment> {
    int start, end;

    public Segment(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Segment o) {
        if (this.start == o.start) {
            return Integer.compare(this.end, o.end);
        }
        return Integer.compare(this.start, o.start);
    }
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Segment> segments = new ArrayList<>();
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			segments.add(new Segment(start, end));
		}
		
		Collections.sort(segments);
		//시작점 설정
		int start = segments.get(0).start;
		int end = segments.get(0).end;
		int length = 0;
		
		for(Segment s : segments) {
			//새로 뽑은 선분과 이전 선분의 범위가 겹치면
			if(s.start <= end) {
				//이전 선분 끝나는 지점과 새로 뽑은 선분 끝나는 지점 중에서 더 큰 거
				end = Math.max(end, s.end);
			} 
			//겹치지 않으면
			else {
				//현재까지 선분 길이 총 길이에 합하고
				length += end-start;
				//시작 위치 새로 뽑은 선분의 시작위치로 갱신
				start = s.start;
				//끝나는 위치 새로 뽑은 선분의 끝나는 위치로 갱신
				end = s.end;
			}
		}
		
		//마지막 선분 길이까지 더해주기
		length += end-start;
		
		System.out.println(length);
	} 
}
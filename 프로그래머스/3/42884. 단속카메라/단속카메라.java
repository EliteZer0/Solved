import java.util.Arrays;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a,b) -> Integer.compare(a[1], b[1]));
        int curCam = routes[0][1];
        int camCnt = 1;
        for(int[] route : routes) {
            if(curCam < route[0] ) {
                camCnt++;
                curCam = route[1];
            }
        }
        return camCnt;
    }
}
class Solution {
    public int solution(int a, int b, int c, int d) {
        int answer = 0;
        int[] cnt = new int[7];
        cnt[a]++;
        cnt[b]++;
        cnt[c]++;
        cnt[d]++;
        for(int i = 1; i<cnt.length; i++){
            if(cnt[i] == 4){
                answer = i*1111;
                break;
            }
            if(cnt[i] == 3){
                for(int j = 1; j<cnt.length; j++){
                    if(cnt[j] == 1){
                        answer = (int)Math.pow(10*i + j,2);
                        break;
                    }
                }
            }
            if(cnt[i] == 2){
                for(int j = 1; j<cnt.length; j++){
                    if(cnt[j] == 2){
                        answer = (i+j) * Math.abs(i-j);
                        break;
                    }
                }
            }
            if(cnt[i] == 2){
                for(int j = 1; j<cnt.length; j++){
                    if(cnt[j] == 1){
                        for(int k = j+1; k<cnt.length; k++){
                            if(cnt[k] == 1){
                                answer = j*k;
                                break;
                            }
                        }
                    }
                }
            }
            if(cnt[i] == 1){
                for(int j = i+1; j<cnt.length; j++){
                    if(cnt[j] == 1){
                        for(int k = j+1; k<cnt.length; k++){
                            if(cnt[k] == 1){
                                for(int l = k+1; l<cnt.length; l++){
                                    if(cnt[l] == 1){
                                        answer = i;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return answer;
    }
}
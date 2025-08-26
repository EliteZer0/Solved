import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        Set<Character> usedChars = new HashSet<>();
        
        for(int t = 0; t < N; t++){
            String input = br.readLine();
            char shortcutChar = ' ';
            int shortcutIdx = -1;
            
            // 각 단어의 첫 글자부터 확인
            String[] words = input.split(" ");
            boolean found = false;
            
            for(int wordIdx = 0; wordIdx < words.length; wordIdx++){
                if(words[wordIdx].length() > 0){
                    char firstSpell = Character.toLowerCase(words[wordIdx].charAt(0));// 대소문자 구분을 안한다고 그럼
                    if(!usedChars.contains(firstSpell)){
                        shortcutChar = firstSpell;
                        usedChars.add(firstSpell);
                        found = true;
                        
                        // 실제 문자열에서 해당 단어의 첫 글자 위치 찾기
                        int curPos = 0;
                        for(int i = 0; i < wordIdx; i++){
                            curPos += words[i].length() + 1; // +1은 공백
                        }
                        shortcutIdx = curPos;
                        break;
                    }
                }
            }
            
            // 단어의 첫 글자로 안되면 words 배열 순회하며 각 단어의 모든 문자 확인
            if(!found){
                int curPos = 0;
                roop: for(int wordIdx = 0; wordIdx < words.length; wordIdx++){
                    String word = words[wordIdx];
                    for(int i = 0; i < word.length(); i++){
                        char c = word.charAt(i);
                        char lowerCase = Character.toLowerCase(c);
                        if(!usedChars.contains(lowerCase)){
                            shortcutChar = lowerCase;
                            shortcutIdx = curPos + i;
                            usedChars.add(lowerCase);
                            found = true;
                            break roop;
                        }
                    }
                    curPos += word.length() + 1; // 다음 단어 시작 위치로 이동 (+1은 공백)
                }
            }
            
            // 결과 출력 생성
            StringBuilder result = new StringBuilder();
            for(int i = 0; i < input.length(); i++){
                if(i == shortcutIdx){
                    result.append('[').append(input.charAt(i)).append(']');
                } else {
                    result.append(input.charAt(i));
                }
            }
            
            System.out.println(result.toString());
        }
    }
}
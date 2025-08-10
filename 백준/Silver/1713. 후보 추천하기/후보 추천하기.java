import java.util.*;
import java.io.*;

public class Main {
    static class Student {
        int studentId;
        int entry;
        int recommendationCnt;

        Student (int studentId, int entry, int recommendationCnt){
            this.studentId = studentId;
            this.entry = entry;
            this.recommendationCnt = recommendationCnt;
        }
    }

    static class StudentComparator implements Comparator<Student>{
        @Override
        public int compare(Student o1, Student o2){
            if(o1.recommendationCnt == o2.recommendationCnt){
                return o1.entry - o2.entry;
            }
            return o1.recommendationCnt - o2.recommendationCnt;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        int recommendationListSize = Integer.parseInt(br.readLine());
        int entryNum = 1;
        
        PriorityQueue<Student> pq = new PriorityQueue<>(new StudentComparator());
        HashMap<Integer, Student> studentMap = new HashMap<>();
        
        st = new StringTokenizer(br.readLine());
        
        while(pq.size() < N && st.hasMoreTokens()){
            int studentId = Integer.parseInt(st.nextToken());
            
            if(studentMap.containsKey(studentId)){
                Student existing = studentMap.get(studentId);
                pq.remove(existing);
                existing.recommendationCnt++;
                pq.offer(existing);
            }
            else{
                Student newStudent = new Student(studentId, entryNum, 1);
                pq.offer(newStudent);
                studentMap.put(studentId, newStudent);
            }
            entryNum++;
        }

        while(st.hasMoreTokens()){
            int studentId = Integer.parseInt(st.nextToken());
            if(studentMap.containsKey(studentId)){
                Student existing = studentMap.get(studentId);
                pq.remove(existing);
                existing.recommendationCnt++;
                pq.offer(existing);
            }
            else{
                Student removed = pq.poll();
                studentMap.remove(removed.studentId);
                
                Student newStudent = new Student(studentId, entryNum, 1);
                pq.offer(newStudent);
                studentMap.put(studentId, newStudent);
            }
            entryNum++;
        }

        List<Student> result = new ArrayList<>(pq);
        result.sort((s1, s2) -> s1.studentId - s2.studentId);

        StringBuilder sb = new StringBuilder();

        for(Student s : result){
            sb.append(s.studentId).append(" ");
        }
        
        System.out.println(sb.toString());
    }
}
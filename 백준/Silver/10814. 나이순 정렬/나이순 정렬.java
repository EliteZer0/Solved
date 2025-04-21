import java.util.*;
import java.io.*;

public class Main {
    static class Person implements Comparable<Person> {
        private String name;
        private int age;

        public Person(String name, int age){
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Person p){
            if (this.age>p.age) return 1;
            else if(this.age<p.age) return -1;
            else return 0;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        List<Person> people = new ArrayList<>();
        
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            people.add(new Person(name, age));
        }

        Collections.sort(people);
        
        StringBuilder sb = new StringBuilder();
        for(Person p : people){
            sb.append(p.age).append(" ").append(p.name).append("\n");
        }
        System.out.println(sb.toString()); 
    }
}
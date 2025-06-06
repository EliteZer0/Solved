import java.util.*;
import java.io.*;

public class Main {
    static class Island {
        char type;
        long animals;
        int parent;
        List<Integer> children;
        
        Island(char type, long animals, int parent) {
            this.type = type;
            this.animals = animals;
            this.parent = parent;
            this.children = new ArrayList<>();
        }
        
        Island() {
            this.children = new ArrayList<>();
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        Island[] islands = new Island[N + 1];
        
        for (int i = 1; i <= N; i++) {
            islands[i] = new Island();
        }
        
        islands[1].type = 'S';
        islands[1].animals = 0;
        islands[1].parent = 0;
        
        for (int i = 2; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            char type = input[0].charAt(0);
            long animals = Long.parseLong(input[1]);
            int parent = Integer.parseInt(input[2]);
            
            islands[i].type = type;
            islands[i].animals = animals;
            islands[i].parent = parent;
            islands[parent].children.add(i);
        }
        
        long result = dfs(1, islands);
        System.out.println(result);
    }
    
    static long dfs(int current, Island[] islands) {
        Island island = islands[current];
        long sheepFromChildren = 0;
        
        for (int child : island.children) {
            sheepFromChildren += dfs(child, islands);
        }
        
        if (current == 1) {
            return sheepFromChildren;
        }
        
        if (island.type == 'S') {
            return island.animals + sheepFromChildren;
        } else {
            return Math.max(0, sheepFromChildren - island.animals);
        }
    }
}
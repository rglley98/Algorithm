import java.util.*;

class Solution {
    int[] parent;
    
    int find(int node) {
        if(parent[node] == node)
            return node;
        
        return parent[node] = find(parent[node]);
    }
    
    void union(int node1, int node2) {
        int parent1 = find(node1);
        int parent2 = find(node2);
        
        if(parent1 != parent2)
            parent[parent2] = parent1;
    }
    
    public int solution(int n, int[][] costs) {
        parent = new int[n];
        for(int node = 0; node < n; node++) {
            parent[node] = node;
        }
        
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
        
        int answer = 0;
        int edgeCount = 0;
        for(int[] info : costs) {
            int from = info[0];
            int to = info[1];
            int cost = info[2];
            
            if(find(from) == find(to))
                continue;
            
            union(from, to);
            
            answer += cost;
            edgeCount++;
            
            if(edgeCount == n - 1)
                break;
        }
        
        return answer;
    }
}


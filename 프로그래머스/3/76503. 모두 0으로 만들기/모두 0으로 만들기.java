import java.util.*;

class Solution {

    long answer = 0;
    long[] weight;
    boolean[] visited;
    List<Integer>[] adjList;

    public long solution(int[] a, int[][] edges) {
        int size = a.length;

        weight = new long[size];
        visited = new boolean[size];
        adjList = new ArrayList[size];

        long total = 0;

        for(int nodeIdx = 0; nodeIdx < size; nodeIdx++) {
            weight[nodeIdx] = a[nodeIdx];
            total += a[nodeIdx];
            adjList[nodeIdx] = new ArrayList<>();
        }

        if(total != 0) {
            return -1;
        }

        for(int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            adjList[from].add(to);
            adjList[to].add(from);
        }

        dfs(0);

        return answer;
    }

    private long dfs(int currentNode) {
        visited[currentNode] = true;

        for(int nodeIdx = 0; nodeIdx < adjList[currentNode].size(); nodeIdx++) {
            int nextNode = adjList[currentNode].get(nodeIdx);
            
            if(visited[nextNode]) {
                continue;
            }

            long childWeight = dfs(nextNode);
            weight[currentNode] += childWeight;

            answer += Math.abs(childWeight);
        }

        return weight[currentNode];
    }
}
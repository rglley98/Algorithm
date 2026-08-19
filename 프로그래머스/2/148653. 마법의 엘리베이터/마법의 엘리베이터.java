class Solution {
    int answer = 0;
    int length;

    void DFS(int count, int num) {
        if(count >= answer) {
            return;
        }

        if(num == 0) {
            answer = Math.min(answer, count);
            return;
        }

        int lastNum = num % 10;

        DFS(count + lastNum, num / 10);
        DFS(count + (10 - lastNum), num / 10 + 1);
    }

    public int solution(int storey) {
        answer = Integer.MAX_VALUE;

        DFS(0, storey);

        return answer;
    }
}
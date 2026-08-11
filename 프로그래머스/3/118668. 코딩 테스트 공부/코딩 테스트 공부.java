import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0;
        int maxCop = 0;

        for (int[] problem : problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }

        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);

        int[][] dp = new int[maxAlp + 1][maxCop + 1];

        for (int alpValue = 0; alpValue <= maxAlp; alpValue++) {
            Arrays.fill(dp[alpValue], Integer.MAX_VALUE);
        }

        dp[alp][cop] = 0;
        
        for (int alpValue = alp; alpValue <= maxAlp; alpValue++) {
            for (int copValue = cop; copValue <= maxCop; copValue++) {
                if (dp[alpValue][copValue] == Integer.MAX_VALUE) {
                    continue;
                }

                if (alpValue < maxAlp) {
                    dp[alpValue + 1][copValue] = 
                        Math.min(dp[alpValue + 1][copValue], dp[alpValue][copValue] + 1);
                }

                if (copValue < maxCop) {
                    dp[alpValue][copValue + 1] = 
                        Math.min(dp[alpValue][copValue + 1], dp[alpValue][copValue] + 1);
                }

                for (int[] problem : problems) {
                    int requiredAlp = problem[0];
                    int requiredCop = problem[1];
                    int rewardAlp = problem[2];
                    int rewardCop = problem[3];
                    int cost = problem[4];

                    if (alpValue < requiredAlp || copValue < requiredCop) {
                        continue;
                    }

                    int nextAlp = Math.min(maxAlp, alpValue + rewardAlp);
                    int nextCop = Math.min(maxCop, copValue + rewardCop);

                    dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[alpValue][copValue] + cost);
                }
            }
        }

        return dp[maxAlp][maxCop];
    }
}
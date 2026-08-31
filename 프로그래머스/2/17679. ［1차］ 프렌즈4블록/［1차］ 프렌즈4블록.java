import java.util.*;

class Solution {
    char[][] map;
    int rowSize, colSize;
    
    public int solution(int m, int n, String[] board) {
        rowSize = m;
        colSize = n;
        
        map = new char[rowSize][colSize];
        for(int row = 0; row < rowSize; row++) {
            String line = board[row];

            for(int col = 0; col < colSize; col++) {
                map[row][col] = line.charAt(col);                
            }
        }
        
        while(true) {
            List<int[]> deleteList = new ArrayList<>();
            for(int row = 0; row < rowSize - 1; row++) {
                for(int col = 0; col < colSize - 1; col++) {
                    if(map[row][col] == '.') {
                        continue;
                    }

                    if(isTarget(row, col)) {
                        deleteList.add(new int[]{row, col});
                    }
                }
            }
            
            if(deleteList.size() == 0) {
                break;
            }
            
            for(int[] info : deleteList) {
                int row = info[0];
                int col = info[1];
                
                map[row][col] = '.';
                map[row + 1][col] = '.';
                map[row][col + 1] = '.';
                map[row + 1][col + 1] = '.';
            }
            
            for(int col = 0; col < colSize; col++) {
                int rowPointer = rowSize - 1;
                while(rowPointer > 0) {
                    if(map[rowPointer][col] != '.') {
                        rowPointer--;
                        continue;
                    }
                    
                    for(int row = rowPointer - 1; row >= 0; row--) {
                         if(map[row][col] == '.') {
                             continue;
                         }
                        
                        char ch = map[row][col];
                        map[row][col] = '.';
                        map[rowPointer][col] = ch;
                        break;
                    }
                    
                    rowPointer--;
                }
                
            }
        }
        
        int answer = 0;
        for(int row = 0; row < rowSize; row++) {
            for(int col = 0; col < colSize; col++) {
                if(map[row][col] == '.') {
                    answer++;
                }
            }
        }
        
        return answer;
    }
    
    boolean isTarget(int row, int col) {
        char ch = map[row][col];
        
        if(map[row + 1][col] == ch && map[row][col + 1] == ch && map[row + 1][col + 1] == ch) {
            return true;
        }
        
        return false;
    }
}
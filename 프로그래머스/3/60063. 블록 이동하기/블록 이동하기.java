import java.util.*;

class Solution {
    int[][] board;
    int size;
    int[][][] record;
    int[] deltaRow = {-1, 0, 1, 0};
    int[] deltaCol = {0, 1, 0, -1};
    
    public int solution(int[][] board) {
        this.board = board;
        size = board.length;
        
        BFS();
        
        int answer = Integer.MAX_VALUE;
        for(int state = 0; state < 4; state++) {
            answer = Math.min(answer, record[size - 1][size - 1][state]);
        }
        
        return answer;
    }
    
    void BFS() {
        Queue<Info> queue = new ArrayDeque<>();
        record = new int[size][size][4];
        for(int r = 0; r < size; r++) {
            for(int c = 0; c < size; c++) {
                for(int s = 0; s < 4; s++) {
                    record[r][c][s] = Integer.MAX_VALUE;
                }
            }
        }
        
        record[0][0][0] = 0;
        record[0][1][1] = 0;
        queue.offer(new Info(new int[]{0, 0}, new int[]{0, 1}, null, null, 0));
        
        while(!queue.isEmpty()) {
            Info current = queue.poll();
            
            if(isArrived(current.left) || isArrived(current.right) || 
               isArrived(current.up) || isArrived(current.down))
                return;
            
            if(current.left != null) {
                for(int delta = 0; delta < 4; delta++) {
                    int nextLeftRow = current.left[0] + deltaRow[delta];
                    int nextLeftCol = current.left[1] + deltaCol[delta];
                    
                    int nextRightRow = current.right[0] + deltaRow[delta];
                    int nextRightCol = current.right[1] + deltaCol[delta];
                    
                    if(isPossible(nextLeftRow, nextLeftCol, 0) && isPossible(nextRightRow, nextRightCol, 1)) {
                        record[nextLeftRow][nextLeftCol][0] = current.step + 1;
                        record[nextRightRow][nextRightCol][1] = current.step + 1;
                        queue.offer(new Info(new int[]{nextLeftRow, nextLeftCol}, new int[]{nextRightRow, nextRightCol}, null, null, current.step + 1));
                    }
                }
                
                int[] left = current.left;
                int[][] check = new int[][]{{left[0] - 1, left[1]}, {left[0] - 1, left[1] + 1}};
                if(canRotate(check)) {
                    int[] up = check[0];
                    int[] down = left;
                    
                    if(isPossible(up[0], up[1], 2) && isPossible(down[0], down[1], 3)) {
                        record[up[0]][up[1]][2] = current.step + 1;
                        record[down[0]][down[1]][3] = current.step + 1;
                        queue.offer(new Info(null, null, up, down, current.step + 1));
                    }
                }
                
                check = new int[][]{{left[0] + 1, left[1]}, {left[0] + 1, left[1] + 1}};
                if(canRotate(check)) {
                    int[] up = left;
                    int[] down = check[0];
                    
                    if(isPossible(up[0], up[1], 2) && isPossible(down[0], down[1], 3)) {
                        record[up[0]][up[1]][2] = current.step + 1;
                        record[down[0]][down[1]][3] = current.step + 1;
                        queue.offer(new Info(null, null, up, down, current.step + 1));
                    }
                }
                
                int[] right = current.right;
                check = new int[][]{{right[0] - 1, right[1]}, {right[0] - 1, right[1] - 1}};
                if(canRotate(check)) {
                    int[] up = check[0];
                    int[] down = right;
                    
                    if(isPossible(up[0], up[1], 2) && isPossible(down[0], down[1], 3)) {
                        record[up[0]][up[1]][2] = current.step + 1;
                        record[down[0]][down[1]][3] = current.step + 1;
                        queue.offer(new Info(null, null, up, down, current.step + 1));
                    }
                }
                
                check = new int[][]{{right[0] + 1, right[1]}, {right[0] + 1, right[1] - 1}};
                if(canRotate(check)) {
                    int[] up = right;
                    int[] down = check[0];
                    
                    if(isPossible(up[0], up[1], 2) && isPossible(down[0], down[1], 3)) {
                        record[up[0]][up[1]][2] = current.step + 1;
                        record[down[0]][down[1]][3] = current.step + 1;
                        queue.offer(new Info(null, null, up, down, current.step + 1));
                    }
                }
                
            } else {
                for(int delta = 0; delta < 4; delta++) {
                    int nextUpRow = current.up[0] + deltaRow[delta];
                    int nextUpCol = current.up[1] + deltaCol[delta];
                    
                    int nextDownRow = current.down[0] + deltaRow[delta];
                    int nextDownCol = current.down[1] + deltaCol[delta];
                    
                    if(isPossible(nextUpRow, nextUpCol, 2) && isPossible(nextDownRow, nextDownCol, 3)) {
                        record[nextUpRow][nextUpCol][2] = current.step + 1;
                        record[nextDownRow][nextDownCol][3] = current.step + 1;
                        queue.offer(new Info(null, null, new int[]{nextUpRow, nextUpCol}, new int[]{nextDownRow, nextDownCol}, current.step + 1));
                    }
                }
                
                int[] up = current.up;
                int[][] check = new int[][]{{up[0], up[1] - 1}, {up[0] + 1, up[1] - 1}};
                if(canRotate(check)) {
                    int[] left = check[0];
                    int[] right = up;
                    
                    if(isPossible(left[0], left[1], 0) && isPossible(right[0], right[1], 1)) {
                        record[left[0]][left[1]][0] = current.step + 1;
                        record[right[0]][right[1]][1] = current.step + 1;
                        queue.offer(new Info(left, right, null, null, current.step + 1));
                    }
                }
                
                check = new int[][]{{up[0], up[1] + 1}, {up[0] + 1, up[1] + 1}};
                if(canRotate(check)) {
                    int[] left = up;
                    int[] right = check[0];
                    
                    if(isPossible(left[0], left[1], 0) && isPossible(right[0], right[1], 1)) {
                        record[left[0]][left[1]][0] = current.step + 1;
                        record[right[0]][right[1]][1] = current.step + 1;
                        queue.offer(new Info(left, right, null, null, current.step + 1));
                    }
                }
                
                int[] down = current.down;
                check = new int[][]{{down[0], down[1] - 1}, {down[0] - 1, down[1] - 1}};
                if(canRotate(check)) {
                    int[] left = check[0];
                    int[] right = down;
                    
                    if(isPossible(left[0], left[1], 0) && isPossible(right[0], right[1], 1)) {
                        record[left[0]][left[1]][0] = current.step + 1;
                        record[right[0]][right[1]][1] = current.step + 1;
                        queue.offer(new Info(left, right, null, null, current.step + 1));
                    }
                }
                
                check = new int[][]{{down[0], down[1] + 1}, {down[0] - 1, down[1] + 1}};
                if(canRotate(check)) {
                    int[] left = down;
                    int[] right = check[0];
                    
                    if(isPossible(left[0], left[1], 0) && isPossible(right[0], right[1], 1)) {
                        record[left[0]][left[1]][0] = current.step + 1;
                        record[right[0]][right[1]][1] = current.step + 1;
                        queue.offer(new Info(left, right, null, null, current.step + 1));
                    }
                }
            }
        
        }
    }
    
    boolean isArrived(int[] info) {
        if(info == null)
            return false;
        
        if(info[0] == size - 1 && info[1] == size - 1)
            return true;
        
        return false;
    }
    
    boolean isPossible(int row, int col, int state) {
        if(row < 0 || row >= size || col < 0 || col >= size)
            return false;
        
        if(board[row][col] == 1 || record[row][col][state] != Integer.MAX_VALUE)
            return false;
        
        return true;
    }
    
    boolean canRotate(int[][] info) {
        int checkRow = info[0][0];
        int checkCol = info[0][1];
        
        if(checkRow < 0 || checkRow >= size || checkCol < 0 || checkCol >= size || 
           board[checkRow][checkCol] == 1)
            return false;
        
        checkRow = info[1][0];
        checkCol = info[1][1];
        if(checkRow < 0 || checkRow >= size || checkCol < 0 || checkCol >= size || 
           board[checkRow][checkCol] == 1)
            return false;
        
        return true;
    }
}

class Info {
    int[] left;
    int[] right;
    int[] up;
    int[] down;
    int step;
    
    public Info(int[] left, int[] right, int[] up, int[] down, int step) {
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
        this.step = step;
    }
}
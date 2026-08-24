class Solution {
    int[][] maze;
    int rowSize;
    int colSize;

    int redEndRow;
    int redEndCol;
    int blueEndRow;
    int blueEndCol;

    boolean[][] redVisited;
    boolean[][] blueVisited;

    int answer = Integer.MAX_VALUE;

    int[] moveRow = {-1, 1, 0, 0};
    int[] moveCol = {0, 0, -1, 1};

    void DFS(int redRow, int redCol, int blueRow, int blueCol, int moveCount) {
        if (moveCount >= answer) {
            return;
        }

        boolean redFinished = redRow == redEndRow && redCol == redEndCol;
        boolean blueFinished = blueRow == blueEndRow && blueCol == blueEndCol;

        if (redFinished && blueFinished) {
            answer = moveCount;
            return;
        }

        for (int redDirection = 0; redDirection < 4; redDirection++) {
            int nextRedRow;
            int nextRedCol;

            if (redFinished) {
                nextRedRow = redRow;
                nextRedCol = redCol;
            } else {
                nextRedRow = redRow + moveRow[redDirection];
                nextRedCol = redCol + moveCol[redDirection];

                if (!canMoveRed(nextRedRow, nextRedCol)) {
                    continue;
                }
            }

            for (int blueDirection = 0; blueDirection < 4; blueDirection++) {
                int nextBlueRow;
                int nextBlueCol;

                if (blueFinished) {
                    nextBlueRow = blueRow;
                    nextBlueCol = blueCol;
                } else {
                    nextBlueRow = blueRow + moveRow[blueDirection];
                    nextBlueCol = blueCol + moveCol[blueDirection];

                    if (!canMoveBlue(nextBlueRow, nextBlueCol)) {
                        continue;
                    }
                }

                if (nextRedRow == nextBlueRow && nextRedCol == nextBlueCol) {
                    continue;
                }

                if (nextRedRow == blueRow && nextRedCol == blueCol 
                    && nextBlueRow == redRow && nextBlueCol == redCol) {
                    continue;
                }

            
                redVisited[nextRedRow][nextRedCol] = true;
                blueVisited[nextBlueRow][nextBlueCol] = true;
               
                DFS(nextRedRow, nextRedCol, nextBlueRow, nextBlueCol, moveCount + 1);

                if (!redFinished) {
                    redVisited[nextRedRow][nextRedCol] = false;
                }

                if (!blueFinished) {
                    blueVisited[nextBlueRow][nextBlueCol] = false;
                }
            }
        }
    }

    boolean canMoveRed(int nextRow, int nextCol) {
        if (nextRow < 0 || nextRow >= rowSize
                || nextCol < 0 || nextCol >= colSize) {
            return false;
        }

        if (maze[nextRow][nextCol] == 5) {
            return false;
        }

        return !redVisited[nextRow][nextCol];
    }

    boolean canMoveBlue(int nextRow, int nextCol) {
        if (nextRow < 0 || nextRow >= rowSize
                || nextCol < 0 || nextCol >= colSize) {
            return false;
        }

        if (maze[nextRow][nextCol] == 5) {
            return false;
        }

        return !blueVisited[nextRow][nextCol];
    }

    public int solution(int[][] maze) {
        this.maze = maze;

        rowSize = maze.length;
        colSize = maze[0].length;

        redVisited = new boolean[rowSize][colSize];
        blueVisited = new boolean[rowSize][colSize];

        int redStartRow = 0;
        int redStartCol = 0;
        int blueStartRow = 0;
        int blueStartCol = 0;

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            for (int colIdx = 0; colIdx < colSize; colIdx++) {

                if (maze[rowIdx][colIdx] == 1) {
                    redStartRow = rowIdx;
                    redStartCol = colIdx;
                } else if (maze[rowIdx][colIdx] == 2) {
                    blueStartRow = rowIdx;
                    blueStartCol = colIdx;
                } else if (maze[rowIdx][colIdx] == 3) {
                    redEndRow = rowIdx;
                    redEndCol = colIdx;
                } else if (maze[rowIdx][colIdx] == 4) {
                    blueEndRow = rowIdx;
                    blueEndCol = colIdx;
                }
            }
        }

        redVisited[redStartRow][redStartCol] = true;
        blueVisited[blueStartRow][blueStartCol] = true;

        DFS(
                redStartRow,
                redStartCol,
                blueStartRow,
                blueStartCol,
                0
        );

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
}
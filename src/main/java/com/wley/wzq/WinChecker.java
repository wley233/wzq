package com.wley.wzq;

public class WinChecker {
    /**
     * 判断在五子棋游戏中，玩家的最后一个移动是否是制胜一招。
     *
     * @param board 表示棋盘当前状态的二维数组，1 表示玩家，-1 表示对手。
     * @param lastRow 最后一个移动的行坐标。
     * @param lastCol 最后一个移动的列坐标。
     * @param player 当前玩家的标识，1 表示黑方，-1 表示白方。
     * @return 如果最后一个移动使当前玩家获胜，则返回 true；否则返回 false。
     */
    public static boolean isLastMoveWinning(int[][] board, int lastRow, int lastCol, int player) {
        // 检查水平方向是否有五个连续的相同玩家的棋子
        // 检查横向
        int countHorizontal = 1;
        for (int j = lastCol - 1; j >= 0 && board[lastRow][j] == player; j--) {
            countHorizontal++;
        }
        for (int j = lastCol + 1; j < board[0].length && board[lastRow][j] == player; j++) {
            countHorizontal++;
        }

        // 检查垂直方向是否有五个连续的相同玩家的棋子
        // 检查纵向
        int countVertical = 1;
        for (int i = lastRow - 1; i >= 0 && board[i][lastCol] == player; i--) {
            countVertical++;
        }
        for (int i = lastRow + 1; i < board.length && board[i][lastCol] == player; i++) {
            countVertical++;
        }

        // 检查主对角线方向是否有五个连续的相同玩家的棋子
        // 检查主对角线
        int countDiagonalMain = 1;
        for (int i = lastRow - 1, j = lastCol - 1; i >= 0 && j >= 0 && board[i][j] == player; i--, j--) {
            countDiagonalMain++;
        }
        for (int i = lastRow + 1, j = lastCol + 1; i < board.length && j < board[0].length && board[i][j] == player; i++, j++) {
            countDiagonalMain++;
        }

        // 检查副对角线方向是否有五个连续的相同玩家的棋子
        // 检查副对角线
        int countDiagonalSub = 1;
        for (int i = lastRow - 1, j = lastCol + 1; i >= 0 && j < board[0].length && board[i][j] == player; i--, j++) {
            countDiagonalSub++;
        }
        for (int i = lastRow + 1, j = lastCol - 1; i < board.length && j >= 0 && board[i][j] == player; i++, j--) {
            countDiagonalSub++;
        }

        // 如果在任何方向上有五个或更多连续的相同玩家的棋子，则该玩家获胜
        return countHorizontal >= 5 || countVertical >= 5 || countDiagonalMain >= 5 || countDiagonalSub >= 5;
    }

    public static void main(String[] args) {
        int[][] boardExample = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 2, 2, 0},
                {0, 0, 2, 1, 1, 2, 0},
                {0, 0, 2, 1, 1, 2, 0},
                {0, 0, 2, 2, 2, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        };
        // 假设最后一次落子在(4, 3)，即第5行第4列，玩家为1（黑棋）
        System.out.println(isLastMoveWinning(boardExample, 4, 3, 1)); // 判断这一步是否让黑棋获胜
    }
}


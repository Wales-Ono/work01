package model;

public class GameLogic{
    public int judge(Board board){
        int judgeFlag = 0; // 0 は続行
        if (board.getCount() < 9) { // 盤面クリックが9回以下のとき
            if (winJudge(board)) { // 勝利者がいるときは 1 ,いないときは 0
                judgeFlag = 1;
            } 
        } else {  // count = 9 のとき
            if (winJudge(board)) { // 勝利者がいるときは 1 ,いないとき(引き分け)は 2
                judgeFlag = 1;
            } else {
                judgeFlag = 2;
            }
        }
        // 0(継続) , 1(どちらか勝利) , 2(引き分け)　を返す
        return judgeFlag;
    }

    public boolean winJudge(Board board){
        // 行の判定
        if ( board.getCells(0) == board.getMark() && board.getCells(1) == board.getMark() && board.getCells(2) == board.getMark() ||
            board.getCells(3) == board.getMark() && board.getCells(4) == board.getMark() && board.getCells(5) == board.getMark() ||
            board.getCells(6) == board.getMark() && board.getCells(7) == board.getMark() && board.getCells(8) == board.getMark() 
        ) {  
            return true;
        
        // 列の判定
        } else if ( board.getCells(0) == board.getMark() && board.getCells(3) == board.getMark() && board.getCells(6) == board.getMark() ||
            board.getCells(1) == board.getMark() && board.getCells(4) == board.getMark() && board.getCells(7) == board.getMark() ||
            board.getCells(2) == board.getMark() && board.getCells(5) == board.getMark() && board.getCells(8) == board.getMark() 
        ) {
            return true;

        // 斜めの判定
        } else if( board.getCells(0) == board.getMark() && board.getCells(4) == board.getMark() && board.getCells(8) == board.getMark() ||
            board.getCells(2) == board.getMark() && board.getCells(4) == board.getMark() && board.getCells(6) == board.getMark()
        ) {
            return true;
        }
        return false;
    }
}
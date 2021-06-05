package model;

public class GameLogic{
    public String getTurn(int count){
        String turn;
        if(count % 2 == 0){
            turn = "×";
        }else{
            turn = "○";
        }
        count++;
        return turn;
    }

    // public int getCount(){
    //     return count;
    // }
}
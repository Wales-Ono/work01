import java.util.Scanner;
import java.util.Random;


public class TicTacToe1{
    public static void main(String[] args){    
        Scanner scanner = new Scanner(System.in);   //標準入力であるキーボードからの入力を受け取る Scanner クラスのインスタンスを作成
        
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("\t三目並べ　へようこそ！！　[Enterで次へ]");
        scanner.nextLine(); //Enter　を入力で改行
        
        //ゲームの説明文を表示
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("=ゲーム説明=");
        System.out.println("このゲーム(三目並べ)はプレイヤー2人で対戦します。まずは2人で先攻後攻を決めてゲームスタート！！\n"
        + "縦横3×3の盤に、プレイヤーが交互にマーク(白丸「○」,黒丸「●」)を書き込んでいきます。\n"
        + "最終的にビンゴのように、縦・横・斜めのいずれか1列に3個自分のマークを並べると勝ちとなるゲームです。\n\n"
        + "[Enterで次へ]");
        scanner.nextLine();
        System.out.println("--------------------------------------------------------------------------------------------------");

        //プレイヤーの名前を登録
        Scanner scanName = new Scanner(System.in); //標準入力であるキーボードからの入力を受け取る Scanner クラスのインスタンスを作成
        System.out.println("それでは始めにプレイヤー2人の名前を登録します。");
        String[] names = new String[2]; //names に2人のプレイヤー名を格納
        for(int i = 0;i < names.length;i++){
            System.out.println(i+1 + "人目のプレイヤーの名前を入力し、\"Enter\"を押してください。");
            System.out.print(">");
            names[i] = scanName.next(); //入力された名前を配列に格納
            System.out.println();
        }
        System.out.println("[Enterで次へ]");
        scanner.nextLine();
        System.out.println("--------------------------------------------------------------------------------------------------");

        //先攻後攻をランダムで決める
        String senkoName,koukoName;
        Random random = new Random(); //Randomクラスをnewする
        int ran = random.nextInt(2); //0 か 1 を生成する
        if(ran == 0){ //生成された数字が 0 のときは1人目に入力した人が先攻、2人目に入力した人が後攻
            senkoName = names[0];
            koukoName = names[1];
        }else{ //生成された数字が 1 のときは1人目に入力した人が後攻、2人目に入力した人が先攻
            senkoName = names[1];
            koukoName = names[0];
        }
        System.out.println("先攻(○)：" + senkoName + "さん");
        System.out.println("後攻(●)：" + koukoName + "さん");
        System.out.println("順番は以上のように決定しました。 [Enterで次へ]");
        scanner.nextLine();
        System.out.println("--------------------------------------------------------------------------------------------------");

        //補足説明
        System.out.println("※補足※");
        System.out.println("マークを書き込む際の数字は以下の盤面を参照してください。\n");
        System.out.println("＝盤面の数字割り当て＝");
        System.out.println("  1  |  2  |  3  ");
        System.out.println("-----------------");
        System.out.println("  4  |  5  |  6  ");
        System.out.println("-----------------");
        System.out.println("  7  |  8  |  9  ");
        System.out.println("[Enterでゲームスタート！！]");
        scanner.nextLine();
        System.out.println("--------------------------------------------------------------------------------------------------");

        //ゲーム開始
        String[][] boards = new String[3][3]; //3×3 のString型の二次元配列を作成し、その参照を boards　に代入する
        for(int i = 0;i < boards.length;i++){
            for(int j = 0;j < boards[i].length;j++){
                if(boards[i][j] == null){ 
                    boards[i][j] = "　"; //null と表示させないため null の部分には空白文字を代入する
                }   
            }
        }
        boardDisp(boards); //最初の盤面を縦横 3×3 で表示
        System.out.println();
        
        Scanner scanNum = new Scanner(System.in); //標準入力であるキーボードからの入力を受け取る Scanner クラスのインスタンスを作成
        
        while(true){ //勝敗が決まる or 引き分け　までループさせる        
            //先攻
            game(senkoName,scanNum,boards,"先攻","○");
            if(isWinJudge(boards)){ //先攻勝利の場合
                System.out.println(senkoName + "さんの勝利です！");
                break;
            }
            if(isDrawJudge(boards)){ //引き分けの場合
                System.out.println("引き分けです！");
                break;
            }
            //後攻
            game(koukoName,scanNum,boards,"後攻","●");
            if(isWinJudge(boards)){ //後攻勝利の場合
                System.out.println(koukoName + "さんの勝利です！");
                break;
            }
            if(isDrawJudge(boards)){ //引き分けの場合
                System.out.println("引き分けです！");
                break;
            }
        }
    }

    //盤面の状態を表示するメソッド
    static void boardDisp(String[][] boards){
        System.out.println("＝現在の盤面＝");
        System.out.println(boards[0][0] + "|" + boards[0][1] + "|" + boards[0][2]);
        System.out.println("--------");
        System.out.println(boards[1][0] + "|" + boards[1][1] + "|" + boards[1][2]);
        System.out.println("--------");
        System.out.println(boards[2][0] + "|" + boards[2][1] + "|" + boards[2][2]);
    }

    //入力された数字をint型にして返すメソッド
    static int getNum(Scanner scanNum){
        int num = Integer.parseInt(scanNum.next()); //入力された数字(String型)をint型にする
        return num; //入力された数字を返す
    }

    //盤面にマークを書き込むメソッド
    static void setMark(int num,String[][] boards,String mark){ 
        int i = (num - 1) / 3;
        int j = num - 3 * i - 1;
        boards[i][j] = mark;
    }

    //どちらかが勝利(同じマークが3つ並ぶ)したら true を返すメソッド
    static boolean isWinJudge(String[][] boards){
        //横一列が一致
        for(int i = 0;i < 3;i++){
            if(!(boards[i][0].equals("　"))){ //左の列にマークがある場合
                if(boards[i][0] == boards[i][1] && boards[i][0] == boards[i][2]){
                    return true;            
                }   
            }
        }
        //縦一列が一致
        for(int j = 0;j < 3;j++){
            if(!(boards[0][j].equals("　"))){ ////上の列にマークがある場合
                if(boards[0][j] == boards[1][j] && boards[0][j] == boards[2][j]){
                    return true;            
                }   
            }
        }
        //斜め一列が一致
        if(!(boards[1][1].equals("　"))){ //真ん中にマークがある場合
            if((boards[0][0] == boards[1][1] && boards[0][0] == boards[2][2]) || (boards[0][2] == boards[1][1] && boards[0][2] == boards[2][0])){     
                return true;
            }
        }
        return false;
    }

    //引き分け(盤面がすべて埋まる)の場合 true を返すメソッド
    static boolean isDrawJudge(String[][] boards){ //全部のマスにマークがある場合
        if((boards[0][0] == "○" || boards[0][0] == "●") && (boards[0][1] == "○" || boards[0][1] == "●") && (boards[0][2] == "○" || boards[0][2] == "●") &&
        (boards[1][0] == "○" || boards[1][0] == "●") && (boards[1][1] == "○" || boards[1][1] == "●") && (boards[1][2] == "○" || boards[1][2] == "●") &&
        (boards[2][0] == "○" || boards[2][0] == "●") && (boards[2][1] == "○" || boards[2][1] == "●") && (boards[2][2] == "○" || boards[2][2] == "●")){
            return true;
        }else{
            return false;
        }
    }

    //ゲームを進行するメソッド
    static void game(String name,Scanner scanNum,String[][] boards,String turn,String mark){
        while(true){
            System.out.println("＜"+ turn + "：" + name +"さんのターン＞");
            System.out.println("どこにマークを書き込みますか？数字を入力し、”Enter”を押してください。");
            System.out.print(">");
            int intNum = getNum(scanNum); //入力された値をintに変換
            int i = (intNum - 1) / 3;
            int j = intNum - 3 * i - 1;
            if(!(intNum == 1 || intNum == 2 || intNum == 3 || intNum == 4 || intNum == 5 || intNum == 6 || intNum == 7 || intNum == 8 || intNum == 9)){
                System.out.println();
                System.out.println("[1～9]以外が入力されました。もう一度正しい数字を入力してください。");
                System.out.println();
                continue;
            }else if(boards[i][j] != "　"){
                System.out.println();
                System.out.println("すでに置かれています。もう一度正しい数字を入力してください。");
                System.out.println();
                continue;
            }
            System.out.println();
            setMark(intNum,boards,mark); //入力された値を基に、指定された盤面の位置に"○"を書き込む
            boardDisp(boards); //現在の盤面を表示
            System.out.println();
            if(!isWinJudge(boards)){ 
                break;
            }
            if(!isDrawJudge(boards)){ 
                break;
            }

        }
    }
}
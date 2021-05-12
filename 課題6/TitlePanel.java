import java.awt.*;
import java.awt.event.*;

//タイトル画面のパネル作成
public class TitlePanel extends Panel{
    //"スタート"ボタンと"やめる"ボタンのインスタンスを作成
    private Button startBtn;
    private Button quitBtn;

    TitlePanel(){
        this.setLayout(new GridLayout(0,3)); //横3列のグリッドレイアウト
        startBtn = new Button("スタート");
        quitBtn = new Button("やめる");
        for(int i = 0;i < 21;i++){ //縦7 × 横3
            if(i == 4){
                this.add(new Label("                        三目並べ")); //i=4 ラベルを貼り付け(空白で余白を埋める)
            }else if(i == 10){
                this.add(startBtn); //i=10 "スタート"ボタンを設置
            }else if(i == 16){
                this.add(quitBtn); //i=16 "やめる"ボタンを設置
            }else{
                this.add(new Label("")); //その他は空白文字にすることでレイアウトを調整
            }
        }
        
        quitBtn.addActionListener(ae -> {
            if(ae.getSource() == quitBtn){ //"やめる"ボタンが押されたとき
                System.exit(0);
            }
        });
    }

    Button getStartBtn(){
        return startBtn;
    }

    Button getQuitBtn(){
        return quitBtn;
    }

}
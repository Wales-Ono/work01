import java.awt.*;
import java.awt.event.*;

//タイトル画面のパネル作成
public class TitlePanel extends Panel implements ActionListener{
    //"スタート"ボタンと"やめる"ボタンのインスタンスを作成
    Button startBtn = new Button("スタート");
    Button quitBtn = new Button("やめる");    
    public TitlePanel(){
        setLayout(new GridLayout(0,3)); //横3列のグリッドレイアウト
        for(int i = 0;i < 21;i++){ //縦7 × 横3
            if(i == 4){　
                this.add(new Label("                      三目並べ")); //i=4 ラベルを貼り付け(空白で余白を埋める)
            }else if(i == 10){
                this.add(startBtn); //i=10 "スタート"ボタンを設置
            }else if(i == 16){
                this.add(quitBtn); //i=16 "やめる"ボタンを設置
            }else{
                this.add(new Label("")); //その他は空白文字にすることでレイアウトを調整
            }
        }
        startBtn.addActionListener(this); //"スタート"ボタンが押されるか監視
        quitBtn.addActionListener(this);　//"やめる"ボタンが押されるか監視
    }

    //"スタート"ボタン→ゲーム画面へ , "やめる"ボタン→画面を閉じる
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == startBtn){ //"スタート"ボタンが
        押されたとき
            
        }else if(ae.getSource() == quitBtn){ //"やめる"ボタンが押されたとき
            System.exit(0);
        }
    }
}
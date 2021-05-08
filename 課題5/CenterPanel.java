import java.awt.*;
import java.awt.event.*;

//ゲーム画面(真ん中)の作成
public class CenterPanel extends Panel implements ActionListener{
    Button[] button = new Button[9]; //Button型配列を要素数9で作成
    public CenterPanel(){
        setLayout(new GridLayout(0,3)); //グリッドレイアウト で盤面を作成(横3列で改行)
        for(int i = 0; i < 9;i++){
            button[i] = new Button(""); //空白文字のボタンを作成
            this.add(button[i]); //ボタンを追加
            button[i].addActionListener(this); //リスナーによりボタンが押されないかを監視
        }
    }

    //ボタンが押されるとマークが書き込まれる
    public void actionPerformed(ActionEvent ae) {
        for(int i = 0; i < 9;i++){
            if (ae.getSource() == button[i]){ //ボタンがクリックされたイベント情報が入っている。オブジェクトaeはボタンがクリックされたときに生成
                if(i % 2 == 0){
                    button[i].setLabel("○");
                }
            }
        }
    }
}
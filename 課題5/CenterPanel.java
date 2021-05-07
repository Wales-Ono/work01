import java.awt.*;
import java.awt.event.*;

//リスナーインターフェースを実装
public class CenterPanel extends Panel implements ActionListener{
    Button[] button = new Button[9]; //Button型配列を要素数9で作成
    
    public CenterPanel(){
        setLayout(new GridLayout(0,3)); //グリッドレイアウト で盤面を作成
        for(int i = 0; i < 9;i++){
            button[i] = new Button(""); 
            add(button[i]);
            button[i].addActionListener(this);
        }
    }

    //ボタンが押されるとマークが書き込まれる
    public void actionPerformed(ActionEvent ae) {
        for(int i = 0; i < 9;i++){
            if (ae.getSource() == button[i]){ //ボタンがクリックされたイベント情報が入っている。オブジェクトaeはボタンがクリックされたときに生成
                button[i].setLabel("○");
            }
        }
    }
}

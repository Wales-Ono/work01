import java.awt.*;
import java.awt.event.*;

public class TitlePanel extends Panel implements ActionListener{
    Button startBtn = new Button("スタート");
    Button quitBtn = new Button("やめる");    
    public TitlePanel(){
        setLayout(new GridLayout(0,1)); //グリッドレイアウト で盤面を作成
        add(new Label("三目並べ"));
        add(startBtn);
        add(quitBtn);
        startBtn.addActionListener(this);
        quitBtn.addActionListener(this);
    }

    //ボタンが押されるとマークが書き込まれる
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == startBtn){ //ボタンがクリックされたイベント情報が入っている。オブジェクトaeはボタンがクリックされたときに生成
            
        }else if(ae.getSource() == quitBtn){
            System.exit(0);
        }
    }
}


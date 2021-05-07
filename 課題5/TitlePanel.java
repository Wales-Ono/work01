import java.awt.*;
import java.awt.event.*;

public class TitlePanel extends Panel implements ActionListener{
    Button startBtn = new Button("スタート");
    Button quitBtn = new Button("やめる");    
    public TitlePanel(){
        setLayout(new GridLayout(0,3));
        for(int i = 0;i < 21;i++){
            if(i == 4){
                this.add(new Label("                      三目並べ"));
            }else if(i == 10){
                this.add(startBtn);
            }else if(i == 16){
                this.add(quitBtn);
            }else{
                this.add(new Label(""));
            }
        }
        startBtn.addActionListener(this);
        quitBtn.addActionListener(this);
    }

    //スタートボタン→ゲーム画面へ、やめるボタン→画面を閉じる
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == startBtn){ //ボタンがクリックされたイベント情報が入っている。オブジェクトaeはボタンがクリックされたときに生成
            
        }else if(ae.getSource() == quitBtn){
            System.exit(0);
        }
    }
}
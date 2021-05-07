import java.awt.*;
import java.awt.event.*;

//リスナーインターフェースを実装
public class SouthPanel extends Panel implements ActionListener{
    Button button;
    public SouthPanel(){
        setLayout(new BorderLayout());
        button = new Button("タイトルに戻る"); 
        add("Center",button);
        button.addActionListener(this);
    }

    //ボタンが押されるとマークが書き込まれる
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == button){ //ボタンがクリックされたイベント情報が入っている。オブジェクトaeはボタンがクリックされたときに生成
            button.setLabel("○");
        }
    }
}

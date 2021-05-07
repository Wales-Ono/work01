import java.awt.*;
import java.awt.event.*;

//リスナーインターフェースを実装
public class SouthPanel extends Panel implements ActionListener{
    Button backBtn;
    public SouthPanel(){
        setLayout(new BorderLayout());
        backBtn = new Button("タイトルに戻る"); 
        this.add("Center",backBtn);
        backBtn.addActionListener(this);
    }

    //ボタンが押されるとタイトルに戻る
    public void actionPerformed(ActionEvent ae) {
        Frame frame = (Frame) getParent();
        CardLayout cardLayout = (CardLayout) frame.getLayout();
        cardLayout.first(frame);
        
        // if (ae.getSource() == backBtn){ //ボタンがクリックされたイベント情報が入っている。オブジェクトaeはボタンがクリックされたときに生成    
        // }
    }
}

import java.awt.*;
import java.awt.event.*;

//ゲーム画面(下)の作成
public class SouthPanel extends Panel implements ActionListener{
    Button backBtn; 
    public SouthPanel(){
        setLayout(new BorderLayout()); //ボーダーレイアウト
        backBtn = new Button("タイトルに戻る");  //"タイトルに戻る"ボタンの作成
        this.add("Center",backBtn); //ボタンを追加
        backBtn.addActionListener(this); //リスナーによりボタンが押されないか監視
    }

    //ボタンが押されるとタイトルに戻る
    public void actionPerformed(ActionEvent ae) {
        Frame frame = (Frame) getParent();
        CardLayout cardLayout = (CardLayout) frame.getLayout();
        cardLayout.first(frame);
        
        // if (ae.getSource() == backBtn){
        // }
    }
}

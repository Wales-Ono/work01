import java.awt.*;
import java.awt.event.*;

//ゲーム画面(上)の作成
public class NorthPanel extends Panel{
    public NorthPanel(){
        Panel cardPanel = new Panel();
        cardPanel.setLayout(new CardLayout()); //先攻,後攻をカードレイアウトで切り替えできるようにする
        cardPanel.add(new Label("<先攻>")); //ラベル"先攻"を追加
        cardPanel.add(new Label("<後攻>")); //ラベル"後攻"を追加

        this.setLayout(new GridLayout(0,1)); //グリッドレイアウト(一列ごとに改行)
        this.add(new Label("三目並べ")); 
        this.add(new Label("マークを書き込みたい場所をクリックしてください"));
        this.add(cardPanel); //上で作成したパネルを追加
    }
}
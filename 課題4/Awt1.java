import java.awt.*;
import java.awt.event.*;

public class Awt1{
    public static void main(String[] args) {
        // 1番上のパネルを作成
        Panel northPanel = new Panel();
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        northPanel.add(new Label("<先攻>"));
        northPanel.add(new Label("マークを書き込みたい場所をクリックしてください"));

        // 真ん中のパネルを作成
        Panel centerPanel = new Panel();
        centerPanel.setLayout(new GridLayout(0,3)); //グリッドレイアウト で盤面を作成
        for(int i = 1;i <= 9; i++){
            centerPanel.add(new Button("")); //空白文字を入れる
        }

        // 1番下のパネルを作成
        Panel southPanel = new Panel();
        southPanel.add("South",new Button("タイトルに戻る"));

        // 大枠のフレームを作成
        MyFrame flame = new MyFrame();
        flame.setLayout(new BorderLayout());
        flame.add("North",northPanel);
        flame.add("Center",centerPanel);
        flame.add("South",southPanel);

        flame.setVisible(true); //画面を表示
    }
}

class MyFrame extends Frame {
    public MyFrame() {
        setSize(600, 600);
        setTitle("三目並べ");
        addWindowListener(new MyWindowAdapter());
    }
}

class MyWindowAdapter extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}
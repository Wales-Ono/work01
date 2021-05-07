import java.awt.*;
import java.awt.event.*;

public class MyFrame extends Frame{
    public static void main(String[] args) {
        // 大枠のフレームを作成
        MyFrame myFrame = new MyFrame();
        myFrame.setVisible(true); //画面を表示
    }
    
    public MyFrame() {
        setSize(600, 600); //フレームサイズを指定
        setTitle("三目並べ"); //フレームにタイトルを付ける

        TitlePanel titlePanel = new TitlePanel();

        Panel gamePanel = new Panel();
        gamePanel.setLayout(new BorderLayout());
        NorthPanel northPanel = new NorthPanel();
        CenterPanel centerPanel = new CenterPanel();
        SouthPanel southPanel = new SouthPanel();
        gamePanel.add("North",northPanel);
        gamePanel.add("Center",centerPanel);
        gamePanel.add("South",southPanel);

        Panel cardPanel = new Panel();
        CardLayout cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        cardPanel.add(titlePanel,"titlePanel");
        cardPanel.add(gamePanel,"gamePanel");
        this.add(cardPanel);

        addWindowListener(new MyWindowAdapter()); //リスナーで'×'が押されたか監視
    }
}

class MyWindowAdapter extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        System.exit(0); //フレームの'×'を押したときに画面を閉じる
    }
}
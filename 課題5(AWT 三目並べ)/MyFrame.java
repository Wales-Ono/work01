import java.awt.*;
import java.awt.event.*;

public class MyFrame extends Frame implements ActionListener{
    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame(); //フレームのインスタンスを作成
        myFrame.setVisible(true); //画面を表示
    }
    
    Panel cardPanel;
    CardLayout cardLayout;

    public MyFrame() {
        setSize(600, 600); //フレームサイズを指定
        setTitle("三目並べ"); //フレームにタイトルを付ける

        //タイトル画面を表示するパネルのインスタンスを作成
        TitlePanel titlePanel = new TitlePanel();
        titlePanel.startBtn.addActionListener(this); //"スタート"ボタンが押されるか監視

        //ゲーム画面を表示するパネルのインスタンスを作成
        Panel gamePanel = new Panel();
        gamePanel.setLayout(new BorderLayout()); //ボーダーレイアウト
        NorthPanel northPanel = new NorthPanel();
        CenterPanel centerPanel = new CenterPanel();
        SouthPanel southPanel = new SouthPanel();
        gamePanel.add("North",northPanel);
        gamePanel.add("Center",centerPanel);
        gamePanel.add("South",southPanel);

        //カードレイアウトによる画面遷移
        cardPanel = new Panel(); //パネルのインスタンスを作成
        cardLayout = new CardLayout(); //カードレイアウトのインスタンスを作成
        cardPanel.setLayout(cardLayout); //作成したインスタンスを設定
        cardPanel.add(titlePanel,"titlePanel"); //cardPanelにタイトル画面のパネルを貼り付け
        cardPanel.add(gamePanel,"gamePanel"); //cardPanelにゲーム画面のパネルを貼り付け
        this.add(cardPanel); //大枠のフレームに cardPanel を追加

        addWindowListener(new MyWindowAdapter()); //リスナーにより、フレームの'×'が押されたか監視
    }

    public void actionPerformed(ActionEvent ae){
        cardLayout.next(cardPanel);
    }
}

class MyWindowAdapter extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        System.exit(0); //フレームの'×'を押したときに画面を閉じる
    }
}
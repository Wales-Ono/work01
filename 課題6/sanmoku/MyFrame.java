package sanmoku;

import java.awt.*;
import java.awt.event.*;

/**
 * 大枠のフレームを生成します。
 */
public class MyFrame extends Frame{
    /**
     * MyFrameを構築します。
     */
    public MyFrame() {
        this.setSize(600, 600); //フレームサイズを指定
        this.setTitle("三目並べ"); //フレームにタイトルを付ける

        TitlePanel titlePanel = new TitlePanel();
        GamePanel gamePanel = new GamePanel();

        //カードレイアウトによる画面遷移
        Panel cardPanel = new Panel(); //パネルのインスタンスを作成
        CardLayout cardLayout = new CardLayout(); //カードレイアウトのインスタンスを作成
        cardPanel.setLayout(cardLayout); //作成したインスタンスを設定
        cardPanel.add(titlePanel); //cardPanelにタイトル画面のパネルを貼り付け
        cardPanel.add(gamePanel);
        this.add(cardPanel); //大枠のフレームに cardPanel を追加

        titlePanel.getStartBtn().addActionListener(ae -> { //スタート が押されると次のパネルへ
            gamePanel.init();
            cardLayout.next(cardPanel);
        });

        gamePanel.getBackBtn().addActionListener(ae -> { //タイトルに戻る が押されると次のパネルへ
            cardLayout.next(cardPanel);
        });
        
        addWindowListener(new MyWindowAdapter()); //リスナーにより、フレームの'×'が押されたか監視
    }

    /**
     * インスタンスを作成し、画面を表示します。
     * @param args 引数
     */
    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame(); //フレームのインスタンスを作成
        myFrame.setVisible(true); //画面を表示
    }
}

/**
 * フレームのボタンが押されたときの動作を設定します。
 */
class MyWindowAdapter extends WindowAdapter {
    /**
     * フレームの'×'を押したときに画面を閉じます。
     * @param e ウィンドウイベント
     */    
    public void windowClosing(WindowEvent e) {
        System.exit(0); 
    }
}
package sanmoku;

import java.awt.*;
import java.awt.event.*;

/**
 * タイトル画面をパネルに貼り付けます。
 */
public class TitlePanel extends Panel{
    
    /**
     *　ボタン"スタート"
     */
    private Button startBtn;
    
    /**
     *　ボタン"やめる"
     */
    private Button quitBtn;

    /**
     * TitlePanelを構築します。
     */
    public TitlePanel(){
        this.setLayout(new GridLayout(0,3)); //横3列のグリッドレイアウト
        startBtn = new Button("スタート");
        quitBtn = new Button("やめる");
        for(int i = 0;i < 21;i++){ //縦7 × 横3
            if(i == 4){
                this.add(new Label("                        三目並べ")); //i=4 ラベルを貼り付け(空白で余白を埋める)
            }else if(i == 10){
                this.add(startBtn); //i=10 ボタン"スタート"を設置
            }else if(i == 16){
                this.add(quitBtn); //i=16 ボタン"やめる"を設置
            }else{
                this.add(new Label("")); //その他は空白文字にすることでレイアウトを調整
            }
        }
        
        quitBtn.addActionListener(ae -> {
            if(ae.getSource() == quitBtn){ //ボタン"やめる"が押されたとき
                System.exit(0);
            }
        });
    }

    /**
     * ボタン"スタート"が押されたとき、ボタン"スタート"を返します。
     * @return "スタート"
     */
    public Button getStartBtn(){
        return startBtn;
    }

    /**
     * ボタン"やめる"が押されたとき、ボタン"やめる"を返します。
     * @return ボタン"やめる"
     */
    public Button getQuitBtn(){
        return quitBtn;
    }

}
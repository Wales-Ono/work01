import java.awt.*;
import java.awt.event.*;

/**
 * タイトル画面のパネルを作成します。
 */
public class TitlePanel extends Panel{

    /**
     *　ボタン"スタート"を宣言します
     */
    private Button startBtn;
    
    /**
     *　ボタン"スタート"を宣言します
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
                this.add(startBtn); //i=10 "スタート"ボタンを設置
            }else if(i == 16){
                this.add(quitBtn); //i=16 "やめる"ボタンを設置
            }else{
                this.add(new Label("")); //その他は空白文字にすることでレイアウトを調整
            }
        }
        
        quitBtn.addActionListener(ae -> {
            if(ae.getSource() == quitBtn){ //"やめる"ボタンが押されたとき
                System.exit(0);
            }
        });
    }

    /**
     * "スタート"ボタンが押されたとき、"スタート"ボタンを返します。
     * @return "スタート"ボタン
     */
    public Button getStartBtn(){
        return startBtn;
    }

    /**
     * "やめる"ボタンが押されたとき、"やめる"ボタンを返します。
     * @return "やめる"ボタン
     */
    public Button getQuitBtn(){
        return quitBtn;
    }

}
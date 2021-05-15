package sanmoku;

import java.awt.*;
import java.awt.event.*;

/**
 * ゲーム画面をパネルに貼り付けます。
 */
public class GamePanel extends Panel implements ActionListener{ 
    /**
     * ボタン"タイトルに戻る"を作成します。
     */
    private Button backBtn;
    /**
     * 盤面がクリックされた回数をカウントします。
     */
    private int count;
    /**
     * パネル上部に設置するためのラベルを作成します。
     */    
    private Label[] label;    
    /**
     * パネル中央に設置される盤面を作成するためのボタンを作成します。
     */ 
    private Button[] button;

    /**
     * GamePanelを構築します。
     */
    public GamePanel(){
        //上
        Panel northPanel = new Panel();
        northPanel.setLayout(new GridLayout(0,3));
        label = new Label[9];
        for(int i = 0; i < 9;i++){
            if(i == 4){
                label[i] = new Label("                        三目並べ");
            }else if(i == 7){
                label[i] = new Label("                         <先攻>");
            }else{
                label[i] = new Label("");
            }
            northPanel.add(label[i]);
        }

        //中央
        Panel centerPanel = new Panel();
        centerPanel.setLayout(new GridLayout(0,3)); //グリッドレイアウト で盤面を作成(横3列で改行)
        button = new Button[9];
        for(int i = 0; i < 9;i++){
            button[i] = new Button(""); 
            centerPanel.add(button[i]);
            button[i].addActionListener(this);
        }
        
        //下
        Panel southPanel = new Panel();
        backBtn = new Button("タイトルに戻る");
        southPanel.add(backBtn);


        //3つのパネルを集約したgamePanel
        this.setLayout(new GridLayout(0,1));
        this.add(northPanel);
        this.add(centerPanel);
        this.add(southPanel);        
    }

    /**
     * 盤面のイベント処理をします。
     * @param ae アクションイベント
     */
    public void actionPerformed(ActionEvent ae) {
        count++; //盤面クリックする度に count が1増える
        for(int i = 0; i < button.length;i++){
            if(ae.getSource() == button[i]){
                if(count % 2 != 0){ //先攻のターン
                    button[i].setLabel("○"); //"○"を盤面に書き込み
                    button[i].setEnabled(false); //押されたボタンを無効化
                    if(isWinJudge()){ //勝ちかどうか判定
                        for(int j = 0;j < button.length;j++){
                            button[j].setEnabled(false); //ボタンすべてを無効化
                        }                        
                        label[7].setText("                 <先攻>の勝利です");
                        break;
                    }else if(isDrawJudge()){ //引き分けかどうか判定
                        label[7].setText("                     引き分けです！");
                        break;
                    }
                    label[7].setText("                         <後攻>");              
                }else{ //後攻のターン
                    button[i].setLabel("×");
                    button[i].setEnabled(false);
                    if(isWinJudge()){            
                        for(int j = 0;j < button.length;j++){
                            button[j].setEnabled(false);
                        }
                        label[7].setText("                 <後攻>の勝利です");
                        break;
                    }else if(isDrawJudge()){
                        label[7].setText("                     引き分けです！");
                        break;
                    }              
                    label[7].setText("                         <先攻>");                   
                }
            }
        }

    }

    /**
     * 勝者がいるかどうかを判定します。
     * @return 縦,横,斜めのいずれか揃った場合にだけ、trueが返される
     */
    public boolean isWinJudge(){
        for(int i = 0;i < 9;i++){
            if(i % 3 == 0 && (button[i].getLabel() != "")){ //横一列の判定
                if(button[i].getLabel() == button[i+1].getLabel() && button[i].getLabel() == button[i+2].getLabel()){
                    return true;
                }
            }
            if(i < 3 && (button[i].getLabel() != "")){ //縦一列の判定
                if(button[i].getLabel() == button[i+3].getLabel() && button[i].getLabel() == button[i+6].getLabel()){
                    return true;
                }
            }
            if(i == 4 && (button[i].getLabel() != "")){ //斜めの判定
                if(button[i].getLabel() == button[0].getLabel() && button[i].getLabel() == button[8].getLabel()){
                    return true;
                }else if(button[i].getLabel() == button[2].getLabel() && button[i].getLabel() == button[6].getLabel()){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 引き分けかどうかを判定します。
     * @return 引き分けだった場合のみ、trueが返される
     */
    public boolean isDrawJudge(){ //盤面すべてがマークで埋まっている場合は引き分け
        if(button[0].getLabel() != "" && button[1].getLabel() != "" && button[2].getLabel() != "" && button[3].getLabel() != "" && button[4].getLabel() != "" &&
         button[5].getLabel() != "" && button[6].getLabel() != "" && button[7].getLabel() != "" && button[8].getLabel() != "" ) {
                return true;
        }
        return false;
    }

    /**
     * 盤面を初期化します。
     */
    public void init(){
        count = 0;
        label[7].setText("                         <先攻>");
        for(int i = 0; i < 9;i++){
            button[i].setLabel("");
            button[i].setEnabled(true); //盤面すべてのボタンを有効化
        }       
    }
    
    /**
     * ボタン"タイトルに戻る"が押されたとき、ボタン"タイトルに戻る"を返します。
     * @return ボタン"タイトルに戻る"
     */
    public Button getBackBtn(){
        return backBtn;
    }
}
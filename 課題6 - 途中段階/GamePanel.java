import java.awt.*;
import java.awt.event.*;

/**
 * ゲーム画面をパネルに貼り付けます。
 */
public class GamePanel extends Panel implements ActionListener{ 
    /**
     * フィールド
     */
    private Button backBtn;
    private String turn;
    private int count;
    private Label[] label = new Label[9];    
    private Button[] button = new Button[9];


    /**
     * GanePanelを構築します。
     */
    public GamePanel(){
        //上
        Panel northPanel = new Panel();
        northPanel.setLayout(new GridLayout(0,3));
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

        //真ん中
        Panel centerPanel = new Panel();
        centerPanel.setLayout(new GridLayout(0,3)); //グリッドレイアウト で盤面を作成(横3列で改行)
        for(int i = 0; i < 9;i++){
            button[i] = new Button(""); //空白文字のボタンを作成
            centerPanel.add(button[i]); //ボタンを追加
            button[i].addActionListener(this);
        }
        
        //下
        Panel southPanel = new Panel();
        backBtn = new Button("タイトルに戻る");
        southPanel.add(backBtn);


        //全てまとめたgamePanel
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
        count++;
        for(int i = 0; i < button.length;i++){
            if(ae.getSource() == button[i]){ //ボタンがクリックされたイベント情報が入っている。オブジェクトaeはボタンがクリックされたときに生成
                if(count % 2 != 0){ //先攻のターン
                    button[i].setLabel("○");
                    if(isWinJudge()){
                        label[7].setText("                 <先攻>の勝利です");
                        break;
                    }else if(isDrawJudge()){
                        label[7].setText("                     引き分けです！");
                        break;
                    }
                    label[7].setText("                         <後攻>");              
                }else{ //後攻のターン
                    button[i].setLabel("×");
                    if(isWinJudge()){            
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
     * 勝者を判断するメソッドです。
     * 縦,横,斜めのいずれか揃った場合にだけ、trueが返される
     */
    public boolean isWinJudge(){
        for(int i = 0;i < 9;i++){
            if(i % 3 == 0 && (button[i].getLabel() != "")){ //横一列
                if(button[i].getLabel() == button[i+1].getLabel() && button[i].getLabel() == button[i+2].getLabel()){
                    return true;
                }
            }else if(i < 3 && (button[i].getLabel() != "")){ //縦一列
                if(button[i].getLabel() == button[i+3].getLabel() && button[i].getLabel() == button[i+6].getLabel()){
                    return true;
                }
            }else if(i == 4 && (button[i].getLabel() != "")){ //斜め一列
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
     * 引き分けを判断するメソッドです。
     * @return 引き分けだった場合にだけ、trueが返される
     */
    public boolean isDrawJudge(){
        if(button[0].getLabel() != "" && button[1].getLabel() != "" && button[2].getLabel() != "" && button[3].getLabel() != "" && button[4].getLabel() != "" &&
         button[5].getLabel() != "" && button[6].getLabel() != "" && button[7].getLabel() != "" && button[8].getLabel() != "" ) {
                return true;
        }
        return false;
    }
    /**
     * "タイトルに戻る"ボタンが押されたとき、"タイトルに戻る"ボタンを返します。
     * @return "タイトルに戻る"ボタン
     */
    public Button getBackBtn(){
        return backBtn;
    }
}
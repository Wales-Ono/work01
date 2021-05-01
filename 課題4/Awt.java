import java.awt.*;
import java.awt.event.*;

public class Awt{
    public static void main(String[] args) {
        MyFrame flame = new MyFrame();
        GridPanel gridPanel = new GridPanel();
        LabelPanel labelPanel = new LabelPanel();

        flame.setLayout(new GridLayout(0,1));
        // for(int i = 0;i < 9; i++){
        //     flame.add(new Button(""));
        // }
        for(int i = 0;i < 3; i++){
            if(i == 0){
                flame.add(labelPanel);
            }else if(i == 1){
                flame.add(gridPanel);
            }else{
                flame.add(new Label(""));
            }
        }                

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

class LavelPanel extends panel {
    public LabelPanel() {
        setLayout(null); 
        label1 = new Label("Hello Java !!",Label.CENTER);  //hello Java"のラベルを作る
        // label1.setBounds(20,40,160,40);       //ラベルの配置 x yの位置と横縦の大きさ
        add(label1);           //ラベルをフレームに配置
    }
}

class GridPanel extends Panel{
    public GridPanel(){
        setLayout(new GridLayout(0,3));
        for(int i = 0;i < 9; i++){
            add(new Button(""));
        }                
    }
}

class MyWindowAdapter extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}
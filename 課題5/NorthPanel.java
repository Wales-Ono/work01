import java.awt.*;
import java.awt.event.*;

public class NorthPanel extends Panel{
    public NorthPanel(){
        Panel cardPanel = new Panel();
        cardPanel.setLayout(new CardLayout());
        cardPanel.add(new Label("<先攻>"));
        cardPanel.add(new Label("<後攻>"));

        this.setLayout(new GridLayout(0,1));
        this.add(new Label("三目並べ"));
        this.add(new Label("マークを書き込みたい場所をクリックしてください"));
        this.add(cardPanel);
    }
}
import java.awt.*;
import java.awt.event.*;

public class TitlePanel extends Panel implements ActionListener{
    Button startBtn;
    Button quitBtn;    
    public TitlePanel(){
        setLayout(new GridLayout(0,1));
        add(new Label("三目並べ"));
        add(startBtn);
        add(quitBtn);
        startBtn.addActionListener(this);
        quitBtn.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == startBtn){
            
        }else if(ae.getSource() == quitBtn){
            System.exit(0);
        }
    }
}


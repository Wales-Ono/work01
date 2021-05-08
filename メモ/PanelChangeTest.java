import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PanelChangeTest extends JFrame implements ActionListener{

    JPanel cardPanel;
    CardLayout layout;

    public static void main(String[] args) {
        PanelChangeTest frame = new PanelChangeTest();
        frame.setTitle("画面遷移テスト");
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public PanelChangeTest() {

        // panel01
        JPanel panel01 = new JPanel();
        JButton btn01 = new JButton("panel01");
        panel01.add(btn01);

        // panel02
        JPanel panel02 = new JPanel();
        panel02.setBackground(Color.DARK_GRAY);
        JButton btn02 = new JButton("panel02");
        panel02.add(btn02);

        // panel03
        JPanel panel03 = new JPanel();
        panel03.setBackground(Color.LIGHT_GRAY);
        JButton btn03 = new JButton("panel03");
        panel03.add(btn03);

        // CardLayout用パネル
        cardPanel = new JPanel();
        layout = new CardLayout();
        cardPanel.setLayout(layout);

        cardPanel.add(panel01, "panel01");
        cardPanel.add(panel02, "panel02");
        cardPanel.add(panel03, "panel03");

        // カード移動用ボタン
        JButton firstButton = new JButton("panel01");
        firstButton.addActionListener(this);
        firstButton.setActionCommand("panel01");

        JButton secondButton = new JButton("panel02");
        secondButton.addActionListener(this);
        secondButton.setActionCommand("panel02");

        JButton thirdButton = new JButton("panel03");
        thirdButton.addActionListener(this);
        thirdButton.setActionCommand("panel03");

        JPanel btnPanel = new JPanel();
        btnPanel.add(firstButton);
        btnPanel.add(secondButton);
        btnPanel.add(thirdButton);

        // cardPanelとカード移動用ボタンをJFrameに配置
        Container contentPane = getContentPane();
        contentPane.add(cardPanel, BorderLayout.CENTER);
        contentPane.add(btnPanel, BorderLayout.PAGE_END);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        layout.show(cardPanel, cmd);
    }
}
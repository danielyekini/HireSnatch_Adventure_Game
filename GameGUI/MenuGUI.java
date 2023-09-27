package GameGUI;

import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**MenuGUI
 * This extends the GUI class and holds the styling for the UI of the menu window. 
 * It’s a functional menu so uses buttons and actionListeners to open other windows in the program.
*/
public class MenuGUI extends GUI {
    private static GridBagConstraints c = new GridBagConstraints();
    private static Container pane;
    static JButton home;
    static JButton save;
    static JButton exit;
    static StoryGUI storyWindow;

    protected MenuGUI (StoryGUI window) {
        super();
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }
        storyWindow = window;
    }

    private static void menuButtons(MenuGUI object) {
        home = new JButton("Home");
        save = new JButton("Save");
        exit = new JButton("EXIT");
        home.setPreferredSize(new Dimension(100,27));
        save.setPreferredSize(new Dimension(100,27));
        exit.setPreferredSize(new Dimension(100,27));
        home.setFocusPainted(false);
        save.setFocusPainted(false);
        exit.setFocusPainted(false);
        home.setBackground(Color.WHITE);
        save.setBackground(Color.WHITE);
        exit.setBackground(Color.WHITE);
        home.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        save.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        exit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        home.addActionListener(object);
        save.addActionListener(object);
        exit.addActionListener(object);

        c.insets=new Insets(10,0,0,0);
        c.ipady=0;
        c.gridy=1;
        pane.add(home,c);
        c.gridy=2;
        pane.add(save,c);
        c.gridy=3;
        pane.add(exit,c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==home) {
            this.dispose();
            isMenuOpen=false;
            storyWindow.dispose();
            HomeGUI home = new HomeGUI();
            home.open(home);
        } 
        else if (e.getSource()==save) {
            if (isSaveOpen) {
                return;
            } else {
                isSaveOpen=true;
                SaveGUI save = new SaveGUI();
                save.open(save);
            }
        }
        else if (e.getSource()==exit) {
            this.dispose();
            isMenuOpen=false;
        }
    }

    @Override
    public void open(GUI o) {
        this.setSize(300, 330);
        this.setLayout(new GridBagLayout());
        WindowListener listener = new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
               isMenuOpen=false;
            }
        };
        addWindowListener(listener);
        pane = getContentPane();
        pane.setBackground(bgColor);
        pane.setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.NONE;
        c.insets=new Insets(0,0,5,0);
        c.ipady=0;
        c.gridy=0;
        c.gridx=0;
        JLabel logolabel = new JLabel();
        logolabel.setIcon(getLogo());
        pane.add(logolabel,c);
        menuButtons(this);
        this.setVisible(true);
    }
}

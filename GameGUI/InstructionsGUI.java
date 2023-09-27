package GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**InstructionsGUI
 * This extends the GUI class and holds the styling for the UI of the instructions window
*/
public class InstructionsGUI extends GUI {
    private static GridBagConstraints c = new GridBagConstraints();
    private static Container pane;

    protected InstructionsGUI() {
        super();
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }
    }

    @Override
    public void open(GUI o) {
        this.setTitle("Instructions");
        this.setSize(300,450);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        WindowListener listener = new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
               isInstructionsOpen=false;
            }
        };
        this.addWindowListener(listener);

        pane = this.getContentPane();
        pane.setLayout(new GridBagLayout());
        c.insets=new Insets(0,0,0,0);
        c.ipady=0;
        c.gridy=0;
        c.gridx=0;

        JLabel header = new JLabel("Instructions");
        header.setFont(new Font("", Font.BOLD, 15));
        header.setHorizontalAlignment(JLabel.CENTER);

        JTextArea instructionTxt = new JTextArea("HireSnatch is a story driven textbased adventure game. You will be presented with some text and options to chose from, each option will take you down a different path so choose wisely. Aquired items are stored in your inventory, you will need to reopen the inventory window after aquiring a new item. Remember to save your progress before exiting the game, you wouldn't want to lose your position or inventory!\n\nLastly, have fun! And thanks for playing!");
        instructionTxt.setPreferredSize(new Dimension(220,280));
        instructionTxt.setOpaque(false);
        instructionTxt.setEditable(false);
        instructionTxt.setLineWrap(true);
        instructionTxt.setWrapStyleWord(true);
        instructionTxt.setFont(new Font("", Font.BOLD, 13));

        JLabel credits = new JLabel("Built by Daniel Yekini");
        credits.setFont(new Font("", Font.BOLD, 13));
        
        c.fill=GridBagConstraints.HORIZONTAL;
        c.ipady=25;
        c.gridy=0;
        c.gridx=0;
        this.add(header,c);
        c.fill=GridBagConstraints.NONE;
        c.ipadx=20;
        c.gridy=1;
        this.add(instructionTxt,c);
        c.gridy=2;
        this.add(credits,c);
        c.ipadx=0;
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

    
    
}

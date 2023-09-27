package GameGUI;

import java.awt.*;
import java.awt.event.ActionEvent;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JTextArea;
/**SaveGUI
 * This extends the GUI class and holds the styling for the UI of the save window and sends a 
 * string variable to the GameControl as the name of the save file.
 */
public class SaveGUI extends GUI  {
    private static Container pane;
    JTextArea inputField;
    SaveGUI instance;

    public SaveGUI() {
        super();
        instance=this;
    }

    @Override
    protected void open(GUI o) {
        this.setTitle("Save");
        this.setSize(400,100);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pane = getContentPane();
        pane.setBackground(bgColor);
        pane.setLayout(new FlowLayout(FlowLayout.CENTER,5,20));
        WindowListener listener = new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
               isSaveOpen=false;
            }
        };
        addWindowListener(listener);

        JLabel saveAs = new JLabel("Save as:");
        saveAs.setForeground(Color.white);
        saveAs.setFont(new Font("", Font.BOLD, 13));

        inputField = new JTextArea();
        if (control.user.getName()!=null) { //if user has reached point where they input name in game, it pre-fills the input field for user
            inputField.setText(control.user.getName());
        }
        inputField.setPreferredSize(new Dimension(180,20));
        inputField.setFont(new Font("", Font.PLAIN, 14));

        JButton save = new JButton("Save");
        save.setBackground(Color.WHITE);
        save.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        save.setPreferredSize(new Dimension(70,21));
        save.addActionListener(instance);

        pane.add(saveAs);
        pane.add(inputField);
        pane.add(save);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        isSaveOpen=false;
        control.saveGame(inputField.getText());
        this.dispose();
    }
    
}

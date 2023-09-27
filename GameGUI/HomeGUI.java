package GameGUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import StoryPack.GameControl;

import java.awt.event.ActionEvent;
import java.io.File;

/**HomeGUI
 * This extends the GUI class and holds the styling for the UI of the home window, it also serves as a menu to 
 * lead to other windows in the program and handles the functionality for opening saved game files using 
 * JFileChooser.
*/
public class HomeGUI extends GUI {
    private static GridBagConstraints c = new GridBagConstraints();
    private static Container pane;
    static JButton start, load, instructions, exit;

    public HomeGUI() {
        super();
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }
    }

    private static void header() {
        JPanel homeImage = new JPanel();
        homeImage.setOpaque(false);
        homeImage.setLayout(new BorderLayout());
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel welcome = new JLabel("Welcome To HireSnatch");
        welcome.setFont(new Font("Times New Roman", Font.BOLD, 30));
        welcome.setForeground(Color.WHITE);
        welcome.setIcon(getLogo());
        welcome.setIconTextGap(20);
        welcome.setHorizontalTextPosition(JLabel.CENTER);
        welcome.setVerticalTextPosition(JLabel.BOTTOM);
        welcome.setVerticalAlignment(JLabel.CENTER);
        welcome.setHorizontalAlignment(JLabel.CENTER);

        homeImage.add(welcome);

        c.ipady=60;
        c.gridy=0;
        c.gridx=0;
        pane.add(homeImage,c);
    }

    private static void homeMenu(HomeGUI object) {
        JPanel homeMenu = new JPanel();
        homeMenu.setOpaque(false);
        homeMenu.setLayout(new FlowLayout(FlowLayout.CENTER,200,10));

        start = new JButton("Start New Game");
        start.setPreferredSize(new Dimension(200,30));
        start.setBackground(Color.WHITE);
        start.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        start.setFocusPainted(false);
        start.addActionListener(object);

        load = new JButton("Load Saved Game");
        load.setPreferredSize(new Dimension(200,30));
        load.setBackground(Color.WHITE);
        load.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        load.setFocusPainted(false);
        load.addActionListener(object);

        instructions = new JButton("Instructions/ Credits");
        instructions.setPreferredSize(new Dimension(200,30));
        instructions.setBackground(Color.WHITE);
        instructions.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        instructions.setFocusPainted(false);
        instructions.addActionListener(object);

        exit = new JButton("EXIT");
        exit.setPreferredSize(new Dimension(200,30));
        exit.setBackground(Color.WHITE);
        exit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        exit.addActionListener(e -> System.exit(0));

        homeMenu.add(start);
        homeMenu.add(load);
        homeMenu.add(instructions);
        homeMenu.add(exit);

        c.ipady=120;
        c.gridy=1;
        pane.add(homeMenu,c);
    }

    private static void footer() {
        JPanel footer = new JPanel();
        footer.setOpaque(false);
        footer.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JLabel copyright = new JLabel("Copyright© HireSnatch 2022");
        copyright.setFont(new Font("", Font.BOLD, 10));
        copyright.setForeground(Color.white);
        copyright.setPreferredSize(new Dimension(280,20));
        JLabel version = new JLabel("Version 1.1");
        version.setFont(new Font("", Font.PLAIN, 10));
        version.setForeground(Color.white);
        version.setPreferredSize(new Dimension(70,20));
        footer.add(copyright);
        footer.add(version);
        
        c.ipady=10;
        c.gridy=2;
        pane.add(footer,c);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==start) {
            this.dispose();
            StoryGUI storyWindow = new StoryGUI();
            storyWindow.open(storyWindow);
            control = new GameControl(storyWindow);
        } 
        else if (e.getSource()==load) {
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("./SavedGames"));//set file directory to specified save game folder
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
            fc.setFileFilter(filter);
            File userFile=null;
            int result = fc.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                userFile = new File(fc.getSelectedFile().getAbsolutePath());
                this.dispose();
                StoryGUI loadStory = new StoryGUI();
                loadStory.open(loadStory);
                control = new GameControl(loadStory);
                control.loadGame(userFile);
            }
            
        }
        else if (e.getSource()==instructions) {
            if (isInstructionsOpen) {//checks if window is already open and will not open a second window
                return;
            } else {
                isInstructionsOpen=true;
                InstructionsGUI instcWindow = new InstructionsGUI();
                instcWindow.open(instcWindow);
            }
        }
    }

    @Override
    public void open(GUI o) {
        this.setSize(600,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pane = getContentPane();
        pane.setLayout(new GridBagLayout());
        pane.setBackground(new Color(18, 52, 86));
        c.fill = GridBagConstraints.NONE;
        c.insets=new Insets(0,0,0,0);
        c.ipady=0;
        c.gridy=0;
        c.gridx=0;
        header();
        homeMenu(this);
        footer();
        this.setVisible(true);
    }
    
}

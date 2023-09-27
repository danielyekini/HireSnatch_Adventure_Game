package GameGUI;

import java.awt.*;
import javax.swing.*;

import StoryPack.ItemsPack.*;

import java.awt.event.ActionEvent;

/**StoryGUI
 * This extends the GUI class and holds the styling for the UI of the story window where the main game 
 * takes place. It provides paths to the mini menu and inventory window through buttons and holds the 
 * setImage(), setOptions(), and setMessage() methods. These are used to switch the scenes displayed on screen. 
*/
public class StoryGUI extends GUI {
    private static GridBagConstraints c = new GridBagConstraints();
    private static Container pane;
    private static JButton menu, inventory;
    private static boolean isImage, isItem1, isItem2;
    private static JPanel imagePanel;
    private static JTextField userInput;
    private static JButton option1, option2;
    private static JTextArea textArea;
    private static StoryGUI instance;
    static Item item1, item2;
    static int transition1, transition2;
    
    public StoryGUI() {
        super();
        instance = this;
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }
    }

    private static void menuPanel(StoryGUI o) {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout());
        menuPanel.setBackground(new Color(179,180,189));
        
        menu = new JButton("Menu");
        menu.setPreferredSize(new Dimension(100,26));
        menu.setFocusPainted(false);
        menu.setBackground(Color.WHITE);
        menu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        menu.addActionListener(o);

        inventory = new JButton("Inventory");
        inventory.setPreferredSize(new Dimension(100,26));
        inventory.setFocusPainted(false);
        inventory.setBackground(Color.WHITE);
        inventory.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        inventory.addActionListener(o);

        c.fill = GridBagConstraints.NONE;
        c.ipady=0;
        c.gridx=0;
        menuPanel.add(menu,c);
        c.gridx=1;
        menuPanel.add(inventory,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady=5;
        c.gridy=0;
        c.gridx=0;
        pane.add(menuPanel, c);
    }

    private static void imgPanel(JLabel displayImg) {
        imagePanel = new JPanel();
        imagePanel.setBackground(new Color(18, 52, 86));
        imagePanel.setLayout(new GridLayout());
        imagePanel.setPreferredSize(new Dimension(0,295));
        imagePanel.setVisible(true);
        if (isImage) {
            c.ipady=5;
            imagePanel.add(displayImg);
        } else {
        }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy=1;
        c.gridx=0;
        c.gridwidth=1;
        pane.add(imagePanel, c);
    }
    
    private static void optionPanel(boolean inputText, String opt1, String opt2) {
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        optionsPanel.setBackground(new Color(179,180,189));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady=0;
        c.gridy=2;
        c.gridx=0;

        if (inputText) {
            userInput = new JTextField();
            userInput.setVisible(true);
            userInput.setPreferredSize(new Dimension(400, 22));
            c.ipady = 5;
            userInput.addActionListener(instance);
            optionsPanel.add(userInput);
        } else {
            option1 = new JButton(opt1);
            option2 = new JButton(opt2);
            option1.setVisible(true);
            option2.setVisible(true);
            option1.setFocusPainted(false);
            option2.setFocusPainted(false);
            option1.setPreferredSize(new Dimension(200,27));
            option2.setPreferredSize(new Dimension(200,27));
            option1.setBackground(Color.WHITE);
            option2.setBackground(Color.WHITE);
            option1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            option2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            option1.addActionListener(instance);
            option2.addActionListener(instance);
            optionsPanel.add(option1);
            optionsPanel.add(option2);
        }
        
        pane.add(optionsPanel, c);
    }
    
    private static void textPanel(String message) {
        JPanel textPanel = new JPanel();
        textPanel.setBackground(new Color(188, 207, 151));
        textPanel.setPreferredSize(new Dimension(0,93));
        
        textArea = new JTextArea(message);
        textArea.setEditable(false);
        textArea.setPreferredSize(new Dimension(570,83));
        textArea.setFont(new Font("", Font.PLAIN, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textPanel.add(textArea);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.gridy = 3;
        c.gridx = 0;
        pane.add(textPanel, c);
        return;
    }
    
    public void setImage(String imageFilePath) {
        imagePanel.setVisible(false);
        JLabel displayImage = new JLabel();
        if (imageFilePath==null) {
            isImage=false;
            displayImage=null;
            imgPanel(displayImage);
        } else {
            isImage=true;
            ImageIcon image = new ImageIcon(imageFilePath);
            Image img = image.getImage(); // transform it 
            Image newImg = img.getScaledInstance(240, 240,  java.awt.Image.SCALE_SMOOTH);
            ImageIcon sizedImg = new ImageIcon(newImg);
            displayImage.setIcon(sizedImg);
            displayImage.setHorizontalAlignment(JLabel.CENTER);
            imgPanel(displayImage);
        }
    }

    public void setOptions(boolean inputText, String opt1, String opt2,int transit1,int transit2, Item itm1, Item itm2) {
        transition1 = transit1;
        transition2 = transit2;
        if (inputText) {
            option1.setVisible(false);
            option2.setVisible(false);
            optionPanel(inputText, opt1, opt2);
        } else {
            if (userInput!=null) {  //removes any existing userInput field so option buttons can be displayed
                userInput.setVisible(false);
            }
            if (itm1!=null) {  //checks for item object, displays item objects to options panel else displays regular text options
                item1=itm1;
                isItem1=true;
                option1.setText("Take "+itm1.getName());
                if (itm2!=null) {
                    item2=itm2;
                    isItem2=true;
                    option2.setText("Take "+itm2.getName());
                }else {
                    option2.setText("");
                }
            } else {
                isItem1=false;
                isItem2=false;
                option1.setText(opt1);
                option2.setText(opt2);
            }
            option1.setVisible(true);
            option2.setVisible(true);
        }
    }

    public void setMessage(String message) {
        textArea.setText(message);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==menu) {
            if (isMenuOpen) {//checks if window is already open and will not open a second window
                return;
            } else {
                isMenuOpen=true;
                MenuGUI menuWindow = new MenuGUI(this);
                menuWindow.open(menuWindow);
            }
        } 
        else if (e.getSource()==inventory) {
            if (isInventoryOpen) {//checks if window is already open and will not open a second window
                return;
            } else {
                isInventoryOpen=true;
                InventoryGUI invetoryWindow = new InventoryGUI();
                invetoryWindow.open(invetoryWindow);
            }
        }
        else if (e.getSource()==option1) {
            if (option1.getText()=="") {
                return;
            }
            if (isItem1) {
                control.newItem(item1);
            }
            control.storyPosition=transition1;
            control.getScene(control.storyPosition);
        }
        else if (e.getSource()==option2) {
            if (option2.getText()=="") {
                return;
            }
            if (isItem2) {
                control.newItem(item2);
            }
            control.storyPosition=transition2;
            control.getScene(control.storyPosition);
        }
        else if (e.getSource()==userInput) {
            control.storyPosition++;
            control.user.setName(userInput.getText());
            control.getScene(control.storyPosition);
        }
    }

    @Override
    public void open(GUI o) {
        this.setSize(600,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        pane = getContentPane();
        pane.setLayout(new GridBagLayout());
        c.insets=new Insets(0,0,0,0);
        c.ipady=0;
        c.gridy=0;
        c.gridx=0;
        c.gridwidth=1;
        menuPanel(this);
        isImage=false;
        imgPanel(null);
        optionPanel(false, "Option 1", "Option 2");
        textPanel("Message");
        this.setVisible(true);
    }
}

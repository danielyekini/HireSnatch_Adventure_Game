package GameGUI;

import javax.swing.*;

import StoryPack.ItemsPack.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

/**InventoryGUI
 * This extends the GUI class and holds the styling for the UI of the inventory window. It also contains 
 * the addItems() method which adds a button representing an item from users item list. These buttons 
 * print statements to the terminal that align with their item Type
*/
public class InventoryGUI extends GUI {
    private static GridBagConstraints c = new GridBagConstraints();
    private static Container pane;
    private static JPanel inventoryPane;
    private static ArrayList<Item> itemList; //List of items to be displayed in inventory

    protected InventoryGUI() {
        super();
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }
        itemList = control.user.items;//adds items the current user is holding every time window is created
    }

    private static void header() {
        JLabel headerTitle = new JLabel("Inventory");
        headerTitle.setOpaque(true);
        headerTitle.setFont(new Font("", Font.BOLD, 20));
        headerTitle.setBackground(new Color(153,153,153));
        headerTitle.setPreferredSize(new Dimension(0,40));
        headerTitle.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady=0;
        c.gridy=0;
        c.gridx=0;
        c.gridwidth=0;
        pane.add(headerTitle, c);
    }

    private static void inventoryPanel() {
        inventoryPane = new JPanel();
        inventoryPane.setBackground(new Color(18, 52, 86));
        inventoryPane.setPreferredSize(new Dimension(0,421));
        inventoryPane.setLayout(new GridLayout(6,2));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy=1;
        pane.add(inventoryPane, c);
    }

    public static void addItems() {
        JButton itemButton;
        if (itemList.size()==0) {
            return;
        }
        for (Item item : itemList) {
            ImageIcon itemImage = new ImageIcon(item.getImage());
            Image img = itemImage.getImage(); // transform it 
            Image newImg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
            ImageIcon sizedImg = new ImageIcon(newImg);
            itemButton = new JButton(item.getName());
            itemButton.setBackground(Color.WHITE);
            itemButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            itemButton.setFocusPainted(false);
            itemButton.setIcon(sizedImg);
            itemButton.setIconTextGap(-6);
            itemButton.setVerticalTextPosition(JButton.BOTTOM);
            itemButton.setHorizontalTextPosition(JButton.CENTER);
            itemButton.addActionListener(e -> item.itemReact());

            inventoryPane.add(itemButton);

        }
        
    }

    @Override
    public void open(GUI o) {
        this.setSize(400, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        WindowListener listener = new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
               isInventoryOpen=false;
            }
        };
        addWindowListener(listener);

        pane = this.getContentPane();
        pane.setLayout(new GridBagLayout());
        c.insets=new Insets(0,0,0,0);
        c.ipady=0;
        c.gridy=0;
        c.gridx=0;
        header();
        inventoryPanel();
        addItems();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}

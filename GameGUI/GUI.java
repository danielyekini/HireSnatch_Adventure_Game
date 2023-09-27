package GameGUI;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import StoryPack.GameControl;

import java.awt.Color;

/**GUI
 * This is an abstact class that defines the basic features of every GUI window in the
 * program. It extends the JFrame class so every subclass automatically has a JFrame, 
 * they use "this." keyword to specify unique features for their window and implements 
 * ActionListener so subclasses don’t have to.
 */
public abstract class GUI extends JFrame implements ActionListener {
    
    public static GameControl control;
    final static boolean shouldWeightX = true;
    final static boolean shouldFill = true;
    static boolean isLoadOpen=false;
    static boolean isInstructionsOpen=false;
    static boolean isInventoryOpen=false;
    static boolean isMenuOpen=false;
    static boolean isSaveOpen=false;
    static Color bgColor = new Color(18, 52, 86);

    public GUI() {
        this.setTitle("HireSnatch");
        this.setIconImage(getLogo().getImage());
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    protected static ImageIcon getLogo() {
        ImageIcon logo = new ImageIcon("./imgs/logo.jpg");
        Image image = logo.getImage(); // transform it 
        Image newImg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon sizedLogo = new ImageIcon(newImg);
        return sizedLogo;
    }

    public static void start(){
        HomeGUI homeWindow = new HomeGUI();
        homeWindow.open(homeWindow);
    }

    protected abstract void open(GUI o);

}

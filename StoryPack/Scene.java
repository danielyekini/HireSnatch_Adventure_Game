package StoryPack;

import java.io.Serializable;

import GameGUI.StoryGUI;
import StoryPack.ItemsPack.Item;

/**Scene
 * This class defines the features of a scene record and holds the setScene() method 
 * which send the scene information to the GUI to be displayed. It also implements 
 * Serializable so Scenes can be saved to file and read back in so they don’t have to 
 * be directly in the program, this makes it easier to switch out scenes and makes the 
 * GUI reusable.
 */
public class Scene implements Serializable{

    String message,opt1,opt2,imageFilePath;
    boolean inputText;
    Item item1, item2;
    int transition1, transition2;

    public Scene(String message, boolean inputText, String opt1, String opt2,int transition1, int transition2, String imageFilePath) {
        this.message = message;
        this.inputText = inputText;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.transition1 = transition1;
        this.transition2 = transition2;
        this.imageFilePath = imageFilePath;
    }

    public Scene(String message, boolean inputText, Item itemOne, Item itemTwo, int transition1, int transition2, String imageFilePath) {
        this.message = message;
        this.inputText = inputText;
        this.item1 = itemOne;
        this.item2 = itemTwo;
        this.transition1 = transition1;
        this.transition2 = transition2;
        this.imageFilePath = imageFilePath;
    }

    public void setScene(StoryGUI story) {
        story.setImage(imageFilePath);
        story.setMessage(message);
        story.setOptions(inputText, opt1, opt2, transition1, transition2, item1, item2);
    }
}
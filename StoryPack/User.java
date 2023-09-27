package StoryPack;

import java.io.Serializable;
import java.util.ArrayList;

import StoryPack.ItemsPack.Item;
/**User
 * This class defines the features of a user, specifying the data that is saved to a user 
 * object and loaded in later from a save file. User implements Serializable so game progress 
 * can be saved to file and loaded back into the program. It also holds setter and getter as 
 * well as other functionality for a user such as addItem().
 */
public class User implements Serializable{
    String name;
    public ArrayList<Item> items = new ArrayList<>();
    ArrayList<Scene> loadedScenes = new ArrayList<>();
    int storyPosition;

    public String getName() {
        return name;
    }
    public void setName(String userName) {
        name = userName;
    }
    public void savePosition(int position) {
        storyPosition = position;
    }

    public void addItem(Item item) {
        if (items.size()<12) {
            items.add(item);
        }
    }
}

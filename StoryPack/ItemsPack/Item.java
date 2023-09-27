package StoryPack.ItemsPack;

import java.io.Serializable;

/**Item
 * This abstract class defines the features of an item and contains getters for item 
 * information. It also extends Serializable so it can be written to file along with 
 * Scene and User objects and loaded back into the game with Users. It also defines 
 * abstract method itemReact() for item subclasses to define item beavior.
 */
public abstract class Item implements Serializable {

    String name;
    String image;

    public Item(String name, String imageFilePath) {
        this.name = name;
        this.image = imageFilePath;
    }
    public String getName() {
        return name;
    }
    public String getImage() {
        return image;
    }

    public abstract void itemReact();
}
package StoryPack.ItemsPack;

/**
 * This class extends Item and is used for Weapon item objects
 */
public class Weapon extends Item{

    public Weapon(String name, String imageFilePath) {
        super(name, imageFilePath);
    }

    @Override
    public void itemReact() {
        System.out.println("Chingg!!");
    }
    
}

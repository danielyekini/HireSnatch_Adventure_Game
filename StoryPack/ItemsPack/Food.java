package StoryPack.ItemsPack;

/**
 * This class extends Item and is used for Food item objects
 */
public class Food extends Item{

    public Food(String name, String imageFilePath) {
        super(name, imageFilePath);
    }

	@Override
	public void itemReact() {
		System.out.println("Yummy!");
	}
    
}

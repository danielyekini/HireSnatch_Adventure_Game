package StoryPack;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import GameGUI.StoryGUI;
import StoryPack.ItemsPack.*;

/**GameControl
 * This class controls the game flow and serves as an intermediary between the GUI classes and 
 * other classes in the program. This class creates a user object when a new game starts and 
 * oversees saving game data to user objects and to file as well as loading new scenes from file 
 * into the scenes ArrayList when needed. It then notifies the GUI when to display a new game 
 * scene. It’s also responsible for loading a saved game file into the program and continuing 
 * from the last saved position.
 */
public class GameControl{

    public int storyPosition;
    public static ArrayList<Scene> scenes;
    StoryGUI gameInstance;
    Scene scene;
    public User user = new User();

    public GameControl(StoryGUI instance) {
        this.gameInstance=instance;
        scenes = new ArrayList<Scene>();
        storyPosition=0;
        loadScenes(storyPosition);
    }

    public void getScene(int position) {
        user.savePosition(position);
        if (position < 5) {
            scenes.get(position).setScene(gameInstance);
            return;
        } else if (position==5) {   
            loadScenes(5);
        }else if (position>=scenes.size()) {//loads final scene "thanks for playing"
            loadScenes(position);
        }
        scenes.get(position).setScene(gameInstance);
    }

    public void loadScenes(int position) {
        try {
            FileInputStream f = new FileInputStream(new File("./StoryPack/scenes/phase1.txt"));
            ObjectInputStream o = new ObjectInputStream(f);
            if (position==0) {
                for (int i = 0; i < 5; i++) {
                    scene = (Scene) o.readObject();
                    scenes.add(scene);
                }
                getScene(position);
                o.close();
                f.close();
                return;
            }else if (position==5&&(scenes.size()==5)) {
                /*5*/scene = new Scene("Harkeep: Well hello there "+user.getName()+" we get people like you who come by often with memory loss, you're going to want to head to the big city just west of here. Speak to the potions master by the entry gate, hopefully he can help jog your memory...", false, "Continue", "",6,0, "./imgs/Harkeep.jpg");
                scenes.add(scene);
                FileInputStream f1 = new FileInputStream(new File("./StoryPack/scenes/phase2.txt"));
                ObjectInputStream o1 = new ObjectInputStream(f1);
                for (int i = 0; i <= 10; i++) {
                    scene = (Scene) o1.readObject();
                    if (scene!=null) {
                        scenes.add(scene);
                    }
                }
                o1.close();
                f1.close();
                return;
            }

            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IOException");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFound");
        }
        if (position>=scenes.size()) {
            scene = new Scene("THANKS FOR PLAYING!", false, "", "", 0,0,"./imgs/fin.jpg");
            scenes.add(scene);
        }
    }

    public void newItem(Item item) {
        user.addItem(item);
    }

    public void saveGame(String saveAs) {
        try {
            FileOutputStream f = new FileOutputStream(new File("./SavedGames/"+saveAs+".txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            user.loadedScenes=scenes;
            o.writeObject(user);

            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        }
    }

    public void loadGame(File userFile) {
        try {
            FileInputStream f = new FileInputStream(userFile);
            ObjectInputStream o = new ObjectInputStream(f);

            user = (User) o.readObject();
            
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
        scenes = new ArrayList<Scene>();
        scenes = user.loadedScenes;
        storyPosition=user.storyPosition;
        getScene(storyPosition);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/* Visualizer class that contains the main method
 * Begins the update loop and the draw function for the program
 */

public class Visualizer extends JFrame{
  // window variables
  boolean isRunning = true;
  int fps = 30;
  int windowHeight = 1080;
  int windowWidth = 1920;
  float deltaTime;

  // graphics variables
  BufferedImage backBuffer;
  Insets insets;
  Scene scene;

  // Start program and create an instance of Visualizer
  public static void main(String[] args) {
    Visualizer game = new Visualizer();
    game.run(args[0]);
    System.exit(0);
  }

  //create window
  //create a scene
  void init(String arg){
    setTitle("Visualizer");
    setSize(windowWidth, windowHeight);
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);
    insets = getInsets();
    backBuffer = new BufferedImage(windowWidth,windowHeight,BufferedImage.TYPE_INT_RGB);
    scene = new Scene(arg);
  }

  //infinite while loop to create update
  public void run(String arg){
    init(arg);
    while(isRunning){
      long time = System.currentTimeMillis();
      update();
      draw();
      time = (1000/fps) - (System.currentTimeMillis() - time);
      if(time > 0){
        try{
          Thread.sleep(time);
        }catch(Exception e){
          System.out.println(e);
        }
      }
    deltaTime = time;
    }
  }

  //update the scene
  void update(){
    this.scene.update();
  }

  //refresh the screen to black, then draw the scene
  void draw() {
    Graphics g = getGraphics();
    Graphics bbg = backBuffer.getGraphics();
    bbg.setColor(Color.BLACK);
    bbg.fillRect(0, 0, windowWidth, windowHeight);
    scene.draw(bbg, windowWidth, windowHeight);
    g.drawImage(backBuffer, insets.left, insets.top, this);
  }
}

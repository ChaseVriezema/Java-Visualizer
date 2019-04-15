import java.awt.*;
import java.util.ArrayList;

/* Scene class holds the audiomanager and tracks
 * Initializes the song and reads the events
 */

public class Scene{
  private AudioManager manager;
  private VisualTrack[] tracks;

  //Constructor plays the music and populates visual tracks with commands
  public Scene(String arg){
    manager = new AudioManager(arg);
    try{
      manager.playMusic(arg);
    } catch(Exception e){
      System.out.print(e);
    }
    tracks = new VisualTrack[manager.tracks];
    for(int i = 0; i < tracks.length; i++){
      tracks[i] = new VisualTrack(manager.GetCommands(i));
    }
  }

  //Update each visual track
  public void update() {
    for (int i = 0; i < tracks.length; i++){
      tracks[i].update(manager.sequencer.getTickPosition());
    }
  }

  //Draw each visual track
  public void draw(Graphics g, int width, int height) {
    for (int i = 0; i < tracks.length; i++)
      tracks[i].draw(g, width, height);
  }

}
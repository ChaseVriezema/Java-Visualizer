import java.awt.*;
import java.util.ArrayList;

/* VisualTrack holds all the lines for a track
 * It iterates through the commands and resolves them if needed
 */
public class VisualTrack {
  //Alive holds the new particle groups
  //Fade holds the particle groups preparing to be removed
  ArrayList<ParticleGroup> alive;
  ArrayList<ParticleGroup> fade;
  ArrayList<MidiCommand> commands;

  // constructor initializes the alive and fade lists and commands
  public VisualTrack(ArrayList<MidiCommand> commands){
    this.alive = new ArrayList<>();
    this.fade = new ArrayList<>();
    this.commands = commands;
  }

  //Check the commands, update particles, remove invisible groups
  public void update (long time){
    checkCommands(time);
    for(ParticleGroup group : alive){
      group.update();
    }
    for (ParticleGroup group : fade) {
      group.update();
    }
    checkRemove();
  }

  //Draw each particle group
  public void draw (Graphics g, int width, int height){
    for (ParticleGroup group : alive) {
      group.draw(g, width, height);
    }
    for (ParticleGroup group : fade) {
      group.draw(g, width, height);
    } 
  }

  //Iterates through the commands and resolve them once the time has surpassed it
  private void checkCommands(long time){
    for(int i = 0; i < commands.size(); i++){
      MidiCommand command = commands.get(i);
      if(command.time < time){
        resolveCommand(commands.remove(i));
      }
    }
  }

  //Iterates through the fading lines, remove any invisible ones
  private void checkRemove(){
    for(int i = 0; i < fade.size(); i++){
      if(fade.get(i).remove){
        fade.remove(i);
        i--;
        System.out.println("Delete");
      }
    }
  }

  //Resolve commands by adding, fading, and bringing them back
  private void resolveCommand(MidiCommand command){
    if (command.noteOn) {
      ParticleGroup p = findGroup(command, fade);
      if (null == p || !p.redeemable) {
        alive.add(new ParticleGroup(command));
        System.out.println("Add");
      } else {
        int index = fade.indexOf(p);
        alive.add(fade.remove(index));
        alive.get(alive.size() - 1).fading = false;
        System.out.println("Redeem");
      }
    } else {
      ParticleGroup p = findGroup(command, alive);
      if (p != null) {
        int index = alive.indexOf(p);
        fade.add(alive.remove(index));
        fade.get(fade.size() - 1).fading = true;
        System.out.println("Remove");
      }
    }
  }

  //Helper method to find a specific particle in the array list.
  public ParticleGroup findGroup(MidiCommand c, ArrayList<ParticleGroup> p) {
    for (int i = 0; i < p.size(); i++) {
      if (p.get(i).command.key == c.key)
        return p.get(i);
    }
    return null;
  }
}
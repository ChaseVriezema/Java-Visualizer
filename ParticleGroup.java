import java.awt.*;
import javax.swing.*;

/* ParticleGroup holds each individual line for a specific note
 * It checks for them to fade and whether or not they need to be removed
 */
public class ParticleGroup{
  public MidiCommand command;
  public boolean redeemable;
  public boolean fading;
  public boolean remove;
  public int lifetime;
  private ParticleLine[] lines;

  //Constructor that initializes particle lines and sets their color
  public ParticleGroup(MidiCommand command){
    this.command = command;
    this.redeemable = true;
    this.fading = false;
    this.remove = false;
    this.lifetime = 0;
    this.lines = new ParticleLine[8];
    for(int i = 0; i < this.lines.length; i++){
      lines[i] = new ParticleLine(command, 40.0*i, 40.0*i);
      setColor(lines[i]);
    }
  }

  //Updates each particle line and checks for any that need to be removed
  public void update(){
    for(ParticleLine line : this.lines){
      line.update(fading);
    }
    checkRedeem();
    lifetime ++;
  }

  //Checks to see if the line should be removed. Lifetime caps at 10 seconds for each note
  public void checkRedeem(){
    if(lines[0].alpha < 255)
      redeemable = false;
    if(lines[0].alpha <= 0)
      remove = true;
    if(lifetime > 300){
      fading = true;
      redeemable = false;
    }

  }

  //Draw each particle line
  public void draw(Graphics g, int width, int height){
    for (ParticleLine line : this.lines) {
      line.draw(g, width, height);
    }
  }

  /* Sets the color of the particle according to each octave and note. Two big
   * switch cases due to changing and experimentation on what looked best
   */ 
  public void setColor(ParticleLine p) {
    switch (command.octave) {
    case 5:
      switch (command.note) {
      default:
        p.col = new Color(2, 0, 116);
        break;
      case 1:
        p.col = new Color(21, 0, 220);
        break;
      case 2:
        p.col = new Color(0, 68, 255);
        break;
      case 3:
        p.col = new Color(0, 162, 255);
        break;
      case 4:
        p.col = new Color(0, 191, 220);
        break;
      case 5:
        p.col = new Color(79, 255, 186);
        break;
      case 6:
        p.col = new Color(45, 181, 255);
        break;
      case 7:
        p.col = new Color(17, 83, 220);
        break;
      case 8:
        p.col = new Color(123, 61, 220);
        break;
      case 9:
        p.col = new Color(220, 62, 217);
        break;
      case 10:
        p.col = new Color(220, 41, 147);
        break;
      case 11:
        p.col = new Color(220, 23, 75);
        break;
      }
      break;
    case 3:
      switch (command.note) {
      default:
        p.col = new Color(0, 116, 1);
        break;
      case 1:
        p.col = new Color(0, 145, 25);
        break;
      case 2:
        p.col = new Color(8, 203, 0);
        break;
      case 3:
        p.col = new Color(0, 255, 4);
        break;
      case 4:
        p.col = new Color(0, 255, 109);
        break;
      case 5:
        p.col = new Color(79, 255, 186);
        break;
      case 6:
        p.col = new Color(45, 181, 255);
        break;
      case 7:
        p.col = new Color(80, 220, 214);
        break;
      case 8:
        p.col = new Color(172, 220, 103);
        break;
      case 9:
        p.col = new Color(137, 220, 25);
        break;
      case 10:
        p.col = new Color(187, 220, 15);
        break;
      case 11:
        p.col = new Color(220, 213, 0);
        break;
      }
      break;
    default:
      switch (command.note) {
      default:
        p.col = new Color(116, 5, 0);
        break;
      case 1:
        p.col = new Color(145, 13, 0);
        break;
      case 2:
        p.col = new Color(203, 0, 7);
        break;
      case 3:
        p.col = new Color(255, 0, 0);
        break;
      case 4:
        p.col = new Color(255, 0, 72);
        break;
      case 5:
        p.col = new Color(255, 0, 110);
        break;
      case 6:
        p.col = new Color(255, 26, 181);
        break;
      case 7:
        p.col = new Color(220, 104, 199);
        break;
      case 8:
        p.col = new Color(220, 149, 100);
        break;
      case 9:
        p.col = new Color(220, 144, 55);
        break;
      case 10:
        p.col = new Color(220, 140, 29);
        break;
      case 11:
        p.col = new Color(220, 105, 0);
        break;
      }
      break;
    case 2:
      switch (command.note) {
      default:
        p.col = new Color(116, 98, 0);
        break;
      case 1:
        p.col = new Color(145, 142, 5);
        break;
      case 2:
        p.col = new Color(203, 192, 2);
        break;
      case 3:
        p.col = new Color(255, 243, 10);
        break;
      case 4:
        p.col = new Color(255, 226, 0);
        break;
      case 5:
        p.col = new Color(255, 180, 7);
        break;
      case 6:
        p.col = new Color(255, 188, 109);
        break;
      case 7:
        p.col = new Color(190, 220, 97);
        break;
      case 8:
        p.col = new Color(185, 220, 72);
        break;
      case 9:
        p.col = new Color(181, 220, 48);
        break;
      case 10:
        p.col = new Color(170, 220, 37);
        break;
      case 11:
        p.col = new Color(169, 220, 0);
        break;
      }
      break;
    case 1:
      switch (command.note) {
      default:
        p.col = new Color(220, 105, 0);
        break;
      case 1:
        p.col = new Color(220, 140, 29);
        break;
      case 2:
        p.col = new Color(220, 149, 100);
        break;
      case 3:
        p.col = new Color(255, 169, 49);
        break;
      case 4:
        p.col = new Color(255, 185, 31);
        break;
      case 5:
        p.col = new Color(255, 203, 26);
        break;
      case 6:
        p.col = new Color(255, 232, 0);
        break;
      case 7:
        p.col = new Color(220, 68, 0);
        break;
      case 8:
        p.col = new Color(220, 0, 10);
        break;
      case 9:
        p.col = new Color(220, 65, 84);
        break;
      case 10:
        p.col = new Color(255, 113, 88);
        break;
      case 11:
        p.col = new Color(220, 130, 95);
        break;
      }
      break;
    case 4:
      switch (command.note) {
      default:
        p.col = new Color(0, 116, 73);
        break;
      case 1:
        p.col = new Color(0, 145, 93);
        break;
      case 2:
        p.col = new Color(0, 203, 132);
        break;
      case 3:
        p.col = new Color(0, 255, 149);
        break;
      case 4:
        p.col = new Color(0, 255, 100);
        break;
      case 5:
        p.col = new Color(0, 255, 63);
        break;
      case 6:
        p.col = new Color(15, 255, 49);
        break;
      case 7:
        p.col = new Color(94, 220, 119);
        break;
      case 8:
        p.col = new Color(123, 219, 255);
        break;
      case 9:
        p.col = new Color(101, 201, 255);
        break;
      case 10:
        p.col = new Color(0, 190, 255);
        break;
      case 11:
        p.col = new Color(0, 139, 255);
        break;
      }
      break;
    case 6:
      switch (command.note) {
      default:
        p.col = new Color(116, 12, 111);
        break;
      case 1:
        p.col = new Color(145, 0, 136);
        break;
      case 2:
        p.col = new Color(203, 0, 187);
        break;
      case 3:
        p.col = new Color(255, 0, 241);
        break;
      case 4:
        p.col = new Color(255, 0, 252);
        break;
      case 5:
        p.col = new Color(224, 0, 255);
        break;
      case 6:
        p.col = new Color(231, 51, 255);
        break;
      case 7:
        p.col = new Color(220, 104, 199);
        break;
      case 8:
        p.col = new Color(220, 65, 89);
        break;
      case 9:
        p.col = new Color(220, 15, 68);
        break;
      case 10:
        p.col = new Color(255, 0, 53);
        break;
      case 11:
        p.col = new Color(255, 0, 10);
        break;
      }
      break;
    }
  }
}
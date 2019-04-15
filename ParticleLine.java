import java.awt.*;

/* Particle Line calculates the position for each line
 * Changes the alpha based on whether or not the line is fading
 */
public class ParticleLine{
  int x, y;
  double r, theta;
  ParticleSegment first, last;
  MidiCommand command;
  public int length, size;
  public int alpha;
  public Color col;
  public double rotation;

  //Constructor sets the base values for the line, takes in the rotation
  public ParticleLine(MidiCommand command, double rotation, double theta){
    this.theta = theta;
    this.rotation = rotation;
    this.command = command;
    this.length = 10;
    this.size = 0;
    this.alpha = 255;
  }

  //Updates the line
  public void update(boolean fade){
    updateEquation(fade);
    updateLine();
    updateColor(fade);
  }

  //Updates the X/Y value of the line to form the spiral pattern, converts the rotation and theta to cartesian values
  private void updateEquation(boolean fade){
    r = 20 * command.note - 50 * command.octave * Math.cos(Math.PI - (this.rotation - this.theta));
    if (fade)
      theta += 0.00001 * (command.velocity * command.velocity);
    else
      theta -= 0.00001 * (command.velocity * command.velocity);

    x = (int) (r * Math.cos(theta));
    y = (int) (r * Math.sin(theta));
  }

  //Updates the segements linked list, adding in a new one at the end
  private void updateLine(){
    if (first == null) {
      first = new ParticleSegment(x, y);
      first.x = x;
      first.y = y;
      last = first;
      size++;
    } else {
      last.next = new ParticleSegment(x, y);
      last = last.next;
      size++;
    }
    if(size > length){
      first = first.next;
      size -- ;
    }
  }

  //Reduces the alpha if fading, updates color accordingly
  private void updateColor(boolean fade){
    if(fade){
      this.alpha -= 15;
      this.alpha = this.alpha < 0 ? 0 : this.alpha;
    }
    col = new Color(col.getRed(), col.getGreen(), col.getBlue(), this.alpha);
  }

  //Draws the entire linked list of line segments
  public void draw(Graphics g, int width, int height){
    g.setColor(col);
    ParticleSegment draw = first;
    while(null != draw.next){
      draw.draw(g, width/2, height/2);
      draw = draw.next;
    }
  }
}
import java.awt.*;

/* Particle Segment is a linked list for a small segment of the line
 * Draws a line between two points, accounts for the offset on the screen
 */
public class ParticleSegment{
    public ParticleSegment next;
    public int x,y;

    public ParticleSegment(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g, int xOff,int yOff){
        if(next != null){
            g.drawLine(x+xOff,y+yOff,next.x+xOff,next.y+yOff);
        }
    }
}

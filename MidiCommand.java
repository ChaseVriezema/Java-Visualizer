/* Simple object to store the data for each event
 * Calculates values based off the passed key and velocity
 */
public class MidiCommand {
  public boolean noteOn;
  public int key;
  public int note;
  public int octave;
  public long time;
  public int velocity;
  
  public MidiCommand(boolean noteOn, int key, long time, int velocity){
    this.noteOn = noteOn;
    this.key = key;
    this.time = time;
    this.velocity = velocity;
    this.octave = (key / 12)-1;
    this.note = key % 12;
  }
}

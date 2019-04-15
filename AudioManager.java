import javax.sound.midi.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/* Audiomanager reads the input of the .mid file
 * Converts the input into MidiCommand objects 
 * Plays the song via InputStream
 */
public class AudioManager {
  public Sequencer sequencer;
  public Sequence sequence;
  public static final int NOTE_ON = 0x90;
  public static final int NOTE_OFF = 0x80;
  public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
  public int tracks;

  //Constructor sets up sequence and sequencer objects
  public AudioManager (String songName){
    System.out.print(new File(songName).getAbsolutePath());
    try{
      this.sequence = MidiSystem.getSequence(new File(songName));
    }catch(Exception e){
      System.out.print(e);
    }

    this.tracks = sequence.getTracks().length;    
  }

  //Returns an array of MidiCommands for the specified track
  public ArrayList<MidiCommand> GetCommands(int trackNum){
    Track track =  sequence.getTracks()[trackNum];
    ArrayList<MidiCommand> commands = new ArrayList<>();
    for (int i=0; i < track.size(); i++) {
      MidiEvent event = track.get(i);
      MidiMessage message = event.getMessage();
      if (message instanceof ShortMessage) {
        ShortMessage sm = (ShortMessage) message;
        if (sm.getData1() != 0){
          commands.add(new MidiCommand(
            sm.getCommand() == NOTE_ON, 
            sm.getData1(), 
            event.getTick(), 
            sm.getData2()));
        }
      }
    }
    return commands;
  }

  //Plays the specified .mid file through an input stream
  public void playMusic(String name) throws Exception {
    this.sequencer = MidiSystem.getSequencer();
    InputStream songInput = new BufferedInputStream(new FileInputStream(new File(name)));
    sequencer.open();
    sequencer.setSequence(songInput);
    sequencer.start();
  }
}
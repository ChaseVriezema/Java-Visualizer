# Java Visualizer
## About
This is a simple program that reads Midi files and creates visuals to go along with the music. Each note is based on a Limacon Equation that utilizes the octave and pitch of the note to determine speed and width.

The program is dependant on the imported .mid file. Not all will work, as some .mid files use a different value for NoteOff events.
Included are a few .mid files that work well with this program

## Running the Program
- Make sure Java is installed on the machine. 
- Unzip and move the project files to the desired directory.
- Navigate to the directory using the command line.
- Run the following commands to compile and run the project

~~~
javac Visualizer.java 
java Visualizer [songName].mid
#Ex:
java Visualizer Hearthstone.mid
~~~
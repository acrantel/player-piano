import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PlayerPiano 
{
	private BufferedReader br;
	private Piano piano;
	public static final int LOW = -12; // low C
	public static final int HIGH = 23; // high B
	public PlayerPiano(File file)
	{
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.piano = new Piano(LOW, HIGH);
	}
	
	public void strikeNextKeys() throws IOException
	{
		String[] keys = br.readLine().split(" ");
		for (int i = 0; i < keys.length; i++)
		{
			piano.strikeKey(convert(keys[i]));
		}
	}
	
	public void playCurrentNotes()
	{
		long start = System.currentTimeMillis();
		long end = start + 500; // 500 milliseconds = .5 sec
		while (System.currentTimeMillis() < end)
		{
			piano.play();
		}
	}
	
	private int convert(String note)
	{
		// "C", "C#" or "Db", "D", "D#" or "Eb", "E", "F", "F#" or "Gb", 
		// "G", "G#" or "Ab", "A", "A#" or "Bb", "B"
		char c = note.charAt(0);
		int wireNum = 0;
		switch (c)
		{
			case 'C': wireNum = 0; break;
			case 'D': wireNum = 2; break;
			case 'E': wireNum = 4; break;
			case 'F': wireNum = 5; break;
			case 'G': wireNum = 7; break;
			case 'A': wireNum = 9; break;
			case 'B': wireNum = 11; break;
		}
		if (note.length() > 1 && note.charAt(1) == '#') {
			wireNum ++;
		} else if (note.length() > 1 && note.charAt(1) == 'b') {
			wireNum --;
		}
		if (note.charAt(note.length()-1) == '+') {
			wireNum += 12;
		} else if (note.charAt(note.length()-1) == '-') {
			wireNum -= 12;
		}
		return wireNum; 
	}
	
	public static void main(String[] args)
	{
		File file = new File("/Users/95024341/eclipse-workspace/PianoPlayer/src/MaryHadALittleLamb.txt");
		PlayerPiano player = new PlayerPiano(file);
		while (true)
		{
			try {
				player.strikeNextKeys();
			} catch (Exception e) {
				break;
			}
			player.playCurrentNotes();
		}
	}
}

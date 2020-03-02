package musiclevi;

import musiclevi.core.*;
import musiclevi.midi.MidiInterface;
import musiclevi.gen.RandomNotes;

import javax.sound.midi.*;
import java.util.Collections;
import java.util.*;
import musiclevi.midi.*;

public class MidiApp {

    public static void runTest() throws Exception{
        Map<String, String> env = System.getenv();
        String midiInputName = env.get("MMIDI");
        if (midiInputName == null) {
            throw new Exception("MMIDI environment variable is not defined");
        }
        List<Integer> pitches = 
            new ArrayList<>(Arrays.asList(60, 64, 67, 72, 76, 79, 84, 88, 91, 96));
        List<Note> notes = RandomNotes.genRandomNotes(50, 200, 300, pitches);
        MidiInterface.openMidiDevice(midiInputName);
        MidiInterface.playNotes(notes, new ArrayList<Raw>(), 1000);
        MidiInterface.closeMidiDevice();
    }
}
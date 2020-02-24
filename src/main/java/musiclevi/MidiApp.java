package musiclevi;

import musiclevi.core.*;
import javax.sound.midi.*;
import java.util.Collections;
import java.util.*;

public class MidiApp {

    public static void findDeviceNames() {
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        for (MidiDevice.Info i: infos) {
            System.out.println(i.getName());
        }
    }

    /*
    public static void runWithComp(Comp c) 
        throws InvalidMidiDataException, InterruptedException 
    {
        MyMidiNote[] notes = c.toMidi2(0);
        List<MyMidiNote> notesL = Arrays.asList(notes);
        Collections.sort(notesL);

        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        MidiDevice.Info theOne = null;
        for (MidiDevice.Info i: infos) {
            if(i.getName().equals("MidiPipe Input 3")) {
                theOne = i;
            }
        }
        System.out.println(theOne);
        try {
            MidiDevice d = MidiSystem.getMidiDevice(theOne);
            if (!d.isOpen()) {
                d.open();
                Receiver r = d.getReceiver();
                long currentT = 0;
                for (MyMidiNote n: notesL) {
                    // what does -1 mean in send? send now?
                    // why are we sending now? why are we only creating
                    // 1 message? where is sending whole comp? Have
                    // we tested that yet?
                    long t = n.timestamp;
                    int ms = (int)((double)(t - currentT)/1000.0);
                    if (ms <= 0) {
                        r.send(n.msg, -1);
                    } else {
                        Thread.sleep(ms);
                        r.send(n.msg, -1);
                        currentT = t;
                    }
                }
                d.close();
            }
        } catch (MidiUnavailableException e) {
            System.out.println("midi unavailable xception");
        }
    }
    */

    public static Note[] createNotes()
    {
        Note[] arr = new Note[3];
        arr[0] = new Note(0, 1, 60);
        arr[1] = new Note(1, 2, 65);
        arr[2] = new Note(2, 3, 70);
        return arr;
    }

        
        
    public static void runManyNotes(Note[] notes) throws InvalidMidiDataException, InterruptedException {
        Map<String, String> env = System.getenv();
        String midiInputName = env.get("MMIDI");

        ArrayList<MyMidiNote> mns = new ArrayList<MyMidiNote>();

        for (Note n: notes)
        {
            MyMidiNote[] m = n.toMyMidiNote(1000000);
            mns.add(m[0]);
            mns.add(m[1]);
        }

        // List<MyMidiNote> notesL = Arrays.asList(notes);
        Collections.sort(mns);

        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        MidiDevice.Info theOne = null;
        for (MidiDevice.Info i : infos) {
            if (i.getName().equals(midiInputName)) {
                theOne = i;
            }
        }
        System.out.println(theOne);
        try {
            MidiDevice d = MidiSystem.getMidiDevice(theOne);
            if (!d.isOpen()) {
                d.open();
                Receiver r = d.getReceiver();
                long currentT = 0;
                for (MyMidiNote n : mns) {
                    // what does -1 mean in send? send now?
                    // why are we sending now? why are we only creating
                    // 1 message? where is sending whole comp? Have
                    // we tested that yet?
                    long t = n.timestamp;
                    int ms = (int) ((double) (t - currentT) / 1000.0);
                    if (ms <= 0) {
                        r.send(n.msg, -1);
                    } else {
                        Thread.sleep(ms);
                        r.send(n.msg, -1);
                        currentT = t;
                    }
                }
                d.close();
            }
        } catch (MidiUnavailableException e) {
            System.out.println("midi unavailable xception");
        }
    }

    public static ShortMessage[] createMsgs(){
        ShortMessage[] arr = new ShortMessage[5];
        try {
            for (int i = 0; i < 5; i++) {
                arr[i] = new ShortMessage();
                arr[i].setMessage(0x90, 1, 60 + 3*i, 64);
            }
        } catch (InvalidMidiDataException e) {
            System.out.println("invalid midi data exception");
        }
        return arr;
    }
}
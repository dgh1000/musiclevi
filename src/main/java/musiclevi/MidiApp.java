package musiclevi;

import javax.sound.midi.*;
import java.util.Collections;
import java.util.*;

public class MidiApp {


    public static void runTest() throws Exception {
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        MidiDevice.Info theOne = null;
        for (MidiDevice.Info i: infos) {
            if(i.getName().equals("Microsoft GS Wavetable Synth")) {
                theOne = i;
            }
        }
        System.out.println(theOne);
        try {
            MidiDevice d = MidiSystem.getMidiDevice(theOne);
            if (!d.isOpen()) {
                d.open();
                Receiver r = d.getReceiver();
                ShortMessage[] msgs = createMsgs();
                for (ShortMessage m: msgs) {
                    // what does -1 mean in send? send now?
                    // why are we sending now? why are we only creating
                    // 1 message? where is sending whole comp? Have
                    // we tested that yet?
                    r.send(m, -1);
                }
                Thread.sleep(1000);
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
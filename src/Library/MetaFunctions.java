package Library;

import MidiData.MidiFile;

import java.util.Arrays;

import static com.company.BinaryTransform.BinaryNoneDecoder;

public class MetaFunctions {

    public  static void metaSet_01(Integer[] arr, int index, MidiFile midiFile) {
        int textSize = arr[index+1];
        StringBuilder text = new StringBuilder();
        for (int i = index+2; i <textSize+index+2 ; i++) {
            text.append((char) (int) arr[i]);
        }
        midiFile.text = text.toString();
    }
    public  static void metaSet_02(Integer[] arr, int index, MidiFile midiFile) {
        int textSize = arr[index+1];
        StringBuilder text = new StringBuilder();
        for (int i = index+2; i <textSize+index+2 ; i++) {
            text.append((char) (int) arr[i]);
        }
        midiFile.text = text.toString();
    }

    public  static void metaSet_03(Integer[] arr, int index, MidiFile midiFile) {
        int textSize = arr[index+1];
        StringBuilder text = new StringBuilder();
        for (int i = index+2; i <textSize+index+2 ; i++) {
            text.append((char) (int) arr[i]);
        }
        midiFile.trackName = text.toString();
    }

    public  static void metaSet_51(Integer[] arr, int index, MidiFile midiFile) {
        int length = arr[index+1];
        //midiFile.tempo=BinaryNoneDecoder(arr[index+2],arr[index+3],arr[index+4]);
        Integer[] a = Arrays.copyOfRange(arr,index+2,index+2+length );

        midiFile.tempo=BinaryNoneDecoder(Arrays.copyOfRange(arr,index+2,index+2+length ));
        midiFile.bpm =60_000_000/ midiFile.tempo;
    }
    public  static void metaSet_58(Integer[] arr, int index, MidiFile midiFile) {
        midiFile.chislitel = arr[index+2];
        midiFile.znamenatel = arr[index+3];
        midiFile.midiCLock = arr[index+4];
        midiFile.amount32 = arr[index+5];
    }

}

package com.company;

import MidiData.MidiFile;
import org.junit.platform.commons.logging.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Arrays;


import static com.company.BinaryTransform.BinaryDecoder;
import static com.company.BinaryTransform.BinaryNoneDecoder;
import static com.company.MidiReader.midiReader;



public class Main {

    public static void main(String[] args) throws IOException {
      //  Logger LOGGER = Logger.getLogger("com.company.Main");
        String path = "C:\\Users\\Kidek\\Desktop\\";
        String fileName = "MIDI test.mid";
        Path file = Paths.get(path+fileName);

        byte[] FullByteArray = Files.readAllBytes(file);
        // LOGGER.info("Все байты прочитаны и записаны в массив FullByteArray");
        System.out.println();
        //System.out.println(Arrays.toString(FullByteArray));
        int FMl = FullByteArray.length;
        Integer[] FullMassiv = new Integer[FMl];
        for (int i = 0; i < FMl; i++) {
            FullMassiv[i] = FullByteArray[i] & 0xFF;
        } //переводим массив в безразмерный интеджер

        System.out.println(Arrays.toString(FullMassiv));


        MidiFile midifile = midiReader(FullMassiv);
        midifile.fileName = fileName;
        System.out.println();
        System.out.println();

        System.out.println(midifile);
        //System.out.println(BinaryDecoder(255, 255));  ;
       //  System.out.println(BinaryNoneDecoder(100,100,80));
      //  System.out.println(midifile);


    }
}
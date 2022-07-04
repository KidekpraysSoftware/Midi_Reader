package com.company;

import MidiData.MidiEvent;
import MidiData.MidiFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Library.MetaFunctions.*;
import static com.company.BinaryTransform.BinaryDecoder;
import static com.company.BinaryTransform.BinaryNoneDecoder;

public  class MidiReader {
  static  Logger LOGGER = Logger.getLogger("com.company.MidiReader");
    public static void mtrkCheck(int ... arr) throws IOException {
        if (arr[0] !=77 | arr[1] !=84 | arr[2] !=114 | arr[3] !=107) {
            System.out.println("ERROR! Вместо MTrk встречено: " + Arrays.toString(arr));
            throw new IOException("Первый блок MTrk не найден");

        }
    };

  public static MidiFile midiReader(Integer[] arr) throws IOException {
      MidiFile midiFile = new MidiFile();
      Iterator<Integer> iter =  Arrays.asList(arr).iterator();

      //Блок MThd
      skip(iter, 9);
      midiFile.fileFormat = iter.next();       System.out.println("Тип файла: " + midiFile.fileFormat);
      if (midiFile.fileFormat != 1) throw new IOException("неверный формат файла");
      iter.next();
      midiFile.MtrkAmount = iter.next(); System.out.println("Количество блоков MTrk: " + midiFile.MtrkAmount);
      midiFile.tick = BinaryNoneDecoder(iter.next(),iter.next());  System.out.println("Количество тиков: " + midiFile.tick);
     // System.out.println();
      //Конец блока MThd


      // Первый блок MTrk
      System.out.println("#### Начало 1 блока MTrk ####");
      mtrkCheck(iter.next(),iter.next(),iter.next(),iter.next()); // скип и проверка первого слова MTrk
      midiFile.firstMtrkLength = BinaryNoneDecoder(iter.next(),iter.next(),iter.next(),iter.next()); //длина первого MTrk
      System.out.println("длина первого MTrk: " + midiFile.firstMtrkLength);
      skip(iter,midiFile.firstMtrkLength);
      //mtrkCheck(iter.next(),iter.next(),iter.next(),iter.next()); // скип и проверка второго слова MTrk
      int noteStartIndex = metaSearcher(arr,midiFile);
      System.out.println("Ноты идут с индекса = " + noteStartIndex);
      System.out.println("первая ячейка = " + arr[noteStartIndex+1]);
      return midiFile;
   }
    /**
     *
     * @param iter
     * @param amount
     * Пропускает заданное количество токенов у итератора
     */
   static void skip(Iterator<Integer> iter, int amount) {
        for (int i = 1; i <= amount; i++) {
            iter.next();
        }
    }
    public static String trackNameReader(Iterator<Integer> iter, int trackNameLength){
        StringBuilder stroka = new StringBuilder();
        for (int i = 0; i < trackNameLength; i++) {

            stroka.append((char) (int) iter.next());

        }

       return stroka.toString();
    }

    public static int metaSearcher(Integer[] arr, MidiFile midiFile){
      int lastIndex = 0;


        for (int i = 0; i < arr.length-3; i++) {
            if (arr[i] == 0x00 && arr[i+1] == 0xFF) {

                switch (arr[i+2]) {
                    //используются только

                    case 0x01: metaSet_01(arr,i+2,midiFile); lastIndex = Math.max(i, lastIndex); break; //сканирует любой текст, но только один
                    case 0x02: metaSet_02(arr,i+2,midiFile); lastIndex = Math.max(i, lastIndex); break; //Информация об авторском праве.
                    case 0x03: metaSet_03(arr,i+2,midiFile); lastIndex = Math.max(i, lastIndex); break; //Название трека.
                   // case 0x04:  break;
                    //case 0x05:  break;
                   // case 0x06:  break;
                   // case 0x07:  break;
                   // case 0x08:  break;
                   // case 0x09:  break;
                    //case 0x20:  break;
                    case 0x2F:  break; // конец блока
                    case 0x51:  metaSet_51(arr,i+2,midiFile); lastIndex = Math.max(i, lastIndex); break; // темп в микросекундах на четверть
                    //case 0x54:  break;
                    case 0x58:  metaSet_58(arr,i+2,midiFile); lastIndex = Math.max(i, lastIndex); break; // данные по темпу
                   // case 0x59:  break;
                   // case 0x7F:  break;
                    default: LOGGER.log(Level.INFO,"Обнаружено необработанное мета-событие " + Integer.toHexString(arr[i+2])); break;
                }
            }
        }
        //System.out.println(lastIndex);
        //System.out.println(arr[lastIndex+3]);
        lastIndex = lastIndex+arr[lastIndex+3]+4;
        //System.out.println(lastIndex);
       // System.out.println(arr[lastIndex]);
return lastIndex;

    };

    public static ArrayList<MidiEvent> eventsReader(Iterator<Integer> iter, int length) {
        ArrayList<MidiEvent> midiEvents = new ArrayList<>();
        int x;
        int[] deltaArr = new int[4];

        int j = 0;

        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = iter.next();
        }
        System.out.println("Dlina = " + length);
        for (int i = 0; i < length; i++) {

            if (arr[i]!=144 & arr[i]!=128) {
                deltaArr[j++] = arr[i];
            } else {

                // System.out.println("deltaARR" +Arrays.toString(Arrays.copyOf(deltaArr,j)));
                System.out.print("i = " + i + " ");
                MidiEvent midiEvent = new MidiEvent();
                System.out.println(Arrays.toString(Arrays.copyOf(deltaArr,j)) + " " + arr[i] + " " + arr[i+1] + " " + arr[i+1]);
                midiEvent.deltaTime = BinaryDecoder(Arrays.copyOf(deltaArr,j));
                j = 0;
                midiEvent.noteTurnOnChannel = arr[i];
                midiEvent.noteNumber = arr[i+1];
                midiEvent.noteVelocity = arr[i+2];
                i+=2;
                midiEvents.add(midiEvent);

                //System.out.println(midiEvent);
            }
        }



        return midiEvents;
    }
}
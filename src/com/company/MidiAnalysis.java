package com.company;

import static com.company.BinaryTransform.BinaryToS;


public class MidiAnalysis {

    public static int GetNoteCell (int arr[]) {

      int x = 0;
      for (int i = 0; i < arr.length; i++)


          if (arr[i] == 77 & (arr[i+1] == 84) & (arr[i+2] == 114) & (arr[i+3] == 107))

          {
              if (x ==0) {x=1; continue;}
              if (x ==1) return (i+11+arr[i+11]+1);

          }

return 0;




  }

    public static int NoteAmount (Integer arr[]) {

      int summ = 0;

      for (int i : arr) if (i == 144) summ+=1;

      return summ;

    }



    public static int BinaryDecoderOld(int arr[], int summ, int pos){ // здесь позиция ознаает ячейку нажатия ноты, то есть позиция младшего байта будет pos-1

        switch (summ) {
            case 2: {

                int LowByte = arr[pos-1];
                int HighByte1 = arr[pos-2];
                String LowByteS = BinaryToS(LowByte);
                String HighByte1S = BinaryToS(HighByte1);
                System.out.println("число в старшем - "+ HighByte1S);
                System.out.println("число в младшем - "+ LowByteS);
                String connect = HighByte1S.substring(1)+LowByteS.substring(1);
                System.out.println(connect);
                System.out.println();
                int t = Integer.parseInt(connect,2);
                System.out.println(t);
                return t;

            }
            case 3: {

                int LowByte = arr[pos-1];
                int HighByte1 = arr[pos-2];
                int HighByte2 = arr[pos-3];

                String LowByteS = BinaryToS(LowByte);
                String HighByte1S = BinaryToS(HighByte1);
                String HighByte2S = BinaryToS(HighByte2);


                String connect = HighByte2S.substring(1)+HighByte1S.substring(1)+LowByteS.substring(1);
                System.out.println(connect);
                System.out.println();
               int t = Integer.parseInt(connect,2);
               return t;

            }
            case 4: {
                int LowByte = arr[pos-1];
                int HighByte1 = arr[pos-2];
                int HighByte2 = arr[pos-3];
                int HighByte3 = arr[pos-4];

                String LowByteS = BinaryToS(LowByte);
                String HighByte1S = BinaryToS(HighByte1);
                String HighByte2S = BinaryToS(HighByte2);
                String HighByte3S = BinaryToS(HighByte3);

                String connect = HighByte3S.substring(1)+HighByte2S.substring(1)+HighByte1S.substring(1)+LowByteS.substring(1);
                int t = Integer.parseInt(connect,2);
                return t;
            }
            default: return 0;
        }










    }

    public static int UniqueNoteAmount (int[][] arr, int NoteNumber) {
        int summ = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][2] == (NoteNumber+21)) {
                summ += 1;

            }

        }
        return summ / 2;
    } //показывает сколько раз встречается конкретная нота (без отпускания)


}

package com.company;

public class BinaryTransform {

    public static String BinaryToS(int x) {

        return String.format("%8s", Integer.toBinaryString(x)).replace(' ', '0');

    } // возвращает строку - бинарное представление байта с нулями в начале.
    public static int BinaryDecoder(int ...arr) {
        //System.out.println(Arrays.toString(arr));
        if (arr.length ==1) return arr[0];
        if (arr.length==2 && arr[arr.length-2]==0) return arr[arr.length-1];

        StringBuilder connect = new StringBuilder();
        for (int j : arr) {
            connect.append(BinaryToS(j).substring(1));
        }
       // System.out.println(connect);
        int t = Integer.parseInt(connect.toString(),2);
        //System.out.println(t);
        return t;
    }
//    public static int BinaryNoneDecoder(int ...arr) {
//        StringBuilder connect = new StringBuilder();
//        for (int x: arr) {
//            connect.append(Integer.toHexString(x));
//        }
//        //System.out.println(connect);
//       // System.out.println(Integer.toHexString(arr[0]));
//        return Integer.parseInt(connect.toString(),16);
//    }
    public static int BinaryNoneDecoder(Integer ...arr) {
        StringBuilder connect = new StringBuilder();
        for (int x: arr) {
            if (Integer.toHexString(x).length() == 1) connect.append("0");
            connect.append(Integer.toHexString(x));
        }
        // System.out.println(Integer.toHexString(arr[0]));
        return Integer.parseInt(connect.toString(),16);
    }
    public static int[] BinaryCoder(int number) {

        String connect = BinaryToS(number);
        System.out.println(connect);
        int length = connect.length();


connect.substring(1);
return null;
    } //дописать
}

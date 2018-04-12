package main;

import java.io.*;
import java.util.*;


public class uniq {
    public static void main(String[] arguments) throws IOException {
        int forFlags = 0;
        Scanner in = new Scanner(System.in);
        String[] args = in.nextLine().split(" ");
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, args);
        flags Flags = new flags();
        for (String arg : list) {
            if (arg.equals("-i")) Flags.setI();
            if (arg.equals("-c")) Flags.setC();
            if (arg.equals("-u")) Flags.setU();
            if (arg.equals("-s")) Flags.setSNum(Integer.parseInt(list.get(list.indexOf(arg) + 1)));
            if (arg.equals("file")) Flags.setFileName(list.get(list.indexOf(arg) + 1));
            if (arg.equals("-o")) Flags.setOutputFileName(list.get(list.indexOf(arg) + 2));
        }
        if (Flags.getInputFileName().equals("")) {
            Flags.setLinesFromConsole();
            forFlags++;
        }
        in.close();
        Flags.work();
        if (forFlags == 1) {
            Flags.deleteInputFile();
        }
    }
}



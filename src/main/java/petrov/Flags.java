package petrov;

import java.io.*;
import java.util.*;

import java.util.regex.Pattern;

class Flags {
    private boolean i;
    private boolean c;
    private boolean u;
    private int sNum;
    private int counterForC;
    private String outputFileName;
    private String inputFileName;
    private Scanner scanner;
    private ArrayList<String> list;
    private ArrayList<String> listForChangedLines;

    /**
     * Sets path to the input file
     *
     * @param path is path to the input file
     */
    void setFileName(String path) {
        this.inputFileName = path;
    }

    /**
     * Sets path to the output file
     *
     * @param path is path to the output file
     */
    void setOutputFileName(String path) {
        this.outputFileName = path;
    }

    /**
     * Sets the flag U value if it was found
     */
    void setU() {
        this.u = true;
    }

    /**
     * Sets the flag C value if it was found
     */
    void setC() {
        this.c = true;
    }

    /**
     * Sets the flag I value if it was found
     */
    void setI() {
        this.i = true;
    }

    /**
     * Sets the flag S value if it was found
     */
    void setSNum(int num) {
        this.sNum = num;
    }

    private void scannerIsEnable() throws Exception {
        if (null == inputFileName) {
            scanner = new Scanner(System.in);
            System.out.println("Input from the console is enabled");
            System.out.println("To finish typing write : exit the program uniq");
        } else scanner = new Scanner(new File(inputFileName));
    }

    private void scannerIsDisable(String line1, String line2) throws Exception {
        if (c) {
            list.add(Integer.toString(counterForC) + line1);
            listForChangedLines.add(line2);
            counterForC = 1;
        } else {
            list.add(line1);
            listForChangedLines.add(line2);
        }
        System.out.println("Input from the console is disabled");
    }

    private void changesWhenLinesIsNotEqual(String line1, String line2, String line3, String line4) {
        if (c) {
            list.add(Integer.toString(counterForC) + line2);
            listForChangedLines.add(line1);
            counterForC = 1;
        } else {
            list.add(line2);
            listForChangedLines.add(line1);
        }
        if (!scanner.hasNextLine()) {
            if (c) {
                list.add(Integer.toString(counterForC) + line4);
                listForChangedLines.add(line3);
                counterForC = 1;
            } else {
                list.add(line4);
                listForChangedLines.add(line3);
            }
        }
    }

    private void changesWhenLinesIsEqual(String line1, String line2) {
        if (!scanner.hasNextLine()) {
            if (c) {
                list.add(Integer.toString(counterForC) + line2);
                listForChangedLines.add(line1);
                counterForC = 1;
            } else {
                list.add(line2);
                listForChangedLines.add(line1);
            }
        }
        if (c) {
            counterForC++;
        }
    }

    private void changesWithU() {
        ListIterator<String> iterator = listForChangedLines.listIterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            int count = Collections.frequency(listForChangedLines, line);
            if (count == 1) iterator.remove();
        }
        int g = 0;
        ListIterator<String> iterator1 = list.listIterator();
        while (iterator1.hasNext()) {
            String line = iterator1.next();
            if (i) line = line.toLowerCase();
            if (sNum != 0) line = line.substring(sNum);
            if (g == listForChangedLines.size()) break;
            if (c) {
                line = Pattern.compile("\\d+").matcher(line).replaceFirst(" ");
                line = line.substring(1);
            }
            if (line.equals(listForChangedLines.get(g))) {
                iterator1.remove();
                g++;
            }
        }
    }

    private void writingLines() throws Exception {
        if (null != outputFileName) {
            File outputFile = new File(outputFileName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            for (String line : list) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        }
    }

    private void linesToTheConsole() throws Exception {
        ListIterator iterator1 = list.listIterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }
    }

    /**
     * The main method of the program which uses the set values of flags
     * to compare input lines and output the result
     *
     * @throws IOException
     */
    void work() throws Exception {
        String secondLineWithoutChanges = "";
        list = new ArrayList<>();
        listForChangedLines = new ArrayList<>();
        counterForC = 1;
        int delayBeforeTheStart = 0;
        scannerIsEnable();
        while (scanner.hasNextLine()) {
            String firstLineForChanges = secondLineWithoutChanges;
            String firstLineWithoutChanges = firstLineForChanges;
            String secondLineForChanges = scanner.nextLine();
            secondLineWithoutChanges = secondLineForChanges;
            if (secondLineWithoutChanges.equals("exit the program uniq")) {
                scannerIsDisable(firstLineForChanges, firstLineWithoutChanges);
                break;
            }
            if (delayBeforeTheStart < 1) {
                delayBeforeTheStart++;
                continue;
            }
            if (sNum != 0) {
                firstLineForChanges = firstLineForChanges.substring(sNum);
                secondLineForChanges = secondLineForChanges.substring(sNum);
            }
            if (i) {
                firstLineForChanges = firstLineForChanges.toLowerCase();
                secondLineForChanges = secondLineForChanges.toLowerCase();
            }
            if (!firstLineForChanges.equals(secondLineForChanges)) {
                changesWhenLinesIsNotEqual(firstLineForChanges, firstLineWithoutChanges,
                        secondLineForChanges, secondLineWithoutChanges);
            } else {
                changesWhenLinesIsEqual(firstLineForChanges, firstLineWithoutChanges);
            }
        }
        if (u) changesWithU();
        scanner.close();
        writingLines();
        if (null == outputFileName) linesToTheConsole();
    }

    /**
     * New elements of the class Flags starts without the values of all flags
     * To set values of flags you need to use set() methods of flags which you will find
     */
    Flags() {
        this.u = false;
        this.c = false;
        this.i = false;
        this.sNum = 0;
    }
}

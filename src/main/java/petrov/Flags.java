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
    private String firstLineForChanges;
    private String secondLineForChanges;
    private String firstLineWithoutChanges;
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

    private void scannerIsEnable() {
        scanner = new Scanner(System.in);
        System.out.println("Input from the console is enabled");
        System.out.println("To finish typing write : exit the program uniq");
    }

    private void scannerIsDisable() throws Exception {
        if (c) changesWithC(firstLineWithoutChanges, firstLineForChanges);
        else changesWithoutC(firstLineWithoutChanges, firstLineForChanges);
        System.out.println("Input from the console is disabled");
    }

    private void changesWithS() {
        firstLineForChanges = firstLineForChanges.substring(sNum);
        secondLineForChanges = secondLineForChanges.substring(sNum);
    }

    private void changesWithI() {
        firstLineForChanges = firstLineForChanges.toLowerCase();
        secondLineForChanges = secondLineForChanges.toLowerCase();
    }

    private void changesWithC(String line, String lineWithChanges) throws Exception {
        list.add(Integer.toString(counterForC) + line);
        listForChangedLines.add(lineWithChanges);
        counterForC = 1;
    }

    private void changesWithoutC(String line, String lineWithChanges) throws Exception {
        list.add(line);
        listForChangedLines.add(lineWithChanges);
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
        File outputFile = new File(outputFileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        for (String line : list) {
            writer.write(line);
            writer.newLine();
        }
        writer.close();
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
        if (inputFileName.equals("withoutInputFile")) scannerIsEnable();
        else scanner = new Scanner(new File(inputFileName));
        while (scanner.hasNextLine()) {
            firstLineForChanges = secondLineWithoutChanges;
            firstLineWithoutChanges = firstLineForChanges;
            secondLineForChanges = scanner.nextLine();
            secondLineWithoutChanges = secondLineForChanges;
            if (delayBeforeTheStart < 1) {
                delayBeforeTheStart++;
                continue;
            }
            if (secondLineWithoutChanges.equals("exit the program uniq")) {
                scannerIsDisable();
                break;
            }
            if (sNum != 0) changesWithS();
            if (i) changesWithI();
            if (!firstLineForChanges.equals(secondLineForChanges)) {
                if (c) changesWithC(firstLineWithoutChanges, firstLineForChanges);
                else changesWithoutC(firstLineWithoutChanges, firstLineForChanges);
                if (!scanner.hasNextLine()) {
                    if (c) changesWithC(secondLineWithoutChanges, secondLineForChanges);
                    else changesWithoutC(secondLineWithoutChanges, secondLineForChanges);
                }
            } else {
                if (!scanner.hasNextLine()) {
                    if (c) changesWithC(firstLineWithoutChanges, firstLineForChanges);
                    else changesWithoutC(firstLineWithoutChanges, firstLineForChanges);
                }
                if (c) {
                    counterForC++;
                }
            }
        }
        if (u) changesWithU();
        scanner.close();
        writingLines();
        if (outputFileName.equals("withoutOutputFileName")) linesToTheConsole();
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

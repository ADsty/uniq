

import java.io.*;
import java.util.Scanner;

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
    private String secondLineWithoutChanges = "";
    private BufferedWriter writer;
    private Scanner scanner;
    private File forChanges;

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
        if (c) changesWithC(firstLineWithoutChanges);
        else changesWithoutC(firstLineWithoutChanges);
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

    private void changesWithC(String line) throws Exception {
        writer.write(Integer.toString(counterForC) + line);
        writer.newLine();
        counterForC = 0;
    }

    private void changesWithoutC(String line) throws Exception {
        writer.write(line);
        writer.newLine();
    }

    private void linesToTheConsole() throws Exception {
        Scanner iterator1 = new Scanner(forChanges);
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }
        iterator1.close();
    }

    /**
     * The main method of the program which uses the set values of flags
     * to compare input lines and output the result
     *
     * @throws IOException
     */
    void work() throws Exception {
        counterForC = 0;
        int delayBeforeTheStart = 0;
        if (inputFileName.equals("")) scannerIsEnable();
        else scanner = new Scanner(new File(inputFileName));
        forChanges = new File("forChanges.txt");
        writer = new BufferedWriter(new FileWriter(forChanges));
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
                if (c) changesWithC(firstLineWithoutChanges);
                else changesWithoutC(firstLineWithoutChanges);
                if (!scanner.hasNextLine()) {
                    if (c) changesWithC(secondLineWithoutChanges);
                    else writer.write(secondLineWithoutChanges);
                }
            } else {
                if (u) {
                    if (!scanner.hasNextLine()) {
                        if (c) changesWithC(firstLineWithoutChanges);
                        else changesWithoutC(firstLineWithoutChanges);
                    }
                    if (c) {
                        counterForC++;
                    }
                } else {
                    if (c) changesWithC(firstLineWithoutChanges);
                    else changesWithoutC(firstLineForChanges);
                }
            }
        }
        writer.close();
        scanner.close();
        if (!outputFileName.equals("")) forChanges.renameTo(new File(outputFileName));
        else linesToTheConsole();
        forChanges.delete();
    }

    /**
     * New elements of the class Flags starts without the values of all flags
     * To set values of flags you need to use set() methods of flags which you will find
     */
    Flags() {
        this.inputFileName = "";
        this.outputFileName = "";
        this.u = false;
        this.c = false;
        this.i = false;
        this.sNum = 0;
    }
}

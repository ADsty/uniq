

import java.io.*;
import java.util.Scanner;

class Flags {
    private boolean i;
    private boolean c;
    private boolean u;
    private int sNum;
    private String outputFileName;
    private String inputFileName;

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

    /**
     * The main method of the program which uses the set values of flags
     * to compare input lines and output the result
     *
     * @throws IOException
     */
    void work() throws IOException {
        Scanner scanner;
        String firstLineForChanges;
        String firstLineWithoutChanges;
        String secondLineForChanges;
        String secondLineWithoutChanges = "";
        int counterForC = 0;
        int delayBeforeTheStart = 0;
        if (inputFileName.equals("")) {
            scanner = new Scanner(System.in);
            System.out.println("Input from the console is enabled");
            System.out.println("To finish typing write : exit the program uniq");
        } else {
            scanner = new Scanner(new File(inputFileName));
        }
        File forChanges = new File("forChanges.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(forChanges));
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
                if (c) {
                    writer.write(Integer.toString(counterForC) + firstLineWithoutChanges);
                    writer.newLine();
                } else {
                    writer.write(firstLineWithoutChanges);
                    writer.newLine();
                }
                System.out.println("Input from the console is disabled");
                break;
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
                if (c) {
                    writer.write(Integer.toString(counterForC) + firstLineWithoutChanges);
                    writer.newLine();
                    counterForC = 0;
                } else {
                    writer.write(firstLineWithoutChanges);
                    writer.newLine();
                }
                if (!scanner.hasNextLine()) {
                    if (c) {
                        writer.write(Integer.toString(counterForC) + secondLineWithoutChanges);
                        counterForC = 0;
                    } else {
                        writer.write(secondLineWithoutChanges);
                    }
                }
            } else {
                if (u) {
                    if (!scanner.hasNextLine()) {
                        if (c) {
                            writer.write(Integer.toString(counterForC) + firstLineWithoutChanges);
                            writer.newLine();
                            counterForC = 0;
                        } else {
                            writer.write(firstLineWithoutChanges);
                            writer.newLine();
                        }
                    }
                    if (c) {
                        counterForC++;
                    }
                } else {
                    if (c) {
                        writer.write(Integer.toString(counterForC) + firstLineWithoutChanges);
                        writer.newLine();
                        counterForC = 0;
                    } else {
                        writer.write(firstLineWithoutChanges);
                        writer.newLine();
                    }
                }
            }
        }
        writer.close();
        scanner.close();
        if (!outputFileName.equals("")) {
            forChanges.renameTo(new File(outputFileName));
        } else {
            Scanner iterator1 = new Scanner(forChanges);
            while (iterator1.hasNext()) {
                System.out.println(iterator1.next());
            }
            iterator1.close();
        }
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

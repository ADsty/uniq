

import java.io.*;
import java.util.Scanner;

public class Flags {
    private boolean i;
    private boolean c;
    private boolean u;
    private int sNum;
    private String outputFileName;
    private String inputFileName;

    /**
     * Sets path to the input file
     *
     * @param x is path to the input file
     */
    void setFileName(String x) {
        this.inputFileName = x;
    }

    /**
     * Sets path to the output file
     *
     * @param x is path to the output file
     */
    void setOutputFileName(String x) {
        this.outputFileName = x;
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
    void setSNum(int x) {
        this.sNum = x;
    }

    /**
     * The main method of the program which uses the set values of flags
     * to compare input lines and output the result
     *
     * @throws IOException
     */
    void work() throws IOException {
        Scanner scanner;
        String j;
        String h;
        String p = "";
        String o;
        if (inputFileName.equals("")) {
            scanner = new Scanner(System.in);
            System.out.println("Input from the console is enabled");
            System.out.println("To finish typing write : exit the program uniq");
        } else {
            scanner = new Scanner(new File(inputFileName));
        }
        int s = 0;
        int g = 0;
        File forChanges = new File("forChanges.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(forChanges));
        while (scanner.hasNextLine()) {
            j = p;
            o = j;
            h = scanner.nextLine();
            p = h;
            if (g < 1) {
                g++;
                continue;
            }
            if (p.equals("exit the program uniq")) {
                if (c) {
                    writer.write(Integer.toString(s) + o);
                    writer.newLine();
                } else {
                    writer.write(o);
                    writer.newLine();
                }
                System.out.println("Input from the console is disabled");
                break;
            }
            if (sNum != 0) {
                j = j.substring(sNum);
                h = h.substring(sNum);
            }
            if (i) {
                j = j.toLowerCase();
                h = h.toLowerCase();
            }
            if (!j.equals(h)) {
                if (c) {
                    writer.write(Integer.toString(s) + o);
                    writer.newLine();
                    s = 0;
                } else {
                    writer.write(o);
                    writer.newLine();
                }
                if (!scanner.hasNextLine()) {
                    if (c) {
                        writer.write(Integer.toString(s) + p);
                        s = 0;
                    } else {
                        writer.write(p);
                    }
                }
            } else {
                if (u) {
                    if (!scanner.hasNextLine()) {
                        if (c) {
                            writer.write(Integer.toString(s) + o);
                            writer.newLine();
                            s = 0;
                        } else {
                            writer.write(o);
                            writer.newLine();
                        }
                    }
                    if (c) {
                        s++;
                    }
                } else {
                    if (c) {
                        writer.write(Integer.toString(s) + o);
                        writer.newLine();
                        s = 0;
                    } else {
                        writer.write(o);
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
    public Flags() {
        this.inputFileName = "";
        this.outputFileName = "";
        this.u = false;
        this.c = false;
        this.i = false;
        this.sNum = 0;
    }
}

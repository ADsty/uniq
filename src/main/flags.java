package main;

import java.io.*;

public class flags {
    private boolean u;
    private boolean c;
    private boolean i;
    private int sNum;
    private File inputFile;
    private String inputFileName;
    private String outputFileName;

    public void setFileName(String x) {
        this.inputFileName = x;
        this.inputFile = new File(x);
    }

    public void setLinesFromConsole() throws IOException {
        File file = new File("linesFromConsole.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        String data = reader.readLine();
        while (!data.equals("exit")) {
            writer.write(data);
            data = reader.readLine();
        }
        writer.close();
        reader.close();
        inputFile = file;
    }

    public String getInputFileName() {
        return this.inputFileName;
    }

    public void deleteInputFile() {
        inputFile.delete();
    }

    public void setOutputFileName(String x) {
        this.outputFileName = x;
    }

    public void setU() {
        this.u = true;
    }

    public void setC() {
        this.c = true;
    }

    public void setI() {
        this.i = true;
    }

    public void setSNum(int x) {
        this.sNum = x;
    }

    public void work() throws IOException {
        String j;
        String h;
        String p;
        int s = 0;
        FileLineIterator iterator = new FileLineIterator(inputFile);
        File forChanges = new File("forChanges.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(forChanges));
        j = iterator.next();
        while (iterator.hasNext()) {
            j = iterator.getString();
            p = j;
            h = iterator.next();
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
                    writer.write(Integer.toString(s) + p);
                    writer.newLine();
                    s = 0;
                } else {
                    writer.write(p);
                    writer.newLine();
                }
                if (!iterator.hasNext()) {
                    if (c) {
                        writer.write(Integer.toString(s) + iterator.getString());
                    } else {
                        writer.write(iterator.getString());
                    }
                }
            } else {
                if (u) {
                    if (!iterator.hasNext()) {
                        if (c) {
                            writer.write(Integer.toString(s) + p);
                            writer.newLine();
                        } else {
                            writer.write(p);
                            writer.newLine();
                        }
                    }
                    if (c) {
                        s++;
                    }
                } else {
                    if (c) {
                        writer.write(Integer.toString(s) + p);
                        writer.newLine();
                        s = 0;
                    } else {
                        writer.write(p);
                        writer.newLine();
                    }
                }
            }
        }
        writer.close();
        if (!outputFileName.equals("")) {
            forChanges.renameTo(new File(outputFileName));
        } else {
            FileLineIterator iterator1 = new FileLineIterator(forChanges);
            while (iterator1.hasNext()) {
                System.out.println(iterator1.next());
            }
        }
        forChanges.delete();
    }

    public flags() {
        this.inputFileName = "";
        this.outputFileName = "";
        this.u = false;
        this.c = false;
        this.i = false;
        this.sNum = 0;
    }
}

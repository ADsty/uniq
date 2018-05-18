package petrov;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;

/**
 * Command-line launcher of the program
 * Combining sequences of identical consecutive lines in a file into one
 * Flags which you need to set for identify how lines will be compared:
 * -i     means that while comparing lines, case should be ignored
 * -s N   means that while comparing lines, the first N characters of each line should be ignored
 * -u     means that only unique lines should be output as a result
 * -c     means that before each output line, writes the number of lines that have been replaced by this one
 * -o     set the name of the output file. If there is no parameter, prints the results in the console
 * (file name) set the name of the input file. If there is no parameter, it reads the text from the console.
 */
public class Uniq {
    @Option(name = "-i", usage = "When comparing strings, you should ignore case")
    private boolean i;

    @Option(name = "-c", usage = "Before each line, output the number of lines that have been replaced by the given")
    private boolean c;

    @Option(name = "-u", usage = "Should only output unique lines as a result")
    private boolean u;

    @Option(name = "-s", usage = "When comparing line, the first N characters of each line should be ignored.")
    private int sNum;

    @Option(name = "-o", metaVar = "output file", usage = "Sets output file name")
    private String outputFileName;

    @Argument(metaVar = "input file", usage = "Sets input file name")
    private String inputFileName;

    public static void main(String[] args) throws Exception {
        new Uniq().launch(args);
    }

    private void launch(String[] args) throws Exception {
        Flags flags = new Flags();
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("valid values of flags is [-i] [-u] [-c] [-s num] [-o ofile] [file]");
            parser.printUsage(System.err);
            return;
        }
        if (i) flags.setI();
        if (c) flags.setC();
        if (u) flags.setU();
        if (sNum > 0) flags.setSNum(sNum);
        else if (sNum != 0) throw new IllegalArgumentException("invalid value for sNum");
        if (null != outputFileName) flags.setOutputFileName(outputFileName);
        if (null != inputFileName) {
            if (new File(inputFileName).exists()) flags.setFileName(inputFileName);
            else throw new IllegalArgumentException("invalid value for input file");
        }
        try {
            flags.work();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}


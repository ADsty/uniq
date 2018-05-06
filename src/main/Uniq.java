

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class Uniq {
    @Option(name = "-i", usage = "When comparing strings, you should ignore case")
    private boolean i;

    @Option(name = "-c", usage = "Before each line, output the number of lines that have been replaced by the given")
    private boolean c;

    @Option(name = "-u", usage = "Should only output unique lines as a result")
    private boolean u;

    @Option(name = "-s", usage = "When comparing line, the first N characters of each line should be ignored.")
    private int sNum;

    @Option(name = "-o", usage = "Sets output file name")
    private String outputFileName;

    @Argument(usage = "Sets input file name")
    private String inputFileName;

    public static void main(String[] args) {
        new Uniq().launch(args);
    }

    private void launch(String[] args) {
        Flags flags = new Flags();
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
        }
        if (i) flags.setI();
        if (c) flags.setC();
        if (u) flags.setU();
        if (sNum > 0) flags.setSNum(sNum);
        if (!outputFileName.equals("")) flags.setOutputFileName(outputFileName);
        if (!inputFileName.equals("")) flags.setFileName(inputFileName);
        try {
            flags.work();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}


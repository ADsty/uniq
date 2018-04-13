package test;

import main.FileLineIterator;
import main.flags;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class flagsTest {
    private flags Flags;
    private File file;
    private File file1;
    private File file2;
    private BufferedWriter writer;
    private BufferedWriter writer1;

    @Before
    public void creatingNewElements() {
        Flags = new flags();
        file = new File("input.txt");
        file1 = new File("output.txt");
        file2 = new File("forComparison.txt");
        Flags.setFileName("input.txt");
        Flags.setOutputFileName("output.txt");
    }

    @Test
    public void work() throws Exception {
        writer = new BufferedWriter(new FileWriter(file));
        writer.write("new line");
        writer.newLine();
        writer.write("NEW LINE");
        writer.newLine();
        writer.write("lnw line");
        writer.newLine();
        writer.write("uniq line");
        writer.close();
        Flags.setU();
        Flags.setC();
        Flags.setI();
        Flags.setSNum(2);
        Flags.work();
        writer1 = new BufferedWriter(new FileWriter(file2));
        writer1.write("2lnw line");
        writer1.newLine();
        writer1.write("0uniq line");
        writer1.newLine();
        writer1.close();
        Boolean equals = true;
        FileLineIterator iterator = new FileLineIterator(file1);
        FileLineIterator iterator1 = new FileLineIterator(file2);
        while (iterator.hasNext() && iterator1.hasNext()) {
            if (!iterator.next().equals(iterator1.next())) equals = false;
        }
        assertEquals(true, equals);
        file.delete();
        file1.delete();
        file2.delete();
    }

}
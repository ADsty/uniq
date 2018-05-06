

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;

public class FlagsTest {
    private Flags Flags;
    private File file1;

    @Before
    public void creatingNewElements() {
        Flags = new Flags();
        file1 = new File("output.txt");
        Flags.setOutputFileName("output.txt");
        Flags.setFileName("resources/InputFilesForTests/FileOne");
    }

    @After
    public void deletingUsedFiles() {
        file1.delete();
    }

    @Test
    public void work() throws Exception {
        Flags.setU();
        Flags.setC();
        Flags.setI();
        Flags.setSNum(2);
        Flags.work();
        Boolean equals = true;
        Scanner iterator = new Scanner(file1);
        Scanner iterator1 = new Scanner(new File("resources/OutputFilesForTests/OutputFileOne"));
        while (iterator.hasNextLine() && iterator1.hasNextLine()) {
            if (!iterator.nextLine().equals(iterator1.nextLine())) equals = false;
        }
        file1.delete();
        assertEquals(true, equals);
    }
}
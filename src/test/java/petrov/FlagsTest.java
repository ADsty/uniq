package petrov;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;

public class FlagsTest {
    private Flags Flags;
    private Scanner iterator1;

    @Before
    public void creatingNewElements() throws Exception {
        Flags = new Flags();
        File file1 = new File("output.txt");
        Flags.setOutputFileName(file1.getPath());
    }

    @After
    public void deletingUsedFiles() {
        new File("output.txt").delete();
    }

    private boolean comparingTwoFiles() throws Exception {
        Boolean equals = true;
        Scanner iterator = new Scanner(new File("output.txt"));
        while (iterator.hasNextLine() && iterator1.hasNextLine()) {
            if (!iterator.nextLine().equals(iterator1.nextLine())) equals = false;
        }
        iterator.close();
        iterator1.close();
        return equals;
    }

    @Test
    public void firstCombination() throws Exception {
        Flags.setFileName("src/test/resources/InputFilesForTests/FileOne");
        Flags.setU();
        Flags.setC();
        Flags.setI();
        Flags.setSNum(2);
        Flags.work();
        iterator1 = new Scanner(new File("src/test/resources/OutputFilesForTests/OutputFileOne"));
        assertEquals(true, comparingTwoFiles());
    }

    @Test
    public void secondCombination() throws Exception {
        Flags.setFileName("src/test/resources/InputFilesForTests/FileTwo");
        Flags.setU();
        Flags.setC();
        Flags.setI();
        Flags.work();
        iterator1 = new Scanner(new File("src/test/resources/OutputFilesForTests/OutputFileTwo"));
        assertEquals(true, comparingTwoFiles());
    }

    @Test
    public void thirdCombination() throws Exception {
        Flags.setFileName("src/test/resources/InputFilesForTests/FileThree");
        Flags.setU();
        Flags.setI();
        Flags.setSNum(8);
        Flags.work();
        iterator1 = new Scanner(new File("src/test/resources/OutputFilesForTests/OutputFileThree"));
        assertEquals(true, comparingTwoFiles());
    }

    @Test
    public void fourthCombination() throws Exception {
        Flags.setFileName("src/test/resources/InputFilesForTests/FileFour");
        Flags.setU();
        Flags.setC();
        Flags.setSNum(4);
        Flags.work();
        iterator1 = new Scanner(new File("src/test/resources/OutputFilesForTests/OutputFileFour"));
        assertEquals(true, comparingTwoFiles());
    }

    @Test
    public void fifthCombination() throws Exception {
        Flags.setFileName("src/test/resources/InputFilesForTests/FileFive");
        Flags.setU();
        Flags.setI();
        Flags.work();
        iterator1 = new Scanner(new File("src/test/resources/OutputFilesForTests/OutputFileFive"));
        assertEquals(true, comparingTwoFiles());
    }

    @Test
    public void sixthCombination() throws Exception {
        Flags.setFileName("src/test/resources/InputFilesForTests/FileSix");
        Flags.setU();
        Flags.setSNum(4);
        Flags.work();
        iterator1 = new Scanner(new File("src/test/resources/OutputFilesForTests/OutputFileSix"));
        assertEquals(true, comparingTwoFiles());
    }

    @Test
    public void seventhCombination() throws Exception {
        Flags.setFileName("src/test/resources/InputFilesForTests/FileSeven");
        Flags.setU();
        Flags.setC();
        Flags.work();
        iterator1 = new Scanner(new File("src/test/resources/OutputFilesForTests/OutputFileSeven"));
        assertEquals(true, comparingTwoFiles());
    }

    @Test
    public void eighthCombination() throws Exception {
        Flags.setFileName("src/test/resources/InputFilesForTests/FileEight");
        Flags.setU();
        Flags.work();
        iterator1 = new Scanner(new File("src/test/resources/OutputFilesForTests/OutputFileEight"));
        assertEquals(true, comparingTwoFiles());
    }

    @Test
    public void ninthCombination() throws Exception {
        Flags.setFileName("src/test/resources/InputFilesForTests/FileNine");
        Flags.setC();
        Flags.work();
        iterator1 = new Scanner(new File("src/test/resources/OutputFilesForTests/OutputFileNine"));
        assertEquals(true, comparingTwoFiles());
    }

    @Test
    public void tenthCombination() throws Exception {
        Flags.setFileName("src/test/resources/InputFilesForTests/FileTen");
        Flags.work();
        iterator1 = new Scanner(new File("src/test/resources/OutputFilesForTests/OutputFileTen"));
        assertEquals(true, comparingTwoFiles());
    }
}
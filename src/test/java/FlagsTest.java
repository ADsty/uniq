
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;

public class FlagsTest {
    private Flags Flags;
    private File file1;
    private Boolean equals;
    private Scanner iterator;
    private Scanner iterator1;

    @Before
    public void creatingNewElements() throws Exception {
        equals = true;
        Flags = new Flags();
        file1 = new File("output.txt");
        Flags.setOutputFileName("output.txt");
    }

    @After
    public void deletingUsedFiles() {
        iterator.close();
        iterator1.close();
        file1.delete();
    }

    @Test
    public void firstCombination() throws Exception {
        Flags.setFileName("src/test/resources/InputFilesForTests/FileOne");
        Flags.setU();
        Flags.setC();
        Flags.setI();
        Flags.setSNum(2);
        Flags.work();
        iterator = new Scanner(file1);
        iterator1 = new Scanner(new File("src/test/resources/OutputFilesForTests/OutputFileOne"));
        while (iterator.hasNextLine() && iterator1.hasNextLine()) {
            if (!iterator.nextLine().equals(iterator1.nextLine())) equals = false;
        }
        assertEquals(true, equals);
    }

    @Test
    public void secondCombination() throws Exception {
        Flags.setFileName("src/test/resources/InputFilesForTests/FileTwo");
        Flags.setU();
        Flags.setC();
        Flags.setI();
        Flags.work();
        iterator = new Scanner(file1);
        iterator1 = new Scanner(new File("src/test/resources/OutputFilesForTests/OutputFileTwo"));
        while (iterator.hasNextLine() && iterator1.hasNextLine()) {
            if (!iterator.nextLine().equals(iterator1.nextLine())) equals = false;
        }
        assertEquals(true, equals);
    }

    @Test
    public void thirdCombination() throws Exception {
        Flags.setFileName("src/test/resources/InputFilesForTests/FileThree");
        Flags.setU();
        Flags.setI();
        Flags.setSNum(8);
        Flags.work();
        iterator = new Scanner(file1);
        iterator1 = new Scanner(new File("src/test/resources/OutputFilesForTests/OutputFileThree"));
        while (iterator.hasNextLine() && iterator1.hasNextLine()) {
            if (!iterator.nextLine().equals(iterator1.nextLine())) equals = false;
        }
        assertEquals(true, equals);
    }

    @Test
    public void fourthCombination() throws Exception {
        Flags.setFileName("src/test/resources/InputFilesForTests/FileFour");
        Flags.setU();
        Flags.setC();
        Flags.setSNum(4);
        Flags.work();
        iterator = new Scanner(file1);
        iterator1 = new Scanner(new File("src/test/resources/OutputFilesForTests/OutputFileFour"));
        while (iterator.hasNextLine() && iterator1.hasNextLine()) {
            if (!iterator.nextLine().equals(iterator1.nextLine())) equals = false;
        }
        assertEquals(true, equals);
    }

    @Test
    public void fifthCombination() throws Exception {
        Flags.setFileName("src/test/resources/InputFilesForTests/FileFive");
        Flags.setU();
        Flags.setI();
        Flags.work();
        iterator = new Scanner(file1);
        iterator1 = new Scanner(new File("src/test/resources/OutputFilesForTests/OutputFileFive"));
        while (iterator.hasNextLine() && iterator1.hasNextLine()) {
            if (!iterator.nextLine().equals(iterator1.nextLine())) equals = false;
        }
        assertEquals(true, equals);
    }

    @Test
    public void sixthCombination() throws Exception {
        Flags.setFileName("src/test/resources/InputFilesForTests/FileSix");
        Flags.setU();
        Flags.setSNum(4);
        Flags.work();
        iterator = new Scanner(file1);
        iterator1 = new Scanner(new File("src/test/resources/OutputFilesForTests/OutputFileSix"));
        while (iterator.hasNextLine() && iterator1.hasNextLine()) {
            if (!iterator.nextLine().equals(iterator1.nextLine())) equals = false;
        }
        assertEquals(true, equals);
    }

    @Test
    public void seventhCombination() throws Exception {
        Flags.setFileName("src/test/resources/InputFilesForTests/FileSeven");
        Flags.setU();
        Flags.setC();
        Flags.work();
        iterator = new Scanner(file1);
        iterator1 = new Scanner(new File("src/test/resources/OutputFilesForTests/OutputFileSeven"));
        while (iterator.hasNextLine() && iterator1.hasNextLine()) {
            if (!iterator.nextLine().equals(iterator1.nextLine())) equals = false;
        }
        assertEquals(true, equals);
    }

    @Test
    public void eighthCombination() throws Exception {
        Flags.setFileName("src/test/resources/InputFilesForTests/FileEight");
        Flags.setU();
        Flags.work();
        iterator = new Scanner(file1);
        iterator1 = new Scanner(new File("src/test/resources/OutputFilesForTests/OutputFileEight"));
        while (iterator.hasNextLine() && iterator1.hasNextLine()) {
            if (!iterator.nextLine().equals(iterator1.nextLine())) equals = false;
        }
        assertEquals(true, equals);
    }

    @Test
    public void ninthCombination() throws Exception {
        Flags.setFileName("src/test/resources/InputFilesForTests/FileNine");
        Flags.setC();
        Flags.work();
        iterator = new Scanner(file1);
        iterator1 = new Scanner(new File("src/test/resources/OutputFilesForTests/OutputFileNine"));
        while (iterator.hasNextLine() && iterator1.hasNextLine()) {
            if (!iterator.nextLine().equals(iterator1.nextLine())) equals = false;
        }
        assertEquals(true, equals);
    }

    @Test
    public void tenthCombination() throws Exception {
        Flags.setFileName("src/test/resources/InputFilesForTests/FileTen");
        Flags.work();
        iterator = new Scanner(file1);
        iterator1 = new Scanner(new File("src/test/resources/OutputFilesForTests/OutputFileTen"));
        while (iterator.hasNextLine() && iterator1.hasNextLine()) {
            if (!iterator.nextLine().equals(iterator1.nextLine())) equals = false;
        }
        assertEquals(true, equals);
    }
}
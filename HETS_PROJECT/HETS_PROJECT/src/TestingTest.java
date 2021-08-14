import static org.junit.Assume.assumeNoException;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class TestingTest {
	Testing testing=new Testing();
@Before
public void test()
{
	testing.source="C:\\Users\\win-10\\Desktop\\test";
	testing.createdir("exefile");
	testing.createdir("outputfile");
	testing.createdir("reportfile");
}
@Test
public void main()
{
	testing.main(null);
}
@Test
public void testRemoveExtension()
{
	assertEquals("shorok", testing.RemoveExtension("shorok.c"));
}
@Test
public void testfileExtension()
{
	assertEquals("c",testing.fileExtension("shorok.c"));
	assertEquals("", testing.fileExtension("."));
}
@Test
public void testcreatedir()
{
	testing.source="C:\\Users\\win-10\\Desktop\\test";
	testing.direxefile="C:\\Users\\win-10\\Desktop\\test\\exefile";
	testing.diroutputfile="C:\\Users\\win-10\\Desktop\\test\\outputfile";
	testing.dirreportfile="C:\\Users\\win-10\\Desktop\\test\\reportfile";
	assertEquals("C:\\Users\\win-10\\Desktop\\test\\exefile", testing.createdir("exefile"));
	assertEquals("C:\\Users\\win-10\\Desktop\\test\\outputfile", testing.createdir("outputfile"));
	assertEquals("C:\\Users\\win-10\\Desktop\\test\\reportfile", testing.createdir("reportfile"));
}
@Test
public void testcompile()
{
	assertTrue(testing.compile("C:\\Users\\win-10\\Desktop\\test/shorok.c", "C:\\Users\\win-10\\Desktop\\test\\exefile/shorok.exe"));
	assertFalse(testing.compile("C:\\Users\\win-10\\\\Desktop\\test/input.txt","C:\\Users\\win-10\\Desktop\\test\\exefile"));
	assertFalse(testing.compile("C:\\Users\\win-10\\\\Desktop\\test/input.txt","C:\\Users\\win-10\\Desktop\\test\\exefile\none"));
}
@Test
public void testrun()
{
	assertTrue(testing.run("C:\\Users\\win-10\\Desktop\\test\\exefile/shorok.exe", "C:\\Users\\win-10\\Desktop\\test\\input.txt", "C:\\Users\\win-10\\Desktop\\test\\outputfile/shorok.text"));
	assertFalse(testing.run("C:\\Users\\win-10\\Desktop\\test\\exefile/amani.exe", "C:\\Users\\win-10\\Desktop\\test\\input.txt", "C:\\Users\\win-10\\Desktop\\test\\outputfile/shorok.text"));
	assertFalse(testing.run("C:\\Users\\win-10\\Desktop\\test\\exefile/shorok.exe", "C:\\Users\\win-10\\Desktop\\test\\input.text", "C:\\Users\\win-10\\Desktop\\test\\outputfile/shorok.text"));
	assertFalse(testing.run("C:\\Users\\win-10\\Desktop\\test\\shorok.c", "C:\\Users\\win-10\\Desktop\\test\\input.text", "C:\\Users\\win-10\\Desktop\\test\\outputfile/shorok.text"));
	assertFalse(testing.run("C:\\Users\\win-10\\Desktop\\test\\exefile", "C:\\Users\\win-10\\Desktop\\test\\input.txt", "C:\\Users\\win-10\\Desktop\\test\\outputfile/shorok.text"));
	assertFalse(testing.run("C:\\Users\\win-10\\Desktop\\test\\exefile/shorok.exe", "C:\\Users\\win-10\\Desktop\\test", "C:\\Users\\win-10\\Desktop\\test\\outputfile/shorok.text"));
	assertFalse(testing.run("C:\\Users\\win-10\\Desktop\\test\\exefile/shorok.exe", "C:\\Users\\win-10\\Desktop\\test\\input.txt", "C:\\Users\\win-10\\Desktop\\test\\outputfile"));
}
@Test
public void testvalidoutput()
{
	assertTrue(testing.validoutput("C:\\Users\\win-10\\Desktop\\test/input.txt", "C:\\Users\\win-10\\Desktop\\test/input.txt"));
	assertFalse(testing.validoutput("C:\\Users\\win-10\\Desktop\\test/input.txt", "C:\\Users\\win-10\\Desktop\\test\\outputfile/ahmadSakran.text"));
	assertFalse(testing.validoutput("C:\\Users\\win-10\\Desktop\\test/input.text", "C:\\Users\\win-10\\Desktop\\test/input.txt"));
	assertFalse(testing.validoutput("C:\\Users\\win-10\\Desktop\\test", "C:\\Users\\win-10\\Desktop\\test/input.txt"));
}
@Test
public void testcreatereport()
{
	assertTrue(testing.createreport("C:\\Users\\win-10\\Desktop\\test\\reportfile/shorok"));
	assertFalse(testing.createreport("C:\\Users\\win-10\\Desktop\\shorok\report"));
}

@Test
public void testreport()
{
	testing.report=new File("C:\\Users\\win-10\\Desktop\\test\\reportfile/homework.csv");
	try {
		testing.fileWriter=new FileWriter(testing.report);
		testing.bufferedWriter=new BufferedWriter(testing.fileWriter);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	assertTrue(testing.report("f.c", true, true));
	assertTrue(testing.report("f.c", true, false));
	assertTrue(testing.report("f.c", false, true));
	assertTrue(testing.report("f.c", false, false));
}
@Test
public void testcheckhome()
{
	testing.Cfile=new File("C:\\Users\\win-10\\Desktop\\test");
	testing.arrayofC=testing.Cfile.listFiles();
	testing.direxefile="C:\\Users\\win-10\\Desktop\\test\\exefile";
	testing.diroutputfile="C:\\Users\\win-10\\Desktop\\test\\outputfile";
	testing.dirreportfile="C:\\Users\\win-10\\Desktop\\test\\reportfile";
	testing.inputfile="C:\\Users\\win-10\\Desktop\\test/input.txt";
	testing.expectedoutput="C:\\Users\\win-10\\Desktop\\test/outputexpected.text";
	testing.report=new File("C:\\Users\\win-10\\Desktop\\test\\reportfile/homework.csv");
	HETS_2.hets2=false;
	try {
		testing.fileWriter=new FileWriter(testing.report);
		testing.bufferedWriter=new BufferedWriter(testing.fileWriter);
		testing.source="C:\\Users\\win-10\\Desktop\\test";
		testing.checkfilehomework();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

	
	}



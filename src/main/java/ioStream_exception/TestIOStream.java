package ioStream_exception;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * test Input/Output stream
 * @author simon
 *
 */
public class TestIOStream {
	public static void main(String[] args) throws Exception {
		copyFile();
		/**
		 * objectstream: read/write object
		 */
		Person person=new Person();
		person.setName("Mary");
		person.setGender("female");
		person.setAge(19);
		ObjectOutputStream oos=new ObjectOutputStream(
				new FileOutputStream("person.obj"));
		// write object: object to byte[], file stream read byte[]
		oos.writeObject(person);
		oos.close();
		
		// read object
		ObjectInputStream ois=new ObjectInputStream(
				new FileInputStream("person.obj"));
		Person p=(Person)ois.readObject(); //return Object and convert to Person
		// age(transient) is ignored
		System.out.println(p);
		ois.close();
		
		/**
		 *Writer/reader with char
		 */
		OutputStreamWriter osw=new OutputStreamWriter(
				new FileOutputStream("osw.txt"),"UTF-8");// unicode
		osw.write("Hello");
		osw.close();
		
		InputStreamReader isr=new InputStreamReader(
				new FileInputStream("osw.txt"),"UTF-8");
		// read by char
//		int d=-1;
//		while ((d=isr.read())!=-1){
//			System.out.print((char)d);
//		}
		//read by char[]
		char[] data=new char[200];
		int len=isr.read(data);
		System.out.println(new String(data,0,len));
		isr.close();// flush butter also
		// write each line of input into file
		writeText();
		
		/**
		 * buffered reader, br.readLine(): return each line, null: end
		 */
		BufferedReader br=new BufferedReader(
				new InputStreamReader(new FileInputStream(
						"src/testAPI/Person.java")));
		String line=null;
		while((line=br.readLine())!=null)
			System.out.println(line);
		br.close();
	}
	
	/**
	 * copy file with buffered stream
	 */
	public static void copyFile() throws IOException {
		// bufferedIS/OS: improve read/write speed, block data
		BufferedInputStream bis=new BufferedInputStream(
				new FileInputStream("user.dat")); //construct from file stream
		BufferedOutputStream bos=new BufferedOutputStream(
				new FileOutputStream("fos.dat",true)); // append data true
		int length=-1;
		//read 1 byte, buffered stream convert to block 
		while ((length=bis.read())!=-1)
			bos.write(length);
//		bos.flush();// dump all buffered data
		bis.close();// will close file stream also
		bos.close();// call flush()
	}
	
		/**
		 *  printWriter: buffer char writer
		 *  PrintWriter(String path), PrintWriter(file)
		 *  pw.println(), pw.print()
		 */
	public static void writeText() throws IOException {
		System.out.print("Please enter file name:");
		Scanner scanner=new Scanner(System.in);
		String str=scanner.nextLine();
		// construct pw with stream links
		// true: flush buffer for println(), not for print()
		PrintWriter pw=new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(str),"UTF-8")),true);
		while(! "exit".equals(str=scanner.nextLine())) {
			pw.println(str);
		}
		pw.close();
		scanner.close();
	}
}

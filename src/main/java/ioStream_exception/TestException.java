package ioStream_exception;

import java.io.FileInputStream;
import java.io.IOException;

public class TestException {

	public static void main(String[] args) {
		FileInputStream fis=null;
		try {
			testThrow();
			fis=new FileInputStream("fis.dat");
			fis.read();
		} catch (IOException e) {
			e.printStackTrace();// print exception information
		} catch (Exception e) {// catch other exception 
			System.out.println("Program has errors");
		}finally {// execute regardless of exception, return...
			try {
				if (fis!=null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * autoclose: compile to close the program, JDK>=1.7
	 */
	public static void testAutoClose() {
		try (
			// classes implement autoClossable
			FileInputStream fis=new FileInputStream("fis.dat");
		){
			fis.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * the method can handle Exception, or throws to main()
	 */
	public static void testThrow() {
		Person person=new Person();
			try {
				person.setAge(-1);
			} catch (InvalidAgeException e) {
				System.out.println(e.getMessage());// print exception message
				e.printStackTrace();
			}

	}
}

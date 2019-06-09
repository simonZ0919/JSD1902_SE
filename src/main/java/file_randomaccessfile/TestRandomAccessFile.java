package file_randomaccessfile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Scanner;

/**
 * RAF: read/write data
 */
public class TestRandomAccessFile {
	public static void main(String[] args) throws IOException {
		/**
		 * raf.write(int), raf.read(), return(-1) if finished
		 * raf.write(byte[], int start, length), raf.read(byte[])
		 */		
		RandomAccessFile raf1=new RandomAccessFile("show.dat", "rw");
		raf1.write(2);// write 1 byte 
		RandomAccessFile raf2=new RandomAccessFile("show.dat", "rw");
		// copy file by blocks
		int length=-1;
		byte[] data=new byte[1024*10];
		while((length=raf1.read(data))!=-1) {
			raf2.write(data,0,length);
		}
		System.out.println(raf2.read());
		// must close raf after used, release control
		raf1.close();
		raf2.close();
		// read data into file and print on console
		//logInputText();
		// write user info into file, then read from file
		//writeUserInfo();
		readUserInfo();
		// match input username with name in file, and change the nickname
		changeNickname();
		readUserInfo();
		

	}
		/**
		 * read/write strings with byte[]
		 */
	public static void logInputText() throws  IOException {
		Scanner scanner=new Scanner(System.in);
		System.out.print("Please input file name:");
		String strName=scanner.nextLine();
		RandomAccessFile rafWrite=new RandomAccessFile(strName, "rw");
		String strLine="";
		// if input string is not exit
		while(! "exit".equals(strLine=scanner.nextLine())) {
			strLine+="\n";
			// getBytes(): convert string to byte[]
			rafWrite.write(strLine.getBytes("UTF-8"));
		} 
		
		RandomAccessFile rafRead=new RandomAccessFile(strName, "r");
		// define the length according to RAF
		byte [] data=new byte[(int)rafWrite.length()];
		rafRead.read(data);
		// convert byte[] to string 
		System.out.println(new String(data,"UTF-8"));
		rafRead.close();
		rafWrite.close();
	}
		/**
		 * name/passwd/nickName: 32 byte, age: 4 byte
		 */
		public static void writeUserInfo() throws IOException {
			Scanner scanner=new Scanner(System.in);
			RandomAccessFile raf=new RandomAccessFile("user.dat", "rw");
			// move pointer to the end of file 			
			raf.seek(raf.length());
			System.out.println("Enter name:");
			byte[] data= (scanner.nextLine()).getBytes("UTF-8");
			raf.write(Arrays.copyOf(data, 32));// write 32 byte
			
			System.out.println("Enter password:");
			data=(scanner.nextLine()).getBytes("UTF-8");
			raf.write(Arrays.copyOf(data, 32));
			
			System.out.println("Enter nickname:");
			data=(scanner.nextLine()).getBytes("UTF-8");
			raf.write(Arrays.copyOf(data, 32));
			
			System.out.println("Enter age:");
			int age=Integer.parseInt(scanner.nextLine());
			raf.writeInt(age);
			/**
			 * equivalent ways of write int/double...
			 * raf.write(max>>>24); // right shift
			   raf.write(max>>>16);
			   raf.write(max>>>8);
		  	   raf.write(max);
			 */
			raf.close();
		}
		
		public static void readUserInfo() throws IOException {
			RandomAccessFile raf=new RandomAccessFile("user.dat", "r");
			for (int i = 0; i < raf.length()/100; i++) {
				// get current file pointer
				while(raf.getFilePointer()<96*(i+1)) {
					byte[] data=new byte[32];
					raf.read(data);
					// trim blanks in 32 byte
					System.out.print(new String(data,"UTF-8").trim()
							+"\t");
				}
				System.out.println(raf.readInt());
			}
		}
		
		public static void changeNickname() throws IOException {
			Scanner scanner=new Scanner(System.in);
			RandomAccessFile raf=new RandomAccessFile("user.dat", "rw");
			System.out.println("change name of:");
			String nameIn=scanner.nextLine(), nameRead="";
			System.out.println("change nickname to:");
			byte[] nickName=scanner.nextLine().getBytes("UTF-8");
			nickName=Arrays.copyOf(nickName, 32);
			boolean matched=false;
			byte[] data=new byte[32];
			
			for (int i=0; i<raf.length()/100;i++) {
				// move pointer to read username
				raf.seek(100*i);
				raf.read(data);
				nameRead=new String(data,"UTF-8").trim();
				if (!nameIn.equals(nameRead)) 
					continue;
				
				// move pointer to write nickname
				matched=true;
				raf.seek(100*i+64);
				raf.write(nickName);
			}
			if(!matched)
				System.out.println("No username matched!");
			raf.close();
		}
}

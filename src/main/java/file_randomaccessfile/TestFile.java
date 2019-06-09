package file_randomaccessfile;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public class TestFile {
	public static void main(String[] args) throws IOException {
		/**
		 * file properties: getname(), length, canRead, isHidden....
		 */
		File file1=new File("./test.txt");
		System.out.println("name:"+file1.getName()+
				"\tlength:"+file1.length());
		System.out.println("readable:"+file1.canRead()+"\twritable:"+
				file1.canWrite()+"\thidden:"+file1.isHidden());
		/**
		 * createNewFile(), delete
		 */
		File file2=new File("./NewFile.txt");
		file2.createNewFile();
		file2.delete();
		
		// recursively delete folder
		File dir1=new File("./New Folder");
		deleteAll(dir1);
		
		// file filter, lambda expression 
		File dir2=new File(".");
		File[] subdir2=dir2.listFiles((file)->
			(file).getName().startsWith("."));
		for (File file : subdir2) {
			System.out.println(file.getName());
		}
 	}
		/*
		 * mkdir(): current folder; mkdirs(): create parent folder also 
		 * delete(): empty folder only
		 * isFile(), isDirectory(), exist()
		 * listFiles(); list all subfilee in File[]
		 */
	public static void deleteAll(File f) {
		if (f.isDirectory()) {
			File[] subs=f.listFiles();
			for (int i = 0; i < subs.length; i++) {
				deleteAll(subs[i]);
				subs[i].delete();// iteration
			}
		}
		f.delete();
		return;
	}
}

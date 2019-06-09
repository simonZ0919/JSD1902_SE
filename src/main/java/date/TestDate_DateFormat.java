package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * data: display time in long(ms),from 1970.1.1
 * getTime(), setTime(long)
 * @author simon
 *
 */
public class TestDate_DateFormat {
	public static final int scale =1000*3600*24;// day to ms
	public static void main(String[] args) throws ParseException {
		Date date=new Date();
		
		System.out.println(date+"\t"+date.getTime());// get time in ms
		date.setTime(0);// set date 
		
		
		//calcuate how many days since birthday
		//the date:10000 days from birthday		
		birthDate();
		
	}
	
		/**
		 * SimpleDateFormat: date-string convertor
		 * format(date): date to string
		 * parse(string): string to data
		 */
	public static void birthDate() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Please input your birthdate(yyyy-MM-dd)");
		String birthStr=scanner.nextLine();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			// string to date
			Date date=sdf.parse(birthStr);
			long time=date.getTime();
			System.out.println((System.currentTimeMillis()-time)/scale);
			
			time+=(long)10000*scale;// time: long
			
			// set time to Date,then convert to string
			System.out.println(sdf.format(new Date(time)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		finally {
			scanner.close();
		}
	}
}

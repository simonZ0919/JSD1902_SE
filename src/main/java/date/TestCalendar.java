package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * Calendar: abstract class, getInstance(): create object
 * getTime(): return Date type
 * setTime(date): date to calendar
 * @author zxh
 *
 */
public class TestCalendar {
	public static void main(String[] args) {
		Calendar calendar=Calendar.getInstance();
		System.out.println(calendar.getTime());
		
		/**
		 * int get(final int): return info of time
		 * set(field, value): set time
		 * add(field, value): add time field
		 */
		int year=calendar.get(calendar.YEAR);
		// get month, start from 0
		int month=calendar.get(calendar.MONTH)+1;
		// get DATE_OF_MONTH
		int day=calendar.get(calendar.DATE);
		int hour=calendar.get(calendar.HOUR_OF_DAY);
		int minute=calendar.get(calendar.MINUTE);
		int second=calendar.get(calendar.SECOND);
		System.out.println(year+"-"+month+"-"+day);
		System.out.println(hour+"-"+minute+"-"+second);
		
		int dow=calendar.get(calendar.DAY_OF_WEEK);
		String[] data= {"Sun","Mon","Tue","Wed","Thurs","Fri","Sat"};
		System.out.println(data[dow-1]);
		
		// maximum value
		System.out.println(calendar.getActualMaximum(calendar.DAY_OF_MONTH));
		
		calendar.set(calendar.DATE, 25);
		// calendar.getTime();		// set() is effective after getTime()
		// check Wednesday of the week
		calendar.set(calendar.DAY_OF_WEEK, calendar.WEDNESDAY);
		System.out.println(calendar.getTime());
		
		// compute the best use time: the Wednesday, two weeks before expire time
		bestUseTime();
	}
	
	public static void bestUseTime() {
		Scanner scanner=new Scanner(System.in);
		Calendar calendar=Calendar.getInstance();
		System.out.println("Please input expiring date(yyyy-mm-dd)");
		
		System.out.println("Please input expiring period");
		int period=Integer.parseInt(scanner.nextLine());		
		
		SimpleDateFormat saf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date=saf.parse(scanner.nextLine());
			calendar.setTime(date);

			// add days, auto calculate date
			calendar.add(calendar.DAY_OF_YEAR, period-14);
			if (period>=14) {
				calendar.set(calendar.DAY_OF_WEEK, calendar.WEDNESDAY);
			}

			System.out.println("best use time:"+saf.format(calendar.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}

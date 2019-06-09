package string;

import java.sql.Date;

/**
 * comment for class
 * @author ZXH
 * @version 1.0
 * @see java.lang.String
 * @since JDK1.0
 */
public class TestString {
	/**
	 * the hello in method
	 */
	public static final String INFO="Hi";
	/**
	 * say hello to name
	 * @param name: input the name of user 
	 * @return return string
	 */
	public String sayHi(String name) {
		return "hello "+name;
	}
	public static void main(String[] args) {
		/*
		 * reuse the same object in the pool
		 */
		String s1="abc", s2="abc";
		System.out.println(s1==s2);
		/*
		 * s1 points to a new object,s2 is not affected
		 */
		s1+="def";// 
		System.out.println("s1:"+s1+"\ts2:"+s2);
		String s3=new String("abc");
		System.out.println(s2==s3);
		/*
		 * compiler will calculate string literal to "abcdef"
		 */
		String s4="abc"+"def";
		System.out.println(s1==s4);
// ----------------------------------------------
	// extract domain name from url link
		System.out.println(getHostname("www.google.com"));
	// check symmetric word
		isSymmetric(" BDFFDB  ");
		/*
		 *StringBuilder: change context of string object
		 *stringBuffer: safter on multithread
		 */
		StringBuilder builder=new StringBuilder(s1);
		builder.append("see you");
		builder.replace(0, 3, "Hellen");
		builder.delete(10,13).insert(0, "hi");
		System.out.println(builder.toString());
// -----------------------------------------------------
		// check format of email
		System.out.println(isEmail("simon@gmail.com"));
		// rename image by current time
		System.out.println(imageRename("abc.jpg"));
		// replace(regex, string)
		String sample="c32335dabc";
		System.out.println(sample.replaceAll("(b|a)","f"));
	}

	/*
	 * string.length()
	 * indexOf: return first index of search result, or -1(no)
	 * lastIdexOf: last found index 
	 * substring(begin,end): str[begin, end)
	 */
	public static String getHostname(String url) {
		int idxBeg=url.indexOf(".")+1;
		int idxEnd=url.indexOf(".",idxBeg);
		return url.substring(idxBeg, idxEnd);
	}
	/*
	 * trim(): delete blank at begin/end
	 * str.charAt(index), [0, str.length)
	 */
	public static void isSymmetric(String word) {
		word=word.trim();
		System.out.print(word+" ");
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i)!=word.charAt(word.length()-1-i)) {
				System.out.print("is not symmetric");
				return;
			}
		}
		System.out.println("is symmetric");
	}
	/*
	 * string.matches(regex): full match(no selected), \\ represents \
	 */
	public static boolean isEmail(String email) {
		return email.matches("\\w+@\\w+(\\.[a-zA-Z]+)+");
	}
	/*
	 * split(regex): split with regular expression
	 */
	public static String imageRename(String name) {
		String[] arr=name.split("\\.");
		long time=System.currentTimeMillis();
		Date date=new Date(time);// print current time 
		System.out.println(date);
		return (time+"."+arr[1]);
	}
}

package reflect;

import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * get class object
 * @author simon
 *
 */
public class TestReflect {
	public static void main(String[] args) throws Exception{
		/**
		 * Person.class: get specific class object
		 * Class.forname("package.class")
		 */	
		Class cls=Class.forName("reflect.Person");
		System.out.println(cls.getName());
		Method[] methods=cls.getDeclaredMethods(); 
		for (Method method : methods) {
			System.out.println(method.getName());
		}
			
		/**
		 * newInstance(): create object with default constructor
		 * cls.getConstructor(param).newInstance()
		 */
		Scanner scanner=new Scanner(System.in);
		Class clsNew=Class.forName(scanner.nextLine());
		Object obj=clsNew.newInstance();// create object of given name
		
		/**
		 * getMethod(string, parameters),parameters: class object, unknown numbers
		 * method.invoke(object,args)
		 */
		System.out.println("Input method name:");
		Method method=clsNew.getMethod(scanner.nextLine(), 
				String.class, String.class);
		System.out.println("Input parameters:");
		method.invoke(obj, scanner.nextLine(),scanner.nextLine());
	}
}

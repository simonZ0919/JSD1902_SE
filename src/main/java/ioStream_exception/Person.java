package ioStream_exception;

import java.io.Serializable;
/**
 * implement Serializable for object Stream
 * @author simon
 */
public class Person implements Serializable{
	/**
	 * check object file version, if different, read failed
	 */
	private static final long serialVersionUID = 2;
	private String name,gender;
	// transient: ignored during serialized
	private  transient int age;
	/**
	 * generate getter, setter
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}
	
	// throws: notify the caller to solve the exception
	// runtimeException(include subclass) can ignore declaring here
	public void setAge(int age) throws InvalidAgeException {
		if (age<0) {
			// throw exception of object
			throw new InvalidAgeException("invalid age!");
		}
		this.age = age;
	}

	public String  toString() {
		return name+","+age+","+gender;
	}

	
}

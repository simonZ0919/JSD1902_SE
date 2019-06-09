package xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.spi.XmlWriter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * parse xml with dom4j
 * @author simon
 *
 */
public class ParseXml {
	public static void main(String[] args) {
		List<Emp> list=new ArrayList<Emp>();
		try {
			/**
			 * create SAXReader and return document object
			 */
			SAXReader reader=new SAXReader();
			Document document=reader.read(
					new File("emplist.xml"));// read file/inputstream...
			/**
			 * get root element
			 * getName(), getText()
			 * element(string), elements(str)
			 * elementText: element(str).getText()
			 * attribute(str) -> getName(), getValue
			 */
			Element root=document.getRootElement();
			String name,gender;
			Integer age,id;
			List<Element> empList=root.elements();// element.elements("emp")
			// get value of name, age,gender
			for (Element e : empList) {
				name=e.element("name").getText();
				age=Integer.parseInt(e.elementText("age"));//
				gender=e.elementText("gender");
							
				//attribute value: e.attribute("id").getValue()
				id=Integer.parseInt(e.attributeValue("id"));
				
				Emp emp=new Emp(id, age, name, gender);
				list.add(emp);
				
				System.out.println(emp);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/**
		 * generate XML
		 */
		try {
			//create document
			Document doc=DocumentHelper.createDocument();
			
			/**
			 * doc.addElement(str): add root tag, only once
			 * elem.addElement(str): add and return sub element
			 * elem.addText(str): add text, addAttribute(name, value)
			 */
			Element root=doc.addElement("list");
			for (Emp emp : list) {
				Element empElem=root.addElement("emp");
				
				//add element and value
				(empElem.addElement("name")).addText(emp.getName());
				(empElem.addElement("age")).addText(emp.getAge()+"");
				(empElem.addElement("gender")).addText(emp.getGender());
				
				//add attrubute
				empElem.addAttribute("id", emp.getId()+"");
				
			}
			
			// write to file, auto-indent
			XMLWriter writer=new XMLWriter(
					new FileOutputStream("myemp.xml"),
					OutputFormat.createPrettyPrint());
			
			//write document
			writer.write(doc);
			
			writer.close();// close the writer
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
		}
	}
}

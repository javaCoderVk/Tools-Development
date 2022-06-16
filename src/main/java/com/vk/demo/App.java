package com.vk.demo;

import static com.vk.demo.json.JsonDataUtil.*;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vk.demo.IO.FileIOStream;
import com.vk.demo.objectCreation.Employee;

/**
 * Hello world!
 *
 */
public class App {

	@SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) {
		// System.out.println("Hello World!");
		/*
		 * Parrent p = new Child(); //parrent class refers to child class only
		 * ovverriden method can access. p.m1(); p.m(); Child c = new Child(); //child
		 * class refers to child class it has both parrent and cild access c.m();
		 * c.m1(); c.m2(); Parrent p2= new Parrent(); //child class refers to child
		 * class it has both parrent and cild access p2.m(); p2.m1();
		 */
		FileIOStream io = new FileIOStream();

		// Object object
		// =(Object)io.createFile("C:\\Users\\vivtiwar3\\company\\example.txt");
		// System.out.println(object);
		String json = "{\n  \"root\": {\n    \"data\": {\n      \"a\": \"1\",\n      \"b\": \"2\" {\n" ;
		String data = "{  \r\n" + "    \"employee\": {  \r\n" + "        \"name\":       \"sonoo\",   \r\n"
				+ "        \"salary\":      56000,   \r\n" + "        \"married\":    true  \r\n" + "    }  \r\n"
				+ "} ";

		String treeData = "[{\"No\":\"17\",\"Name\":\"Andrew\"},{\"No\":\"18\",\"Name\":\"Peter\"}, {\"No\":\"19\",\"Name\":\"Tom\"}]";
		System.out.println("1---testCreatingKeyValues\n");
		//JsonDataUtil.testCreatingKeyValues(treeData);
		System.out.println("2---testCreatingKeyValues\n");
		//JsonDataUtil.testCreatingKeyValues(data);
		System.out.println("3---testCreatingKeyValues   <data not printed due to some erro at line number 42\n");
		 //JsonDataUtil.testCreatingKeyValues(json);
		
		String json2 = "{ \"name\":\"David\", \"position\":\"SOFTWARE_ENGINEER\", \"skilltree\":[ \"Java\", \"Python\", \"JavaScript\" ], \"address\":{ \"street\":\"Street\", \"streetNo\":\"123\" } }";
		String path = "C:\\Users\\vivtiwar3\\company\\sts\\demo\\target\\data.json";
		
		try {

			System.out.println("4----parsToJsonObject\n" + parsToJsonObject(treeData).get(1).get("Name"));
		
		
			
			Employee emp = jsonInAnyObject(json2, Employee.class);
			ObjectNode objNode = jsonInAnyObject(json2, ObjectNode.class);
			System.out.println("5 ---prettyJsonWithStringify\n" + prettyJsonWithStringify(emp));
			System.out.println("6 ---flatJson\n" );
			
			Object obj= flatJson(json2);
			System.out.println("JsonDataUtil.prettyJsonWithStringify(obj)\n"+prettyJsonWithStringify(obj));
			
			System.out.println("8 ---prettyJsonWithStringify\n" + prettyJsonWithStringify(objNode));
			System.out.println("9 -----objNode.get(\"name\")\n" + objNode.get("name"));
			System.out.println(
					"10 -----jsonInAnyObject->get(2).get(\"Name\")\n" + jsonInAnyObject(new File(path), JsonNode.class).get(2).get("Name"));
			System.out.println("11 -----JsonDataUtil.testCreatingKeyValues" );
			//JsonDataUtil.testCreatingKeyValues(json2);
			
			System.out.println("12 -----jsonInAnyObject"+jsonInAnyObject(json2,Employee.class));

			
			
			
			
			String nestedJson="{\r\n"
					+ "    \"emp1\" : {\r\n"
					+ "	\"EmpName\" : \"Chris Jackman\",\r\n"
					+ "	\"EmpAge\" : \"34\",\r\n"
					+ "	\"EmpGender\" : \"Male\",\r\n"
					+ "	\"EmpDept\" : \"IT\",\r\n"
					+ "	\"EmpDesg\" : \"Project Manager\"\r\n"
					+ " },\r\n"
					+ "\r\n"
					+ "    \"emp2\" : {\r\n"
					+ "     \"EmpName\" : \"Mary Jane\",\r\n"
					+ "	 \"EmpAge\" : \"27\",\r\n"
					+ "	 \"EmpGender\" : \"Female\",\r\n"
					+ "	 \"EmpDept\" : \"IT\",\r\n"
					+ "	 \"EmpDesg\" : \"Team Leader\"\r\n"
					+ "  }\r\n"
					+ "}";
			
			System.out.println("13 -----parsToJsonObject\n"+parsToJsonObject(nestedJson));
//nested to flate jason
			//flate to nested jason
			
		} catch (IOException e) {
			e.printStackTrace();

		}
		String nessjson="{\r\n"
				+ "  \"emp1\" : {\r\n"
				+ "    \"EmpName\" : \"Chris Jackman\",\r\n"
				+ "    \"EmpAge\" : 34,\r\n"
				+ "    \"EmpGender\" : \"Male\",\r\n"
				+ "    \"EmpDept\" : \"IT\",\r\n"
				+ "    \"EmpDesg\" : \"Project Manager\"\r\n"
				+ "  },\r\n"
				+ "  \"emp2\" : {\r\n"
				+ "    \"EmpName\" : \"Mary Jane\",\r\n"
				+ "    \"EmpAge\" : 27,\r\n"
				+ "    \"EmpGender\" : \"Female\",\r\n"
				+ "    \"EmpDept\" : \"IT\",\r\n"
				+ "    \"EmpDesg\" : \"Team Leader\"\r\n"
				+ "  }\r\n"
				+ "}";
		
		//System.out.println(JsonDataUtil.writeJsonIntoFile(nessjson,"Employe.json"));
	
		try {
			System.out.println(flatToNestedJSONStr(FileResourcePath("CallSixthApiRequest.json")));
			//System.out.println("line number 122 using map "+JsonDataUtil.testCreatingKeyValues(data));
			System.out.println("line number 123   \n"+SearchAndManupulateJsonByKey("$..[?(@.EmpID==1234)].EmpName", FileResourcePath("Employe.json")));
			//System.out.println("line number 123   \n"+JsonDataUtil.SearchAndManupulateJsonByKey("$.employees[1].EmpName", JsonDataUtil.FileResourcePath("Employe.json")));
			System.out.println("124---"+testCreatingKeyValues(json2));
			//System.out.println("line number 123   \n"+SearchAndManupulateJsonByKey("$..[?(@.EmpCat)]", FileResourcePath("Employe.json")));
			System.out.println("line number 125   \n"+SearchAndManupulateJsonByKey("$.*[?(@.department.id!=4567)]", FileResourcePath("Employee2.json")).toString().replace("[", "").replace("]", ""));
			//String s11="[{\"EmpName\":\"Chris Jackman\",\"EmpAge\":34,\"EmpGender\":\"Male\",\"EmpDept\":[\"IT\",\"SELL\",\"PRODUCTION\"],\"EmpDesg\":\"Project Manager\",\"id\":12}]";
			//String nodjs =nestedToFlatJSONStr(FileResourcePath("employee.json"));
			//System.out.println(nodjs.replace("\"EmpAge\":34", "\"EmpAge\":38"));
		
			//JsonNode obj11=nestedToFlatJSONObject(FileResourcePath("employee.json"));
			//ObjectNode objnode=null;

	        ObjectMapper mapper = new ObjectMapper();
	        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
	        

	       // @SuppressWarnings("unchecked")
			//Map<String, Object> dataMap =  mapper.readValue(FileResourcePath("employee.json"), ArrayList.class);
	       // System.out.println(dataMap);
	        // jsonGenerator.writeStartObject();
			
			//jsonGenerator.writeObjectField("EmpAge",34);
	       String  Resp= SearchAndManupulateJsonByKey("$..id", FileResourcePath("employee.json"));
	       System.out.println(Resp.contains("12"));
	       System.out.println(modifyJson("$..[?(@.id==23)].EmpAge", "$..EmpAge", FileResourcePath("Employe.json"),FileResourcePath("employee.json")));
	       System.out.println(modifyJson("$..[0].EmpDept", "$..[1].EmpDept", FileResourcePath("Employe.json")));
	       
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
		
		

	
	

	}
}

package com.vk.demo.json;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class JsonDataUtil {
	private static ObjectMapper mapper = getDefaultObjectMapper();
	private static final String path = "C://Users/vivtiwar3/company/Worksapce/Projects/ms-life-service/src/main/resources/request/dummy/";

	public static File FileResourcePath(String fileName) {
		return new File(path + fileName);

	}

	private static ObjectMapper getDefaultObjectMapper() {
		ObjectMapper defaultObjectMapper = new ObjectMapper();
		defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return defaultObjectMapper;
	}

	private static JsonNode FileAndStringCommonLogic(Object obj) throws IOException {

		return (obj instanceof String) == true ? mapper.readTree((String) obj)
				: (obj instanceof File) == true ? mapper.readTree((File) obj) : null;
	}

	private static <T extends Object> T FileAndStringCommonLogic(Object obj, Class<T> clazz) throws IOException {

		return (obj instanceof String) == true ? mapper.readValue((String) obj, clazz)
				: (obj instanceof File) == true ? mapper.readValue((File) obj, clazz) : null;
	}
	// hardcoded algorithm for json key value

	public static Map<String, Object> testCreatingKeyValues(String json) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			addKeys("", new ObjectMapper().readTree(json), map);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (map);
	}

	private static void addKeys(String currentPath, JsonNode jsonNode, Map<String, Object> map) {
		if (jsonNode.isObject()) {
			ObjectNode objectNode = (ObjectNode) jsonNode;
			Iterator<Map.Entry<String, JsonNode>> iter = objectNode.fields();
			String pathPrefix = currentPath.isEmpty() ? "" : currentPath + ".";

			while (iter.hasNext()) {
				Map.Entry<String, JsonNode> entry = iter.next();
				addKeys(pathPrefix + entry.getKey(), entry.getValue(), map);
			}
		} else if (jsonNode.isArray()) {
			ArrayNode arrayNode = (ArrayNode) jsonNode;
			map.put(currentPath + "[]", arrayNode);

		} else if (jsonNode.isValueNode()) {
			ValueNode valueNode = (ValueNode) jsonNode;
			map.put(currentPath, valueNode.asText());
		}
	}

	@SuppressWarnings("unchecked")
	public static <T extends Object> T flatJson(Object obj)
			throws JsonMappingException, JsonProcessingException, IOException {
		String pathPrefix = "".isEmpty() ? "" : "" + ".";
		Map<String, Object> map = new HashMap<>();
		JsonNode jsonNode = FileAndStringCommonLogic(obj);
		if (jsonNode.isObject()) {
			ObjectNode objectNode = (ObjectNode) jsonNode;
			Iterator<Map.Entry<String, JsonNode>> iter = objectNode.fields();

			// String pathPrefix = "";

			while (iter.hasNext()) {
				Map.Entry<String, JsonNode> entry = iter.next();
				map.put(pathPrefix + entry.getKey(), entry.getValue());
			}
		} else if (jsonNode.isArray()) {
			ArrayNode arrayNode = (ArrayNode) jsonNode;
			map.put("" + "[]", arrayNode);

		} else if (jsonNode.isValueNode()) {
			ValueNode valueNode = (ValueNode) jsonNode;
			map.put("", valueNode.asText());
		}
		return (T) (map.isEmpty() == true ? "<proccess failed check and try again>" : map);
	}

//convert flat jason to json object
	public static JsonNode parsToJsonObject(Object obj) throws IOException {
		return FileAndStringCommonLogic(obj);

	}

//copy jason  data in any type of  object type of object must be compatible:  need to change the name of method jsonToObject
	public static <T extends Object> T jsonInAnyObject(Object obj, Class<T> clazz) throws IOException {
		return FileAndStringCommonLogic(obj, clazz);

	}

	// print any json string or any object in to pretty json format.
	public static <T> String prettyJsonWithStringify(T t) throws JsonProcessingException {
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(t);
	}

	// nested json ,file or string to flate json node object.
	public static JsonNode nestedToFlatJSONObject(Object obj) throws IOException {
		return FileAndStringCommonLogic(obj);

	}

	/*
	 * public static String FlatJSONToNested(Object obj) throws IOException { return
	 * (obj instanceof String) == true ? (mapper.readTree((String)
	 * obj).toPrettyString()) : (obj instanceof File) == true ?
	 * (mapper.readTree((File) obj)).toPrettyString() : null;
	 * 
	 * }
	 */
	// nested json ,file or string to flat json String.
	public static String nestedToFlatJSONStr(Object obj) throws IOException {
		return FileAndStringCommonLogic(obj).toString();

	}
	// flat json ,file or string to nested json PrettyString().

	public static String flatToNestedJSONStr(Object obj) throws IOException {
		return FileAndStringCommonLogic(obj).toPrettyString();

	}
	// flat json ,file or string to json node object.

	public static Object FlatToNestedJSONObj(Object obj) throws IOException {
		return FileAndStringCommonLogic(obj);

	}

	// write nested jason into json file
	public static String writeJsonIntoFile(String json, String fileName) {

		try {
			ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
			writer.writeValue(new FileOutputStream(path + fileName), mapper.readTree(json));
			// mapper.writeValue(new FileOutputStream(path+fileName), );
			return "file creted at : " + path + fileName;
		} catch (IOException e) {
			return e.getMessage();
		}
	}
	/*
	 * @SuppressWarnings("unchecked") public static <T> T
	 * SearchAndManupulateJsonByKey(String expression,String jsonStr) throws
	 * IOException {
	 * 
	 * 
	 * return (T)
	 * JsonPath.parse(mapper.writeValueAsString(mapper.readTree(JsonDataUtil.
	 * FileResourcePath(jsonStr)))).read(expression,JsonNode.class);
	 * 
	 * }
	 */

	@SuppressWarnings("unchecked")
	public static <T> T SearchAndManupulateJsonByKey(String expression, Object jsonData)  {
		
		String response = null;
		try {
		JsonNode node = FileAndStringCommonLogic(jsonData);
		DocumentContext context = JsonPath.parse(mapper.writeValueAsString(node));
		T obj = (T) JsonPath.parse(mapper.writeValueAsString(node)).read(expression, Object.class);
		

		if (obj == null) {

			LinkedHashMap<String, Object> dataList = context.read(expression);
			if (dataList.isEmpty() || dataList == null) {
				response = "no data found";
			} else {
				response = prettyJsonWithStringify(dataList);

			}

		} else {
			response = prettyJsonWithStringify(obj);

		}

		
	}catch (Exception e ) {
		response =e.getMessage().contains("No results for path:") ? "no value found for this Key :"+expression.replace("$.", "") :
			e.getMessage();
	}
		return (T) response;
	}
	public static String jsonPathExpressionProducer(String expression) {
		String  response = null;
		response = expression.equals(".") ? "$"
				: expression.matches("^[0-9].*$") ? "$..["+ expression+"]"
						: expression.matches("^[a-zA-Z].*$")
								&& !List.of("=", "+", "%", "-", "/").stream().anyMatch(expression::contains)
								|| List.of("[","]").stream().anyMatch(expression::contains) && !expression.startsWith("[(")
										? "$.." + expression
										:expression.startsWith("[(") ? expression.replace("[(", "$..[?(@.") : "$..[?(@." + expression + ")]";
		System.out.println("expression response :"+response);
		return !response.isEmpty() && !response.isBlank() && response!=null ? response.replace("$..$", "$.") : expression;
		//return response;

	}
	public static String modifyJson(String targetKey,String dependentKey,Object targetJson,Object dependetJson) throws IOException {
		String target,depedent,searchResult1,searchResult2,out,response=null;
		target=nestedToFlatJSONStr(targetJson);
		depedent=nestedToFlatJSONStr(dependetJson);
		searchResult1=SearchAndManupulateJsonByKey(targetKey, target).toString().replace("[", "").replace("]", "").trim();
	
		searchResult2=SearchAndManupulateJsonByKey(dependentKey, depedent).toString().replace("[", "").replace("]", "").trim();
		out= target.contains(searchResult1)? target.replace(searchResult1, searchResult2) : target;
		response=writeJsonIntoFile(out, targetJson instanceof File ? ((File)targetJson).getName():null) ;
		return response.isEmpty() || response.isBlank() ? prettyJsonWithStringify("{\"message\" :\"Modification failed No result found\"}") : flatToNestedJSONStr(targetJson);
	
	}
	public static String modifyJson(String targetKey,String dependentKey,Object targetJson) throws IOException {
		String target,searchResult1,searchResult2,out,response=null;
		target=nestedToFlatJSONStr(targetJson);
		searchResult1=SearchAndManupulateJsonByKey(targetKey, target).toString().replace("[", "").replace("]", "").trim();
	
		searchResult2=SearchAndManupulateJsonByKey(dependentKey, target).toString().replace("[", "").replace("]", "").trim();
		out= target.contains(searchResult1)? target.replace(searchResult1, searchResult2) : target;
		response=writeJsonIntoFile(out, targetJson instanceof File ? ((File)targetJson).getName():null) ;
		return response.isEmpty() || response.isBlank() ? prettyJsonWithStringify("{\"message\" :\"Modification failed No result found\"}") : flatToNestedJSONStr(targetJson);
	}
}

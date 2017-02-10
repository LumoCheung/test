package cn.paypalm.uss.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 *
 * <p> Description:  </p>
 * @Author rensk
 * @Date [2014年11月10日]
 * @Version V1.0
 * @修改记录
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		任思坤	2014年11月10日	新建文件.
 *
 * </pre>
 */
public class UJackson {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();
    private UJackson(){}

    /**
     *
     * @return
     */
    public static ObjectMapper getInstance() {
        return mapper;
    }

    @SuppressWarnings("unchecked")
	public static Map<String, Object> getMap(String json)
            throws JsonParseException, JsonMappingException, IOException{
        if(json == null) return null;
        return mapper.readValue(json, Map.class);
    }

    /**
     * 将json数组 转化成List
     * @param jsonArray 格式 "[{\"id\":\"\",\"name\":\"全国\"}]"
     * @return
     * @throws org.codehaus.jackson.JsonParseException
     * @throws org.codehaus.jackson.map.JsonMappingException
     * @throws java.io.IOException
     */
    @SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getList(String jsonArray)
            throws JsonParseException, JsonMappingException, IOException{
        if(jsonArray == null) return null;
        return mapper.readValue(jsonArray, List.class);
    }

    public static String getJSONStr(Object map)
            throws JsonParseException, JsonMappingException, IOException{
        StringWriter sw = new StringWriter();
        JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
        mapper.writeValue(gen, map);
        gen.close();
        return sw.toString();
    }
    
    public static String getMapJSONStr(Map map){
    	try {
			return getJSONStr(map);
		} catch (Exception e) {
			return "";
		}
    }

    @SuppressWarnings("deprecation")
	public static String getPrettyJson(Object map) throws JsonGenerationException, JsonMappingException, JsonParseException, IOException{
    	return mapper.defaultPrettyPrintingWriter().writeValueAsString(map);
    }
    public static String getXMLFromBean(Object obj)
            throws JsonParseException, JsonMappingException, IOException{
        if(obj == null) return null;
        return xmlMapper.writeValueAsString(obj);
    }
    
    @SuppressWarnings("unchecked")
	public static Map<String, Object> getMapFromXML(String xml) 
    		throws JsonParseException, JsonMappingException, IOException{
    	if(xml == null) return null;
    	return xmlMapper.readValue(xml, Map.class);
    }

    public static String getXMLStr(Object obj)
            throws JsonParseException, JsonMappingException, IOException{
        if(obj == null) return null;

        StringWriter sw = new StringWriter();

//    	return xmlMapper.writeValueAsString(obj);
        xmlMapper.writeValue(sw, obj);

        return sw.toString();
    }
    
    public static String getPaypalmXml(Map<String, Object> map){
    	StringBuilder data = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
    	data.append("<paypalm>");
    	for (Object key : map.keySet().toArray()) {
    		Object obj=map.get(key);
			if(obj instanceof List){
				data.append("<").append(String.valueOf(key)).append(">");
				List<Map<String, Object>> list=(List<Map<String, Object>>) obj;
				for(Map<String, Object> m:list){
					data.append("<detail>");
					for (Object k : m.keySet().toArray()) {
						data.append("<").append(String.valueOf(k)).append(">")
						.append(m.get(k)!=null?m.get(k).toString():"")
						.append("</").append(String.valueOf(k)).append(">");
					}
					data.append("</detail>");
				}
				data.append("</").append(String.valueOf(key)).append(">");
				continue;
			}
			else{
				data.append("<").append(String.valueOf(key)).append(">")
				.append(map.get(key)==null?"":map.get(key).toString())
				.append("</").append(String.valueOf(key)).append(">");
			}
    	}
    	data.append("</paypalm>");
    	return data.toString();
    }
    
    /**
     * 将指定等json串转换为对象
     * @param json json字符串
     * @param clazz 目标对象类型
     * @return 目标对象
     * @throws JsonProcessingException
     * @throws IOException
     */
    public static <T> T json2Obj(String json, Class<T> clazz) throws JsonProcessingException, IOException{
    	ObjectReader reader = new ObjectMapper().reader(clazz);
		T obj = reader.readValue(json);
		return obj;
	}
}
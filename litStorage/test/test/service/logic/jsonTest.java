package test.service.logic;

import static org.junit.Assert.*;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import domain.DiscussionPlace;
import service.facade.DiscussionPlaceService;
import service.logic.DiscussionPlaceServiceLogic;

public class jsonTest {


    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {      
        if (map == null)     
            return null;      
     
        Object obj = beanClass.newInstance();    
     
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());      
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();      
        for (PropertyDescriptor property : propertyDescriptors) {    
            Method setter = property.getWriteMethod();      
            if (setter != null) {    
                setter.invoke(obj, map.get(property.getName()));     
            }    
        }    
     
        return obj;    
    }      
         
    public static Map<String, Object> objectToMap(Object obj) throws Exception {      
        if(obj == null)    
            return null;        
     
        Map<String, Object> map = new HashMap<String, Object>();     
     
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());      
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();      
        for (PropertyDescriptor property : propertyDescriptors) {      
            String key = property.getName();      
            if (key.compareToIgnoreCase("class") == 0) {     
                continue;    
            }    
            Method getter = property.getReadMethod();    
            Object value = getter!=null ? getter.invoke(obj) : null;    
            map.put(key, value);    
        }      
     
        return map;    
    }      

	@Test
	public void test() {
		
//		DiscussionPlaceService service = new DiscussionPlaceServiceLogic();
//		DiscussionPlace dp=service.findDiscussionPlaceById("4");
//		
//		try {
//			Map map = objectToMap(dp);
//
//			JSONObject obj = new JSONObject();
//			obj.putAll(map);
//			//obj.put("id", dp.getId());
//			System.out.print(obj);
//		
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 

		
		//-------------encode
		 JSONObject obj = new JSONObject();

	      obj.put("name", "foo");
	      obj.put("num", new Integer(100));
	      obj.put("balance", new Double(1000.21));
	      obj.put("is_vip", new Boolean(true));
	      
	      //--1
	      System.out.print(obj);
//	      
//		  //--2
//	      StringWriter out = new StringWriter();
//	      try {
//	    	  obj.writeJSONString(out);
//	    	  String jsonText = out.toString();
//	    	  System.out.print(jsonText);	
//	      } catch (IOException e) {
//	    	  e.printStackTrace();
//	      }
	      
	      //결과:
//	      //{"balance":1000.21,"is_vip":true,"num":100,"name":"foo"}
//	      
	      
	      //-----------decode
	      
//	      JSONParser parser=new JSONParser();
//	      String s = "[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";
//	      try{
//	         Object obj = parser.parse(s);
//	         JSONArray array = (JSONArray)obj;
//	         System.out.println("The 2nd element of array");
//	         System.out.println(array.get(1));
//	         System.out.println();
//
//	         JSONObject obj2 = (JSONObject)array.get(1);
//	         System.out.println("Field \"1\"");
//	         System.out.println(obj2.get("1"));    
//
//	         s = "{}";
//	         obj = parser.parse(s);
//	         System.out.println(obj);
//
//	         s= "[5,]";
//	         obj = parser.parse(s);
//	         System.out.println(obj);
//
//	         s= "[5,,2]";
//	         obj = parser.parse(s);
//	         System.out.println(obj);
//	      }catch(ParseException pe){
//	         System.out.println("position: " + pe.getPosition());
//	         System.out.println(pe);
//	      }
	    // 결과:
		//  The 2nd element of array
		//  {"1":{"2":{"3":{"4":[5,{"6":7}]}}}}
		//
		//  Field "1"
		//  {"2":{"3":{"4":[5,{"6":7}]}}}
		//  {}
		//  [5]
		//  [5,2]

	      

//	      -------------------------------------
//	      JSON			Java
//	      -------------------------------------
//	      string		java.lang.String
//	      number		java.lang.Number
//	      true|false	java.lang.Boolean
//	      null			null
//	      array			java.util.List
//	      object		java.util.Map
//	      -------------------------------------
		
	}

}

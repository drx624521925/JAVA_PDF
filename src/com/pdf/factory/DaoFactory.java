package com.pdf.factory;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class DaoFactory {

	private static DaoFactory instance = new DaoFactory();
	private Document document = null;
	private Map<String, Object> beanMap = new HashMap<String, Object>();  
	
	private DaoFactory(){
		
		try{
			document = new SAXReader().read(Thread.currentThread()  
                      .getContextClassLoader().getResourceAsStream(  
                            "DaoInterfaceFactory.xml"));  
		}catch (DocumentException e){
			
	        e.printStackTrace();  
	    }  
	}
	
	public static DaoFactory getInstance() { 
		
        return instance;  
    }
	
	/**
	 * 
	 *���ߣ�ZhangHuanMing
	 *ʱ�䣺2017��3��8������6:59:28
	 *��������getBean
	 *���ܣ�����id��ȡclass
	 *������
	 *����ֵ��Object
	 */
    @SuppressWarnings("rawtypes")
	public synchronized Object getBean(Class c) {  
    	
        Object bean = null;  
        bean = beanMap.get(c.getName());                      //���map�������class��ֱ�ӷ��أ�������ȥ��xml�ļ����ȡ 
        
        if (bean == null) {                               //��map��û���ҵ����class,��ȥxml������
            try {  
            	 Element rootElement = document.getRootElement();
            	 String className = "";
            	 Node node = null;
            	 
            	 for ( int i = 0, size = rootElement.nodeCount(); i < size; i++ ) {
                     node = rootElement.node(i);
                     if(node instanceof  Element){
                         Element e = (Element) node;
                         
                         if(e.attribute("id").getText().equals(c.getName())){
                        	 className = e.attributeValue("class");
                        	 break;
                         }
                     }
                 }
            	
                bean = Class.forName(className).newInstance();  
                beanMap.put(c.getName(), bean);  
            } catch (InstantiationException e1) {  
                e1.printStackTrace();  
            } catch (IllegalAccessException e2) {  
                e2.printStackTrace();  
            } catch (ClassNotFoundException e3) {  
                e3.printStackTrace();  
            }  
        }  
        return bean;  
    } 
}

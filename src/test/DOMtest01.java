package test;

import java.io.File;
import java.util.Iterator;

import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.dom4j.*;
public class DOMtest01 {
	@Test
	public void t1() throws DocumentException{
		//创建xml解析器对象
		SAXReader reader = new SAXReader();
		//读取xml文档，返回document对象
		Document document = reader.read(new File("./src/test1.xml"));
		// nodeIterator得到当前节点下所有子节点，不包含孙节点以以下节点。
		Iterator<Node> iterator= document.nodeIterator();
		while(iterator.hasNext()){// 判断是否有下一个元素
			Node node= iterator.next();
			System.out.println(node.getName());// 得到节点名称
			
			// 继续找出其下面的子节点
            // 只有标签节点才有子节点
            // 判断当前节点是标签节点
			if(node instanceof Element){
				Element element = (Element)node;
				Iterator<Node> it = element.nodeIterator();
				while(it.hasNext()){
					Node node2 = it.next();
					System.out.println(node2.getName());
				}
			}
		}
		
	}
}





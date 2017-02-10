package cn.paypalm.xsl;

import java.io.FileOutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2016年6月29日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2016年6月29日	新建文件.
 * 
 * </pre>
 */
public class HowToXSLT {
	public static void main(String[] args) {
		  try {
		    TransformerFactory tFactory = TransformerFactory.newInstance();

		    Transformer transformer =
		      tFactory.newTransformer
		         (new javax.xml.transform.stream.StreamSource
		            ("/report.html.xsl"));

		    transformer.transform
		      (new javax.xml.transform.stream.StreamSource
		            ("/report.xml"),
		       new javax.xml.transform.stream.StreamResult
		            ( new FileOutputStream("/report.xml.html")));
		    }
		  catch (Exception e) {
		    e.printStackTrace( );
		    }
		  }
}

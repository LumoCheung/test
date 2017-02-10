package cn.paypalm.signature;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2016年5月27日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2016年5月27日	新建文件.
 * 
 * </pre>
 */
public class SignatureUtil {
	public static final String signDate(String info,String keystorefile,String keypasswd,String alias,String encode) { 
		try { 		
//		String keystorefile="/cert/YS_test4.pfx";
//		String keypasswd="123456";
//		String alias="";
		KeyStore ks=KeyStore.getInstance("PKCS12");
		FileInputStream fin=new FileInputStream(keystorefile);
		ks.load(fin, keypasswd.toCharArray());
		if(alias==null||alias.length()<1){
			Enumeration<String> e=ks.aliases();
			while(e.hasMoreElements()){
				alias=e.nextElement();
			}
		}
		PrivateKey myprikey=(PrivateKey) ks.getKey(alias, keypasswd.toCharArray());
		// 用私钥对信息生成数字签名 
		java.security.Signature signet = Signature.getInstance("MD5withRSA"); 
		signet.initSign(myprikey); 
		signet.update(info.getBytes(encode==null||encode.length()<1?"utf-8":encode)); 
		byte[] signed = signet.sign(); // 对信息的数字签名 
		return encodeBase64(signed);
//
//		System.out.println("signed(签名内容)原值=" + encodeBase64(signed)); 
//		System.out.println("info（原值）=" + myinfo); 
//
//
//		System.out.println("签名并生成文件成功"); 
		} catch (Exception e) { 
		//e.printStackTrace(); 
		System.out.println("签名并生成文件失败"); 
		return "";
		}
	} 
	
	public boolean verifySign(String info,String sign,String keystorefile) { 
		try {
	//		String keystorefile="/cert/businessgate.cer";
			CertificateFactory certificatefactory=CertificateFactory.getInstance("X.509");
			 FileInputStream bais=new FileInputStream(keystorefile);
			 X509Certificate Cert = (X509Certificate)certificatefactory.generateCertificate(bais);
			 PublicKey pubKey  = Cert.getPublicKey();
	
			byte[] signed = decodeBase64(sign);//这是SignatureData输出的数字签名 
			Signature signetcheck=Signature.getInstance("MD5withRSA"); 
			signetcheck.initVerify(pubKey); 
			signetcheck.update(info.getBytes()); 
			if (signetcheck.verify(signed)) { 
				System.out.println("签名正常");
				return true;
			} 
			else {
				System.out.println("签名错误");
				return false;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;
			} 
	} 
	/** 
	* Transform the specified byte into a Hex String form. 
	*/ 
	public static final String bytesToHexStr(byte[] bcd) { 
	StringBuffer s = new StringBuffer(bcd.length * 2); 

	for (int i = 0; i < bcd.length; i++) { 
	s.append(bcdLookup[(bcd[i] >>> 4) & 0x0f]); 
	s.append(bcdLookup[bcd[i] & 0x0f]); 
	} 

	return s.toString(); 
	}
	/** 
	* Transform the specified Hex String into a byte array. 
	*/ 
	public static final byte[] hexStrToBytes(String s) { 
	byte[] bytes = new byte[s.length() / 2]; 
	for (int i = 0; i < bytes.length; i++) { 
		bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16); 
	}
	return bytes; 
	} 

	private static final char[] bcdLookup = { '0', '1', '2', '3', '4', '5', 
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' }; 
		
	/*** 
     * encode by Base64 
     */  
    public static final String encodeBase64(byte[]input) throws Exception{  
        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
        Method mainMethod= clazz.getMethod("encode", byte[].class);  
        mainMethod.setAccessible(true);  
         Object retObj=mainMethod.invoke(null, new Object[]{input});  
         return (String)retObj;  
    }  
    /*** 
     * decode by Base64 
     */  
    public static final byte[] decodeBase64(String input) throws Exception{  
        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
        Method mainMethod= clazz.getMethod("decode", String.class);  
        mainMethod.setAccessible(true);  
         Object retObj=mainMethod.invoke(null, input);  
         return (byte[])retObj;  
    }  

	/** 
	* @param args 
	*/ 
	public static void main(String[] args) { 
	String keystorefile="/cert/YS_test4.pfx";
	String keypasswd="123456";
	String alias="";
	String info="orderId=10dkfadsfksdkssdkd&amount=80&orderTime=20060509";
	System.out.println(signDate(info, keystorefile, keypasswd, alias, "gbk"));
	} 
}

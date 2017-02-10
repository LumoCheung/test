package cn.paypalm.uss.util;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * HTTP请求服务
 * @author thy
 *
 */
public class HttpInformServicePost {
	
	/**
	 * HTTP请求处理
	 * 
	 */
	public static String request(String serverURL, String msg,boolean isPost) throws Exception
	{
		return request(serverURL, msg, false,null,0,4000,60000,isPost);
	}
	
	/**
	 * HTTP请求处理
	 * 
	 */
	public static String request(String serverURL, String msg, boolean isProxy, String proxyHost, int proxyPort,int connectTimeout,int readTimeout,boolean isPost) throws Exception
	{
		String url = serverURL;
		if(!isPost){
			if (serverURL.indexOf("?") != -1)
				url = serverURL + "&" + msg;
			else
				url = serverURL + "?" + msg;
		}
		
		System.out.println(url);
		//使客户端信任服务器的证书
		boolean isSSL = url.toUpperCase().indexOf("HTTPS://") > -1;  
        if (isSSL)  
            setTrustSSLContent(); 
        
		java.net.URL aURL = new java.net.URL(url);

		HttpURLConnection urlConnection = null;
		if(isProxy)
		{
			Proxy proxy=new Proxy(java.net.Proxy.Type.HTTP,new InetSocketAddress(proxyHost,proxyPort));
			urlConnection = (HttpURLConnection)aURL.openConnection(proxy);
		}
		else
			urlConnection =(java.net.HttpURLConnection) aURL.openConnection();
		
		try {
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);
			urlConnection.setUseCaches(false);
			urlConnection.setConnectTimeout(connectTimeout);
			urlConnection.setReadTimeout(readTimeout);

			urlConnection.setRequestProperty("User-Agent", "MSIE");
			
			if(!isPost)
			{
				urlConnection.setRequestMethod("GET");
				urlConnection.setRequestProperty("Accept", "text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2");
			}
			else {
				urlConnection.setRequestMethod("POST");
				urlConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			}
			urlConnection.connect();
			
			if(isPost)
			{
				//数据发送
				System.out.println("post date:"+msg);
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
				out.write(msg);
				out.flush();
				out.close();
			}
			
			int resCode = urlConnection.getResponseCode();

			if (resCode != 200) {
				System.out.println("服务器返回ResponseCode:"+resCode);
				System.out.println("请求服务页面找不到");
				return null;
			}
			int contentLen = urlConnection.getContentLength();

			java.io.DataInputStream in =
				new java.io.DataInputStream(urlConnection.getInputStream());

			if (contentLen <= 0)
				contentLen = 10240;
			//else if (contentLen > 10240)
			//	contentLen = 10240;

			byte buffer[] = new byte[contentLen];

			int off = 0;
			int len = 0;
			while (true) {
				len = in.read(buffer, off, contentLen - off);
				if (len == -1 || len == 0)
					break;
				off = off + len;
				if (off >= contentLen)
					break;
			}
			return new String(buffer, 0, off,"UTF-8");

		} catch (Exception e) {
			System.out.println("请求服务发生错误！");
			throw e;
		}finally{
			if(urlConnection!=null)
			{
				try{
				urlConnection.disconnect();
				urlConnection = null;
				}catch(Exception e){}
			}
		}
	}
	
	/**
	 * 使客户端信任服务器的证书
	 */
	private static void setTrustSSLContent() throws Exception {
 
        //  Create a trust manager that does not validate certificate chains:
 
        TrustManager[] trustAllCerts =new TrustManager[1];
 
        TrustManager tm = new miTM();
 
        trustAllCerts[0] = tm;
 
        SSLContext sc = SSLContext.getInstance("SSL");
 
        sc.init(null, trustAllCerts, null);
 
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        
        HostnameVerifier hv = new HostnameVerifier(){
	        public boolean verify(String urlHostName, SSLSession session) {
	            return true;
	        }
	    };
	    HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }
	
	public static class miTM implements TrustManager, X509TrustManager {
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	
		public boolean isServerTrusted(X509Certificate[] certs) {
			return true;
		}
	
		public boolean isClientTrusted(X509Certificate[] certs) {
		    return true;
		}
		
		public void checkServerTrusted(X509Certificate[] certs, String authType)
		        throws java.security.cert.CertificateException {
		    return;
		}
		
		public void checkClientTrusted(X509Certificate[] certs, String authType)
		        throws java.security.cert.CertificateException {
		    return;
		}
	}
	
}


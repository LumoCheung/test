package cn.paypalm.sort;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2016年6月13日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2016年6月13日	新建文件.
 * 
 * </pre>
 */
public class Test {
	
	public static void main(String[] args) throws UnsupportedEncodingException{
		String st="QSwYGNYd8mrEgJZSC%2FymJS4%2Bb7ubGYg4nA8qw6IWiMfrelHzjQVWNB4V8G%2B%2Bn9olDZPSLSsmFmtWl55bwvausVVKJAPjw93bRjGQVLCKJMrek9PUjL2l%2BcCR%2FaMKYyvkfmm0nNiqfHkBtRVTxC8Cl0BRDLdhS0Gvcp0xm%2BoZyOYYKlvlGEZ1XlhmcWQaUlC3TR%2F5UuXnZNlGg84Mpuaar5jYJqt0QdWe0XWoCRhs5Qa7x4tpRjU6D3wpi5M6tZ62utv93m0jWhO8sdtIMPiz5V%2BaNxP0Etl0u2XX1C0a6%2F5gz7pCI20SASFOyMDYVrjN52MaXsWEv%2FzyqCndxH9X5FXFMbV9Y3JMRRUwVUUwx%2BBww%2Fu%2FcTvuEQaFmI3Fh5%2BX%2Bc8hefLB3K1Ngw10%2BsZic3M4n%2BZ1N6thIAJTMn4vh%2BICTPwNsOsHwO7sKaKQ%2FtiKpIJ%2BCKu54C3h7MG7eNe7uUIsWhe804LIp6U8GNM53Ym0utGbJytxhwaiHhAddlZlx7qMLlJMy5if6rACHOgJNWmVdzy0IAoBbUBkf5ERayu6edI%2BDgtMTbqzAM7MfTK%2FB2Pzlo8iz5XdmhBmoBqUEZD1YWTeY%2FKd7Qh82dnpgyjPTEVmjfXl%2BeUlzHcfhBwOOYcpheyb8sfupsEMJ1RJhqkn5s6JLdE0aUsyTDGq3Y6Tu30pN99q3sFyRdWYSYEeQxkZwXIbSFEYBCJTxXpuquzd3mmTMd8%2B%2BxxKDa9yyuWrFb0AQ%2FZEPBiv4CDlr0fuVfGKV1QHskxVvA4C3DjC08IFrG8j8j9D99UMVZ3cqLJ2aNAq7%2F6HCZ3XNcOHGPp4pO8fJE6zXvmtyXQ%2FAmp0ygBRAlLqpyPcoRQ0Ej1DHtuF%2B2N8UHt6qcUd9jwoDZFgC6w%2FDIIUqhQIgEBUwtovqiEL3Y5ht%2FcfdwIbIsZoLidUOCuOaDJNIvRjvRHXKHxoZ%2B%2FcblK0TSV1qhUvyKZg5h24KwSsS5I7h2pM2qarLmVCZjfh3EfOzEIBcpWjFKMRQQaw3478YKGzHO3AqZpbi67EJqJoK8dFDYgToR%2FXRRMssZ1UDn9NHqpPknwaR0ZniSjz45Q3t%2FwxC3%2BeNHWJYzD86ICSevYt0W8SK5jkCCyMDZkm1PFERMt%2BO0n7H%2F%2FxGnWEKt9hpeo0GCfuoDqYkqYRu6C9vdfdCzJkP1rxwT%2BztcfDIIkYKtR33Haab2PVQE5w%2B13JYnHtwsMH2fC7PkY0B1BPOGDEx%2B4M";
		
		System.out.println(URLDecoder.decode(st, "gbk"));
	}

}

package cn.paypalm.spring.redis;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import redis.clients.jedis.JedisShardInfo;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年1月12日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年1月12日	新建文件.
 * 
 * </pre>
 */
//@SpringBootApplication
public class Application {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

		@Bean
		RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
				MessageListenerAdapter listenerAdapter) {

			RedisMessageListenerContainer container = new RedisMessageListenerContainer();
			container.setConnectionFactory(connectionFactory);
			container.addMessageListener(listenerAdapter, new PatternTopic("chat"));

			return container;
		}

		@Bean
		MessageListenerAdapter listenerAdapter(Receiver receiver) {
			return new MessageListenerAdapter(receiver, "receiveMessage");
		}

		@Bean
		Receiver receiver(CountDownLatch latch) {
			return new Receiver(latch);
		}

		@Bean
		CountDownLatch latch() {
			return new CountDownLatch(1);
		}

		@Bean
		StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
			return new StringRedisTemplate(connectionFactory);
		}
		
		@Bean
		RedisConnectionFactory factory(){
			return new JedisConnectionFactory(new JedisShardInfo("192.168.31.42"));
		}

		public static void main(String[] args) throws InterruptedException {

			ApplicationContext ctx = SpringApplication.run(Application.class, args);

			StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
			CountDownLatch latch = ctx.getBean(CountDownLatch.class);

			LOGGER.info("发送信息...");
			template.convertAndSend("chat", "你好 redis");

			latch.await();

			System.exit(0);
		}

}

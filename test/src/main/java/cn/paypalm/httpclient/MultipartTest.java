package cn.paypalm.httpclient;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MultipartTest {

    private static final Logger log = LogManager.getLogger(MultipartTest.class);
    /**
     * 模拟表单上传文件
     */
    public Map<String,Object> uploadFileByHTTP(File postFile, String postUrl, Map<String,String> postParam){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try{
            //把一个普通参数和文件上传给下面这个地址    是一个servlet
            HttpPost httpPost = new HttpPost(postUrl);
            //把文件转换成流对象FileBody
            FileBody fundFileBin = new FileBody(postFile);
            //设置传输参数
            MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
            multipartEntity.addPart(postFile.getName(), fundFileBin).setMode(HttpMultipartMode.RFC6532);//相当于<input type="file" name="media"/>
            multipartEntity.addBinaryBody(postFile.getName(),new FileInputStream(postFile),ContentType.MULTIPART_FORM_DATA,postFile.getName()).setMode(HttpMultipartMode.RFC6532);
            //设计文件以外的参数
            Set<String> keySet = postParam.keySet();
            for (String key : keySet) {
                //相当于<input type="text" name="name" value=name>
                multipartEntity.addPart(key, new StringBody(postParam.get(key), ContentType.create("text/plain", Consts.UTF_8)));
            }

            HttpEntity reqEntity =  multipartEntity.build();
            httpPost.setEntity(reqEntity);

            log.info("发起请求的页面地址 " + httpPost.getRequestLine());
            //发起请求   并返回请求的响应
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                log.info("----------------------------------------");
                //打印响应状态
                log.info(response.getStatusLine());
                resultMap.put("statusCode", response.getStatusLine().getStatusCode());
                //获取响应对象
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    //打印响应长度
                    log.info("Response content length: " + resEntity.getContentLength());
                    //打印响应内容
                    resultMap.put("data", EntityUtils.toString(resEntity, Charset.forName("UTF-8")));
                }
                //销毁
                EntityUtils.consume(resEntity);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally{
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("uploadFileByHTTP result:"+resultMap);
        return resultMap;
    }

    public static void main(String[] args) {
        //要上传的文件的路径
        String filePath = "E:/718878b5jw1f5td2unbakj20dw0af0sz.jpg";
        String postUrl  = "http://localhost:8080/test-web/upload/upload1";
        Map<String,String> postParam = new HashMap<String,String>();
        postParam.put("custNo", "11122");
        File postFile = new File(filePath);
        Map<String,Object> resultMap = new MultipartTest().uploadFileByHTTP(postFile,postUrl,postParam);
        System.out.println(resultMap);
    }

}

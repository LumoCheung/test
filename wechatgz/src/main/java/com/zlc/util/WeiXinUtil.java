package com.zlc.util;

import com.zlc.entity.AccessToken;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author zhangzilu02
 * @create 2018/2/1
 **/
public class WeiXinUtil {
    private static final Logger log = LoggerFactory.getLogger(WeiXinUtil.class);

    //从微信后台拿到APPID和APPSECRET 并封装为常量
    private static final String APPID = "wx64c678f340e44dfa";
    private static final String APPSECRET = "c31699e2de7db0f4164d0d4d6b26b4ff";
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    /**
     * 编写Get请求的方法。但没有参数传递的时候，可以使用Get请求
     *
     * @param url 需要请求的URL
     * @return 将请求URL后返回的数据，转为JSON格式，并return
     */
    public static JSONObject doGetStr(String url) throws ClientProtocolException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();//获取DefaultHttpClient请求
        HttpGet httpGet = new HttpGet(url);//HttpGet将使用Get方式发送请求URL
        JSONObject jsonObject = null;
        HttpResponse response = client.execute(httpGet);//使用HttpResponse接收client执行httpGet的结果
        HttpEntity entity = response.getEntity();//从response中获取结果，类型为HttpEntity
        if(entity != null){
            String result = EntityUtils.toString(entity,"UTF-8");//HttpEntity转为字符串类型
            jsonObject = JSONObject.fromObject(result);//字符串类型转为JSON类型
        }
        return jsonObject;
    }

    /**
     * 编写Post请求的方法。当我们需要参数传递的时候，可以使用Post请求
     *
     * @param url 需要请求的URL
     * @param outStr  需要传递的参数
     * @return 将请求URL后返回的数据，转为JSON格式，并return
     */
    public static JSONObject doPostStr(String url,String outStr) throws ClientProtocolException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();//获取DefaultHttpClient请求
        HttpPost httpost = new HttpPost(url);//HttpPost将使用Get方式发送请求URL
        JSONObject jsonObject = null;
        httpost.setEntity(new StringEntity(outStr,"UTF-8"));//使用setEntity方法，将我们传进来的参数放入请求中
        HttpResponse response = client.execute(httpost);//使用HttpResponse接收client执行httpost的结果
        String result = EntityUtils.toString(response.getEntity(),"UTF-8");//HttpEntity转为字符串类型
        jsonObject = JSONObject.fromObject(result);//字符串类型转为JSON类型
        return jsonObject;
    }

    /**
     * 获取AccessToken
     * @return 返回拿到的access_token及有效期
     */
    public static AccessToken getAccessToken() throws ClientProtocolException, IOException {
        AccessToken token = new AccessToken();
        String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);//将URL中的两个参数替换掉
        JSONObject jsonObject = doGetStr(url);//使用刚刚写的doGet方法接收结果
        if(jsonObject!=null){ //如果返回不为空，将返回结果封装进AccessToken实体类
            token.setToken(jsonObject.getString("access_token"));//取出access_token
            token.setExpiresIn(jsonObject.getInt("expires_in"));//取出access_token的有效期
        }
        return token;
    }

    public static String upload(String accessToken,String type,String postUrl,String postFile){
//        Map<String,Object> resultMap = new HashMap<String,Object>();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try{
            //把一个普通参数和文件上传给下面这个地址    是一个servlet
            HttpPost httpPost = new HttpPost(postUrl.replace("ACCESS_TOKEN",accessToken).replace("TYPE",type));
            //把文件转换成流对象FileBody
            FileBody fundFileBin = new FileBody(new File(postFile));
            //设置传输参数
            MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
            multipartEntity.addPart(postFile, fundFileBin).setMode(HttpMultipartMode.RFC6532);//相当于<input type="file" name="media"/>
//            multipartEntity.addBinaryBody(postFile,new FileInputStream(postFile), ContentType.MULTIPART_FORM_DATA,postFile).setMode(HttpMultipartMode.RFC6532);
//            multipartEntity.addBinaryBody(postFile.getName(),new FileInputStream(postFile),ContentType.MULTIPART_FORM_DATA,postFile.getName()).setMode(HttpMultipartMode.RFC6532);

            HttpEntity reqEntity =  multipartEntity.build();
            httpPost.setEntity(reqEntity);

            log.info("发起请求的页面地址 " + httpPost.getRequestLine());
            //发起请求   并返回请求的响应
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                log.info("----------------------------------------");
                //打印响应状态
                log.info(response.getStatusLine().toString());
//                resultMap.put("statusCode", response.getStatusLine().getStatusCode());
                if(response.getStatusLine().getStatusCode()!=200){
                    log.error("error");
                    return "error";
                }
                //获取响应对象
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    //打印响应长度
                    log.info("Response content length: " + resEntity.getContentLength());
                    //打印响应内容
                    String result="";
                    log.info("data:{}", result=EntityUtils.toString(resEntity, Charset.forName("UTF-8")));
                    return result;
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
//        log.info("uploadFileByHTTP result:"+ );
//        return resultMap;
        return null;
    }

    public static void main(String[] args) throws IOException {
        String token=WeiXinUtil.getAccessToken().getToken();
        String data=WeiXinUtil.upload(token
                ,MessageUtil.MESSAGE_IMAGE, UrlContant.QT_YONGJIU_SUCAI_URL
                ,"/Users/zlc/Documents/图片/img-8ad5639f3e101672e6ebb8e31e2dde48.jpg");
        /*JSONObject object=JSONObject.fromObject(data);
        log.info(object.toString());
        String mediaId=object.getString("media_id");
        data=WeiXinUtil.upload(token
                ,MessageUtil.MESSAGE_IMAGE, UrlContant.TW_2_URL
                ,"/Users/zlc/Documents/图片/img-8ad5639f3e101672e6ebb8e31e2dde48.jpg");
        object=JSONObject.fromObject(data);
        log.info(object.toString());
        String url=object.getString("url");
        JSONObject o=new JSONObject();
        o.accumulate("title","离骚");
        o.accumulate("thumb_media_id",mediaId);
        o.accumulate("author","zlc");
        o.accumulate("digest","纷吾既有此内美兮，又重之以修能。");
        o.accumulate("show_cover_pic",1);
        o.accumulate("content","帝高阳之苗裔兮，朕皇考曰伯庸。\n" +
                "\n" +
                "摄提贞于孟陬兮，惟庚寅吾以降。\n" +
                "\n" +
                "皇览揆余初度兮，肇锡余以嘉名。\n" +
                "\n" +
                "名余曰正则兮，字余曰灵均。\n" +
                "\n" +
                "纷吾既有此内美兮，又重之以修能。\n" +
                "\n" +
                "扈江离与辟芷兮，纫秋兰以为佩。\n" +
                "\n" +
                "汨余若将不及兮，恐年岁之不吾与。\n" +
                "\n" +
                "朝搴阰之木兰兮，夕揽洲之宿莽。\n" +
                "\n" +
                "日月忽其不淹兮，春与秋其代序。\n" +
                "\n" +
                "唯草木之零落兮，恐美人之迟暮。\n" +
                "\n" +
                "不抚壮而弃秽兮，何不改乎此度？\n" +
                "\n" +
                "乘骐骥以驰骋兮，来吾道夫先路！\n" +
                "\n" +
                "昔三后之纯粹兮，固众芳之所在。\n" +
                "<img src=\""+url+" \" />");
        o.accumulate("content_source_url","http://hanyu.baidu.com/shici/detail?pid=9ad3463ac8944527947d959b7c8b1f8e&from=kg0");
        log.debug(o.toString());*/
    }

}

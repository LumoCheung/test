package cn.paypalm.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {
    private static final Logger log= LoggerFactory.getLogger(UploadController.class);
    @RequestMapping(value="/upload1")
    public String upload(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //利用apache的common-upload上传组件来进行  来解析获取到的流文件
        //把上传来的文件放在这里
        String uploadPath = "/upload";//获取文件路径
        //检测是不是存在上传文件
        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {

            DiskFileItemFactory factory = new DiskFileItemFactory();
            //指定在内存中缓存数据大小,单位为byte,这里设为1Mb
            factory.setSizeThreshold(1024 * 1024);
            //设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
            factory.setRepository(new File("D://temp"));
            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 指定单个上传文件的最大尺寸,单位:字节，这里设为5Mb
            upload.setFileSizeMax(5 * 1024 * 1024);
            //指定一次上传多个文件的总尺寸,单位:字节，这里设为10Mb
            upload.setSizeMax(10 * 1024 * 1024);
            upload.setHeaderEncoding("UTF-8"); //设置编码，因为我的jsp页面的编码是utf-8的

            List<FileItem> items = null;

            try {
                // 解析request请求
                items = upload.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            if (items != null) {
                //把上传文件放到服务器的这个目录下
                if (!new File(uploadPath).isDirectory()) {
                    new File(uploadPath).mkdirs(); //选定上传的目录此处为当前目录，没有则创建
                }
                //解析表单项目
                // Process the uploaded items
                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    //如果是普通表单属性
                    if (item.isFormField()) {
                        //<input type="text" name="content">
                        String name = item.getFieldName();//相当于input的name属性
                        String value = item.getString();//input的value属性
                        log.debug("属性:" + name + " 属性值:" + value);
                    }
                    //如果是上传文件
                    else {
                        //属性名
                        String fieldName = item.getFieldName();
                        //上传文件路径
                        String fileName = item.getName();
                        log.debug("文件：{}/{}",fieldName,fileName);
//                        fileName = fileName.substring(fileName.lastIndexOf("/") + 1);// 获得上传文件的文件名
                        fileName= UUID.randomUUID().toString().replace("-","")+".jpg";
                        try {
                            item.write(new File(uploadPath, fileName));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                //给请求页面返回响应
                return "success";
            }
        }
        return "failed";
    }
}

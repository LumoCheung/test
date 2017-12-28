package cn.paypalm.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年1月19日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年1月19日	新建文件.
 * 
 * </pre>
 */
@Controller
@RequestMapping(("/index"))
public class WebController {
	@RequestMapping("/index")
	public String hello(){
		return "index";
	}
	
	@ResponseBody
	@RequestMapping(value="/data",produces="text/html;charset=utf-8")
	public String get(HttpServletRequest request) {
		//汉字测试
		return "汉字测试";
	}


}

package com.wx.watersupplierservice.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wx.watersupplierservice.req.OrderListReq;
import com.wx.watersupplierservice.service.BusinessService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/download")
public class DownloadController {

	@Autowired
	private BusinessService businessService;
	
	/**
	 * @throws UnsupportedEncodingException 
	 * 
	 * @Title: encodeFileName   
	 * @Description: 解决不同浏览器文件名乱码
	 * @param request
	 * @param fileName
	 * @return: String    
	 * @author: weili
	 * @date: 2018年9月5日 下午4:17:07
	 * @throws
	 */
	public static String encodeFileName(HttpServletRequest request, String fileName)
			throws UnsupportedEncodingException {
		String userAgent = request.getHeader("User-Agent");
		String rtn = "";
		String new_filename = URLEncoder.encode(fileName, "UTF8");
		// 如果没有UA，则默认使用IE的方式进行编码，因为毕竟IE还是占多数的
		rtn = "filename=\"" + new_filename + "\"";
		if (userAgent != null) {
			userAgent = userAgent.toLowerCase();
			// IE浏览器，只能采用URLEncoder编码
			if (userAgent.indexOf("msie") != -1) {
				rtn = "filename=\"" + new_filename + "\"";
			}
			// Opera浏览器只能采用filename*
			else if (userAgent.indexOf("opera") != -1) {
				rtn = "filename*=UTF-8''" + new_filename;
			}
			// Safari浏览器，只能采用ISO编码的中文输出,Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出
			else if (userAgent.indexOf("safari") != -1 || userAgent.indexOf("applewebkit") != -1) {
				rtn = "filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + "\"";
			}
			// FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
			else if (userAgent.indexOf("mozilla") != -1) {
				rtn = "filename*=UTF-8''" + new_filename;
			}
		}
		return rtn;
	}
	
	/**
	 * 
	 * @Title: batchExportNtoCurriculumInfo
	 * @Description: 导出所有课程
	 * @param request
	 * @param response
	 * @throws IOException
	 * @author weili
	 * @date 2020-01-14 16:57:52
	 */
	@RequestMapping(value = "/v1/{userId}/{startTime}/{endTime}" , method = RequestMethod.GET)
	public void download(@PathVariable Integer userId, @PathVariable String startTime, @PathVariable String endTime, HttpServletRequest request, HttpServletResponse response) throws IOException{
		OrderListReq orderListReq = new OrderListReq();
		orderListReq.setUserId(userId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (StringUtils.isNotBlank(startTime)){
			try {
				startTime = startTime+" 00:00:00";
				orderListReq.setStartTime(sdf.parse(startTime));
			} catch (ParseException e) {
				orderListReq.setStartTime(null);
			}
		}
		if (StringUtils.isNotBlank(endTime)){
			try {
				endTime = endTime+" 00:00:00";
				orderListReq.setEndTime(sdf.parse(endTime));
			} catch (ParseException e) {
				orderListReq.setEndTime(null);
			}
		}
		HSSFWorkbook hb = businessService.download(orderListReq);
		String docFileName="订单数据明细.xls";

		if (orderListReq.getStartTime() != null && orderListReq.getEndTime() != null){
			docFileName = startTime + "至" + endTime + ".xls";
		}
		String strFileName = encodeFileName(request,docFileName);
		response.reset();
		response.setHeader("Content-disposition", "attachment;"+ strFileName);
		response.setContentType("application/octet-stream;charset=UTF-8");
		OutputStream os = response.getOutputStream();
		hb.write(os);
		os.flush();
		os.close();
	}
}

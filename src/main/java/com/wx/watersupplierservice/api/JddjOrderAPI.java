package com.wx.watersupplierservice.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wx.watersupplierservice.util.ResponseUtils;
import com.wx.watersupplierservice.util.jddj.JddjOrderUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *        接收第三方消息推送
 * @author wang-ql
 *
 */
@Controller
public class JddjOrderAPI {
    
	/**
	 * 京东新订单
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/api/jddj/djsw/newOrder")
	public void receiveOrderFromjd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String token = request.getParameter("token");
        String app_key = request.getParameter("app_key");
        String sign = request.getParameter("sign");
        String timestamp = request.getParameter("timestamp");
        String format = request.getParameter("format");
        String v = request.getParameter("v");
        String jd_param_json = request.getParameter("jd_param_json");
        
        //解析数据
        JSONObject orderJson = new JSONObject(jd_param_json);
        
        //根据appkey获取商家授权基本信息 appKey,appSecret,token
        JSONObject orgJson = new JSONObject();
        
        // 根据单号获取订单详细信息
        String orderInfo = JddjOrderUtil.findOrderFromJddj(orgJson,orderJson.optString("billId"));
        // 将订单信息保存到数据库
        JSONObject expireData = new JSONObject();
        expireData.put("code", "0");
        expireData.put("message",  "success");
        expireData.put("data",  "");
        ResponseUtils.putJsonResponse(response, expireData);
	}
	
	/**
	 * 用户取消申请
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/api/jddj/djsw/applyCancelOrder")
	public void receiveApplyCancelOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String token = request.getParameter("token");
        String app_key = request.getParameter("app_key");
        String sign = request.getParameter("sign");
        String timestamp = request.getParameter("timestamp");
        String format = request.getParameter("format");
        String v = request.getParameter("v");
        String jd_param_json = request.getParameter("jd_param_json");
        
        //解析数据
        JSONObject orderJson = new JSONObject(jd_param_json);
//        billId statusId remark timestamp
        
        // 将订单信息保存到数据库
   

        JSONObject expireData = new JSONObject();
        expireData.put("code", "0");
        expireData.put("message",  "success");
        expireData.put("data",  "");
        ResponseUtils.putJsonResponse(response, expireData); 
	}
	
	/**
	 * 订单完成
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/api/jddj/djsw/orderFinish")
	public void receiveOrderFinish(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String token = request.getParameter("token");
        String app_key = request.getParameter("app_key");
        String sign = request.getParameter("sign");
        String timestamp = request.getParameter("timestamp");
        String format = request.getParameter("format");
        String v = request.getParameter("v");
        String jd_param_json = request.getParameter("jd_param_json");
        
        //解析数据
        JSONObject orderJson = new JSONObject(jd_param_json);
//        billId businessTag
        
        // 将订单信息保存到数据库
   

        JSONObject expireData = new JSONObject();
        expireData.put("code", "0");
        expireData.put("message",  "success");
        expireData.put("data",  "");
        ResponseUtils.putJsonResponse(response, expireData); 
	}
	
}

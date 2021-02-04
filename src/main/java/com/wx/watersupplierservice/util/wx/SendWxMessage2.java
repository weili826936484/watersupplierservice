package com.wx.watersupplierservice.util.wx;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.wx.watersupplierservice.dto.OrderDto;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class SendWxMessage2 {
	
	/**
	 * 水站新订单
     * @param orderDto  订单信息
     * @param productList  商品信息
     * @param userList  orderId 对应水站人员openId列表
     */
	public static void sendNewOrder(OrderDto orderDto, List<OrderDto.ProductDto> productList,
                                    List<String> userList) {
		
		//新订单通知模板
		String templatId = "MNnysHBph8N-gPiUIUoLgl_Uju9gdnlIjCfN-wh2Xwk";
		String clickUrl = "http://www.yhzav.cn/wx/siteNewOrder.html";
		
		//拼接商品信息
		StringBuffer str = new StringBuffer(); 
		if (productList.size() > 0) {
			for(int n=0;n<productList.size();n++) {
				str.append(productList.get(n).getSkuName() + " | 数量*" + productList.get(n).getSkuCount());
			}
		}
			
		//拼接推送消息模板				
        String topColor = "";
        JSONObject jsc = new JSONObject();
        
        JSONObject first = new JSONObject();
        first.put("value", "收到一个新的订单，请及时处理");
        first.put("color", "#173177");
        jsc.put("first", first);
        
        JSONObject keyword1 = new JSONObject();
        keyword1.put("value", orderDto.getOrderid());
        keyword1.put("color", "#173177");
        jsc.put("keyword1", keyword1);
        
        JSONObject keyword2 = new JSONObject();
        keyword2.put("value", orderDto.getBuyerfullname() + orderDto.getBuyertelephone());
        keyword2.put("color", "#173177");
        jsc.put("keyword2", keyword2);
        
        JSONObject keyword3 = new JSONObject();
        keyword3.put("value", str.toString());
        keyword3.put("color", "#173177");
        jsc.put("keyword3", keyword3);
        
        JSONObject keyword4 = new JSONObject();
        keyword4.put("value", orderDto.getBuyerfulladdress());
        keyword4.put("color", "#173177");
        jsc.put("keyword4", keyword4);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        JSONObject keyword5 = new JSONObject();
        keyword5.put("value", format.format(orderDto.getOrderprestartdeliverytime()).substring(0, 16) + "至" + format.format(orderDto.getOrderpreenddeliverytime()).substring(11, 16));
        keyword5.put("color", "#173177");
        jsc.put("keyword5", keyword5);
        
        JSONObject remark = new JSONObject();
        remark.put("value", orderDto.getOrderbuyerremark());
        remark.put("color", "#173177");
        jsc.put("remark", remark);

		if(userList.size() > 0) {
			for(int m=0;m<userList.size();m++) {
				String openId = userList.get(m);
				if (StringUtils.isNotBlank(openId)) {
					//向管理员推送微信
					String result =  WeixinUtil.sendNotify(openId, templatId, clickUrl, topColor, jsc);
				}
				
			}
		}
	}
	
	
	/**
	 * 水站拒单
	 * @param orderDto  订单信息
	 * @param productList  商品信息
	 * @param userList   orderId 对应管理员openId列表
	 */
	public static void sendRefuseOrder(OrderDto orderDto, List<JSONObject> productList,
                                       List<String> userList, Date refuseTime,String refuseReason) {
		
		//新订单通知模板
		String templatId = "fpyeyXcYEWnmQ6HDIyYJzqd7xDCb6Mhrwof7_NP6Q-E";
		String clickUrl = "http://www.yhzav.cn/wx/orderNew.html";
			
		//拼接推送消息模板				
        String topColor = "";
        JSONObject jsc = new JSONObject();
        
        JSONObject first = new JSONObject();
        first.put("value", "水站拒单，请及时处理");
        first.put("color", "#173177");
        jsc.put("first", first);
        
        JSONObject keyword1 = new JSONObject();
        keyword1.put("value", orderDto.getOrderid());
        keyword1.put("color", "#173177");
        jsc.put("keyword1", keyword1);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        JSONObject keyword2 = new JSONObject();
        keyword2.put("value", format.format(orderDto.getOrderstarttime()).substring(0, 19));  //下单时间
        keyword2.put("color", "#173177");
        jsc.put("keyword2", keyword2);
        
        JSONObject keyword3 = new JSONObject();
        keyword3.put("value", format.format(refuseTime));  //拒绝时间   业务流程明细表拒单记录创建时间
        keyword3.put("color", "#173177");
        jsc.put("keyword3", keyword3);
        
        JSONObject keyword4 = new JSONObject();
        keyword4.put("value", refuseReason); //拒绝理由   业务流程明细表拒单记录result_info
        keyword4.put("color", "#173177");
        jsc.put("keyword4", keyword4);
        
        
        
        JSONObject remark = new JSONObject();
        remark.put("value", "请重新分配水站，或致电客人说明原因");
        remark.put("color", "#173177");
        jsc.put("remark", remark);

		if(userList.size() > 0) {
			for(int m=0;m<userList.size();m++) {
				String openId = userList.get(m);
				if (StringUtils.isNotBlank(openId)) {
					//向管理员推送微信
					String result =  WeixinUtil.sendNotify(openId, templatId, clickUrl, topColor, jsc);
				}
				
			}
		}
	}
	
	/**
	 * 商家撤单
	 * @param orderDto  订单信息
	 * @param productList  商品信息
	 * @param userList   orderId 对应水站人员openId列表
	 */
	public static void sendCancleOrder(OrderDto orderDto, List<JSONObject> productList,
			List<String> userList) {
		
		//新订单通知模板
		String templatId = "A5lnP2T1KF6Hq8VBvnAnLrErvzi95VY17UEtZgoPsv8";
		String clickUrl = "";
			
		//拼接推送消息模板				
        String topColor = "";
        JSONObject jsc = new JSONObject();
        
        JSONObject first = new JSONObject();
        first.put("value", "商家撤单，无需配送");
        first.put("color", "#173177");
        jsc.put("first", first);
        
        JSONObject keyword1 = new JSONObject();
        keyword1.put("value", orderDto.getOrderid());
        keyword1.put("color", "#173177");
        jsc.put("keyword1", keyword1);
        
        JSONObject keyword2 = new JSONObject();
        keyword2.put("value", "订单分配错了");  
        keyword2.put("color", "#173177");
        jsc.put("keyword2", keyword2);

        JSONObject remark = new JSONObject();
        remark.put("value", "客户信息：" + orderDto.getBuyerfullname() + orderDto.getBuyertelephone());
        remark.put("color", "#173177");
        jsc.put("remark", remark);
        
		if(userList.size() > 0) {
			for(int m=0;m<userList.size();m++) {
				String openId = userList.get(m);
				if (StringUtils.isNotBlank(openId)) {
					//向管理员推送微信
					String result =  WeixinUtil.sendNotify(openId, templatId, clickUrl, topColor, jsc);
				}
				
			}
		}
	}

}

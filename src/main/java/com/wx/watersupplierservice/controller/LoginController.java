package com.wx.watersupplierservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wx.watersupplierservice.po.SysWeixinPo;
import com.wx.watersupplierservice.service.WeixinService;
import com.wx.watersupplierservice.util.Cfg;
import com.wx.watersupplierservice.util.Const;
import com.wx.watersupplierservice.util.ResponseUtils;
import com.wx.watersupplierservice.util.wx.Oaut2TokenJson;
import com.wx.watersupplierservice.util.wx.SNSUserInfo;
import com.wx.watersupplierservice.util.wx.WeixinUtil;


@Controller
public class LoginController {	
	@Autowired
	private WeixinService weixinService;
	
	/**
     * 
     * <B>方法名称：</B>公众号点击菜单跳转<BR>
     * <B>概要说明：</B><BR>
     * @param response 
     * @param request 
     * @param attr  
     * @return url
     */
    @RequestMapping("/wx/tofunction.html")
    public String toLogin(HttpServletResponse response, HttpServletRequest request, RedirectAttributes attr) {
        String appId = Cfg.getConfig("web.appId");
        String appSecret = Cfg.getConfig("web.appSecret");
        try {
            String openId = null; 
            String accessToken = null;
            String code = request.getParameter("code");
            // 通过code获取openId
            Oaut2TokenJson oauth2Token = WeixinUtil.getOauth2AccessToken(appId, appSecret, code);
            openId = oauth2Token.getOpenid();
            accessToken = oauth2Token.getAccess_token();

            //获取到用户的基本信息
            SNSUserInfo snsUserInfo = WeixinUtil.getSNSUserInfo(accessToken, openId);
            if (snsUserInfo != null) {
                //根据openId更新 或者保存微信基本信息
            	SysWeixinPo weixinInfo = new SysWeixinPo();
            	weixinInfo.setOpenid(openId);
            	weixinInfo.setNickname(snsUserInfo.getNickname());
            	weixinInfo.setHeadimgurl(snsUserInfo.getHeadImgUrl());
            	weixinService.saveOrUpdateWx(weixinInfo);
                
            }
            //根据openid查询是否绑定了操作员
            JSONObject params = new JSONObject();
            params.put("openId", openId);
            List<JSONObject> userList =  new  ArrayList<JSONObject>(); //sysUserDao.findUsers(params);
            if(userList.size() == 1) {
            	//免登陆，直接进入功能列表页面
            	JSONObject userInfo = userList.get(0);
            	String userId = userInfo.optString("user_id");
            	String user_name = userInfo.optString("user_name");
                String role_code = userInfo.optString("role_code");
                String role_tel = userInfo.optString("role_tel");
                attr.addFlashAttribute("openId", openId);
                attr.addFlashAttribute("userId", userId);
                attr.addFlashAttribute("role_code", role_code);
                attr.addFlashAttribute("user_name", user_name);
                attr.addFlashAttribute("role_tel", role_tel);
                
                return "redirect:/wx/function.html"; 
            } else {
            	
            	//如果没有绑定，跳转登陆页面
                attr.addFlashAttribute("openId", openId);
                return "redirect:" + "/wx/login.html";
            }         
            
        } catch (Exception e) {
        	e.printStackTrace();
            return "redirect:" + "/wx/error.html";
        }   
        
    }
    
    /**
     * 
     * <B>方法名称：</B>微信登陆<BR>
     * <B>概要说明：</B><BR>
     * @param response 
     * @param request 
     * @throws JSONException  
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping("/wx/user/login")
    public void login(HttpServletResponse response, HttpServletRequest request) throws Exception {
    	JSONObject outdata = new JSONObject();
        String user_tel = request.getParameter("user_tel");
        String password = request.getParameter("password");
        String openId = request.getParameter("openId");

        JSONObject params = new JSONObject();
        params.put("user_tel", user_tel);
        params.put("password", password);
        
        /**
         **  根据user_tel和password查找用户
         */
        try {
        	List<JSONObject> userList =   null; //sysUserDao.findUsers(params);
            if (userList.size() == 1) {
            	JSONObject userInfo = userList.get(0);
            	if (StringUtils.isNotBlank(userInfo.optString("openId"))) {
            		outdata.put("code", Const.CODE_ERR_EXP);
                    outdata.put("message", "账户已绑定其他微信。");
            	} else {
            		//sysUserDao.updateUser(userInfo.optString("user_id"), openId);
            		outdata.put("code", Const.CODE_SUCCESS);
                    outdata.put("message", "success");
            	}
            	
            } else {
            	outdata.put("code", Const.CODE_ERR_EXP);
                outdata.put("message", "用户名或者密码错误。");
            }
        } catch (Exception e) {
        	outdata.put("code", Const.CODE_ERR_EXP);
            outdata.put("message", "异常错误。");
        }

        ResponseUtils.putJsonResponse(response, outdata);
    }
}

package com.wx.watersupplierservice.util.jddj;

/**
 * ClassName:RequestDTO <br/>
 * Function: 通用请求类   <br/>
 * Date:     2015年5月25日 下午4:22:16 <br/>
 * @author   zhoudeming
 */
public class WebRequestDTO {

  /**
   * token令牌
   */
  private String token;
  /**
   * 应用appkey
   */
  private String app_key;
  /**
   * 时间戳 yyyy-MM-dd HH:mm:ss
   */
  private String timestamp;
  /**
   * 版本
   */
  private String v;
  /**
   * 数据格式
   */
  private String format;
  /**
   * 应用级别参数
   */
  private String jd_param_json;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getApp_key() {
    return app_key;
  }

  public void setApp_key(String app_key) {
    this.app_key = app_key;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getV() {
    return v;
  }

  public void setV(String v) {
    this.v = v;
  }

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public String getJd_param_json() {
    return jd_param_json;
  }

  public void setJd_param_json(String jd_param_json) {
    this.jd_param_json = jd_param_json;
  }

  @Override
  public String toString() {
    return "WebRequestDTO [token=" + token + ", app_key=" + app_key
            + ", timestamp=" + timestamp + ", v=" + v + ", format="
            + format + ", jd_param_json=" + jd_param_json + "]";
  }
}
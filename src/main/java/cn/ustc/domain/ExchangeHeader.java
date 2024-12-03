package cn.ustc.domain;

import cn.ustc.constant.Constant;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ExchangeHeader {
    @JSONField(name = "vid")
    private String vid;
    @JSONField(name = "skey")
    private String sKey;
    @JSONField(name = "User-Agent")
    private String userAgent;

    public ExchangeHeader() {
    }

    public ExchangeHeader(String vid, String sKey) {
        this.vid = vid;
        this.sKey = sKey;
        this.userAgent = Constant.UserAgentForApp;
    }

    public ExchangeHeader(JSONObject wxReaderHeader) {
        String[] splitCookies = wxReaderHeader.getString("Cookie").split(";");
        Arrays.stream(splitCookies).forEach(cookie -> {
            cookie = cookie.trim();
            if (cookie.startsWith("wr_vid")) {
                this.vid = cookie.split("=")[1];
            } else if (cookie.startsWith("wr_skey")) {
                this.sKey = cookie.split("=")[1];
            }
        });
        this.userAgent = Constant.UserAgentForApp;
    }

    public Map<String, String> toMap() {
        Map<String, String> res = new HashMap<>();
        res.put("vid", vid);
        res.put("skey", sKey);
        res.put("User-Agent", userAgent);
        return res;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getsKey() {
        return sKey;
    }

    public void setsKey(String sKey) {
        this.sKey = sKey;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}

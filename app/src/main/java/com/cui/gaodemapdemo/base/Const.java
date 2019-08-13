package com.cui.gaodemapdemo.base;

/**
 * 数据常量
 *
 * @author cui7dr by 2019/07/29
 */
public class Const {

    // 平台版本号
    public static final String version = "1";
    // 访问公钥
    public static final String pubKey = "1fd9f8c8-d5a7-4196-8602-0e1b7d9dd253";
    // 访问加密参数（sha256(version + pubkey + 私钥
    public static final String sign = "d106da109961624666b36085f6bdba048ce34996271d90610096afb2d3dcf34e";

    /**
     * 许昌平台信息
     */
    // URL
    public static final String url_xc = "http://222.89.181.200:10000/carCon-web-outer/api/client/appinte.htm?";
    // 登录方法
    public static final String method_login_xc = "applogin";
    // 查看点位信息方法
    public static final String method_pointLatLng_xc = "pointInfo";
    // 查看点位数据方法
    public static final String method_pointData_xc = "latestPtInfoData";
    // 查看数据查询方法
    public static final String method_dataInfo_xc = "telDataInfo";
    // 查看设备在线状态方法
    public static final String method_status_xc = "actualDataInfo";

    /**
     * 郑州平台信息
     */
    // URL
    public static final String url_zz = "http://222.143.52.84:10000/carCon-web-outer/";
    // 站点信息目录
    public static final String path_pointInfo_zz = "api/client/airenvir.htm?";
    // 登录目录
    public static final String path_login_zz = "api/client/appinte.htm?";

    /**
     * 提示信息
     */
    // 超时
    public static final String OUT_TIME = "连接服务器超时，请稍后重试。。。";
    // 正在加载
    public static final String DATA_IS_LOADING = "数据正在加载，请稍后重试。。。";
    // 请输入检索条件
    public static final String RETRIEVAL_CONDITION = "请输入检索条件";
    // 不符合账号规则
    public static final String NOT_NAME = "不符合账号规则";
    // 不符合密码规则
    public static final String NOT_PWD = "不符合密码规则";
    // IP 地址不匹配
    public static final String NOT_IP = "IP 地址不匹配";
    // 手机号码不符合规则
    public static final String NOT_PHONE = "手机号码不符合规则";
    // 端口号不符合规则
    public static final String NOT_PORT = "端口号不符合规则";

}

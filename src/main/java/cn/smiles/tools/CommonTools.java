package cn.smiles.tools;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Splitter;

import cn.smiles.log.CommonLogger;

public class CommonTools {

    public static String getGenerateNumStr(int generateNum) {
        String generateNumStr = "1万";
        if (generateNum >= 10000) {
            generateNumStr = generateNum / 10000 + "万";
        } else {
            generateNumStr = generateNum + "";
        }
        return generateNumStr;
    }

    public static double getFormatDouleValue(Double f) {
        if (f == null) {
            return 0;
        }
        DecimalFormat df = new DecimalFormat("0.###");
        return Double.valueOf(df.format(f));
    }

    public static String getFormatValue(Double f) {
        if (f == null) {
            return null;
        }
        DecimalFormat df = new DecimalFormat("0.###");
        return df.format(f);
    }

    public static String getStringFormatValue(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        Double d = Double.valueOf(str);
        DecimalFormat df = new DecimalFormat("0.###");
        return df.format(d);
    }

    public static String getBase64Decoder(String base64Str) {
        byte[] decodedBytes = Base64.decodeBase64(base64Str);
        return new String(decodedBytes);
    }

    public static int getUserId(String sessionData) {
        if (StringUtils.isNotBlank(sessionData)) {
            String decoder = getBase64Decoder(sessionData);
            String json = decoder.substring(decoder.indexOf("{"));
            JSONObject jo = JSONObject.parseObject(json);
            return jo.getInteger("_auth_user_id");
        }
        return 0;
    }

    // 只允许输入 数字，数字+百分号
    public static boolean numberCheck(String number) {
        if (number.contains("%")) {
            if (!number.endsWith("%")) {
                return false;
            } else {
                number = number.replace("%", "");
            }
        }

        try {
            Double.valueOf(number);
        } catch (Exception ex) {
            CommonLogger.error("number ");
            return false;
        }
        return true;
    }

    public static boolean checkAccount(String account) {
        if (StringUtils.isBlank(account)) {
            return false;
        }
        // todo: 用户账号格式校验
        return true;
    }

    public static boolean checkPassword(String password) {
        if (StringUtils.isBlank(password)) {
            return false;
        }
        // todo: 密码格式校验
        return true;
    }

    public static boolean isMail(String mail) {
        if (StringUtils.isBlank(mail)) {
            return false;
        }
//        String check =
//                "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
//        Pattern regex = Pattern.compile(check);
//        Matcher matcher = regex.matcher(mail);
//
//        String mailPerson =
//                "@163|@126|@yahoo|@139|@hotmail|@188|@tom|@aliyun|@sohu|@gmail|@189|@qq|@sina";
//        Pattern regexPerson = Pattern.compile(mailPerson);
//        Matcher matcherPerson = regexPerson.matcher(mail);
//        boolean personFinder = !matcherPerson.find();
//
//        return matcher.matches() & personFinder;
        return true;
    }

    public static boolean isPhone(String phone) {
        if (StringUtils.isBlank(phone)) {
            return false;
        }
        String regex =
                "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            return m.matches();
        }
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getIpAddressJsonstr(HttpServletRequest request) {
        JSONObject ipJson = new JSONObject();
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isNotBlank(ip) || !"unknown".equalsIgnoreCase(ip)) {
            ipJson.put("x-forwarded-for", ip);
        }
        ip = request.getHeader("Proxy-Client-IP");
        if (StringUtils.isNotBlank(ip) || !"unknown".equalsIgnoreCase(ip)) {
            ipJson.put("Proxy-Client-IP", ip);
        }
        ip = request.getHeader("WL-Proxy-Client-IP");
        if (StringUtils.isNotBlank(ip) || !"unknown".equalsIgnoreCase(ip)) {
            ipJson.put("WL-Proxy-Client-IP", ip);
        }
        ip = request.getHeader("HTTP_CLIENT_IP");
        if (StringUtils.isNotBlank(ip) || !"unknown".equalsIgnoreCase(ip)) {
            ipJson.put("HTTP_CLIENT_IP", ip);
        }
        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (StringUtils.isNotBlank(ip) || !"unknown".equalsIgnoreCase(ip)) {
            ipJson.put("HTTP_X_FORWARDED_FOR", ip);
        }
        ip = request.getRemoteAddr();
        if (StringUtils.isNotBlank(ip) || !"unknown".equalsIgnoreCase(ip)) {
            ipJson.put("remoteIp", ip);
        }
        return ipJson.toJSONString();
    }

    public static String getRandomCode() {
        int code = RandomUtils.nextInt(100000, 999999);
        return String.valueOf(code);
    }

    public static void setSessionKey(HttpServletResponse response, String session) {
        Cookie cookie = new Cookie("sessionid", session);
        response.addCookie(cookie);
    }

    public static List<Integer> convertIntList(List<String> list) {
        List<Integer> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            try {
                for (String s : list) {
                    result.add(Integer.valueOf(s));
                }
            } catch (Exception ex) {
                CommonLogger.error("string cast to integer error, ex:", ex);
            }
        }
        return result;
    }

    public static List<Integer> convertIntList(String str, String split) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        List<String> strList = Splitter.on(split).splitToList(str);
        return convertIntList(strList);
    }

    public static int getPageCount(int allCount, int pageSize) {
        return allCount % pageSize == 0 ? allCount / pageSize : allCount / pageSize + 1;
    }
}

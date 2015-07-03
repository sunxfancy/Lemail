package lemail.utils;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * Struts2的Action工具类，方便使用
 * Created by sxf on 15-6-28.
 */
public class Action {

    /**
     * 格式化后json数据字符串为标准格式
     * @param status  状态码
     * @param message 信息内容
     */
    public static void echojson(int status, String message) {
        echojson(status, message, null);
    }

    /**
     * @param status  状态码
     * @param message 信息内容
     * @param data    格式化后数据段json字符串，若没有设置为null
     */
    public static void echojson(int status, String message, String data) {
        String str;
        ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
        if (data != null) {
            str = String.format("{\"status\":%d, \"message\":\"%s\", \"data\":%s}",
                    status, message, data);
        } else {
            str = String.format("{\"status\":%d, \"message\":\"%s\"}",
                    status, message);
        }
        try {
            PrintWriter pw = ServletActionContext.getResponse().getWriter();
            pw.println(str);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void println(String str) {
        try {
            PrintWriter pw = ServletActionContext.getResponse().getWriter();
            pw.println(str);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 这个方法只是为了好看的包装Action的返回值
     */
    public static String text(String str) {
        println(str);
        return null;
    }

    public static String error(int status, String message) {
        echojson(status, message, null);
        return null;
    }

    public static Object getSession(String key) {
        return ActionContext.getContext().getSession().get(key);
    }

    public static Object setSession(String key, Object value) {
        return ActionContext.getContext().getSession().put(key, value);
    }
}

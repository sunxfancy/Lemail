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
    public static void echojson(String str) {
        ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
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

    public static String error(String str) {
        println("{error:\""+str+"\"}");
        return null;
    }

    public static Object getSession(String key) {
        return ActionContext.getContext().getSession().get(key);
    }

    public static Object setSession(String key, Objects value) {
        return ActionContext.getContext().getSession().put(key, value);
    }
}

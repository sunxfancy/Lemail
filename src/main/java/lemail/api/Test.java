package lemail.api;

import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 只为测试
 * Created by sxf on 15-6-28.
 */
public class Test {
    public String test() {
        println("Hello");
        return null;
    }

    private void println(String str) {
        ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter pw = ServletActionContext.getResponse().getWriter();
            pw.println(str);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

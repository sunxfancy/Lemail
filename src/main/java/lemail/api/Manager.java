package lemail.api;

import lemail.utils.Action;
import lemail.utils.AutoMail;

import lemail.model.Department;
import lemail.utils.DBSession;

import java.util.List;

/**
 * 管理员接口类
 * Created by XYN on 7/4/2015.
 */
public class Manager {

    public String departmentList() {
        Integer uid = (Integer) Action.getSession("uid");
        String role = (String) Action.getSession("role");
        if (uid != null) {
            if (role.contains("M")) {
                try {
                    List<Department> deps = DBSession.find_list(Department.class);
                    StringBuilder sb = new StringBuilder();
                    sb.append("[");
                    for (Department dep : deps) {
                        sb.append(dep.toJson());
                        sb.append(",");
                    }
                    if (sb.length() > 1) {
                        sb.setCharAt(sb.length() - 1, ']');
                    } else {
                        sb.append("]");
                    }
                    Action.echojson(0, "success", sb.toString());
                } catch (Exception ex) {
                    Action.error(-1, "未知异常");
                }
            } else {
                Action.error(403, "权限不足");
            }
        } else {
            Action.error(401, "管理员未登录");
        }
        return null;
    }



    public String username;
    public String password;
    public String hostname; // smtp
    public String hostname_send; // imap

    /**
     * 设置邮箱配置信息
     */
    public String setConf() {
        try {
            checkRole();
            AutoMail.getInstance().setProp(
                    username,
                    password,
                    hostname,
                    hostname_send
            );
            return Action.success();
        } catch (ApiException e) {
            e.printStackTrace();
            return Action.error(e.getId(), e.getMessage());
        }
    }

    /**
     * 读取邮箱配置信息接口
     */
    public String getConf() {
        try {
            checkRole();
            AutoMail m = AutoMail.getInstance();
            String username = m.getUsername();
            String password = m.getPassword();
            String hostname = m.getHostname();
            String hostname_send = m.getHostname_send();

            Action.echojson(0,"success",
                    String.format(
                            "{\"username\":\"%s\"," +
                             "\"password\":\"%s\"," +
                             "\"hostname\":\"%s\"," +
                             "\"hostname_send\":\"%s\"}",
                            username, password, hostname, hostname_send));

            return null;
        } catch (ApiException e) {
            e.printStackTrace();
            return Action.error(e.getId(), e.getMessage());
        }
    }

    private void checkRole() throws ApiException {
        Integer uid = (Integer)Action.getSession("uid");
        String role = (String) Action.getSession("role");
        if (uid == null) throw new ApiException(401, "用户未登录");
        if (role == null || !role.contains("M"))
            throw new ApiException(500, "用户缺少处理者权限");
    }
}

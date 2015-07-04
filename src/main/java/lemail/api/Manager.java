package lemail.api;

import lemail.model.Department;
import lemail.utils.Action;
import lemail.utils.DBSession;

import java.util.List;

/**
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
}

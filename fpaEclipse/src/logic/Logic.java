package logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Logic {
	String execute(HttpServletRequest req,
            HttpServletResponse res) throws Exception;
}

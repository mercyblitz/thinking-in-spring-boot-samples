package thinking.in.spring.boot.samples.chapter4.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Echo {@link HttpServlet} 实现
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class EchoServlet extends HttpServlet {

    /**
     * 输出 HTTP 请求参数 "messsage" 的内容（支持任意 HTTP 方法）
     *
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @throws IOException
     * @throws ServletException
     */
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // 获取请求参数 "message" 内容
        String message = request.getParameter("message");
        PrintWriter writer = response.getWriter();
        // 输出 "message" 参数内容
        writer.println(message);
        writer.flush();
    }
}

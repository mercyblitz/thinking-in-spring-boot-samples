package thinking.in.spring.boot.samples.chapter4.traditional.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 输出 "Hello,World" 的 {@link HttpServlet}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class HelloWorldServlet extends HttpServlet {

    /**
     * 覆盖父类的{@link HttpServlet#service(ServletRequest, ServletResponse)} 方法，
     * 无论何种 HTTP 方法（GET、POST等）
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();
        // 输出 "Hello,World" 到客户端（浏览器）
        writer.write("Hello,World");

    }
}

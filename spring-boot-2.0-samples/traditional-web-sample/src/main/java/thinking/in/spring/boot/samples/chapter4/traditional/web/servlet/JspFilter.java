package thinking.in.spring.boot.samples.chapter4.traditional.web.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Date;

/**
 * JSP Filter，过滤所有的 "*.jsp" URL映射
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@WebFilter(urlPatterns = "*.jsp") // 过滤所有的 "*.jsp" URL映射
public class JspFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        Date date = new Date();
        request.setAttribute("message", "Hello World");  // 设置名额为"message"的渲染上下文变量
        response.getWriter().println("<!-- executed by JspFilter -->"); // HTML注释，说明执行过滤
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
    }
}

package thinking.in.spring.boot.shared.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Collections;

/**
 * HelloWorld Servlet
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class HelloWorldServlet extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig) {
        Collections.list(servletConfig.getInitParameterNames())
                .forEach(name -> {
                    System.out.println("Init param name : " + name
                            + " , value : " + servletConfig.getInitParameter(name));
                });
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Writer writer = response.getWriter();
        writer.write("Hello,World");
        writer.flush();
    }

}

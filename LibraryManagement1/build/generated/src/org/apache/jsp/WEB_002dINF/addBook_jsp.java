package org.apache.jsp.WEB_002dINF;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class addBook_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    <title>Add Book</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <h2>Add a New Book</h2>\n");
      out.write("    <form action=\"LibraryServlet\" method=\"post\">\n");
      out.write("        <label for=\"bookid\">Book ID:</label><br>\n");
      out.write("        <input type=\"text\" id=\"BookId\" name=\"BookId\" required><br><br>\n");
      out.write("\n");
      out.write("        <label for=\"bookname\">Book Name:</label><br>\n");
      out.write("        <input type=\"text\" id=\"BookName\" name=\"BookName\" required><br><br>\n");
      out.write("\n");
      out.write("        <label for=\"author\">Author:</label><br>\n");
      out.write("        <input type=\"text\" id=\"AuthorName\" name=\"AuthorName\" required><br><br>\n");
      out.write("\n");
      out.write("        <label for=\"category\">Category:</label><br>\n");
      out.write("        <input type=\"text\" id=\"Category\" name=\"Category\" required><br><br>\n");
      out.write("\n");
      out.write("        <input type=\"submit\" value=\"Add Book\">\n");
      out.write("    </form>\n");
      out.write("    <br>\n");
      out.write("    <a href=\"HomePage.jsp\">Back to Home</a>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

package org.apache.jsp.WEB_002dINF;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public final class LibraryServlet_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    <title>Library Actions Result</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <h2>Library Actions Result</h2>\n");
      out.write("\n");
      out.write("    ");

        String action = request.getParameter("action");
        if ("view".equals(action)) {
            String bookName = request.getParameter("BookName");
            Connection conn = null;
            PreparedStatement pst = null;
            ResultSet rs = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
                pst = conn.prepareStatement("SELECT * FROM books WHERE BookName = ?");
                pst.setString(1, bookName);
                rs = pst.executeQuery();

                boolean found = false;
                while (rs.next()) {
                    found = true;
                    String bookId = rs.getString("BookId");
                    String name = rs.getString("BookName");
                    String author = rs.getString("AuthorName");
                    String category = rs.getString("Category");

                    out.println("<p>Book ID: " + bookId + "</p>");
                    out.println("<p>Book Name: " + name + "</p>");
                    out.println("<p>Author: " + author + "</p>");
                    out.println("<p>Category: " + category + "</p>");
                }

                if (!found) {
                    out.println("<p>No books found with the name: " + bookName + "</p>");
                }

            } catch (Exception e) {
                out.println("<p>Error: " + e.getMessage() + "</p>");
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pst != null) pst.close();
                    if (conn != null) conn.close();
                } catch (Exception e) {
                    out.println("<p>Error closing resources: " + e.getMessage() + "</p>");
                }
            }
        } else if ("delete".equals(action)) {
            String bookName = request.getParameter("bookname");

            Connection conn = null;
            PreparedStatement pst = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
                pst = conn.prepareStatement("DELETE FROM books WHERE BookName = ?");
                pst.setString(1, bookName);
                int rows = pst.executeUpdate();

                if (rows > 0) {
                    out.println("<p>Book deleted successfully: " + bookName + "</p>");
                } else {
                    out.println("<p>No book found with the name: " + bookName + "</p>");
                }

            } catch (Exception e) {
                out.println("<p>Error: " + e.getMessage() + "</p>");
            } finally {
                try {
                    if (pst != null) pst.close();
                    if (conn != null) conn.close();
                } catch (Exception e) {
                    out.println("<p>Error closing resources: " + e.getMessage() + "</p>");
                }
            }
        } else {
            out.println("<p>Invalid action specified.</p>");
        }
    
      out.write("\n");
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

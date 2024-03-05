package p1;

import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ValidAdmin
 */
@WebServlet("/ValidAdmin")
public class ValidAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		out.println("loaded driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CollegeManagement","root","tiger");
		out.println("connection created");
		String name=request.getParameter("Admin");
		int pwd=Integer.parseInt(request.getParameter("t2"));
		Statement st=con.createStatement();		
		String q1="select * from Admin where username='"+name+"'AND password="+pwd;
		ResultSet rs=st.executeQuery(q1);
		while(rs.next())
		{
			response.sendRedirect("AdminIndex.html");
		}
		
		}
		catch(Exception e)
		{
			out.println(e);
		}
		
		
	}

}

package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentDetails
 */
@WebServlet("/StudentDetails")
public class StudentDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentDetails() {
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
		Statement st=con.createStatement();
		Enumeration<String>p1=request.getParameterNames();	
		String id=p1.nextElement();
		if(id.equals("register"))
		{
			int stdid=Integer.parseInt(request.getParameter("register"));
			String name=request.getParameter("t2");
			int c=Integer.parseInt(request.getParameter("t3"));
			int java=Integer.parseInt(request.getParameter("t4"));
			int python=Integer.parseInt(request.getParameter("t5"));
			String q1="insert into Student values("+stdid+",'"+name+"',"+c+","+java+","+python+")";
			st.execute(q1);
			out.println("record inserted");
		}
		else if(id.equals("update"))
		{
			int stdid=Integer.parseInt(request.getParameter("update"));
			String subname=request.getParameter("t2");
			int marks=Integer.parseInt(request.getParameter("t3"));
			String q1="update Student set "+subname+"="+marks+" where id="+stdid+" ";
			st.executeUpdate(q1);
			out.println("updated successfully");
		}
		else if(id.equals("delete"))
		{
			int stdid=Integer.parseInt(request.getParameter("delete"));
			String q1="delete from Student where id="+stdid;
			st.execute(q1);
		}
		else if(id.equals("Student"))
		{
			int stdid=Integer.parseInt(request.getParameter("Student"));
			String q1="select * from Student where id="+stdid;
			ResultSet rs=st.executeQuery(q1);
			response.setContentType("text/html");
			while(rs.next())
			{
				out.println("<html><body align='conter' bgcolor='yellow'>"); 

				out.println("Student id is"+rs.getInt(1)+"+<br>");
				out.println("Student name is"+rs.getString(2)+"+<br>");
				

				out.println("<table border='1' align='center'>");

				out.println("<tr><th>subname</th><th>Obtained Marks</th><th>Grade</th></tr>");

				out.println("<tr>"); out.println("<td>"+"C language"+"</td>");

				out.println("<td>"+rs.getInt(3)+"</td>"); out.println("<td>"+Grade.gradePrint (rs.getInt(3))+"</td>");

				out.println("</tr><tr>");

				out.println("<td>"+"java"+"</td>");

				out.println("<td>"+rs.getInt (4)+"</td>");

				out.println("<td>"+Grade.gradePrint(rs.getInt(4))+"</td>");

				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td>"+"python"+"</td>");
				out.println("<td>"+rs.getInt (5)+"</td>");
				out.println("<td>"+Grade.gradePrint(rs.getInt(5))+"</td>");
				out.println("</tr>");
				out.println("<table>");

				out.println("</body></html>");
			}
			
		}

		}
		catch(Exception e)
		{
			out.println(e);
		}
	}

}

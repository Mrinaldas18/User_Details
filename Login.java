import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pwd=request.getParameter("password");
		String uname=request.getParameter("name");
		String cnfm=request.getParameter("cnfm");
		PrintWriter out=response.getWriter();
		
		if(pwd.equals(cnfm))
		{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/userdetails","root","2341");
				String query="select * from login where name=? and password=?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, uname);
				ps.setString(2, pwd);
				
				ResultSet rs= ps.executeQuery();
				//System.out.println(rs.next());
				
				if(rs.next())
				{
					out.println("Login Successfull");
					response.sendRedirect("Success.html");
				}
				else
				{
					response.sendRedirect("Registration.html");
				}
			} 
			catch (ClassNotFoundException e) {
				out.println("Exception"+e);
			} 
			catch (SQLException e) {
				out.println("Exception"+e);
			}
		}
	}
}

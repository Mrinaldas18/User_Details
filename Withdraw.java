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

@WebServlet("/Withdraw")
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String name = request.getParameter("uname");
		int Acnum= Integer.parseInt(request.getParameter("accountNumber"));
		int Amount = Integer.parseInt(request.getParameter("amount"));
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection com = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdetails","root","2341");
			
			String query = "update createservlet set balance=balance-? where num=?";
			
			PreparedStatement ps = com.prepareStatement(query);
			ps.setInt(1, Amount);
			//ps.setString(2, name);
			ps.setInt(2, Acnum);
			
			int count = ps.executeUpdate();
			if (count <= Amount) {
			if(count > 0) {
				out.println("withdraw Successful");
			}
			else {
				out.println("withdraw unsuccessful");
			}
			}
			else {
				out.println("Insufficient Funds");
			}
		}x
			catch(Exception e) {
				out.println("<h1> Exception:- "+e.getMessage()+"</h1>");
			}
		}
	}



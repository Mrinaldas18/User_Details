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

@WebServlet("/Create")
public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname=request.getParameter("uname");
		int num=Integer.parseInt(request.getParameter("acc"));
		int amt=Integer.parseInt(request.getParameter("amt"));
		
		PrintWriter out=response.getWriter();	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/userdetails","root","2341");
			String query = "Insert into createservlet values(?,?,?)";
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1,num);
			ps.setString(2, uname);
			ps.setInt(3, amt);
			
			int count=ps.executeUpdate();
			
			if(count>0) {
				out.println("<h1>Created</h1>");
			}else {
				out.println("<h1> Invlaid Account Number</h1>");
		}
	}
		catch(Exception e) {
		out.println("<h1> Exception:- "+e.getMessage()+"</h1>");
		}
	}

}

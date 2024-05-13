import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Display extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname = request.getParameter("uname");
		int acc = Integer.parseInt(request.getParameter("acc"));
		
		PrintWriter out =response.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdetails","root","2341");
			
			String query="Select * from createServlet where name=? and num=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,uname);
			ps.setInt(2,acc);

			response.setContentType("text/html");
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
                // Assuming the table has columns named 'column1', 'column2', etc.
                int column1 = rs.getInt("num");
                String column2 = rs.getString("name");
                int column3= rs.getInt("Balance");
                // Display the data
                out.println("<h3>Account no:" + column1 + "</h3><br><h3>Name: " + column2 + "</h3><br><h3>Balance: "+ column3+"</h3>");
            }
	}
		catch(Exception e) {
		out.println("<h1> Exception:- "+e.getMessage()+"</h1>");
	}
		}
	}

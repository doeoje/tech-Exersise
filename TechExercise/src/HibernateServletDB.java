
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.Month;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Item;
import util.UtilDB;

/**
 * Servlet implementation class HibernateServletDB
 */
@WebServlet("/HibernateServletDB")
public class HibernateServletDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static int id;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HibernateServletDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String temp = request.getParameter("month");
		String yearString = request.getParameter("year");
		int month = temp == null?0 : Integer.parseInt(temp);
		int year = yearString == null?0 : Integer.parseInt(yearString);
		retrieveDisplayData(response.getWriter(), month, year);
	}

	private void retrieveDisplayData(PrintWriter out, int month, int year) {
		// TODO Auto-generated method stub
		String title = "Database Result";
		
		String markPaidleft = "<form action=\"TogglePaid\" method=\"POST\">" +
		"<input type=\"hidden\" id=\"this\" name=\"this\" value=\"";
		String markPaidright= "\"><input type=\"submit\" value=\"TogglePaid\" /></form>";
		
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
				"transitional//en\">\n"; //
		out.println(docType + //
				"<html>\n" + //
				"<head><title>" + title + "</title></head>\n" + //
				"<body bgcolor=\"#f0f0f0\">\n" + //
				"<h1 align=\"center\">" + title + "</h1>\n");
		out.println("<ul>");
		List<Item> listItems = UtilDB.listItem(month, year);
		if(!listItems.isEmpty()) {
			out.println("<h1>" + Month.of(month+1) + "/" + (year + 1900) + "</h1>");
			for (Item item : listItems) {
				String markPaid = markPaidleft + item.getId() + markPaidright;
				String paymentStatus = item.getPaid()?"Paid":"Not Paid";
				out.println("<li>" + item.getItemname() +",\t\tCost:$" + 
				item.getCost() + "\t\t"+ paymentStatus + markPaid +"</li>");
			}
			out.println("<br><br><br>");
		}
		
		
		out.println("<li>" + "<a href=/TechExercise/select_month.html>Select Month</a>" + "</li>");
		out.println("<li>" + "<a href=/TechExercise/add_item.html>Add Item</a>" + "</li>");
		

		out.println("</ul>");
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void change(Item item)
	{
		item.setPaid(true);
	}

}

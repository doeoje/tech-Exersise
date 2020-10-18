
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * Servlet implementation class AddItem
 */
@WebServlet("/AddItem")
public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddItem() {
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
		PrintWriter out = response.getWriter();
		String title = "Database Result";

		String markPaid = "";

		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
				"transitional//en\">\n"; //
		out.println(docType + //
				"<html>\n" + //
				"<head><title>" + title + "</title></head>\n" + //
				"<body bgcolor=\"#f0f0f0\">\n" + //
				"<h1 align=\"center\">" + title + "</h1>\n");
		if (request.getParameter("itemname") == null || request.getParameter("cost") == null
				|| request.getParameter("month") == null || request.getParameter("itemname") == ""
				|| request.getParameter("cost") == "" || request.getParameter("month") == "") {
			out.println("<h1>Item could not be Added</h1>");
		} else {
			String itemname = request.getParameter("itemname");
			Double cost = Double.parseDouble(request.getParameter("cost"));

			Date date;
			int month = Integer.parseInt(request.getParameter("month"));
			int year = Integer.parseInt(request.getParameter("year"));
			date = new Date(year, month, 28);
			UtilDB.createItems(itemname, cost, date);
			out.println("<h1>Item Added</h1>");

		}
		out.println("<ul>");

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

}

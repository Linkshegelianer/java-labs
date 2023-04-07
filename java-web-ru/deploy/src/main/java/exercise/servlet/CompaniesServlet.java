package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    private List<String> companies = getCompanies();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException {

        String searchQuery = request.getParameter("search");

        if (searchQuery != null) { // filter all companies to find needed
            List<String> result = companies.stream()
                    .filter(company -> company.contains(searchQuery)).collect(Collectors.toList());

        if (result.isEmpty()) {
            response.getWriter().write("Companies not found");
        } else {
            PrintWriter out = response.getWriter(); // print founded companies
            for (String company : result) {
                out.write(company + "\n");
            }
            out.close();
        }

    } else {
            PrintWriter out = response.getWriter(); // if the query string is empty print all companies
            for (String company : companies) {
                out.write(company + "\n");
            }
            out.close();
        }
        // END
    }
}

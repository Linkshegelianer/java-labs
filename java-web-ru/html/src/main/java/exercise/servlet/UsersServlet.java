package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.*; // added
import java.util.Collections; // added

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference; // added
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        // path information is the part of the URL that comes after the servlet path and before any query parameters
        // example - in URL "http://example.com/users/123" path is "/123"
        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response); // if there is no path, the user is requesting the default page
            return; // in this case, display a list of all users
        }

        String[] pathParts = pathInfo.split("/"); // split path like "/users/id"
        String id = ArrayUtils.get(pathParts, 1, ""); // get ID of the user
        // if the id is absent whop default page

        showUser(request, response, id); // show user according to the request
    }

    private List<Map<String, String>> getUsers() throws JsonProcessingException, IOException {
        final String FILE_PATH = "./src/main/resources/users.json";
        ObjectMapper mapper = new ObjectMapper(); // objectmapper for deserializing the JSON content from the users.json

        try (Stream<String> stream = Files.lines(Path.of(FILE_PATH))) {
            String content = stream.collect(Collectors.joining()); // read file containing users and store it in content variable
            return mapper.readValue(content, new TypeReference<List<Map<String, String>>>() {
            });
            /*
            TypeReference object tells the mapper what type to deserialize the JSON into.
            In this case, the TypeReference is an anonymous inner class that specifies that the JSON should be deserialized
            into a List<Map<String, String>>
            */
        } catch (IOException e) {
            System.out.println("Error while reading file!");
            return Collections.emptyList();
        }
    }

    private void showUsers(HttpServletRequest request,
                           HttpServletResponse response)
            throws IOException {

        List<Map<String, String>> users = getUsers();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");

        StringBuilder body = new StringBuilder();
        body.append("""
                <!DOCTYPE html>
                <html lang="ru">
                    <head>
                        <meta charset="UTF-8">
                        <title>Application</title>
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" 
                        rel="stylesheet" 
                        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
                        crossorigin="anonymous">
                    </head>
                    <body>
                    <div class="container">
                    <table>
                        <tr>
                            <th>id</th>
                            <th>Full name</th>
                            <th>Email</th>
                        </tr>
                """);

        for (var user : users) {
            body.append("<tr>"
                    + "<td>" + user.get("id") + "</td>"
                    + "<td>" + "<a jref=\"users/" + user.get("id") + "\">"
                    + user.get("firstName") + " " + user.get("lastName") + "</a></td>"
                    + "<td>" + user.get("email") + "</td>"
                    + "</tr>");
        }

        body.append("""
                </table>
                </div>
                </body>
                </html>
                """);
        out.println(body);
    }

    private void showUser(HttpServletRequest request,
                          HttpServletResponse response,
                          String id)
            throws IOException {

        List<Map<String, String>> users = getUsers();

        boolean found = false;
        for (Map<String, String> user : users) {
            if (id.equals(user.get("id"))) {
                PrintWriter out = response.getWriter();
                response.setContentType("text/html;charset=UTF-8");
                StringBuilder body = new StringBuilder();
                body.append("""
                        <!DOCTYPE html>
                        <html lang="ru">
                            <head>
                                <meta charset="UTF-8">
                                <title>Application</title>
                                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" 
                                rel="stylesheet" 
                                integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
                                crossorigin="anonymous">
                            </head>
                            <body>
                            <div class="container">
                            <table>
                                <tr>
                                    <th>id</th>
                                    <th>Full name</th>
                                    <th>Email</th>
                                </tr>
                                """
                        + "<tr>"
                        + "<td>" + user.get("id") + "</td>"
                        + "<td>" + user.get("firstName") + " " + user.get("lastName") + "</td>"
                        + "<td>" + user.get("email") + "</td>"
                        + "</tr>" +
                        """
                                </table>
                                </div>
                                </body>
                                </html>
                                """);
                out.println(body);
                found = true;
                break;
            }
        }

        if (!found) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            PrintWriter out = response.getWriter();
            String errorMessage = "Not Found\n";
            out.println(errorMessage.trim());
        }
    }
}
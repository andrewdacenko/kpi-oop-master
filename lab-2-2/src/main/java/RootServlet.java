import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class RootServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("index.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String postNumbersString = req.getParameter("numbers");

        ArrayList<Integer> numbers = new ArrayList<>();

        if (postNumbersString != null && !postNumbersString.isEmpty()) {
            postNumbersString = postNumbersString.replaceAll("[^0-9]+", ",");
            String[] postNumbers = postNumbersString.split(",");

            for (String number : postNumbers) {
                if (!number.isEmpty()) {
                    numbers.add(Integer.parseInt(number));
                }
            }

            System.out.println(numbers);

            Collections.sort(numbers);
        }

        req.setAttribute("numbers", numbers);
        RequestDispatcher view = req.getRequestDispatcher("result.jsp");
        view.forward(req, resp);
    }
}

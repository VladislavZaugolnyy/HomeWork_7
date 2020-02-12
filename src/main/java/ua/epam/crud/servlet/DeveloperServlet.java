package ua.epam.crud.servlet;

import com.google.gson.Gson;
import ua.epam.crud.exceptions.UniqueException;
import ua.epam.crud.mapper.AccountMapper;
import ua.epam.crud.mapper.DeveloperMapper;
import ua.epam.crud.mapper.SkillMapper;
import ua.epam.crud.model.Developer;
import ua.epam.crud.repository.AccountRepository;
import ua.epam.crud.repository.jdbc.AccountRepositoryJdbcImpl;
import ua.epam.crud.repository.jdbc.DeveloperRepositoryJdbcImpl;
import ua.epam.crud.repository.jdbc.SkillRepositoryJdbcImpl;
import ua.epam.crud.service.DeveloperService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "DeveloperServlet", urlPatterns = "/api/v1/developer")
public class DeveloperServlet extends HttpServlet {
    private DeveloperService developerService;
    private Gson gson = new Gson();

    {
        try {
            AccountRepository accountRepository = new AccountRepositoryJdbcImpl(new AccountMapper());
            developerService = new DeveloperService(
                    new DeveloperRepositoryJdbcImpl(new DeveloperMapper(accountRepository),
                            new SkillRepositoryJdbcImpl(new SkillMapper()),
                            accountRepository));
        } catch (UniqueException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Developer developer = gson.fromJson(request.getReader(), Developer.class);
        developerService.save(developer);

        response.sendRedirect("/api/v1/developer");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Developer developer = gson.fromJson(request.getReader(), Developer.class);
        developerService.update(developer);

        response.sendRedirect("/api/v1/developer");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Developer developer = gson.fromJson(request.getReader(), Developer.class);
        developerService.delete(developer);

        response.sendRedirect("/api/v1/developer");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String id = request.getParameter("id");
        if (id == null) {
            List<Developer> developers = developerService.getAll();
            if (developers == null) {
                response.sendError(404);
            }
            else {
                for (Developer developer : developers) {
                    writer.println(gson.toJson(developer));
                }
            }
        }
        else {
            Long developerId = Long.parseLong(id);
            Developer developer = developerService.getById(developerId);
            writer.println(gson.toJson(developer));
        }

        writer.flush();
        writer.close();
    }
}

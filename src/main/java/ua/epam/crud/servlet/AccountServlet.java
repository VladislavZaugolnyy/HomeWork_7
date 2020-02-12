package ua.epam.crud.servlet;

import com.google.gson.Gson;
import ua.epam.crud.exceptions.UniqueException;
import ua.epam.crud.mapper.AccountMapper;
import ua.epam.crud.model.Account;
import ua.epam.crud.repository.jdbc.AccountRepositoryJdbcImpl;
import ua.epam.crud.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AccountServlet", urlPatterns = "/api/v1/account")
public class AccountServlet extends HttpServlet {
    private AccountService accountService;
    private Gson gson = new Gson();

    {
        try {
            accountService = new AccountService(new AccountRepositoryJdbcImpl(new AccountMapper()));
        } catch (UniqueException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = gson.fromJson(request.getReader(), Account.class);
        accountService.save(account);

        response.sendRedirect("/api/v1/account");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = gson.fromJson(request.getReader(), Account.class);
        accountService.delete(account);

        response.sendRedirect("/api/v1/account");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = gson.fromJson(request.getReader(), Account.class);
        accountService.update(account);

        response.sendRedirect("/api/v1/account");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        if (id == null) {
            List<Account> accounts = accountService.getAll();
            if (accounts == null) {
                response.sendError(404);
            } else {
                for (Account account : accounts) {
                    writer.println(gson.toJson(account));
                }
            }
        }
        else {
            Long accountId = Long.parseLong(id);
            Account account = accountService.getById(accountId);
            writer.println(gson.toJson(account));
        }

        writer.flush();
        writer.close();
    }
}

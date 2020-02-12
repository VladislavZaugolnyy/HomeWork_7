package ua.epam.crud.repository.jdbc;

import ua.epam.crud.exceptions.UniqueException;
import ua.epam.crud.mapper.Mapper;
import ua.epam.crud.model.Account;
import ua.epam.crud.repository.AccountRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountRepositoryJdbcImpl extends JdbcAbstractRepository<Account> implements AccountRepository {

    public AccountRepositoryJdbcImpl(Mapper<Account, ResultSet, PreparedStatement> mapper) throws UniqueException {
        super(AccountRepositoryJdbcImpl.class, mapper);
    }
}

package ua.epam.crud.mapper;

import ua.epam.crud.exceptions.UniqueException;
import ua.epam.crud.exceptions.WrongArgumentPersistentException;
import ua.epam.crud.model.Account;
import ua.epam.crud.model.Developer;
import ua.epam.crud.repository.AccountRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeveloperMapper implements Mapper<Developer, ResultSet, PreparedStatement> {
    private AccountRepository accountRepository;

    public DeveloperMapper(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Developer> map(ResultSet resultSet) throws UniqueException {
        if (resultSet == null) {
            throw new WrongArgumentPersistentException(" Result Set can't be null");
        }

        List<Developer> result = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Long accountId = resultSet.getLong("account");
                Account account = accountRepository.getById(accountId);
                result.add(new Developer(id, name, account, null));
            }
        } catch (SQLException e) {
            throw new UniqueException(e);
        }
        return result;
    }

    @Override
    public void map(Developer developer, PreparedStatement preparedStatement) throws UniqueException {
        if (developer == null || preparedStatement == null) {
            throw new WrongArgumentPersistentException("Developer and/or Prepared Statement can't be  null");
        }
        Long developerId = developer.getId();
        String name = developer.getName();
        long accountStatus = developer.getAccount().getStatus().ordinal() + 1L;

        try {
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, accountStatus);
            if (developerId != null) {
                preparedStatement.setLong(3, developerId);
            }
        } catch (SQLException e) {
            throw new UniqueException(e);
        }
    }
}

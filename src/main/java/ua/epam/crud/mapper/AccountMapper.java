package ua.epam.crud.mapper;

import ua.epam.crud.exceptions.UniqueException;
import ua.epam.crud.exceptions.WrongArgumentPersistentException;
import ua.epam.crud.model.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountMapper implements Mapper<Account, ResultSet, PreparedStatement> {
    @Override
    public List<Account> map(ResultSet resultSet) throws UniqueException {
        if (resultSet == null) {
            throw new WrongArgumentPersistentException(" Result Set can't be null");
        }

        List<Account> result = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String data = resultSet.getString("data");
                String status = resultSet.getString("account_status.status").toUpperCase();
                Account.AccountStatus accountStatus = Account.AccountStatus.valueOf(status);
                result.add(new Account(id, data, accountStatus));
            }
        } catch (SQLException e) {
            throw new UniqueException(e);
        }
        return result;
    }

    @Override
    public void map(Account account, PreparedStatement preparedStatement) throws UniqueException {
        if (account == null || preparedStatement == null) {
            throw new WrongArgumentPersistentException("Account and/or Prepared Statement can't be null");
        }

        Long id = account.getId();
        String data = account.getData();
        long statusId = account.getStatus().ordinal() + 1L;

        try {
            preparedStatement.setString(1, data);
            preparedStatement.setLong(2, statusId);
            if (id != null) {
                preparedStatement.setLong(3, id);
            }
        } catch (SQLException e) {
            throw new UniqueException(e);
        }
    }
}

package ua.epam.crud.controller;

import ua.epam.crud.exceptions.DoesNotExistException;
import ua.epam.crud.messages.Messages;
import ua.epam.crud.model.Account;
import ua.epam.crud.repository.AccountRepository;
import ua.epam.crud.repository.io.AccountRepositoryImpl;

import java.util.List;

public class AccountController {
    private AccountRepository accountRepository = new AccountRepositoryImpl();

    public String getAll() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Account> accounts = accountRepository.getAll();

        for (Account account : accounts) {
            stringBuilder.append(account);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public String getById(long id) {
        Account account = accountRepository.getById(id);
        if (account == null) {
            return Messages.ACCOUNT_NOT_FOUND.getMessage();
        } else {
            return account.toString();
        }
    }

    public String addNewAccount(String data) {
        long id = accountRepository.getLastId() + 1;
        Account account = new Account(id, data, AccountStatus.ACTIVE);
        List<Account> accounts = accountRepository.getAll();
        if (accounts.contains(account)) {
            return Messages.ACCOUNT_ALREADY_EXISTS.getMessage();
        } else {
            accountRepository.create(account);
            return Messages.ACCOUNT_ADDED.getMessage();
        }
    }

    public String update(long input, String email) {
        Account account = new Account(input, email, AccountStatus.ACTIVE);
        try {
            accountRepository.update(account);
            return Messages.SUCCESS.getMessage();
        } catch (DoesNotExistException e) {
            e.printStackTrace();
            return Messages.FAIL.getMessage();
        }
    }

    public String disable(long id) {
        Account account = accountRepository.getById(id);
        if (account == null) {
            return Messages.ACCOUNT_NOT_FOUND.getMessage();
        } else if (account.getStatus() == AccountStatus.NOT_ACTIVE) {
            return Messages.ACCOUNT_ALREADY_DISABLED.getMessage();
        } else {
            account.setStatus(AccountStatus.NOT_ACTIVE);
            try {
                accountRepository.update(account);
                return Messages.SUCCESS.getMessage();
            } catch (DoesNotExistException e) {
                e.printStackTrace();
                return Messages.FAIL.getMessage();
            }
        }
    }
}

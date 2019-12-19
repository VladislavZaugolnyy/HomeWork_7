package ua.epam.task_7.repository.io;

import ua.epam.task_7.exceptions.FileProcessingException;
import ua.epam.task_7.exceptions.DoesNotExistException;
import ua.epam.task_7.model.Account;
import ua.epam.task_7.model.AccountStatus;
import ua.epam.task_7.repository.AccountRepository;
import ua.epam.task_7.util.FileProcessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AccountRepositoryImpl implements AccountRepository {
    private FileProcessor fileProcessor = new FileProcessor("src\\main\\resources\\Account.txt");

    @Override
    public Account getById(Long id) {
        Account account = null;
        try {
            for (String string : fileProcessor.readFile()) {
                if (string.startsWith("id=" + id)) {
                    account = deserialize(string);
                    break;
                }
            }
        } catch (FileProcessingException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>();
        try {
            for (String string : fileProcessor.readFile()) {
                Account account = deserialize(string);
                if (account.getId() != null && account.getStatus() != null && account.getEmail() != null) {
                    accounts.add(account);
                }
            }
        } catch (FileProcessingException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public void create(Account account) {
        List<Account> accounts = getAll();
        accounts.add(account);
        serialize(accounts);
    }

    @Override
    public void delete(Account account) {
        List<Account> accounts = getAll();
        if (accounts.remove(account)) {
            serialize(accounts);
        }
    }

    @Override
    public void update(Account account) throws DoesNotExistException {
        List<Account> accounts = getAll();
        boolean updated = accounts.removeIf(acc -> acc.getId().equals(account.getId()));
        if (!updated) {
            throw new DoesNotExistException();
        }
        accounts.add(account);
        serialize(accounts);
    }

    private String accountToString(Account account) {
        return "id=" + account.getId() + "/data=" + account.getEmail() + "/status=" + account.getStatus();
    }

    private void serialize(Collection<Account> collection) {
        List<String> serialized = collection.stream().map(this::accountToString).collect(Collectors.toList());
        try {
            fileProcessor.writeToFile(serialized);
        } catch (FileProcessingException e) {
            e.printStackTrace();
        }
    }

    private Account deserialize(String string) {
        Long id = null;
        String data = null;
        AccountStatus status = null;

        String[] parts = string.split("/");
        for (String part : parts) {
            if (part.startsWith("id=")) {
                id = Long.parseLong(part.substring(3));
            }
            if (part.startsWith("data=")) {
                data = part.substring(5);
            }
            if (part.startsWith("status=")) {
                status = AccountStatus.valueOf(part.substring(7));
            }
        }
        return new Account(id, data, status);
    }
}
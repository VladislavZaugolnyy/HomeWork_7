package ua.epam.crud.repository.jdbc;

import ua.epam.crud.exceptions.UniqueException;

import java.util.HashMap;
import java.util.Map;

public class QueryHandlerStorage {
    private static Map<Class, QueryHandler> storage = new HashMap<>();

    static {
        storage.put(
                AccountRepositoryJdbcImpl.class,
                new QueryHandler() {
                    @Override
                    public String getSelectAllQuery() {
                        return "SELECT accounts.id, accounts.data, account_status.status \n" +
                                "FROM accounts JOIN account_status ON accounts.status = account_status.id";
                    }

                    @Override
                    public String getSelectByIdQuery() {
                        return getSelectAllQuery() + " WHERE accounts.id = ?";
                    }

                    @Override
                    public String getInsertQuery() {
                        return "INSERT INTO accounts (data, status) VALUES (?, ?)";
                    }

                    @Override
                    public String getDeleteQuery() {
                        return "DELETE FROM accounts WHERE id = ?";
                    }

                    @Override
                    public String getUpdateQuery() {
                        return "UPDATE accounts SET data = ?, status = ? WHERE id = ?";
                    }

                    @Override
                    public String getLastIdQuery() {
                        return "SELECT MAX(id) FROM accounts";
                    }
                });

        storage.put(
                SkillRepositoryJdbcImpl.class,
                new QueryHandler() {
                    @Override
                    public String getSelectAllQuery() {
                        return "SELECT * FROM skills";
                    }

                    @Override
                    public String getSelectByIdQuery() {
                        return getSelectAllQuery() + " WHERE id = ?";
                    }

                    @Override
                    public String getInsertQuery() {
                        return "INSERT INTO skills (name) VALUES (?)";
                    }

                    @Override
                    public String getDeleteQuery() {
                        return "DELETE FROM skills WHERE id = ?";
                    }

                    @Override
                    public String getUpdateQuery() {
                        return "UPDATE skills SET name = ? WHERE id = ?";
                    }

                    @Override
                    public String getLastIdQuery() {
                        return "SELECT MAX(id) FROM skills";
                    }
                });

        storage.put(
                DeveloperRepositoryJdbcImpl.class,
                new QueryHandler() {
                    @Override
                    public String getSelectAllQuery() {
                        return "SELECT * FROM developers";
                    }

                    @Override
                    public String getSelectByIdQuery() {
                        return getSelectAllQuery() + " WHERE id = ?";
                    }

                    @Override
                    public String getInsertQuery() {
                        return "INSERT INTO developers (name, account) VALUES (?, ?)";
                    }

                    @Override
                    public String getDeleteQuery() {
                        return "DELETE FROM developers WHERE id = ?";
                    }

                    @Override
                    public String getUpdateQuery() {
                        return "UPDATE developers SET name = ?, account = ? WHERE ID = ?";
                    }

                    @Override
                    public String getLastIdQuery() {
                        return "SELECT MAX(id) FROM developers";
                    }
                }
        );
    }

    public static QueryHandler getQueryHandler(Class clazz) throws UniqueException {
        QueryHandler result = storage.get(clazz);
        if (result == null) {
            throw new UniqueException("QueryHandler not found for class " + clazz.getName());
        }
        return result;
    }
}

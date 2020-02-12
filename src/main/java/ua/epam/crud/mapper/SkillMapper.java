package ua.epam.crud.mapper;

import ua.epam.crud.exceptions.UniqueException;
import ua.epam.crud.exceptions.WrongArgumentPersistentException;
import ua.epam.crud.model.Skill;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkillMapper implements Mapper<Skill, ResultSet, PreparedStatement> {
    @Override
    public List<Skill> map(ResultSet resultSet) throws UniqueException {
        if (resultSet == null) {
            throw new WrongArgumentPersistentException(" resultSet can't be null");
        }

        List<Skill> result = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                result.add(new Skill(id, name));
            }
        } catch (SQLException e) {
            throw new UniqueException(e);
        }
        return result;
    }

    @Override
    public void map(Skill skill, PreparedStatement preparedStatement) throws UniqueException {
        if (skill == null || preparedStatement == null) {
            throw new WrongArgumentPersistentException("Skill and/or prepared statement can't be null");
        }

        Long id = skill.getId();
        String name = skill.getName();

        try {
            preparedStatement.setString(1, name);
            if (id != null) {
                preparedStatement.setLong(2, id);
            }
        } catch (SQLException e) {
            throw new UniqueException(e);
        }
    }
}

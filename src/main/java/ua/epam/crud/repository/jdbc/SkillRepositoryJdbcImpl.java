package ua.epam.crud.repository.jdbc;

import ua.epam.crud.exceptions.UniqueException;
import ua.epam.crud.mapper.Mapper;
import ua.epam.crud.model.Skill;
import ua.epam.crud.repository.SkillRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SkillRepositoryJdbcImpl extends JdbcAbstractRepository<Skill> implements SkillRepository {

    public SkillRepositoryJdbcImpl(Mapper<Skill, ResultSet, PreparedStatement> mapper) throws UniqueException {
        super(SkillRepositoryJdbcImpl.class, mapper);
    }
}

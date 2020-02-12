package ua.epam.crud.repository.jdbc;

import ua.epam.crud.exceptions.UniqueException;
import ua.epam.crud.mapper.Mapper;
import ua.epam.crud.model.Developer;
import ua.epam.crud.model.Skill;
import ua.epam.crud.repository.AccountRepository;
import ua.epam.crud.repository.DeveloperRepository;
import ua.epam.crud.repository.SkillRepository;
import ua.epam.crud.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeveloperRepositoryJdbcImpl extends JdbcAbstractRepository<Developer> implements DeveloperRepository {
    private SkillRepository skillRepository;
    private AccountRepository accountRepository;
    private String selectSkillsQuery = "SELECT id FROM skills WHERE id IN (SELECT skill_id FROM developer_skills WHERE developer_id = ?)";
    private String deleteSkillsQuery = "DELETE FROM developer_skills WHERE developer_id = ?";
    private String insertSkillsQuery = "INSERT INTO developer_skills VALUES (?, ?)";

    public DeveloperRepositoryJdbcImpl(Mapper<Developer, ResultSet, PreparedStatement> mapper,
                                       SkillRepository skillRepository,
                                       AccountRepository accountRepository) throws UniqueException {
        super(DeveloperRepositoryJdbcImpl.class, mapper);
        this.skillRepository = skillRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Developer getById(Long id) throws UniqueException {
        Developer developer = super.getById(id);
        Set<Skill> developerSkills = getSkillsByDeveloperId(id);
        developer.setSkills(developerSkills);
        return developer;
    }

    @Override
    public List<Developer> getAll() throws UniqueException {
        List<Developer> developers = super.getAll();
        for (Developer developer : developers) {
            Set<Skill> skills = getSkillsByDeveloperId(developer.getId());
            developer.setSkills(skills);
        }
        return developers;
    }

    @Override
    public void save(Developer developer) throws UniqueException {
        super.save(developer);
        accountRepository.save(developer.getAccount());
        insertSkills(developer.getSkills(), developer.getId());
    }

    @Override
    public void delete(Developer developer) throws UniqueException {
        deleteSkillsByDeveloperId(developer.getId());
        super.delete(developer);
    }

    @Override
    public void update(Developer developer) throws UniqueException {
        super.update(developer);
        Long id = developer.getId();
        Set<Skill> skills = developer.getSkills();
        updateSkillsByDeveloperId(skills, id);
    }

    private Set<Skill> getSkillsByDeveloperId(Long developerId) throws UniqueException {
        Set<Skill> skills = new HashSet<>();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSkillsQuery)) {
            preparedStatement.setLong(1, developerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                skills.add(skillRepository.getById(resultSet.getLong(1)));
            }
        } catch (SQLException e) {
            throw new UniqueException(e);
        }
        return skills;
    }

    private void updateSkillsByDeveloperId(Set<Skill> skills, Long developerId) throws UniqueException {
        deleteSkillsByDeveloperId(developerId);
        insertSkills(skills, developerId);
    }

    private void deleteSkillsByDeveloperId(Long developerId) throws UniqueException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSkillsQuery)) {
            preparedStatement.setLong(1, developerId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new UniqueException(e);
        }
    }

    private void insertSkills(Set<Skill> skills, Long developerId) throws UniqueException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSkillsQuery)) {
            for (Skill skill : skills) {
                preparedStatement.setLong(1, developerId);
                preparedStatement.setLong(2, skill.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new UniqueException(e);
        }
    }
}

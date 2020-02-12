package ua.epam.crud.service;

import lombok.extern.log4j.Log4j;
import ua.epam.crud.exceptions.UniqueException;
import ua.epam.crud.model.Developer;
import ua.epam.crud.repository.DeveloperRepository;

import java.util.List;

@Log4j
public class DeveloperService {
    private DeveloperRepository developerRepository;

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public Developer getById(Long id) {
        try {
            return developerRepository.getById(id);
        } catch (UniqueException e) {
           // log.error("Receiving developer id=" + id, e);
        }
        return null;
    }

    public List<Developer> getAll() {
        try {
            return developerRepository.getAll();
        } catch (UniqueException e) {
            //log.error("Receiving all developers", e);
        }
        return null;
    }

    public void save(Developer developer) {
        try {
            developerRepository.save(developer);
        } catch (UniqueException e) {
            //log.error("Saving developer id=" + developer.getId(), e);
        }
    }

    public void delete(Developer developer) {
        try {
            developerRepository.delete(developer);
        } catch (UniqueException e) {
           // log.error("Deleting developer id=" + developer.getId(), e);
        }
    }

    public void update(Developer developer) {
        try {
            developerRepository.update(developer);
        } catch (UniqueException e) {
           // log.error("Updating developer id=" + developer.getId(), e);
        }
    }
}

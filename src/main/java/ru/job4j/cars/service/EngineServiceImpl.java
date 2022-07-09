package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.persistance.api.EngineStore;
import ru.job4j.cars.service.api.EngineService;

import java.util.List;

@Service
public class EngineServiceImpl implements EngineService {

    private final EngineStore engineStore;

    public EngineServiceImpl(EngineStore engineStore) {
        this.engineStore = engineStore;
    }

    @Override
    public Engine add(Engine engine) {
        return engineStore.add(engine);
    }

    @Override
    public boolean replace(long id, Engine engine) {
        return engineStore.replace(id, engine);
    }

    @Override
    public boolean delete(long id) {
        return engineStore.delete(id);
    }

    @Override
    public List<Engine> findAll() {
        return engineStore.findAll();
    }

    @Override
    public Engine findById(long id) {
        return engineStore.findById(id);
    }
}

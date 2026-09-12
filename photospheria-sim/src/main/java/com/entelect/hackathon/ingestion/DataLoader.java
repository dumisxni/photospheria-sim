package com.entelect.hackathon.ingestion;

import com.entelect.hackathon.models.staticdata.Plant;
import com.entelect.hackathon.models.staticdata.UnlockCondition;
import com.entelect.hackathon.models.staticdata.Animal;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class DataLoader {
    private final ObjectMapper mapper;

    public DataLoader() {
        this.mapper = new ObjectMapper();
    }

    public List<Plant> loadPlants() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("plant_dataset.json")) {
            if (inputStream == null) {
                throw new RuntimeException("Could not find plant_dataset.json in resources.");
            }
            return mapper.readValue(inputStream, new TypeReference<List<Plant>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to load plants", e);
        }
    }

    public List<UnlockCondition> loadUnlockConditions() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("unlock_conditions.json")) {
            if (inputStream == null) {
                throw new RuntimeException("Could not find unlock_conditions.json in resources.");
            }
            return mapper.readValue(inputStream, new TypeReference<List<UnlockCondition>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to load unlock conditions", e);
        }
    }

    public Map<String, List<String>> loadClassifications() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("classifications.json")) {
            if (inputStream == null) {
                throw new RuntimeException("Could not find classifications.json in resources.");
            }
            // Maps the JSON dictionary into a Java Map[cite: 1]
            return mapper.readValue(inputStream, new TypeReference<Map<String, List<String>>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to load classifications", e);
        }
    }

    public List<Animal> loadAnimals() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("animals.json")) {
            if (inputStream == null) {
                throw new RuntimeException("Could not find animals.json in resources.");
            }
            return mapper.readValue(inputStream, new TypeReference<List<Animal>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to load animals", e);
        }
    }
}
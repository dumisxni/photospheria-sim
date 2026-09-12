package com.entelect.hackathon.ingestion;

import com.entelect.hackathon.models.staticdata.Plant;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

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
}
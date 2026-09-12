package com.entelect.hackathon;

import com.entelect.hackathon.ingestion.DataLoader;
import com.entelect.hackathon.models.staticdata.Plant;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataLoader loader = new DataLoader();
        List<Plant> plants = loader.loadPlants();

        System.out.println("Successfully loaded " + plants.size() + " plants.");
        for (Plant p : plants) {
            System.out.println(p.getIndex() + ": " + p.getPlant() + " (Invasiveness: "
                    + p.getGrowth().getInvasivenessRank() + ")");
        }
    }
}
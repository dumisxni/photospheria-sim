package com.entelect.hackathon.engine;

import com.entelect.hackathon.models.state.Cell;
import com.entelect.hackathon.models.state.Grid;
import java.util.HashMap;
import java.util.Map;

public class ScoreCalculator {
    // Hidden scaling parameters from the rules[cite: 4]. Defaulting to 1.0 for
    // testing.
    private double alpha = 1.0;
    private double k = 1.0;

    private final int totalGameSpecies; // The N in the entropy formula[cite: 4]
    private final int totalTicks; // The T in the longevity formula[cite: 4]

    public ScoreCalculator(int totalGameSpecies, int totalTicks) {
        this.totalGameSpecies = totalGameSpecies;
        this.totalTicks = totalTicks;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double calculateFinalScore(Grid grid) {
        int C = 0;
        double Cmax = grid.getWidth() * grid.getHeight();
        Map<String, Integer> speciesCounts = new HashMap<>();
        double longevitySum = 0;

        // 1. Gather final tick data[cite: 4]
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                Cell cell = grid.getCell(x, y);
                if (cell.isOccupied()) {
                    C++;
                    String plantName = cell.getCurrentPlant().getSpecies().getPlant();
                    speciesCounts.put(plantName, speciesCounts.getOrDefault(plantName, 0) + 1);

                    // l_ij / T formula[cite: 4]
                    double lifespan = cell.getCurrentPlant().getAgeInTicks();
                    longevitySum += Math.pow(lifespan / (double) totalTicks, k);
                }
            }
        }

        if (C == 0)
            return 0.0; // Avoid division by zero if the board is completely dead

        // 2. Diversity Score (Entropy) - H[cite: 4]
        double H = 0.0;
        for (int count : speciesCounts.values()) {
            double p_i = (double) count / C;
            if (p_i > 0) {
                // Change of base formula: log_N(p_i) = ln(p_i) / ln(N)
                double logN_pi = Math.log(p_i) / Math.log(totalGameSpecies);
                H -= p_i * logN_pi;
            }
        }

        // 3. Sample Size Factor[cite: 4]
        double sampleSizeFactor = Math.pow(C / Cmax, alpha);

        // 4. Main Score[cite: 4]
        double mainScore = H * sampleSizeFactor;

        // 5. Secondary Longevity Score[cite: 4]
        double longevityScore = (1.0 / Cmax) * longevitySum;

        // 6. Final Score Weighting[cite: 4]
        return (0.8 * mainScore) + (0.2 * longevityScore);
    }
}
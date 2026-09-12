package com.entelect.hackathon.engine;

import com.entelect.hackathon.models.staticdata.Animal;
import com.entelect.hackathon.models.staticdata.AnimalCondition;
import com.entelect.hackathon.models.staticdata.AnimalRequirement;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class AnimalEvaluator {

    public static boolean isAnimalActive(Animal animal, GridAnalytics analytics) {
        AnimalRequirement req = animal.getRequirements();
        if (req == null || req.getConditions() == null)
            return false;

        return switch (req.getType().toUpperCase()) {
            case "AND" -> req.getConditions().stream().allMatch(c -> evaluateCondition(c, analytics));
            case "OR" -> req.getConditions().stream().anyMatch(c -> evaluateCondition(c, analytics));
            default -> false;
        };
    }

    private static boolean evaluateCondition(AnimalCondition condition, GridAnalytics analytics) {
        return switch (condition.getType()) {
            case "coverage" -> compare(getCoverageForSpeciesNode(condition.getSpecies(), analytics),
                    condition.getOperator(), condition.getThreshold());
            case "count" -> {
                if (condition.getSpeciesGroup() != null) {
                    yield compare((double) analytics.getGroupCount(condition.getSpeciesGroup()),
                            condition.getOperator(), condition.getThreshold());
                } else {
                    yield compare((double) getCountForSpeciesNode(condition.getSpecies(), analytics),
                            condition.getOperator(), condition.getThreshold());
                }
            }
            case "group_coverage" -> compare(analytics.getGroupCoverage(condition.getSpeciesGroup()),
                    condition.getOperator(), condition.getThreshold());
            case "dominance" -> compare(analytics.getHighestSpeciesDominance(), ">=", condition.getThreshold()); // Dominance
                                                                                                                 // implies
                                                                                                                 // single
                                                                                                                 // species
                                                                                                                 // taking
                                                                                                                 // over[cite:
                                                                                                                 // 5]
            default -> false;
        };
    }

    private static double getCoverageForSpeciesNode(JsonNode speciesNode, GridAnalytics analytics) {
        return extractSpeciesList(speciesNode).stream().mapToDouble(analytics::getPlantCoverage).sum();
    }

    private static int getCountForSpeciesNode(JsonNode speciesNode, GridAnalytics analytics) {
        return extractSpeciesList(speciesNode).stream().mapToInt(analytics::getPlantCount).sum();
    }

    private static List<String> extractSpeciesList(JsonNode node) {
        List<String> species = new ArrayList<>();
        if (node != null) {
            if (node.isArray()) {
                node.forEach(n -> species.add(n.asText()));
            } else {
                species.add(node.asText());
            }
        }
        return species;
    }

    private static boolean compare(double actual, String operator, double target) {
        if (operator == null)
            return actual >= target; // Default safe fallback
        return switch (operator) {
            case ">" -> actual > target;
            case ">=" -> actual >= target;
            case "<" -> actual < target;
            case "<=" -> actual <= target;
            case "==" -> actual == target;
            default -> false;
        };
    }
}
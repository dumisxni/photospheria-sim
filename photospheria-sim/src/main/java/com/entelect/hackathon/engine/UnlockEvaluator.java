package com.entelect.hackathon.engine;

import com.entelect.hackathon.models.staticdata.ConditionNode;
import java.util.Set;

public class UnlockEvaluator {

    public static boolean evaluate(ConditionNode node, GridAnalytics analytics, Set<String> activeEvents) {
        // 1. Process Logical Nodes (AND, OR, NOT)[cite: 3]
        if (node.getOp() != null) {
            return switch (node.getOp().toUpperCase()) {
                case "AND" -> node.getChildren().stream().allMatch(child -> evaluate(child, analytics, activeEvents));
                case "OR" -> node.getChildren().stream().anyMatch(child -> evaluate(child, analytics, activeEvents));
                case "NOT" -> !evaluate(node.getChild(), analytics, activeEvents);
                default -> false;
            };
        }

        // 2. Process Leaf Nodes[cite: 3]
        if (node.getType() != null) {
            return switch (node.getType()) {
                case "species_present" -> analytics.isSpeciesPresent(node.getSpecies());
                case "species_absent" -> !analytics.isSpeciesPresent(node.getSpecies());
                case "coverage" ->
                    compareValues(analytics.getPlantCoverage(node.getPlant()), node.getOperator(), node.getValue());
                case "count" -> compareValues((double) analytics.getPlantCount(node.getPlant()), node.getOperator(),
                        node.getValue());
                case "feature_count" -> compareValues((double) analytics.getFeatureCount(node.getFeature()),
                        node.getOperator(), node.getValue());
                case "event" -> activeEvents.contains(node.getEvent());
                default -> false;
            };
        }

        return false;
    }

    private static boolean compareValues(double actual, String operator, double target) {
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
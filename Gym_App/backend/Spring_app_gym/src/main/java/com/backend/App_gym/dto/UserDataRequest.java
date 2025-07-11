package com.backend.App_gym.dto;

import jakarta.validation.constraints.NotNull;

public class UserDataRequest {
    @NotNull
    private Long userId;

    private Integer size;
    private Integer weight;
    private String physicalActivity;
    private String daysTraining;
    private String healthProblems;
    private String preferenceSchedule;
    private String motivation;

    // Getters y Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getPhysicalActivity() {
        return physicalActivity;
    }

    public void setPhysicalActivity(String physicalActivity) {
        this.physicalActivity = physicalActivity;
    }

    public String getDaysTraining() {
        return daysTraining;
    }

    public void setDaysTraining(String daysTraining) {
        this.daysTraining = daysTraining;
    }

    public String getHealthProblems() {
        return healthProblems;
    }

    public void setHealthProblems(String healthProblems) {
        this.healthProblems = healthProblems;
    }

    public String getPreferenceSchedule() {
        return preferenceSchedule;
    }

    public void setPreferenceSchedule(String preferenceSchedule) {
        this.preferenceSchedule = preferenceSchedule;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }
}
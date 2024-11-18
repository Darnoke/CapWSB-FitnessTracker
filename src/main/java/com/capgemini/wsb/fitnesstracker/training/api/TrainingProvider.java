package com.capgemini.wsb.fitnesstracker.training.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    Optional<Training> getTraining(Long trainingId);

    List<Training> getAllTrainings();

    List<Training> getTrainingsByUserId(final Long userId);

    List<Training> getTrainingsByActivityType(final String activityType);

    List<Training> getTrainingsOlderThan(LocalDate date);

    Training updateTraining(Training training);
}

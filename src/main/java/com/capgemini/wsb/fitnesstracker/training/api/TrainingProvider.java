package com.capgemini.wsb.fitnesstracker.training.api;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    Optional<Training> getTraining(Long trainingId);

    List<Training> getAllTrainings();

    List<Training> getTrainingsByUserId(final Long userId);

    List<Training> getTrainingsByActivityType(final String activityType);

    List<Training> getTrainingsOlderThan(Date date);

    Training updateTraining(Training training);

    Training createTraining(TrainingDto trainingDto);

    Training createTraining(Training training);
}

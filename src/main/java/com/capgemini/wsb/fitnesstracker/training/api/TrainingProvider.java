package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import jakarta.validation.Valid;

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

    Training createTraining(@Valid TrainingRequestDto trainingRequestDto, User user);
}

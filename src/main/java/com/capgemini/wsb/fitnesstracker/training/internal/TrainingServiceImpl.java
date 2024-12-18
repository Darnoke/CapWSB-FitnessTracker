package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingRequestDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;

    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getTrainingsByUserId(final Long userId) {
        return trainingRepository.findByUserId(userId);
    }

    @Override
    public List<Training> getTrainingsByActivityType(final String activityType) { return trainingRepository.findByActivityType(activityType); }

    @Override
    public List<Training> getTrainingsOlderThan(Date time) { return trainingRepository.findByDateBefore(time); }

    @Override
    public Training updateTraining(final Training training) {
        log.info("Updating Training {}", training);
        if (training.getId() == null) {
            throw new IllegalArgumentException("Training has no DB ID, create is not permitted!");
        }
        return trainingRepository.save(training);
    }

    @Override
    public Training createTraining(TrainingRequestDto requestDto, User user) {
        Training training = Training.create();
        training.setUser(user);
        training.setStartTime(requestDto.getStartTime());
        training.setEndTime(requestDto.getEndTime());
        training.setActivityType(ActivityType.valueOf(requestDto.getActivityType()));
        training.setDistance(requestDto.getDistance());
        training.setAverageSpeed(requestDto.getAverageSpeed());

        return trainingRepository.save(training);
    }
}

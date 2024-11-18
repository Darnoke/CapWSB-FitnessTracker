package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TrainingMapper {

    public final UserMapper userMapper;

    public TrainingDto toDto(Training training) {
        return new TrainingDto(training.getId(),
                userMapper.toDto(training.getUser()),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed());
    }

    public Training toEntity(TrainingDto trainingDto) {
        return new Training(
                trainingDto.getId(),
                userMapper.toEntity(trainingDto.getUser()),
                trainingDto.getStartTime(),
                trainingDto.getEndTime(),
                trainingDto.getActivityType(),
                trainingDto.getDistance(),
                trainingDto.getAverageSpeed());
    }


    public Training toUpdateEntity(TrainingDto trainingDto, Training training) {
        training.setStartTime(trainingDto.getStartTime() != null ? trainingDto.getStartTime() : training.getStartTime());
        training.setEndTime(trainingDto.getEndTime() != null ? trainingDto.getEndTime() : training.getEndTime());
        training.setActivityType(trainingDto.getActivityType() != null ? trainingDto.getActivityType() : training.getActivityType());
        training.setDistance(trainingDto.getDistance() != null ? trainingDto.getDistance() : training.getDistance());
        training.setAverageSpeed(trainingDto.getAverageSpeed() != null ? trainingDto.getAverageSpeed() : training.getAverageSpeed());

        return training;
    }
}

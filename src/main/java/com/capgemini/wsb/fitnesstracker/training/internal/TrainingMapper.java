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
                userMapper.toEntity(trainingDto.user()),
                trainingDto.startTime(),
                trainingDto.endTime(),
                trainingDto.activityType(),
                trainingDto.distance(),
                trainingDto.averageSpeed());
    }

    public Training toUpdateEntity(TrainingDto trainingDto, Training training) {
        training.setStartTime(trainingDto.startTime() != null ? trainingDto.startTime() : training.getStartTime());
        training.setEndTime(trainingDto.endTime() != null ? trainingDto.endTime() : training.getEndTime());
        training.setActivityType(trainingDto.activityType() != null ? trainingDto.activityType() : training.getActivityType());
        training.setDistance(trainingDto.distance() != 0 ? trainingDto.distance() : training.getDistance());
        training.setAverageSpeed(trainingDto.averageSpeed() != 0 ? trainingDto.averageSpeed() : training.getAverageSpeed());

        return training;
    }
}

package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingRequestDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor

public class TrainingController {
    private final TrainingServiceImpl trainingService;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private final TrainingMapper trainingMapper;
    private final UserServiceImpl userService;

    @GetMapping()
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("{userId}")
    public List<TrainingDto> getTrainingsByUserId(@PathVariable Long userId) {
        return trainingService.getTrainingsByUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/activity/activityType")
    public ResponseEntity<List<TrainingDto>> getTrainingsByActivityType(@RequestParam String activityType) {
        List<Training> trainings = trainingService.getTrainingsByActivityType(activityType);
        List<TrainingDto> response = trainings.stream()
                .map(trainingMapper::toDto)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/finished/{dateString}")
    public List<TrainingDto> getTrainingsOlderThan(@PathVariable String dateString) throws ParseException {
        Date date = formatter.parse(dateString);
        return trainingService.getTrainingsOlderThan(date)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @PostMapping
    public ResponseEntity<TrainingDto> addTraining(@RequestBody @Valid TrainingRequestDto trainingRequestDto) {
        User user = userService.getUser(trainingRequestDto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Training training = trainingService.createTraining(trainingRequestDto, user);
        TrainingDto responseDto = trainingMapper.toDto(training);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }


    @PutMapping("/{id}")
    public Training updateTraining(@PathVariable Long id, @RequestBody TrainingDto trainingDto) {
        try {
            Training mappedTraining = trainingService.getTraining(id).orElseThrow();
            Training updatedTraining = trainingMapper.toUpdateEntity(trainingDto, mappedTraining);
            return trainingService.updateTraining(updatedTraining); } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

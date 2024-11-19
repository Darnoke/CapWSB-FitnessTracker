package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/activity/{activityType}")
    public List<TrainingDto> getTrainingsByActivityType(@PathVariable String activityType) {
        return trainingService.getTrainingsByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
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
    public ResponseEntity<Training> addTraining(@RequestBody Training trainingDto) throws InterruptedException {
        trainingService.createTraining(trainingMapper.toDto(trainingDto));
        return new ResponseEntity<>(null, HttpStatus.CREATED);
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

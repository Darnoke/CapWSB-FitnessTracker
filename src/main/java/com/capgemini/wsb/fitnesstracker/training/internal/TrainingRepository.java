package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface TrainingRepository extends JpaRepository<Training, Long> {
    default List<Training> findByUserId(Long userId) {
        return findAll().stream()
                .filter(training -> {
                    assert training.getUser().getId() != null;
                    return training.getUser().getId().equals(userId);
                })
                .toList();
    }
}

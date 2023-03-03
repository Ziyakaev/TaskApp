package com.hh.TaskApp.dao;

import com.hh.TaskApp.model.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskDao extends JpaRepository<Task, UUID> {

    @Transactional
    Task findTaskByIdAndDeletedIsFalse(UUID id);

    @Transactional
    List<Task> findAllByDeletedIsFalse();

}
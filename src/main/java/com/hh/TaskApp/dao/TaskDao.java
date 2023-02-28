package com.hh.TaskApp.dao;

import com.hh.TaskApp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskDao extends JpaRepository<Task, UUID> {

    public Task findTaskById(UUID id);

}
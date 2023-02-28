package com.hh.TaskApp.converter;

import com.hh.TaskApp.dto.TaskInfo;
import com.hh.TaskApp.model.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskConverter {
    private final ModelMapper modelMapper;

    public TaskConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public TaskInfo convertToDto(Task task) {
        return modelMapper.map(task, TaskInfo.class);
    }

    public Task convertFromDto(TaskInfo taskInfo) {
        return modelMapper.map(taskInfo, Task.class);
    }
}

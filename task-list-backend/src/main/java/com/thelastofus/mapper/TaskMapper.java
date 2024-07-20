package com.thelastofus.mapper;

import com.thelastofus.dto.task.TaskResponse;
import com.thelastofus.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface TaskMapper {

    TaskResponse convertToTaskResponse(Task task);

}

package com.backend.university.course.service;

import com.backend.university.course.action.CourseCreateAction;
import com.backend.university.course.action.CourseDeleteAction;
import com.backend.university.course.action.CourseGetAction;
import com.backend.university.course.action.CourseUpdateAction;
import com.backend.university.course.domain.Course;
import com.backend.university.course.dto.CourseInputDTO;
import com.backend.university.course.dto.CourseOutputDTO;
import com.backend.university.course.dto.CourseUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseGetAction getAction;

    private final CourseCreateAction createAction;

    private final CourseUpdateAction updateAction;

    private final CourseDeleteAction deleteAction;

    @Transactional(readOnly = true)
    public CourseOutputDTO findById(Long id) {
        return getAction.findById(id);
    }

    @Transactional(readOnly = true)
    public List<CourseOutputDTO> findAll() {
        return getAction.findAll();
    }

    @Transactional
    public Course findEntityByName(String name) {
        return getAction.findEntityByName(name);
    }

    @Transactional
    public CourseOutputDTO create(CourseInputDTO input) {
        return createAction.create(input);
    }

    @Transactional
    public CourseOutputDTO update(CourseUpdateDTO update) {
        return updateAction.update(update);
    }

    @Transactional
    public void delete(Long id) {
        deleteAction.delete(id);
    }

}

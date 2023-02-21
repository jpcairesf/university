package com.backend.university.coursesubject.action;

import com.backend.university.coursesubject.domain.CourseSubject;
import com.backend.university.coursesubject.dto.CourseSubjectOutputDTO;
import com.backend.university.coursesubject.dto.mapper.CourseSubjectMapper;
import com.backend.university.coursesubject.exception.CourseSubjectExceptionSupplier;
import com.backend.university.coursesubject.repository.CourseSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CourseSubjectGetAction {

    private final CourseSubjectRepository repository;

    @Transactional(readOnly = true)
    public CourseSubjectOutputDTO findById(Long id) {
        return CourseSubjectMapper.entityToOutput(this.findEntityById(id));
    }

    @Transactional(readOnly = true)
    public List<CourseSubjectOutputDTO> findAll() {
        return repository.findAll().stream()
                .map(CourseSubjectMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    private CourseSubject findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(CourseSubjectExceptionSupplier.notFoundById(id));
    }

}

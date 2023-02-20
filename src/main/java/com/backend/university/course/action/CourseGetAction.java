package com.backend.university.course.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.course.domain.Course;
import com.backend.university.course.dto.CourseOutputDTO;
import com.backend.university.course.dto.mapper.CourseMapper;
import com.backend.university.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class CourseGetAction {

    private final CourseRepository repository;

    @Transactional(readOnly = true)
    public CourseOutputDTO findById(Long id) {
        return CourseMapper.entityToOutput(this.findEntityById(id));
    }

    @Transactional(readOnly = true)
    public List<CourseOutputDTO> findAll() {
        return repository.findAll().stream()
                .map(CourseMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    private Course findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no course with ID \"%s\".", id)));
    }

}

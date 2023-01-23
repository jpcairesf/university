package com.backend.university.coursesubject.service;

import com.backend.university.common.error.BusinessException;
import com.backend.university.course.service.CourseService;
import com.backend.university.coursesubject.domain.CourseSubject;
import com.backend.university.coursesubject.dto.CourseSubjectInputDTO;
import com.backend.university.coursesubject.dto.CourseSubjectOutputDTO;
import com.backend.university.coursesubject.dto.mapper.CourseSubjectMapper;
import com.backend.university.coursesubject.repository.CourseSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseSubjectService {

    private final CourseSubjectRepository repository;

    private final CourseSubjectMapper mapper;

    private final CourseService courseService;

    @Transactional
    public List<CourseSubjectOutputDTO> createMany(List<CourseSubjectInputDTO> inputs) {
        long nameCounter = inputs.stream().map(CourseSubjectInputDTO::getCourse).distinct().count();
        if (nameCounter > 1L) {
            throw new BusinessException("More then one name found for course.");
        } else if (nameCounter == 0) {
            throw new BusinessException("No name defined for course.");
        }

        List<CourseSubject> entities = new ArrayList<>();
        List<CourseSubjectOutputDTO> outputs = new ArrayList<>();
        for (CourseSubjectInputDTO input : inputs) {
            CourseSubject courseSubject = mapper.inputToEntity(input);
            courseService.addSubject(courseSubject);
            entities.add(courseSubject);
            outputs.add(mapper.entityToOutput(courseSubject));
        }

        repository.saveAll(entities);
        return outputs;
    }

    @Transactional
    public CourseSubjectOutputDTO create(CourseSubjectInputDTO input) {
        CourseSubject courseSubject = mapper.inputToEntity(input);
        courseService.addSubject(courseSubject);
        repository.save(courseSubject);
        return mapper.entityToOutput(courseSubject);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }


    public CourseSubject findEntityByCourseNameAndSubjectCode(String courseName, String subjectCode) {
        return repository.findByCourseNameAndSubjectCode(courseName, subjectCode)
                .orElseThrow(() -> new BusinessException(format("There no subject with code \"%s\" in the course \"%s\".", subjectCode, courseName)));
    }

    public CourseSubject findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There no CourseSubject with id \"%s\".", id)));
    }

    public void validateExistsByCourseNameAndSubjectCode(String courseName, String subjectCode) {
        if (repository.existsByCourseNameAndSubjectCode(courseName, subjectCode)) {
            throw new BusinessException(format("There is already a subject with code \"%s\" in the course \"%s\".", subjectCode, courseName));
        }
    }

}

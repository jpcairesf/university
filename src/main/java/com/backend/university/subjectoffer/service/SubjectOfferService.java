package com.backend.university.subjectoffer.service;

import com.backend.university.subjectoffer.repository.SubjectOfferRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class SubjectOfferService {

    private SubjectOfferRepository repository;

    public Long findIdByCourseSubjectSemesterClass(String courseName, String subjectCode, int semester, int classNumber) {
        return repository.findIdByCourseSubjectSemesterClass(courseName, subjectCode, semester, classNumber)
                .orElseThrow(() -> new EntityNotFoundException(""));
    }
}

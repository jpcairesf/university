package com.backend.university.subjectoffer.action;

import com.backend.university.subjectoffer.domain.SubjectOffer;
import com.backend.university.subjectoffer.exception.SubjectOfferExceptionSupplier;
import com.backend.university.subjectoffer.repository.SubjectOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SubjectOfferGetAction {

    private final SubjectOfferRepository repository;

    public SubjectOffer findById(Long id) {
        return repository.findById(id)
                .orElseThrow(SubjectOfferExceptionSupplier.notFoundById(id));
    }

    public List<SubjectOffer> findAll() {
        return repository.findAll();
    }

}

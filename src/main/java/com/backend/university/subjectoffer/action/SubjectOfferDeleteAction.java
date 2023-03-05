package com.backend.university.subjectoffer.action;

import com.backend.university.subjectoffer.domain.SubjectOffer;
import com.backend.university.subjectoffer.exception.SubjectOfferExceptionSupplier;
import com.backend.university.subjectoffer.repository.SubjectOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubjectOfferDeleteAction {

    private final SubjectOfferRepository repository;

    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    private SubjectOffer findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(SubjectOfferExceptionSupplier.notFoundById(id));
    }

}

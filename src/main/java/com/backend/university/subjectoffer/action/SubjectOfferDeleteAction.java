package com.backend.university.subjectoffer.action;

import com.backend.university.subjectoffer.domain.SubjectOffer;
import com.backend.university.subjectoffer.repository.SubjectOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class SubjectOfferDeleteAction {

    private final SubjectOfferRepository repository;

    @Transactional
    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    private SubjectOffer findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("There is no subject offer with ID \"%s\".", id)));
    }

}

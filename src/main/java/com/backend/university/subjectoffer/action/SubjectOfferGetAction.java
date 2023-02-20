package com.backend.university.subjectoffer.action;

import com.backend.university.subjectoffer.domain.SubjectOffer;
import com.backend.university.subjectoffer.dto.SubjectOfferOutputDTO;
import com.backend.university.subjectoffer.dto.mapper.SubjectOfferMapper;
import com.backend.university.subjectoffer.repository.SubjectOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class SubjectOfferGetAction {

    private final SubjectOfferRepository repository;

    @Transactional(readOnly = true)
    public SubjectOfferOutputDTO findById(Long id) {
        SubjectOffer subjectOffer = this.findEntityById(id);
        return SubjectOfferMapper.entityToOutput(subjectOffer);
    }

    @Transactional(readOnly = true)
    public List<SubjectOfferOutputDTO> findAll() {
        return repository.findAll().stream()
                .map(SubjectOfferMapper::entityToOutput)
                .collect(Collectors.toList());
    }

    private SubjectOffer findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("There is no subject offer with ID \"%s\".", id)));
    }

}

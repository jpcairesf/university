package com.backend.university.secretary.action;

import com.backend.university.common.error.BusinessException;
import com.backend.university.secretary.domain.Secretary;
import com.backend.university.secretary.repository.SecretaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class SecretaryDeleteAction {

    private final SecretaryRepository repository;

    @Transactional
    public void delete(Long id) {
        repository.delete(this.findEntityById(id));
    }

    private Secretary findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(format("There is no secretary with ID \"%s\".", id)));
    }

}

package com.backend.university.institute.service;

import com.backend.university.institute.action.InstituteCreateAction;
import com.backend.university.institute.action.InstituteDeleteAction;
import com.backend.university.institute.action.InstituteGetAction;
import com.backend.university.institute.action.InstituteUpdateAction;
import com.backend.university.institute.domain.Institute;
import com.backend.university.institute.dto.InstituteInputDTO;
import com.backend.university.institute.dto.InstituteOutputDTO;
import com.backend.university.institute.dto.InstituteUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstituteService {

    private final InstituteGetAction getAction;

    private final InstituteCreateAction createAction;

    private final InstituteUpdateAction updateAction;

    private final InstituteDeleteAction deleteAction;

    @Transactional(readOnly = true)
    public InstituteOutputDTO findById(Long id) {
        return this.getAction.findById(id);
    }

    @Transactional(readOnly = true)
    public List<InstituteOutputDTO> findAll() {
        return this.getAction.findAll();
    }

    public Institute findEntityByName(String name) {
        return this.getAction.findEntityByName(name);
    }

    @Transactional
    public InstituteOutputDTO create(InstituteInputDTO input) {
        return this.createAction.create(input);
    }

    @Transactional
    public InstituteOutputDTO update(InstituteUpdateDTO update) {
        return this.updateAction.update(update);
    }

    @Transactional
    public void delete(Long id) {
        this.deleteAction.delete(id);
    }

}

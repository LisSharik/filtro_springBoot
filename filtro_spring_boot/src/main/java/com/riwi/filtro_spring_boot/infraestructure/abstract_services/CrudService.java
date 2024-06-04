package com.riwi.filtro_spring_boot.infraestructure.abstract_services;

import org.springframework.data.domain.Page;

public interface CrudService<RQ, RS, ID> {
    public Page<RS> getAll(int page, int size);

    public RS get(ID id);

    public RS create(RQ request);

}

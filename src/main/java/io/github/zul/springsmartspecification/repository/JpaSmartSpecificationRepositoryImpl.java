package io.github.zul.springsmartspecification.repository;

import jakarta.persistence.EntityManager;

public class JpaSmartSpecificationRepositoryImpl<D> extends DefaultJpaSmartSpecificationRepository<D> {

    public JpaSmartSpecificationRepositoryImpl(EntityManager em) {
        super(em);
    }

}

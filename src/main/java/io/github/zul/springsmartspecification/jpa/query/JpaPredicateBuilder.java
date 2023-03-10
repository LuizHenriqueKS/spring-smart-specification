package io.github.zul.springsmartspecification.jpa.query;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;

import io.github.zul.springsmartspecification.specification.SmartSpecification;

public class JpaPredicateBuilder {

    public static Expression<Boolean> build(JpaPathRouter router, SmartSpecification<?> specification,
            CriteriaQuery<?> cq, CriteriaBuilder cb) {
        JpaSpecificationMeta meta = JpaSpecificationMetaFactory.get(specification);
        Predicate result = cb.and();
        for (JpaSpecificationField field : meta.getFields()) {
            if (field.isValidValue(specification)) {
                JpaSpecificationArgs args = JpaSpecificationArgs.builder()
                        .specification(specification)
                        .router(router)
                        .criteriaQuery(cq)
                        .criteriaBuilder(cb)
                        .build();
                result = cb.and(result, field.buildPredicate(args));
            }
        }
        return result;
    }

}

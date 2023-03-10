package io.github.zul.springsmartspecification.jpa.query;

import java.util.Set;

import jakarta.persistence.criteria.Predicate;

public interface JpaSpecificationFieldPredicateBuilder {

    Set<String> getPatterns();

    Predicate build(JpaSpecificationArgs args);

}

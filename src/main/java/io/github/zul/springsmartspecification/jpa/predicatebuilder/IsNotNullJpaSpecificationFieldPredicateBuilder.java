package io.github.zul.springsmartspecification.jpa.predicatebuilder;

import java.util.Set;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;

import io.github.zul.springsmartspecification.jpa.query.JpaSpecificationArgs;
import io.github.zul.springsmartspecification.jpa.query.JpaSpecificationFieldPredicateBuilder;

public class IsNotNullJpaSpecificationFieldPredicateBuilder implements JpaSpecificationFieldPredicateBuilder {

    @Override
    public Set<String> getPatterns() {
        return Set.of("IsNotNull", "NotNull");
    }

    @Override
    public Predicate build(JpaSpecificationArgs args) {
        if (args.getExpressions().size() != 2) {
            throw new IllegalArgumentException("Invalid number of expressions: " + args.getExpressions().size());
        }
        Expression<?> path = (Expression<?>) args.getExpressions().get(0);
        Boolean value = (Boolean) args.getExpressions().get(1);
        if (value) {
            return args.getCriteriaBuilder().isNotNull(path);
        } else {
            return args.getCriteriaBuilder().isNull(path);
        }
    }

}

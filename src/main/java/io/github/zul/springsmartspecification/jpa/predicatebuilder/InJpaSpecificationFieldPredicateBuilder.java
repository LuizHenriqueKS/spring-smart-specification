package io.github.zul.springsmartspecification.jpa.predicatebuilder;

import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder.In;

import io.github.zul.springsmartspecification.jpa.query.JpaSpecificationArgs;
import io.github.zul.springsmartspecification.jpa.query.JpaSpecificationFieldPredicateBuilder;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

public class InJpaSpecificationFieldPredicateBuilder implements JpaSpecificationFieldPredicateBuilder {

    @Override
    public Set<String> getPatterns() {
        return Set.of("In");
    }

    @Override
    @SuppressWarnings("unchecked")
    public Predicate build(JpaSpecificationArgs args) {
        if (args.getExpressions().size() != 2) {
            throw new IllegalArgumentException("Invalid number of expressions: " + args.getExpressions().size());
        }
        Expression<?> path = (Expression<?>) args.getExpressions().get(0);
        In<Object> in = args.getCriteriaBuilder().in(path);
        Object value = args.getExpressions().get(1);
        if (value instanceof Iterable) {
            ((Iterable<Object>) value).forEach(in::value);
        } else {
            Object[] array = (Object[]) value;
            for (Object o : array) {
                in.value(o);
            }
        }
        return in;
    }

}

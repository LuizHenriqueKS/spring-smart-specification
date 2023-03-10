package io.github.zul.springsmartspecification.mongo.criteriabuilder;

import java.util.Set;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;

import io.github.zul.springsmartspecification.mongo.query.MongoSpecificationArgs;
import io.github.zul.springsmartspecification.mongo.query.MongoSpecificationFieldCriteriaBuilder;

public class IsNotNullMongoSpecificationFieldCriteriaBuilder implements MongoSpecificationFieldCriteriaBuilder {

    @Override
    public Set<String> getPatterns() {
        return Set.of("IsNotNull", "NotNull");
    }

    @Override
    public CriteriaDefinition build(MongoSpecificationArgs args) {
        if (args.getExpressions().size() != 2) {
            throw new IllegalArgumentException("Invalid number of expressions: " + args.getExpressions().size());
        }
        String path = (String) args.getExpressions().get(0);
        Object value = args.getExpressions().get(1);
        if (value instanceof Boolean) {
            Boolean bool = (Boolean) value;
            if (bool == true) {
                return Criteria.where(path).ne(null);
            } else if (bool == false) {
                return Criteria.where(path).isNull();
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }

}

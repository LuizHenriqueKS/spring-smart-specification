package io.github.zul.springsmartspecification.mongo.criteriabuilder;

import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;

import io.github.zul.springsmartspecification.mongo.query.MongoSpecificationArgs;
import io.github.zul.springsmartspecification.mongo.query.MongoSpecificationFieldCriteriaBuilder;
import io.github.zul.springsmartspecification.mongo.query.MongoValue;

public class EqualsMongoSpecificationFieldCriteriaBuilder implements MongoSpecificationFieldCriteriaBuilder {

    @Override
    public Set<String> getPatterns() {
        return Set.of("Equals", "Eq", "Is");
    }

    @Override
    public CriteriaDefinition build(MongoSpecificationArgs args) {
        if (args.getExpressions().size() != 2) {
            throw new IllegalArgumentException("Invalid number of expressions: " + args.getExpressions().size());
        }
        String path = (String) args.getExpressions().get(0);
        Object value = args.getExpressions().get(1);
        if (value instanceof MongoValue) {
            MongoValue mv = (MongoValue) value;
            if (mv.isIgnoreCase()) {
                String regex = "^" + mv.quote() + "$";
                Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                return Criteria.where(path).regex(pattern);
            }
            throw new IllegalArgumentException("Invalid value: " + value);
        }
        return Criteria.where(path).is(value);
    }

}

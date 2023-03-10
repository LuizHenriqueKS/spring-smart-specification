package io.github.zul.springsmartspecification.specification;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmartSpecificationMeta {

    private Class<?> specificationClass;
    private List<SmartSpecificationFieldMeta> fields;

}

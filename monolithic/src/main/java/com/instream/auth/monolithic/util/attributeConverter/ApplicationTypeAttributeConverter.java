package com.instream.auth.monolithic.util.attributeConverter;

import com.instream.auth.monolithic.util.enums.ApplicationType;
import com.instream.auth.monolithic.util.enums.DbStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;


@Converter
@Slf4j
public class ApplicationTypeAttributeConverter implements AttributeConverter<ApplicationType, String> {

  @Override
  public String convertToDatabaseColumn(ApplicationType attribute) {
    if (attribute == null)
      return null;
    return attribute.getCode();
  }

  @Override
  public ApplicationType convertToEntityAttribute(String dbData) {
    if (dbData == null) {
      return null;
    }
    try {
      return ApplicationType.fromCode(dbData);
    } catch (IllegalArgumentException e) {
      log.error("failure to convert cause unexpected code [{}]", dbData, e);
      throw e;
    }
  }
}

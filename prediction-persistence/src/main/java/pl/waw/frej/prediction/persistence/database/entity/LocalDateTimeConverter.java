package pl.waw.frej.prediction.persistence.database.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Date> {

    public static final ZoneId TIME_ZONE = ZoneId.systemDefault();

    @Override
    public Date convertToDatabaseColumn(LocalDateTime attribute) {
        return Date.from(Instant.from(attribute.atZone(TIME_ZONE)));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Date dbData) {
        return LocalDateTime.ofInstant(dbData.toInstant(), TIME_ZONE);
    }
}

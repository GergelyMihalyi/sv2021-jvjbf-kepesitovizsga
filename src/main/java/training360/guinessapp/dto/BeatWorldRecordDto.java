package training360.guinessapp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BeatWorldRecordDto {
    private String description;
    private String unitOfMeasure;
    private LocalDate dateOfRecord;
    private String oldRecorderName;
    private String newRecorderName;
    private Double oldRecordValue;
    private Double newRecordValue;
}

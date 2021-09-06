package training360.guinessapp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WorldRecordDto {
    private Long id;
    private String description;
    private Double value;
    private String unitOfMeasure;
    private LocalDate dateOfRecord;
    private RecorderDto recorder;
    private String recorderName;
}

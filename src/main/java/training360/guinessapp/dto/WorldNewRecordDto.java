package training360.guinessapp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WorldNewRecordDto {

    private String description;
    private Double value;
    private String recorderName;
    private String unitOfMeasure;
    private LocalDate dateOfRecord;
    private RecorderDto recorder;

}

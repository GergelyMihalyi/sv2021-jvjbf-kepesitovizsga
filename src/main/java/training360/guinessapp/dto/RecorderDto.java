package training360.guinessapp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RecorderDto {
    private Long id;
    private String name;
    private LocalDate dateOfBirth;

}

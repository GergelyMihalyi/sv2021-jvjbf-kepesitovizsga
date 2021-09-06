package training360.guinessapp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RecorderShortDto {
    private String name;
    private LocalDate dateOfBirth;

}

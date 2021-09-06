package training360.guinessapp.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "world_record")
public class WorldRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double value;
    @Column(name = "unit_of_measure")
    private String unitOfMeasure;
    @Column(name = "date_of_record")
    private LocalDate dateOfRecord;
    @OneToOne
    @JoinColumn(name = "recorder_id", referencedColumnName = "id")
    private Recorder recorder;
    @Transient
    private Recorder oldRecorder;
    @Transient
    private Double oldValue;

    public WorldRecord(String description, Double value, String unitOfMeasure, LocalDate dateOfRecord, Recorder recorder) {
        this.description = description;
        this.value = value;
        this.unitOfMeasure = unitOfMeasure;
        this.dateOfRecord = dateOfRecord;
        this.recorder = recorder;
    }

    public Double getNewRecordValue(){
        return value;
    }

    public String getNewRecorderName(){
        return recorder.getName();
    }

    public Double oldRecordValue(){
        return oldValue;
    }

    public String oldRecorderName(){
        return oldRecorder.getName();
    }

}

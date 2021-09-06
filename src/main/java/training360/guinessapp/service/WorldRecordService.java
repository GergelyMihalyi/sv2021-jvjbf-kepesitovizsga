package training360.guinessapp.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training360.guinessapp.dto.BeatWorldRecordDto;
import training360.guinessapp.dto.WorldRecordCreateCommand;
import training360.guinessapp.dto.WorldRecordDto;
import training360.guinessapp.dto.BeatWorldRecordCommand;
import training360.guinessapp.exception.NotNewRecordException;
import training360.guinessapp.exception.RecorderNotFoundException;
import training360.guinessapp.exception.WorldRecordNotFoundException;
import training360.guinessapp.repository.Recorder;
import training360.guinessapp.repository.RecordersRepository;
import training360.guinessapp.repository.WorldRecord;
import training360.guinessapp.repository.WorldRecordRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class WorldRecordService {
    private ModelMapper modelMapper;
    private WorldRecordRepository worldRecordRepository;
    private RecordersRepository recordersRepository;

    public WorldRecordDto createWorldRecord(WorldRecordCreateCommand command){
        Recorder recorder = recordersRepository.findById(command.getRecorderId()).orElseThrow( ()-> new RecorderNotFoundException("Recorder not found"));
        WorldRecord worldRecord = new WorldRecord(command.getDescription(),command.getValue(), command.getUnitOfMeasure(), command.getDateOfRecord(),recorder);
        worldRecordRepository.save(worldRecord);
        return modelMapper.map(worldRecord,WorldRecordDto.class);
    }

    @Transactional
    public BeatWorldRecordDto updateWorldRecord(long id, BeatWorldRecordCommand command){
        WorldRecord worldRecord = worldRecordRepository.findById(id).orElseThrow( ()-> new WorldRecordNotFoundException("World record not found"));
        Recorder recorder = recordersRepository.findById(command.getRecorderId()).orElseThrow( ()-> new RecorderNotFoundException("Recorder not found"));
        if(worldRecord.getValue() > command.getValue()){
            throw new NotNewRecordException("Can not beat");
        }
        worldRecord.setOldRecorder(worldRecord.getRecorder());
        worldRecord.setRecorder(recorder);
        worldRecord.setValue(command.getValue());
        worldRecord.setOldValue(worldRecord.getValue());
        worldRecord.setDateOfRecord(LocalDate.now());
        worldRecordRepository.save(worldRecord);
        return modelMapper.map(worldRecord, BeatWorldRecordDto.class);
    }
}

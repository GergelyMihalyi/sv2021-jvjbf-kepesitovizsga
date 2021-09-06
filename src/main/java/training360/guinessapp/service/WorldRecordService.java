package training360.guinessapp.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training360.guinessapp.dto.WorldRecordCreateCommand;
import training360.guinessapp.dto.WorldRecordDto;
import training360.guinessapp.exception.RecorderNotFoundException;
import training360.guinessapp.repository.Recorder;
import training360.guinessapp.repository.RecordersRepository;
import training360.guinessapp.repository.WorldRecord;
import training360.guinessapp.repository.WorldRecordRepository;

@Service
@AllArgsConstructor
public class WorldRecordService {
    private ModelMapper modelMapper;
    private WorldRecordRepository worldRecordRepository;
    private RecordersRepository recordersRepository;

    public WorldRecordDto createWorldRecord(WorldRecordCreateCommand command){
        Recorder recorder = recordersRepository.findById(command.getRecorderId()).orElseThrow( ()-> new RecorderNotFoundException("recorder not found"));
        WorldRecord worldRecord = new WorldRecord(command.getDescription(),command.getValue(), command.getUnitOfMeasure(), command.getDateOfRecord(),recorder);
        worldRecordRepository.save(worldRecord);
        return modelMapper.map(worldRecord,WorldRecordDto.class);
    }
}

package training360.guinessapp.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training360.guinessapp.dto.RecorderCreateCommand;
import training360.guinessapp.dto.RecorderDto;
import training360.guinessapp.exception.RecorderNotFoundException;
import training360.guinessapp.repository.Recorder;
import training360.guinessapp.repository.RecordersRepository;

@Service
@AllArgsConstructor
public class RecordersService {

    private ModelMapper modelMapper;
    private RecordersRepository recordersRepository;

    public RecorderDto findRecorderById(long id){
        return modelMapper.map(recordersRepository.findById(id)
        .orElseThrow( ()-> new RecorderNotFoundException("recorder not found")),
                RecorderDto.class);
    }

    public RecorderDto createRecorder(RecorderCreateCommand command){
        Recorder recorder = new Recorder(command.getName(),command.getDateOfBirth());
        recordersRepository.save(recorder);
        return modelMapper.map(recorder, RecorderDto.class);
    }
}

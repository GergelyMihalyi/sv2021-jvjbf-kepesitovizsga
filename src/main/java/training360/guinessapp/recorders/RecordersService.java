package training360.guinessapp.recorders;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training360.guinessapp.dto.RecorderCreateCommand;
import training360.guinessapp.dto.RecorderDto;

@Service
@AllArgsConstructor
public class RecordersService {

    private ModelMapper modelMapper;
    private RecordersRepository recordersRepository;

    public RecorderDto createRecorder(RecorderCreateCommand command){
        Recorder recorder = new Recorder(command.getName(),command.getDateOfBirth());
        recordersRepository.save(recorder);
        return modelMapper.map(recorder, RecorderDto.class);
    }
}

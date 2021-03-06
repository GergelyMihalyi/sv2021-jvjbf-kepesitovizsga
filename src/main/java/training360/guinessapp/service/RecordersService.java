package training360.guinessapp.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training360.guinessapp.dto.RecorderCreateCommand;
import training360.guinessapp.dto.RecorderDto;
import training360.guinessapp.dto.RecorderShortDto;
import training360.guinessapp.repository.Recorder;
import training360.guinessapp.repository.RecordersRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecordersService {

    private ModelMapper modelMapper;
    private RecordersRepository recordersRepository;

    public List<RecorderShortDto> listRecorders() {
        return recordersRepository.listAllRecorderWithFiltersAndOrder().stream().map(e -> modelMapper.map(e, RecorderShortDto.class)).collect(Collectors.toList());
    }

    public RecorderDto createRecorder(RecorderCreateCommand command) {
        Recorder recorder = new Recorder(command.getName(), command.getDateOfBirth());
        recordersRepository.save(recorder);
        return modelMapper.map(recorder, RecorderDto.class);
    }
}

package training360.guinessapp.recorders;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import training360.guinessapp.dto.RecorderCreateCommand;
import training360.guinessapp.dto.RecorderDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/recorders")
public class RecordersController {

    private RecordersService recordersService;

    public RecordersController(RecordersService recordersService) {
        this.recordersService = recordersService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecorderDto createRecorder(@Valid @RequestBody RecorderCreateCommand command){
        return recordersService.createRecorder(command);
    }
}

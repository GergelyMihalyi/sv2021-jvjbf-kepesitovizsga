package training360.guinessapp.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import training360.guinessapp.dto.RecorderCreateCommand;
import training360.guinessapp.dto.RecorderDto;
import training360.guinessapp.dto.RecorderShortDto;
import training360.guinessapp.exception.RecorderNotFoundException;
import training360.guinessapp.service.RecordersService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/recorders")
public class RecordersController {

    private RecordersService recordersService;

    public RecordersController(RecordersService recordersService) {
        this.recordersService = recordersService;
    }

    @GetMapping
    public List<RecorderShortDto> listRecorders(){
        return recordersService.listRecorders();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecorderDto createRecorder(@Valid @RequestBody RecorderCreateCommand command){
        return recordersService.createRecorder(command);
    }

    @ExceptionHandler(RecorderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(RecorderNotFoundException iae) {
        Problem problem = Problem.builder()
                .withType(URI.create("recorders/not-found"))
                .withTitle("not found")
                .withStatus(Status.NOT_FOUND)
                .withDetail(iae.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }
}

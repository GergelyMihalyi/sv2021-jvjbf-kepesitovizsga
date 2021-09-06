package training360.guinessapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import training360.guinessapp.dto.BeatWorldRecordDto;
import training360.guinessapp.dto.WorldRecordCreateCommand;
import training360.guinessapp.dto.WorldRecordDto;
import training360.guinessapp.dto.BeatWorldRecordCommand;
import training360.guinessapp.exception.NotNewRecordException;
import training360.guinessapp.exception.RecorderNotFoundException;
import training360.guinessapp.exception.WorldRecordNotFoundException;
import training360.guinessapp.service.WorldRecordService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/worldrecords")
public class WorldRecordController {
    private WorldRecordService worldRecordService;

    public WorldRecordController(WorldRecordService worldRecordService) {
        this.worldRecordService = worldRecordService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorldRecordDto createWorldRecord(@Valid @RequestBody WorldRecordCreateCommand command) {
        return worldRecordService.createWorldRecord(command);
    }

    @PutMapping("/{id}/beatrecords")
    @ResponseStatus(HttpStatus.CREATED)
    public BeatWorldRecordDto updateWorldRecord(@PathVariable("id") long id, @Valid @RequestBody BeatWorldRecordCommand command) {
        return worldRecordService.updateWorldRecord(id, command);
    }

    @ExceptionHandler(RecorderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFoundRecorder(RecorderNotFoundException iae) {
        Problem problem = Problem.builder()
                .withType(URI.create("recorders/not-found"))
                .withTitle("Recorder not found")
                .withStatus(Status.NOT_FOUND)
                .withDetail(iae.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @ExceptionHandler(WorldRecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(WorldRecordNotFoundException iae) {
        Problem problem = Problem.builder()
                .withType(URI.create("world-record/not-found"))
                .withTitle("World record not found")
                .withStatus(Status.NOT_FOUND)
                .withDetail(iae.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }


    @ExceptionHandler(NotNewRecordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Problem> handleNotRecord(NotNewRecordException iae) {
        Problem problem = Problem.builder()
                .withType(URI.create("world-record/not-record"))
                .withTitle("Can not beat")
                .withStatus(Status.BAD_REQUEST)
                .withDetail(iae.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }
}

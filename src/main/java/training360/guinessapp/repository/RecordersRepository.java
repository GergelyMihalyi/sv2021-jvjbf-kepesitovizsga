package training360.guinessapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecordersRepository extends JpaRepository<Recorder, Long> {

    @Query("select r FROM Recorder r where r.name like 'B%' or r.name like '%e%' order by r.dateOfBirth desc ")
    List<Recorder> listAllRecorderWithFiltersAndOrder();
}

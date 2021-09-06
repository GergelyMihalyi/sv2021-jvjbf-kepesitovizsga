create table recorder(id bigint auto_increment, name varchar(60) not null, dob date not null, primary key(id));
create table world_record(id bigint auto_increment, description varchar(255) not null, value DOUBLE not null,unit_of_measure varchar(255) not null, date_of_record date not null,recorder_id bigint, primary key(id));
ALTER TABLE world_record
    ADD CONSTRAINT WORLD_RECORD_RECORDER_ID_FK
        FOREIGN KEY (recorder_id) REFERENCES recorder(id);






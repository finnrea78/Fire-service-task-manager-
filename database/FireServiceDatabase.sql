CREATE TABLE FireCrew(
    Id int NOT NULL AUTO_INCREMENT,
    WeekStart date NOT NULL,
    PRIMARY KEY(Id)  
);

CREATE TABLE FireWorkers(
    Id int NOT NULL AUTO_INCREMENT,
    IdCrew int Not Null,
    Name varchar(255) not NULL,
    PRIMARY KEY (Id),
    FOREIGN KEY (IdCrew) REFERENCES FireCrew(Id)
);


CREATE TABLE Tasks (
    Id int NOT NULL AUTO_INCREMENT,
    Description varchar(255) NOT NULL,
    IndividualTask boolean NOT NULL,
    AssignedToCrew int NULL,
    AssignedToIndividual int NULL,
    StartDate date NOT NULL,
    EndDate date NULL, 
    Frequency int null,
    DayOfMonth int null,
    PRIMARY KEY (Id), 
    FOREIGN KEY (AssignedToCrew) REFERENCES FireCrew(Id),
    FOREIGN KEY (AssignedToIndividual) REFERENCES FireWorkers(Id)
);


CREATE TABLE Dates (
    Id int NOT NULL AUTO_INCREMENT,
    DateTask date NOT NULL,
    WorkerID int NOT NULL,
    TaskID int NOT NULL,
    PRIMARY KEY (Id), 
    FOREIGN KEY (WorkerID) REFERENCES FireWorkers(Id),
    FOREIGN KEY (TaskID) REFERENCES Tasks(Id)   
);



INSERT INTO FireCrew (WeekStart) VALUES
('2021-02-01'),
('2021-01-25');


INSERT INTO Tasks (Description, IndividualTask, AssignedToCrew, AssignedToIndividual, StartDate, EndDate,Frequency, DayOfMonth ) VALUES
('Clean the floors', false, 1, NULL, '2021-02-01',null, 1, null),
('Empty trash can', false, 1,  NULL, '2021-02-01',null, 2, null),
('Wash fire truck 1', false, 1,  NULL, '2021-02-01',null, 7, null),
('Wash fire truck 2', false, 1,  NULL, '2021-02-09',null, 7, null),
('Service fire truck 1', false, 1, NULL, '2021-02-14', null,  null, 14),
('Service fire truck 2', false, 1, NULL, '2021-02-01', null,  null, 1);



INSERT INTO FireWorkers (IdCrew, Name) VALUES
(1,"John Smith"),
(1,"Finn Rea"),
(1,"James arrowsmith"),
(2,"Jess Pawson"),
(2,"George Harrison"),
(2,"Ella Jonson");





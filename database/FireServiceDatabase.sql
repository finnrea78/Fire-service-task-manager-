CREATE TABLE FireCrew(
    Id int NOT NULL,
    WeekStart date NOT NULL,
    PRIMARY KEY(Id)  
);

CREATE TABLE FireWorkers(
    Id int Not NULL,
    IdCrew int Not Null,
    Name varchar(255) not NULL,
    PRIMARY KEY (Id),
    FOREIGN KEY (IdCrew) REFERENCES FireCrew(Id)
);

CREATE TABLE Tasks (
    Id int NOT NULL,
    Description varchar(255) NOT NULL,
    IndividualTask boolean NOT NULL,
    AssignedToCrew int NULL,
    AssignedToIndividual int NULL,
    startDate date NOT NULL,
    Frequency int Not null, 
    PRIMARY KEY (Id), 
    FOREIGN KEY (AssignedToCrew) REFERENCES FireCrew(Id),
    FOREIGN KEY (AssignedToIndividual) REFERENCES FireWorkers(Id)
);




INSERT INTO FireCrew VALUES
(1,'2021-02-01'),
(2,'2021-02-08');

INSERT INTO Tasks VALUES
(1, 'Clean the floors', false, 1, NULL, '2021-02-01', 1),
(2, 'Empty trash can', false, 1,  NULL, '2021-02-01', 2),
(3, 'Wash fire truck', false, 1,  NULL, '2021-02-01', 7),
(4, 'Service fire truck', false, 1, NULL, '2021-02-01', 30);

INSERT INTO FireWorkers VALUES
(1,1,"John Smith"),
(2,1,"Finn Rea"),
(3,1,"James arrowsmith"),
(4,2,"Jess Pawson"),
(5,2,"George Harrison"),
(6,2,"Ella Jonson");





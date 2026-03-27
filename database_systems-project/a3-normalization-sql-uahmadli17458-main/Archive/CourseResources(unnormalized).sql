CREATE TABLE CourseResources (
    CRN INTEGER NOT NULL,
    ISBN VARCHAR(20) NOT NULL,
    Title VARCHAR(255),
    Author VARCHAR(255),
    Edition VARCHAR(50),
    Publisher VARCHAR(255),
    Publisher_Address VARCHAR(255),
    Pages INTEGER,
    Year INTEGER,
    Course_Name VARCHAR(255),
    PRIMARY KEY (CRN, ISBN)
);

select * from CourseResources

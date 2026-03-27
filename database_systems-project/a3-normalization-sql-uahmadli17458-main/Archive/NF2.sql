DROP TABLE IF EXISTS Books CASCADE;
DROP TABLE IF EXISTS Books_3nf CASCADE;
DROP TABLE IF EXISTS course_3nf CASCADE;
DROP TABLE IF EXISTS CourseManual_2NF CASCADE;
DROP TABLE IF EXISTS Course CASCADE;
DROP TABLE IF EXISTS CourseManual CASCADE;
DROP TABLE IF EXISTS CourseResources CASCADE;
DROP TABLE IF EXISTS coursemanual_3nf CASCADE;
DROP TABLE IF EXISTS publishers CASCADE;


-- UNNORMALIZED TABLE
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
    Course_Name VARCHAR(255)
);


-- COPY CourseResources (CRN, ISBN, Title, Author, Edition, Publisher, Publisher_Address, Pages, Year, Course_Name)
-- FROM '/Users/ulkarahmadli/Documents/Unnormalized1.csv' DELIMITER ',' CSV HEADER;

-- select * from CourseResources



-- NF1 TABLE
CREATE TABLE CourseManual AS
SELECT
    CRN,
    ISBN,
    Title,
    UNNEST(string_to_array(Author, ',')) AS Authors,
    Edition,
    Publisher,
    Publisher_Address,
    Pages,
    Year,
    Course_Name
FROM CourseResources;

-- select * from CourseManual
drop table CourseManual





-- NF2 TABLES

-- Create the Authors_2NF table for authors
CREATE TABLE Author AS
SELECT DISTINCT
    ISBN,
    Authors
FROM CourseManual;
-- Remove Authors column from Book_Records_NF1 after splitting
ALTER TABLE Author
ADD PRIMARY KEY (Authors);


-- select * from Author
-- drop table author

CREATE TABLE CourseManual_2NF AS
SELECT DISTINCT
    ISBN,
    Title,
    Edition,
    Publisher,
    Publisher_Address,
    Pages,
    Year
FROM CourseManual;
-- Add constraints to `CourseManual_2NF`
ALTER TABLE CourseManual_2NF
ADD PRIMARY KEY (ISBN);
ALTER TABLE Author
ADD FOREIGN KEY (ISBN) REFERENCES CourseManual_2NF(ISBN) ON DELETE CASCADE;



CREATE TABLE Course AS
SELECT DISTINCT
    CRN,
    Course_Name
FROM CourseManual;
-- Add constraints to `Course`
ALTER TABLE Course
ADD PRIMARY KEY (CRN);


CREATE TABLE Books AS
SELECT DISTINCT
    ISBN,
    CRN
FROM CourseManual;
-- Add constraints
ALTER TABLE Books
ADD PRIMARY KEY  (ISBN,CRN), 
ADD FOREIGN KEY (ISBN) REFERENCES CourseManual_2NF(ISBN) ON DELETE CASCADE,
ADD FOREIGN KEY (CRN) REFERENCES Course(CRN) ON DELETE CASCADE;

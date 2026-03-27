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
    UNNEST(string_to_array(Author, ',')) AS Author,
    Edition,
    Publisher,
    Publisher_Address,
    Pages,
    Year,
    Course_Name
FROM CourseResources;

-- select * from CourseManual


[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/w1BdldVH)
# SQL Database Normalization Project

This project demonstrates database normalization principles using SQL. It showcases the transition of an unnormalized database into 1NF, 2NF, and 3NF while maintaining relational database design integrity.

## Table of Contents
1. [Introduction](#introduction)
2. [Prerequisites](#prerequisites)
3. [How to Use the SQL File](#how-to-use-the-sql-file)
4. [Normalization Process](#normalization-process)
5. [Verification](#verification)
6. [Support](#support)

## Introduction

This project is a part of an assignment focused on relational database normalization. The steps involve:
- Understanding principles of normalization.
- Transitioning data through 1NF, 2NF, and 3NF.
- Critiquing database design implementations.

The provided SQL script performs these transformations on a sample dataset derived from unnormalized data.

## Prerequisites

1. **PostgreSQL**: Install [PostgreSQL](https://www.postgresql.org/download/) and set up your environment.
2. **pgAdmin 4**: Download and install [pgAdmin 4](https://www.pgadmin.org/download/).
3. **Optional**: Use the `psql` CLI if you prefer command-line operations.

## How to Use the SQL File

### Step 1: Setup
- Open **pgAdmin 4** or the **psql CLI**.
- Create a database (e.g., `NormalizationDB`).

### Step 2: Run the SQL Script
1. Open the Query Tool in pgAdmin or connect to the database using psql.
2. Copy and paste the SQL script (`Normalization_Ulkar_Ahmadli.sql`) into the Query Tool.
3. Execute the script to apply the transformations.

### Step 3: Import Data
#### Common Errors to Watch For:
- **File Path Issues**: Ensure the file path to `Book_Records.csv` is correctly formatted for your system (use single forward slashes `/` on Linux/Mac or double backslashes `\\` on Windows).
- **Permission Denied**: Grant appropriate permissions to the folder containing `Book_Records.csv`. On Linux, you might use `chmod 777 [folder_path]`.
- **Data Format Errors**: Confirm that the `Book_Records.csv` file matches the expected schema.

#### Import Methods:
- **Using pgAdmin 4**: Utilize the Import/Export Data option.
- **Using psql CLI**: Uncomment the `copy from` command in the SQL file and update the file path to match your system.

## Normalization Process

The script executes the following transformations:
1. **1NF**: Converts unnormalized data by eliminating repeating groups.
2. **2NF**: Eliminates partial dependencies by reorganizing attributes into separate tables.
3. **3NF**: Removes transitive dependencies to ensure every attribute is dependent only on the primary key.

Refer to the comments in the SQL script for detailed explanations of each step.

## Verification

Run these sample queries to verify each step of normalization:
```sql
-- Check 1NF
SELECT * FROM Book_1NF;

-- Check 2NF
SELECT * FROM Book_2NF;

-- Check 3NF
SELECT * FROM Book_3NF;
```
### Support
For questions or troubleshooting:
- Refer to the list of common errors above to resolve frequent issues.
- If you need additional help, feel free to reach out to me via email at [uahmadli17458@ada.edu.az](mailto:uahmadli17458@ada.edu.az).

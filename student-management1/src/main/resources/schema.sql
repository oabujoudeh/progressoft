CREATE TABLE IF NOT EXISTS students (
                                        id VARCHAR(36) PRIMARY KEY,
    full_name VARCHAR(50) NOT NULL,
    grade VARCHAR(20) NOT NULL,
    class_section VARCHAR(1) NOT NULL,
    date_of_birth DATE NOT NULL,
    age INT NOT NULL,
    phone_number VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    address VARCHAR(100) NOT NULL,
    nationality VARCHAR(20) NOT NULL
    );
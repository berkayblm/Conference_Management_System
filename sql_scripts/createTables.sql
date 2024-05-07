create database CONFERENCE_MANAGEMENT_DB;

use CONFERENCE_MANAGEMENT_DB;

create table USER(
                     user_id INT PRIMARY KEY AUTO_INCREMENT,
                     username VARCHAR(255) UNIQUE,
                     email VARCHAR(255) UNIQUE,
                     password VARCHAR(255),
                     user_role ENUM('Participant','Reviewer','Organizer','Presenter','Author')

);



create table CONFERENCE(
                           conference_id INT PRIMARY KEY AUTO_INCREMENT,
                           title VARCHAR(255),
                           date DATE,
                           theme VARCHAR(255),
                           location VARCHAR(255)

);


create table PAPER(
                      paper_id INT PRIMARY KEY AUTO_INCREMENT,
                      title VARCHAR(255),
                      abstract VARCHAR(1000),
                      keywords VARCHAR(255),
                      status ENUM('Pending','Accepted','Rejected','Revision'),
                      paper_url VARCHAR(1000),
                      sender_user_id INT,
                      conference_id INT,


                      FOREIGN KEY (sender_user_id) REFERENCES USER(user_id),
                      FOREIGN KEY (conference_id) REFERENCES CONFERENCE(conference_id)


);


create table REVIEW(
                       review_id INT PRIMARY KEY AUTO_INCREMENT,
                       reviewer_id INT,
                       paper_id INT,
                       rating FLOAT,
                       comment VARCHAR(1000),

                       FOREIGN KEY(paper_id) REFERENCES PAPER(paper_id),
                       FOREIGN KEY(reviewer_id) REFERENCES USER(user_id)

);


INSERT INTO USER (username, email, password, user_role)
VALUES
    ('john_doe', 'john@example.com', 'password123', 'Participant'),
    ('jane_smith', 'jane@example.com', 'pass456', 'Organizer'),
    ('bob_jackson', 'bob@example.com', 'securepass', 'Reviewer');

-- Adding conferences
INSERT INTO CONFERENCE (title, date, theme, location)
VALUES
    ('Tech Summit 2024', '2024-07-15', 'Emerging Technologies', 'Istanbul'),
    ('Science Conference', '2024-09-20', 'Advancements in Physics', 'Dubai'),
    ('Business Expo', '2024-11-10', 'Entrepreneurship and Innovation','FLorida');


INSERT INTO `USER` (username, email, password, user_role)
VALUES
    ('author1', 'author1@example.com', 'password1', 'Author'),
    ('author2', 'author2@example.com', 'password2', 'Author'),
    ('author3', 'author3@example.com', 'password3', 'Author'),
    ('author4', 'author4@example.com', 'password4', 'Author'),
    ('author5', 'author5@example.com', 'password5', 'Author');


INSERT INTO `USER` (username, email, password, user_role)
VALUES
    ('reviewer1', 'reviewer1@example.com', 'password1', 'Reviewer'),
    ('reviewer2', 'reviewer2@example.com', 'password2', 'Reviewer'),
    ('reviewer3', 'reviewer3@example.com', 'password3', 'Reviewer'),
    ('reviewer4', 'reviewer4@example.com', 'password4', 'Reviewer'),
    ('reviewer5', 'reviewer5@example.com', 'password5', 'Reviewer');
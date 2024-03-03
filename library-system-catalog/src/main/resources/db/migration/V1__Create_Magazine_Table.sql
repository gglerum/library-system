CREATE TABLE magazine (
  id BIGINT NOT NULL,
   magazine_type VARCHAR(31),
   age_rating VARCHAR(255),
   title VARCHAR(255),
   publication_date date,
   publisher VARCHAR(255),
   genre VARCHAR(255),
   is_on_loan SMALLINT,
   copy_editor VARCHAR(255),
   isnn VARCHAR(255),
   appearance_number INT NOT NULL,
   name VARCHAR(255),
   CONSTRAINT pk_magazine PRIMARY KEY (id)
);
CREATE SEQUENCE magazine_seq INCREMENT BY 50;
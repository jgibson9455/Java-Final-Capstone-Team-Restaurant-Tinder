START TRANSACTION;

DROP TABLE IF EXISTS profile_preferences;
DROP TABLE IF EXISTS restaurant;
DROP TABLE IF EXISTS profile;
DROP TABLE IF EXISTS preferences;
DROP TABLE IF EXISTS restaurant_type;
DROP TABLE IF EXISTS matching_results;

-- Create Tables 
CREATE TABLE profile(
        user_id         SERIAL PRIMARY KEY,
        first_name      VARCHAR(50),
        last_name       VARCHAR(50),
        user_name       VARCHAR(50),
        email_address   VARCHAR(100),
        city            VARCHAR(250)
);

CREATE TABLE restaurant(
        restaurant_id   SERIAL PRIMARY KEY,
        restaurant_name VARCHAR(50),
        description     VARCHAR(250),
        zip_code        INTEGER,
        city            VARCHAR(250),
        phone_number    VARCHAR(15),
        image_link      VARCHAR(200),
        address         VARCHAR(100),
        type_id         INTEGER
);

CREATE TABLE matching_results(
        user_name       VARCHAR(50),
        restaurant_id   INTEGER,
        preference_id   INTEGER
);

CREATE TABLE profile_preferences(
        user_name       VARCHAR(50),
        type_id         INTEGER,
        preference_id   INTEGER
);

CREATE TABLE preferences(
        preference_id   SERIAL PRIMARY KEY,
        description     VARCHAR(20)
        CONSTRAINT ch_preference CHECK(description IN('Like', 'Dislike'))
);

CREATE TABLE restaurant_type(
        type_id         SERIAL PRIMARY KEY,
        type_name       VARCHAR(50)
);


--Add FKs

--Profile FK
ALTER TABLE profile
ADD FOREIGN KEY(user_id)
REFERENCES users(user_id);

--Profile Preferences FKS
--ALTER TABLE profile_preferences
--ADD FOREIGN KEY(user_name)
--REFERENCES profile(user_name);

ALTER TABLE profile_preferences
ADD FOREIGN KEY(type_id)
REFERENCES restaurant_type(type_id);

ALTER TABLE profile_preferences
ADD FOREIGN KEY(preference_id)
REFERENCES preferences(preference_id);

--Matching Results FK
--ALTER TABLE matching_results
--ADD FOREIGN KEY(user_name)
--REFERENCES profile(user_name);

--Restaurant FK
ALTER TABLE restaurant
ADD FOREIGN KEY(type_id)
REFERENCES restaurant_type(type_id);

COMMIT
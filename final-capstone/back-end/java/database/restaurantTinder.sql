START TRANSACTION;

DROP TABLE IF EXISTS profile_preferences;
DROP TABLE IF EXISTS restaurant;
DROP TABLE IF EXISTS profile;
DROP TABLE IF EXISTS preferences;
DROP TABLE IF EXISTS restaurant_type;

-- Create Tables 
CREATE TABLE profile(
        user_id         INTEGER PRIMARY KEY,
        first_name      VARCHAR(50),
        last_name       VARCHAR(50),
        user_name       VARCHAR(50),
        email_address   VARCHAR(100),
        zip_code        INTEGER
);

CREATE TABLE restaurant(
        restaurant_id   SERIAL PRIMARY KEY,
        restaurant_name VARCHAR(50),
        description     VARCHAR(250),
        zip_code        INTEGER,
        phone_number    VARCHAR(15),
        image_link      VARCHAR(200),
        address         VARCHAR(100),
        type_id         INTEGER
);

CREATE TABLE profile_preferences(
        user_id         INTEGER,
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

--Profile Preferences FKS
ALTER TABLE profile_preferences
ADD FOREIGN KEY(user_id)
REFERENCES profile(user_id);

ALTER TABLE profile_preferences
ADD FOREIGN KEY(type_id)
REFERENCES restaurant_type(type_id);

ALTER TABLE profile_preferences
ADD FOREIGN KEY(preference_id)
REFERENCES preferences(preference_id);

--Restaurant FK
ALTER TABLE restaurant
ADD FOREIGN KEY(type_id)
REFERENCES restaurant_type(type_id);

COMMIT
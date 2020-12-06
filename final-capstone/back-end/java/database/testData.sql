---TEST DATA FOR RESTURANT TINDER---
START TRANSACTION;

--Clear Data From all tables
DELETE FROM preferences;
DELETE FROM restaurant_type;
DELETE FROM restaurant;
DELETE FROM profile;
DELETE FROM profile_preferences;

--Test Data for -- Preferences
INSERT INTO preferences(description) VALUES('Like');
INSERT INTO preferences(description) VALUES('Dislike');

--Test Data for -- Restaurant_Types
INSERT INTO restaurant_type(type_name) VALUES('Bar');
INSERT INTO restaurant_type(type_name) VALUES('Mexican');
INSERT INTO restaurant_type(type_name) VALUES('BBQ');
INSERT INTO restaurant_type(type_name) VALUES('Chinese');

--Test Data for -- Restaurants
INSERT INTO restaurant(restaurant_name, description, zip_code, phone_number, image_link, address, type_id)
VALUES('Chiptole', 'An authentic mexican experince', 12345, '(330) 555-1589', 
'https://image.cnbcfm.com/api/v1/image/106304982-15767633092704_chipotle_2019-206.jpg?v=1576763379', 
'123 Main St', (SELECT type_id FROM restaurant_type WHERE type_name ='Mexican'));

INSERT INTO restaurant(restaurant_name, description, zip_code, phone_number, image_link, address, type_id)
VALUES('City BBQ', 'National award-winning barbeque - authentic and inspired', 12345, '(440) 555-9548', 
'https://ewscripps.brightspotcdn.com/e5/e3/80fc9b494dababbc1b17b5f6159b/city-bbq-4-of-5.jpg', 
'6548 Center Rd', (SELECT type_id FROM restaurant_type WHERE type_name ='BBQ'));

-- Test Data for -- Profile
INSERT INTO profile VALUES(1, 'Test', 'User', (SELECT username FROM users WHERE user_id = 1), 
'TheTestUser@Gmail.com', 12345);

--Test Data For -- profile_preferences
--VALUES(user_id, type, like(1)/dislike(2))
INSERT INTO profile_preferences VALUES(1,2,1);
INSERT INTO profile_preferences VALUES(1,3,2);

COMMIT;
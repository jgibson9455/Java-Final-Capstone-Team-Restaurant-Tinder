---TEST DATA FOR RESTURANT TINDER---
START TRANSACTION;

--Clear Data From all tables
DELETE FROM matching_results;
DELETE FROM profile_preferences;
DELETE FROM preferences;
DELETE FROM restaurant_type;
DELETE FROM restaurant;
DELETE FROM profile;

--Test Data for -- Preferences
INSERT INTO preferences(description) VALUES('Like');
INSERT INTO preferences(description) VALUES('Dislike');

--Test Data for -- Restaurant_Types
INSERT INTO restaurant_type(type_name) VALUES('Bar');
INSERT INTO restaurant_type(type_name) VALUES('Mexican');
INSERT INTO restaurant_type(type_name) VALUES('BBQ');
INSERT INTO restaurant_type(type_name) VALUES('Chinese');
INSERT INTO restaurant_type(type_name) VALUES('Steak House');

--Test Data for -- Restaurants
INSERT INTO restaurant(restaurant_name, description, zip_code, phone_number, image_link, address, type_id)
VALUES('Chiptole', 'An authentic mexican experince', 12345, '(330) 555-1589', 
'https://image.cnbcfm.com/api/v1/image/106304982-15767633092704_chipotle_2019-206.jpg?v=1576763379', 
'123 Main St', (SELECT type_id FROM restaurant_type WHERE type_name ='Mexican'));

INSERT INTO restaurant(restaurant_name, description, zip_code, phone_number, image_link, address, type_id)
VALUES('City BBQ', 'National award-winning barbeque - authentic and inspired', 12345, '(440) 555-9548', 
'https://ewscripps.brightspotcdn.com/e5/e3/80fc9b494dababbc1b17b5f6159b/city-bbq-4-of-5.jpg', 
'6548 Center Rd', (SELECT type_id FROM restaurant_type WHERE type_name ='BBQ'));

INSERT INTO restaurant(restaurant_name, description, zip_code, phone_number, image_link, address, type_id)
VALUES('Rose Wood Grill', 'Intimate setting for inventive American eats (almond-crusted tofu & fish tacos), plus craft brews.', 12345, '(440) 285-9875', 
'https://cdn.usarestaurants.info/assets/uploads/951e30d114af60bd8a54ad5798075baf_-united-states-ohio-cuyahoga-county-strongsville-851920-rosewood-grill-strongsvillehtm.jpg',
'98744 Royalton Rd', (SELECT type_id FROM restaurant_type WHERE type_name ='Steak House'));

INSERT INTO restaurant(restaurant_name, description, zip_code, phone_number, image_link, address, type_id)
VALUES('The Rail', 'Known for serving juicy Certified Angus Beef steaks, The City Square Steakhouse also quickly developed a following for the local, fresh beef burgers served on the bar menu.',
12345, '(440) 858-9958', 'https://i2.wp.com/dougbardwell.com/db/wp-content/uploads/2016/08/RAIL2.jpg?fit=1200%2C661&ssl=1', '8498 Royalton Rd',
(SELECT type_id FROM restaurant_type WHERE type_name ='Bar'));

INSERT INTO restaurant(restaurant_name, description, zip_code, phone_number, image_link, address, type_id)
VALUES('Barrio', 'Local chain serving signature & build-your-own tacos, plus cocktails, in a hip, casual space.', 12345, '(216) 984-9925',
'https://media-cdn.tripadvisor.com/media/photo-s/0c/bb/f3/76/photo0jpg.jpg', '123 Detriot Rd', 
(SELECT type_id FROM restaurant_type WHERE type_name ='Mexican'));

-- Test Data for -- Profile
INSERT INTO profile VALUES(1, 'Test', 'User', (SELECT username FROM users WHERE user_id = 1), 
'TheTestUser@Gmail.com', 12345);

--Test Data For -- profile_preferences
--VALUES(user_id, type, like(1)/dislike(2))
INSERT INTO profile_preferences VALUES((SELECT user_name FROM profile WHERE user_name = 'user'),2,1);
INSERT INTO profile_preferences VALUES((SELECT user_name FROM profile WHERE user_name = 'user'),3,2);

--Test Data For Matching_results
INSERT INTO matching_results VALUES((SELECT user_name FROM profile WHERE user_name = 'user'), 1,1);

COMMIT;
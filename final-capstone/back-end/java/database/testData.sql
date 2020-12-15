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
INSERT INTO restaurant_type VALUES(1, 'American');
INSERT INTO restaurant_type VALUES(3, 'Asian');
INSERT INTO restaurant_type VALUES(193, 'BBQ');
INSERT INTO restaurant_type VALUES(227,'Bar Food');
INSERT INTO restaurant_type VALUES(182, 'Breakfast');
INSERT INTO restaurant_type VALUES(168, 'Burger');
INSERT INTO restaurant_type VALUES(40, 'Fast Food');
INSERT INTO restaurant_type VALUES(148, 'Indian');
INSERT INTO restaurant_type VALUES(55, 'Italian');
INSERT INTO restaurant_type VALUES(60, 'Japanese');
INSERT INTO restaurant_type VALUES(70, 'Mediterranian');
INSERT INTO restaurant_type VALUES(73, 'Mexican');
INSERT INTO restaurant_type VALUES(82, 'Pizza');
INSERT INTO restaurant_type VALUES(998, 'Salad');
INSERT INTO restaurant_type VALUES(304, 'Sandwich');
INSERT INTO restaurant_type VALUES(83, 'Seafood');
INSERT INTO restaurant_type VALUES(141, 'Steak');
INSERT INTO restaurant_type VALUES(177, 'Sushi');
INSERT INTO restaurant_type VALUES(95, 'Thai');
INSERT INTO restaurant_type VALUES(308, 'Vegetarian');

--Test Data for -- Restaurants
INSERT INTO restaurant(restaurant_name, description, city, zip_code, phone_number, image_link, address, type_id)
VALUES('Chiptole', 'An authentic mexican experince', 'Strongsville', 44136, '(330) 555-1589', 
'https://image.cnbcfm.com/api/v1/image/106304982-15767633092704_chipotle_2019-206.jpg?v=1576763379', 
'123 Main St', (SELECT type_id FROM restaurant_type WHERE type_name ='Mexican'));

INSERT INTO restaurant(restaurant_name, description, city, zip_code, phone_number, image_link, address, type_id)
VALUES('City BBQ', 'National award-winning barbeque - authentic and inspired', 'Cleveland', 44121, '(440) 555-9548', 
'https://ewscripps.brightspotcdn.com/e5/e3/80fc9b494dababbc1b17b5f6159b/city-bbq-4-of-5.jpg', 
'6548 Center Rd', (SELECT type_id FROM restaurant_type WHERE type_name ='BBQ'));

INSERT INTO restaurant(restaurant_name, description, city, zip_code, phone_number, image_link, address, type_id)
VALUES('Rose Wood Grill', 'Intimate setting for inventive American eats (almond-crusted tofu & fish tacos), plus craft brews.', 'Strongsville', 44136, '(440) 285-9875', 
'https://cdn.usarestaurants.info/assets/uploads/951e30d114af60bd8a54ad5798075baf_-united-states-ohio-cuyahoga-county-strongsville-851920-rosewood-grill-strongsvillehtm.jpg',
'98744 Royalton Rd', (SELECT type_id FROM restaurant_type WHERE type_name ='Steak'));

INSERT INTO restaurant(restaurant_name, description, city, zip_code, phone_number, image_link, address, type_id)
VALUES('The Rail', 'Known for serving juicy Certified Angus Beef steaks, The City Square Steakhouse also quickly developed a following for the local, fresh beef burgers served on the bar menu.',
'Strongsville', 44136, '(440) 858-9958', 'https://i2.wp.com/dougbardwell.com/db/wp-content/uploads/2016/08/RAIL2.jpg?fit=1200%2C661&ssl=1', '8498 Royalton Rd',
(SELECT type_id FROM restaurant_type WHERE type_name ='Bar Food'));

INSERT INTO restaurant(restaurant_name, description, city, zip_code, phone_number, image_link, address, type_id)
VALUES('Barrio', 'Local chain serving signature & build-your-own tacos, plus cocktails, in a hip, casual space.', 'Cleveland', 44121, '(216) 984-9925',
'https://barrio-tacos.com/themes/custom/barrio_fdn/images/default/food-menu-tacos02.jpg', '123 Detriot Rd', 
(SELECT type_id FROM restaurant_type WHERE type_name ='Mexican'));

-- Test Data for -- Profile
INSERT INTO profile VALUES(1, 'Test', 'User', (SELECT username FROM users WHERE user_id = 1), 
'TheTestUser@Gmail.com', 'Strongsville');

--Test Data For -- profile_preferences
--VALUES(user_id, type, like(1)/dislike(2))
INSERT INTO profile_preferences VALUES((SELECT user_name FROM profile WHERE user_name = 'user'),
(SELECT type_id FROM restaurant_type WHERE type_name = 'Mexican'),1, 'Mexican');

INSERT INTO profile_preferences VALUES((SELECT user_name FROM profile WHERE user_name = 'user'),
(SELECT type_id FROM restaurant_type WHERE type_name = 'Sushi'),2, 'Sushi');

--Test Data For Matching_results
--INSERT INTO matching_results VALUES((SELECT user_name FROM profile WHERE user_name = 'user'), 1,1);

COMMIT;
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
INSERT INTO restaurant_type VALUES(1, 'American', 'y');
INSERT INTO restaurant_type VALUES(3, 'Asian', 'y');
INSERT INTO restaurant_type VALUES(193, 'BBQ', 'y');
INSERT INTO restaurant_type VALUES(227,'Bar Food', 'y');
INSERT INTO restaurant_type VALUES(182, 'Breakfast', 'y');
INSERT INTO restaurant_type VALUES(168, 'Burger', 'y');
INSERT INTO restaurant_type VALUES(40, 'Fast Food', 'y');
INSERT INTO restaurant_type VALUES(148, 'Indian', 'y');
INSERT INTO restaurant_type VALUES(55, 'Italian', 'y');
INSERT INTO restaurant_type VALUES(60, 'Japanese', 'y');
INSERT INTO restaurant_type VALUES(70, 'Mediterranian', 'y');
INSERT INTO restaurant_type VALUES(73, 'Mexican', 'y');
INSERT INTO restaurant_type VALUES(82, 'Pizza', 'y');
INSERT INTO restaurant_type VALUES(998, 'Salad', 'y');
INSERT INTO restaurant_type VALUES(304, 'Sandwich', 'y');
INSERT INTO restaurant_type VALUES(83, 'Seafood', 'y');
INSERT INTO restaurant_type VALUES(141, 'Steak', 'y');
INSERT INTO restaurant_type VALUES(177, 'Sushi', 'y');
INSERT INTO restaurant_type VALUES(95, 'Thai', 'y');
INSERT INTO restaurant_type VALUES(308, 'Vegetarian', 'y');

INSERT INTO restaurant_type VALUES(152, 'African', 'n');
INSERT INTO restaurant_type VALUES(5, 'Bakery', 'n');
INSERT INTO restaurant_type VALUES(159, 'Brazilian', 'n');
INSERT INTO restaurant_type VALUES(133, 'British', 'n');
INSERT INTO restaurant_type VALUES(30, 'Cafe', 'n');
INSERT INTO restaurant_type VALUES(158, 'Caribbean', 'n');
INSERT INTO restaurant_type VALUES(491, 'Cajun', 'n');
INSERT INTO restaurant_type VALUES(161, 'Coffee and Tea', 'n');
INSERT INTO restaurant_type VALUES(287, 'Colombian', 'n');
INSERT INTO restaurant_type VALUES(881, 'Crepes', 'n');
INSERT INTO restaurant_type VALUES(153, 'Cuban', 'n');
INSERT INTO restaurant_type VALUES(192, 'Deli', 'n');
INSERT INTO restaurant_type VALUES(100, 'Desserts', 'n');
INSERT INTO restaurant_type VALUES(411, 'Dim Sum', 'n');
INSERT INTO restaurant_type VALUES(541, 'Diner', 'n');
INSERT INTO restaurant_type VALUES(959, 'Donuts', 'n');
INSERT INTO restaurant_type VALUES(268, 'Drinks Only', 'n');
INSERT INTO restaurant_type VALUES(149, 'Ethiopian', 'n');
INSERT INTO restaurant_type VALUES(38, 'European', 'n');
INSERT INTO restaurant_type VALUES(112, 'Filipino', 'n');
INSERT INTO restaurant_type VALUES(45, 'French', 'n');
INSERT INTO restaurant_type VALUES(134, 'German', 'n');
INSERT INTO restaurant_type VALUES(156, 'Greek', 'n');
INSERT INTO restaurant_type VALUES(181, 'Grill', 'n');
INSERT INTO restaurant_type VALUES(143, 'Healthy Food', 'n');
INSERT INTO restaurant_type VALUES(233, 'Ice Cream', 'n');
INSERT INTO restaurant_type VALUES(135, 'Irish', 'n');
INSERT INTO restaurant_type VALUES(207, 'Jamaican', 'n');
INSERT INTO restaurant_type VALUES(164, 'Juices', 'n');
INSERT INTO restaurant_type VALUES(67, 'Korean', 'n');
INSERT INTO restaurant_type VALUES(136, 'Latin American', 'n');
INSERT INTO restaurant_type VALUES(66, 'Lebanese', 'n');
INSERT INTO restaurant_type VALUES(137, 'Middle Eastern', 'n');
INSERT INTO restaurant_type VALUES(147, 'Moroccan', 'n');
INSERT INTO restaurant_type VALUES(139, 'Pakistani', 'n');
INSERT INTO restaurant_type VALUES(219, 'Polish', 'n');
INSERT INTO restaurant_type VALUES(87, 'Portuguese', 'n');
INSERT INTO restaurant_type VALUES(361, 'Puerto Rican', 'n');
INSERT INTO restaurant_type VALUES(601, 'Salvadorian', 'n');
INSERT INTO restaurant_type VALUES(461, 'Soul Food', 'n');
INSERT INTO restaurant_type VALUES(972, 'South American', 'n');
INSERT INTO restaurant_type VALUES(471, 'Southern', 'n');
INSERT INTO restaurant_type VALUES(966, 'Southwestern', 'n');
INSERT INTO restaurant_type VALUES(89, 'Spanish', 'n');
INSERT INTO restaurant_type VALUES(190, 'Taiwanese', 'n');
INSERT INTO restaurant_type VALUES(179, 'Tapas', 'n');
INSERT INTO restaurant_type VALUES(142, 'Turkish', 'n');
INSERT INTO restaurant_type VALUES(99, 'Vietnamese', 'n');

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
'TheTestUser@Gmail.com', 'Strongsville', 'Cleveland');

--Test Data For -- profile_preferences
--VALUES(user_id, type, like(1)/dislike(2))
INSERT INTO profile_preferences VALUES((SELECT user_name FROM profile WHERE user_name = 'user'),
(SELECT type_id FROM restaurant_type WHERE type_name = 'Mexican'),1, 'Mexican');

INSERT INTO profile_preferences VALUES((SELECT user_name FROM profile WHERE user_name = 'user'),
(SELECT type_id FROM restaurant_type WHERE type_name = 'Sushi'),2, 'Sushi');

--Test Data For Matching_results
--INSERT INTO matching_results VALUES((SELECT user_name FROM profile WHERE user_name = 'user'), 1,1);

COMMIT;
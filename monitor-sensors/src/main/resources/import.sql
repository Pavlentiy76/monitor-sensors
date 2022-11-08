insert into ranges values (1, 10, 20);
insert into ranges values (2, 30, 40);
insert into ranges values (3, 10, 90);
insert into ranges values (4, 1, 29);
insert into ranges values (5, 5, 180);

insert into sensors (location, model, title, sensor_type, unit, description, range_id) values ('kitchen', 'mod1', 'title1', 'PRESSURE', 'BAR', 'description', 1);
insert into sensors (location, model, title, sensor_type, unit, description, range_id) values ('room', 'mod2', 'title2', 'TEMPERATURE', 'VOLTAGE', 'description', 2);
insert into sensors (location, model, title, sensor_type, unit, description, range_id) values ('garden', 'mod3', 'title3', 'VOLTAGE', 'CELSIUS', 'description', 3);
insert into sensors (location, model, title, sensor_type, unit, description, range_id) values ('bath', 'mod4', 'title4', 'HUMIDITY', 'PERCENT', 'description', 4);
insert into sensors (location, model, title, sensor_type, unit, description, range_id) values ('toilet', 'mod5', 'title5', 'PRESSURE', 'BAR', 'description', 5);
INSERT INTO project (id, name, description, createdAt, updatedAt) VALUES (1, 'Project 0', 'Test Project', '2020-10-25 00:00:00-00', '2020-10-25 00:00:00-00');

INSERT INTO sprint (id, projectId, name, number, createdAt, updatedAt) VALUES (1, 1, 'Sprint 0', 1, '2020-10-25 00:00:00-00', '2020-10-25 00:00:00-00');
INSERT INTO sprint (id, projectId, name, number, createdAt, updatedAt) VALUES (2, 1, 'Sprint 1', 2, '2020-10-25 00:00:00-00', '2020-10-25 00:00:00-00');

INSERT INTO userType (id, name, projectId) VALUES (1, 'Administrator', 1);
INSERT INTO userType (id, name, projectId) VALUES (2, 'Manager', 1);
INSERT INTO userType (id, name, projectId) VALUES (3, 'User', 1);

INSERT INTO userStory (id, projectId, sprintId, userTypeId, whoDescription, what, why, businessValue, size, version, isArchived, createdAt, updatedAt)
VALUES (1, 1, 1, 1, 'with confirmed email', 'needs to login to control panel', 'he can create users', 1, 1, 1, false, '2020-10-25 00:00:00-00', '2020-10-25 00:00:00-00');
INSERT INTO userStory (id, projectId, sprintId, userTypeId, whoDescription, what, why, businessValue, size, version, isArchived, createdAt, updatedAt)
VALUES (2, 1, 1, 3, '', 'needs to login to system', 'he can build applications', 4, 2, 1, false, '2020-10-25 00:00:00-00', '2020-10-25 00:00:00-00');

INSERT INTO userStory_acceptanceCriterias (userStory_id, acceptanceCriterias) VALUES (1, 'Users must be of a level lower than the connected user');
INSERT INTO userStory_acceptanceCriterias (userStory_id, acceptanceCriterias) VALUES (2, 'Users must confirm email in order to login');
INSERT INTO userStory_acceptanceCriterias (userStory_id, acceptanceCriterias) VALUES (2, 'Users must have a paid plan');

SELECT SETVAL('projectSeq', 1);
SELECT SETVAL('sprintSeq', 2);
SELECT SETVAL('userTypeSeq', 3);
SELECT SETVAL('userStorySeq', 2);
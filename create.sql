CREATE DATABASE heroika;
\c hero;
CREATE TABLE hero(id serial PRIMARY KEY, heroName VARCHAR, heroAge INTEGER, heroStrength VARCHAR, heroWeakness VARCHAR, squadId INTEGER);
CREATE TABLE squad(id serial PRIMARY KEY, squadName VARCHAR, squadCause VARCHAR, squadSize INTEGER, squadCreatedAt TIMESTAMP);

CREATE DATABASE heroika_test WITH TEMPLATE heroika;


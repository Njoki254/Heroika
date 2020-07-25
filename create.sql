CREATE DATABASE heroika;
\c heroika;
CREATE TABLE heroes(id serial PRIMARY KEY, heroName VARCHAR, heroAge INTEGER, heroStrength VARCHAR, heroWeakness VARCHAR, squadId INTEGER);
CREATE TABLE squads(id serial PRIMARY KEY, squadName VARCHAR, squadCause VARCHAR, squadSize INTEGER, squadCreatedAt TIMESTAMP);

CREATE DATABASE heroika_test WITH TEMPLATE heroika;


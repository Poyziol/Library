DROP TABLE IF EXISTS eleve;
DROP TABLE IF EXISTS promotion;

-- 1. Création de la table promotion
CREATE TABLE promotion (
    id_promotion SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL
);

-- 2. Création de la table eleve
CREATE TABLE eleve (
    id_eleve SERIAL PRIMARY KEY,
    id_promotion INTEGER,
    nom VARCHAR(100) NOT NULL,
    FOREIGN KEY(id_promotion) REFERENCES promotion(id_promotion) ON DELETE CASCADE
);
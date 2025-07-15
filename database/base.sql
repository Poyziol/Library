DROP TABLE reserver_exemplaire;
DROP TABLE preter_exemplaire;
DROP TABLE abonnement;
DROP TABLE penalite;
DROP TABLE prolongement;
DROP TABLE reservation;
DROP TABLE pret;
DROP TABLE adherant;
DROP TABLE users;
DROP TABLE inscription;
DROP TABLE exemplaire;
DROP TABLE etat;
DROP TABLE type_users;
DROP TABLE type_pret;
DROP TABLE status;
DROP TABLE type_adherant;
DROP TABLE livre;

-- 1) Livres
CREATE TABLE livre(
   id_livre SERIAL PRIMARY KEY,
   resume VARCHAR(300),
   titre VARCHAR(70)    NOT NULL,
   auteur VARCHAR(50)   NOT NULL,
   annee_publication DATE NOT NULL,
   age_min INTEGER      NOT NULL
);

-- 2) Type d’adhérent
CREATE TABLE type_adherant(
   id_type_adherant SERIAL PRIMARY KEY,
   libelle VARCHAR(50) NOT NULL UNIQUE
);

-- 3) Statuts
CREATE TABLE status(
   id_status SERIAL PRIMARY KEY,
   libelle VARCHAR(70) NOT NULL UNIQUE
);

-- 4) Types de prêt
CREATE TABLE type_pret(
   id_type_pret SERIAL PRIMARY KEY,
   libelle VARCHAR(70) NOT NULL UNIQUE
);

-- 5) Types d’utilisateurs
CREATE TABLE type_users(
   id_type_users SERIAL PRIMARY KEY,
   libelle VARCHAR(50) NOT NULL UNIQUE
);

-- 6) États d’un exemplaire
CREATE TABLE etat(
   id_etat BOOLEAN PRIMARY KEY,
   libelle VARCHAR(50) NOT NULL UNIQUE
);

-- 7) Exemplaires
CREATE TABLE exemplaire(
   id_exemplaire SERIAL PRIMARY KEY,
   disponible BOOLEAN NOT NULL,
   id_etat BOOLEAN NOT NULL,
   id_livre INTEGER NOT NULL,
   FOREIGN KEY (id_etat) REFERENCES etat(id_etat) ON DELETE CASCADE,
   FOREIGN KEY (id_livre) REFERENCES livre(id_livre) ON DELETE CASCADE
);

-- 8) Inscriptions
CREATE TABLE inscription(
   id_inscription SERIAL PRIMARY KEY,
   date_inscription DATE NOT NULL,
   id_status INTEGER NOT NULL,
   FOREIGN KEY (id_status) REFERENCES status(id_status) ON DELETE CASCADE
);

-- 9) Utilisateurs
CREATE TABLE users(
   id_users SERIAL PRIMARY KEY,
   nom VARCHAR(50) NOT NULL UNIQUE,
   mot_de_passe VARCHAR(16) NOT NULL,
   id_type_users INTEGER NOT NULL,
   FOREIGN KEY (id_type_users) REFERENCES type_users(id_type_users) ON DELETE CASCADE
);

-- 10) Adhérents
CREATE TABLE adherant(
   id_adherant SERIAL PRIMARY KEY,
   nom VARCHAR(50) NOT NULL,
   prenom VARCHAR(50) NOT NULL,
   date_de_naissance DATE NOT NULL,
   telephone INTEGER UNIQUE,
   limite_quota INTEGER NOT NULL,
   id_users INTEGER NOT NULL UNIQUE,
   id_inscription INTEGER NOT NULL,
   id_type_adherant INTEGER NOT NULL,
   FOREIGN KEY (id_users)         REFERENCES users(id_users)         ON DELETE CASCADE,
   FOREIGN KEY (id_inscription)   REFERENCES inscription(id_inscription) ON DELETE CASCADE,
   FOREIGN KEY (id_type_adherant) REFERENCES type_adherant(id_type_adherant) ON DELETE CASCADE
);

-- 11) Prêts
CREATE TABLE pret(
   id_pret SERIAL PRIMARY KEY,
   date_pret DATE NOT NULL,
   date_retour_estime DATE NOT NULL,
   date_retour_reel DATE,
   quota_actuel INTEGER NOT NULL,
   id_type_pret INTEGER NOT NULL,
   id_adherant INTEGER NOT NULL,
   id_status INTEGER NOT NULL,
   FOREIGN KEY (id_type_pret) REFERENCES type_pret(id_type_pret) ON DELETE CASCADE,
   FOREIGN KEY (id_adherant)   REFERENCES adherant(id_adherant)   ON DELETE CASCADE,
   FOREIGN KEY (id_status)     REFERENCES status(id_status)       ON DELETE CASCADE
);

-- 12) Réservations
CREATE TABLE reservation(
   id_reservation SERIAL PRIMARY KEY,
   date_reservation DATE,
   id_status INTEGER NOT NULL,
   id_adherant INTEGER NOT NULL,
   FOREIGN KEY (id_status)   REFERENCES status(id_status)   ON DELETE CASCADE,
   FOREIGN KEY (id_adherant) REFERENCES adherant(id_adherant) ON DELETE CASCADE
);

-- 13) Prolongements
CREATE TABLE prolongement(
   id_prolongement SERIAL PRIMARY KEY,
   date_prolongement DATE NOT NULL,
   nouvelle_date_retour DATE,
   nbr_prolongement_actuel INTEGER NOT NULL,
   id_status INTEGER NOT NULL,
   id_pret INTEGER NOT NULL,
   FOREIGN KEY (id_status) REFERENCES status(id_status) ON DELETE CASCADE,
   FOREIGN KEY (id_pret)   REFERENCES pret(id_pret)     ON DELETE CASCADE
);

-- 14) Pénalités
CREATE TABLE penalite(
   id_penalite SERIAL PRIMARY KEY,
   motif VARCHAR(300),
   date_debut_penalite DATE NOT NULL,
   est_reglee BOOLEAN NOT NULL,
   duree INTEGER NOT NULL,
   id_pret INTEGER NOT NULL UNIQUE,
   id_adherant INTEGER NOT NULL UNIQUE,
   FOREIGN KEY (id_pret)     REFERENCES pret(id_pret)     ON DELETE CASCADE,
   FOREIGN KEY (id_adherant) REFERENCES adherant(id_adherant) ON DELETE CASCADE
);

-- 15) Abonnements
CREATE TABLE abonnement(
   id_abonnement SERIAL PRIMARY KEY,
   date_debut DATE NOT NULL,
   date_fin DATE NOT NULL,
   id_adherant INTEGER NOT NULL,
   FOREIGN KEY (id_adherant) REFERENCES adherant(id_adherant) ON DELETE CASCADE
);

-- 16) Association prêt ↔ exemplaire
CREATE TABLE preter_exemplaire(
   id_exemplaire INTEGER NOT NULL,
   id_pret       INTEGER NOT NULL,
   PRIMARY KEY (id_exemplaire, id_pret),
   FOREIGN KEY (id_exemplaire) REFERENCES exemplaire(id_exemplaire) ON DELETE CASCADE,
   FOREIGN KEY (id_pret)       REFERENCES pret(id_pret)           ON DELETE CASCADE
);

-- 17) Association réservation ↔ exemplaire
CREATE TABLE reserver_exemplaire(
   id_exemplaire INTEGER NOT NULL,
   id_reservation INTEGER NOT NULL,
   PRIMARY KEY (id_exemplaire, id_reservation),
   FOREIGN KEY (id_exemplaire)  REFERENCES exemplaire(id_exemplaire)  ON DELETE CASCADE,
   FOREIGN KEY (id_reservation) REFERENCES reservation(id_reservation) ON DELETE CASCADE
);


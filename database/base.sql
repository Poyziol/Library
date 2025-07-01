DROP TABLE reserver_exemplaire;
DROP TABLE preter_exemplaire;
DROP TABLE abonnement;
DROP TABLE penalite;
DROP TABLE prolongement;
DROP TABLE pret;
DROP TABLE adherant;
DROP TABLE inscription;
DROP TABLE reservation;
DROP TABLE type_pret;
DROP TABLE status;
DROP TABLE type_adherant;
DROP TABLE exemplaire;
DROP TABLE etat;
DROP TABLE livre;
DROP TABLE users;
DROP TABLE type_users;

CREATE TABLE livre(
   id_livre SERIAL,
   resume VARCHAR(300),
   titre VARCHAR(70) NOT NULL,
   auteur VARCHAR(50) NOT NULL,
   annee_publication DATE NOT NULL,
   age_min INTEGER NOT NULL,
   PRIMARY KEY(id_livre)
);

CREATE TABLE type_adherant(
   id_type_adherant SERIAL,
   libelle VARCHAR(50) NOT NULL,
   PRIMARY KEY(id_type_adherant),
   UNIQUE(libelle)
);

CREATE TABLE status(
   id_status SERIAL,
   libelle VARCHAR(70) NOT NULL,
   PRIMARY KEY(id_status),
   UNIQUE(libelle)
);

CREATE TABLE type_pret(
   id_type_pret SERIAL,
   libelle VARCHAR(70) NOT NULL,
   PRIMARY KEY(id_type_pret),
   UNIQUE(libelle)
);

CREATE TABLE reservation(
   id_reservation SERIAL,
   date_reservation DATE,
   id_status INTEGER NOT NULL,
   PRIMARY KEY(id_reservation),
   FOREIGN KEY(id_status) REFERENCES status(id_status)
);

CREATE TABLE type_users(
   id_type_users SERIAL,
   libelle VARCHAR(50) NOT NULL,
   PRIMARY KEY(id_type_users),
   UNIQUE(libelle)
);

CREATE TABLE etat(
   id_etat BOOLEAN,
   libelle VARCHAR(50) NOT NULL,
   PRIMARY KEY(id_etat),
   UNIQUE(libelle)
);

CREATE TABLE exemplaire(
   id_exemplaire SERIAL,
   disponible BOOLEAN NOT NULL,
   id_etat BOOLEAN NOT NULL,
   id_livre INTEGER NOT NULL,
   PRIMARY KEY(id_exemplaire),
   FOREIGN KEY(id_etat) REFERENCES etat(id_etat),
   FOREIGN KEY(id_livre) REFERENCES livre(id_livre)
);

CREATE TABLE inscription(
   id_inscription SERIAL,
   date_inscription DATE NOT NULL,
   id_status INTEGER NOT NULL,
   PRIMARY KEY(id_inscription),
   FOREIGN KEY(id_status) REFERENCES status(id_status)
);

CREATE TABLE users(
   id_users SERIAL,
   nom VARCHAR(50) NOT NULL,
   mot_de_passe VARCHAR(16) NOT NULL,
   id_type_users INTEGER NOT NULL,
   PRIMARY KEY(id_users),
   UNIQUE(nom),
   FOREIGN KEY(id_type_users) REFERENCES type_users(id_type_users)
);

CREATE TABLE adherant(
   id_adherant SERIAL,
   nom VARCHAR(50) NOT NULL,
   prenom VARCHAR(50) NOT NULL,
   date_de_naissance DATE NOT NULL,
   telephone INTEGER,
   limite_quota INTEGER NOT NULL,
   id_reservation INTEGER NOT NULL,
   id_inscription INTEGER NOT NULL,
   id_type_adherant INTEGER NOT NULL,
   PRIMARY KEY(id_adherant),
   UNIQUE(telephone),
   FOREIGN KEY(id_reservation) REFERENCES reservation(id_reservation),
   FOREIGN KEY(id_inscription) REFERENCES inscription(id_inscription),
   FOREIGN KEY(id_type_adherant) REFERENCES type_adherant(id_type_adherant)
);

CREATE TABLE pret(
   id_pret SERIAL,
   date_pret DATE NOT NULL,
   date_retour_estime DATE NOT NULL,
   date_retour_reel DATE,
   quota_actuel INTEGER NOT NULL,
   id_type_pret INTEGER NOT NULL,
   id_adherant INTEGER NOT NULL,
   id_status INTEGER NOT NULL,
   PRIMARY KEY(id_pret),
   FOREIGN KEY(id_type_pret) REFERENCES type_pret(id_type_pret),
   FOREIGN KEY(id_adherant) REFERENCES adherant(id_adherant),
   FOREIGN KEY(id_status) REFERENCES status(id_status)
);

CREATE TABLE prolongement(
   id_prolongement SERIAL,
   date_prolongement DATE NOT NULL,
   nouvelle_date_retour DATE,
   nbr_prolongement_actuel INTEGER NOT NULL,
   id_pret INTEGER NOT NULL,
   PRIMARY KEY(id_prolongement),
   FOREIGN KEY(id_pret) REFERENCES pret(id_pret)
);

CREATE TABLE penalite(
   id_penalite SERIAL,
   motif VARCHAR(300),
   date_debut_penalite DATE NOT NULL,
   est_reglee BOOLEAN NOT NULL,
   duree INTEGER NOT NULL,
   id_pret INTEGER NOT NULL,
   id_adherant INTEGER NOT NULL,
   PRIMARY KEY(id_penalite),
   UNIQUE(id_pret),
   UNIQUE(id_adherant),
   FOREIGN KEY(id_pret) REFERENCES pret(id_pret),
   FOREIGN KEY(id_adherant) REFERENCES adherant(id_adherant)
);

CREATE TABLE abonnement(
   id_abonnement SERIAL,
   date_debut DATE NOT NULL,
   date_fin DATE NOT NULL,
   id_adherant INTEGER NOT NULL,
   PRIMARY KEY(id_abonnement),
   FOREIGN KEY(id_adherant) REFERENCES adherant(id_adherant)
);

CREATE TABLE preter_exemplaire(
   id_exemplaire INTEGER,
   id_pret INTEGER,
   PRIMARY KEY(id_exemplaire, id_pret),
   FOREIGN KEY(id_exemplaire) REFERENCES exemplaire(id_exemplaire),
   FOREIGN KEY(id_pret) REFERENCES pret(id_pret)
);

CREATE TABLE reserver_exemplaire(
   id_exemplaire INTEGER,
   id_reservation INTEGER,
   PRIMARY KEY(id_exemplaire, id_reservation),
   FOREIGN KEY(id_exemplaire) REFERENCES exemplaire(id_exemplaire),
   FOREIGN KEY(id_reservation) REFERENCES reservation(id_reservation)
);

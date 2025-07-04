-- 1) Type d adhérent
INSERT INTO type_adherant(libelle) VALUES
  ('Professionnel'),
  ('Expert'),
  ('Etudiant');

-- 2) Type d utilisateurs
INSERT INTO type_users(libelle) VALUES
  ('Bibliothecaire'),
  ('Client');

-- 3) Statuts de prêt / réservation
INSERT INTO status(libelle) VALUES
  ('en_cours'),
  ('retourne'),
  ('reserve');

-- 4) Types de prêt
INSERT INTO type_pret(libelle) VALUES
  ('lecture_sur_place'),
  ('emporter'),
  ('reserver');

-- 5) États d un exemplaire
INSERT INTO etat(id_etat, libelle) VALUES
  (TRUE,  'Bon état'),
  (FALSE, 'Endommagé');

-- 6) Livres
INSERT INTO livre(resume, titre, auteur, annee_publication, age_min) VALUES
  ('Un classique de la littérature française.', 'Le Comte de Monte-Cristo', 'Alexandre Dumas', '1844-08-28', 16),
  ('Introduction aux bases de données relationnelles.', 'SGBD et SQL', 'Jean Dupont', '2019-02-15', 14),
  ('Roman d aventures pour adolescents.', 'Voyage au centre de la Terre', 'Jules Verne', '1864-11-25', 12);

-- 7) Exemplaires
INSERT INTO exemplaire(disponible, id_etat, id_livre) VALUES
  (TRUE,  TRUE,  1),
  (TRUE,  TRUE,  2),
  (FALSE, TRUE,  3);

-- 8) Réservations
INSERT INTO reservation(date_reservation, id_status) VALUES
  ('2025-07-01', 3),  -- statut ‘reserve 
  ('2025-06-25', 1);  -- statut ‘en_cours  (par exemple)

-- 9) Inscriptions d adhérents
INSERT INTO inscription(date_inscription, id_status) VALUES
  ('2025-01-10', 1),  -- en cours
  ('2024-09-05', 2);  -- retourné/archivé

-- 10) Utilisateurs (pour login)
INSERT INTO users(nom, mot_de_passe, id_type_users) VALUES
  ('admin',    '123', 1),  -- Bibliothécaire
  ('client1',  '123', 2),  -- Client
  ('client2',  '123', 2),
  ('client3',  '123', 2),
  ('client4',  '123', 2);

-- 11) Adhérents
INSERT INTO adherant(nom, prenom, date_de_naissance, telephone, limite_quota, id_users, id_reservation, id_inscription, id_type_adherant) VALUES
  ('Martin',    'Alice',  '1995-04-12', 3, 3, 5, 1, 1, 3),  -- Étudiant
  ('Leblanc',   'Bruno',  '1980-11-03', 4, 5, 3, 2, 2, 1);  -- Professionnel

-- 12) Prêts
INSERT INTO pret(date_pret, date_retour_estime, date_retour_reel, quota_actuel, id_type_pret, id_adherant, id_status) VALUES
  ('2025-06-20', '2025-06-27', NULL, 4, 2, 1, 1),  -- Emprunt à emporter, non encore retourné
  ('2025-06-01', '2025-06-01', '2025-06-01', 0, 1, 2, 2);  -- Lecture sur place, déjà retourné

-- 13) Prolongements
INSERT INTO prolongement(date_prolongement, nouvelle_date_retour, nbr_prolongement_actuel, id_pret) VALUES
  ('2025-06-26', '2025-07-04', 1, 1);

-- 14) Pénalités
INSERT INTO penalite(motif, date_debut_penalite, est_reglee, duree, id_pret, id_adherant) VALUES
  ('Retard de retour', '2025-06-28', FALSE, 7, 1, 1);

-- 15) Abonnements
INSERT INTO abonnement(date_debut, date_fin, id_adherant) VALUES
  ('2025-01-01', '2025-12-31', 1),
  ('2024-07-01', '2025-06-30', 2);

-- 16) Association prêt ↔ exemplaire
INSERT INTO preter_exemplaire(id_exemplaire, id_pret) VALUES
  (1, 1),
  (2, 2);

-- 17) Association réservation ↔ exemplaire
INSERT INTO reserver_exemplaire(id_exemplaire, id_reservation) VALUES
  (3, 1);


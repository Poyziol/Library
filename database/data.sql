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
  ('Roman d aventures pour adolescents.', 'Voyage au centre de la Terre', 'Jules Verne', '1864-11-25', 12),
('Un classique de la littérature française.', 'Le Comte de Monte-Cristo', 'Alexandre Dumas', '1844-01-01', 16),
  ('Introduction aux bases de données relationnelles.', 'SGBD et SQL', 'Jean Dupont', '2019-01-01', 14),
  ('Roman d aventures pour adolescents.', 'Voyage au centre de la Terre', 'Jules Verne', '1864-01-01', 12);

-- 7) Exemplaires
INSERT INTO exemplaire(disponible, id_etat, id_livre) VALUES
  (TRUE,  TRUE,  1),
  (TRUE,  TRUE,  2),
  (FALSE, TRUE,  3);

-- 9) Inscriptions d adhérents
INSERT INTO inscription(date_inscription, id_status) VALUES
  ('2025-01-10', 1),
  ('2024-09-05', 2),
  ('2025-03-15', 1),
  ('2025-04-20', 1),
  ('2025-05-10', 1);

-- 10) Utilisateurs
INSERT INTO users(nom, mot_de_passe, id_type_users) VALUES
  ('admin',    '123', 1),
  ('client1',  '123', 2),
  ('client2',  '123', 2),
  ('client3',  '123', 2),
  ('client4',  '123', 2),
  ('client5', '123', 2),
  ('client6', '123', 2),
  ('client7', '123', 2);

-- 11) Adhérents (téléphones uniques)
INSERT INTO adherant(nom, prenom, date_de_naissance, telephone, limite_quota, id_users, id_inscription, id_type_adherant) VALUES
  ('Martin',    'Alice',  '1995-04-12', 611111111, 3, 5, 1, 3),
  ('Leblanc',   'Bruno',  '1980-11-03', 622222222, 5, 3, 2, 1),
  ('Bernard', 'Sophie', '1990-08-22', 633333333, 4, 6, 3, 1),
  ('Petit', 'Thomas', '1998-12-05', 644444444, 3, 7, 4, 3),
  ('Robert', 'Julie', '1985-03-30', 655555555, 5, 8, 5, 2);

-- 8) Réservations (après adhérents)
INSERT INTO reservation(date_reservation, id_status, id_adherant) VALUES
  ('2025-07-01', 3, 1),  -- Adhérent ID 1
  ('2025-06-25', 1, 4);  -- Adhérent ID 4

-- 12) Prêts (après adhérents)
INSERT INTO pret(date_pret, date_retour_estime, date_retour_reel, quota_actuel, id_type_pret, id_adherant, id_status) VALUES
  ('2025-06-20', '2025-06-27', NULL, 4, 2, 1, 1),
  ('2025-06-01', '2025-06-01', '2025-06-01', 0, 1, 2, 2),
  ('2025-06-10', '2025-06-24', NULL, 2, 2, 3, 1),
  ('2025-07-01', '2025-07-15', NULL, 1, 2, 3, 1),
  ('2025-06-28', '2025-07-12', NULL, 3, 2, 4, 1),
  ('2025-06-15', '2025-06-29', '2025-06-28', 0, 2, 5, 2),
  ('2025-07-02', '2025-07-16', NULL, 2, 1, 5, 1);

-- 13) Prolongements (après prêts)
INSERT INTO prolongement(date_prolongement, nouvelle_date_retour, nbr_prolongement_actuel, id_pret) VALUES
  ('2025-06-26', '2025-07-04', 1, 1);  -- Prêt ID 1

-- 14) Pénalités (prêts et adhérents uniques)
INSERT INTO penalite(motif, date_debut_penalite, est_reglee, duree, id_pret, id_adherant) VALUES
  ('Retard de retour', '2025-06-28', FALSE, 7, 1, 1),  -- Prêt 1/Adhérent 1
  ('Livre abîmé', '2025-06-30', TRUE, 10, 4, 3);       -- Prêt 4/Adhérent 3

-- 15) Abonnements (après adhérents)
INSERT INTO abonnement(date_debut, date_fin, id_adherant) VALUES
  ('2025-01-01', '2025-12-31', 1),  -- Adhérent ID 1
  ('2024-07-01', '2025-06-30', 2);  -- Adhérent ID 2

-- 16) Association prêt ↔ exemplaire (après prêts et exemplaires)
INSERT INTO preter_exemplaire(id_exemplaire, id_pret) VALUES
  (1, 1),  -- Exemplaire 1/Prêt 1
  (2, 2);  -- Exemplaire 2/Prêt 2

-- 17) Association réservation ↔ exemplaire (après réservations)
INSERT INTO reserver_exemplaire(id_exemplaire, id_reservation) VALUES
  (3, 1);  -- Exemplaire 3/Réservation 1

  
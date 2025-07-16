-- 1) Type d’adhérent
INSERT INTO type_adherant(libelle) VALUES
  ('Professionnel'),
  ('Expert'),
  ('Etudiant');

-- 2) Type d’utilisateurs
INSERT INTO type_users(libelle) VALUES
  ('Bibliothecaire'),
  ('Client');

-- 3) Statuts de prêt / réservation
INSERT INTO status(libelle) VALUES
  ('EN_ATTENTE'),
  ('EN_COURS'),
  ('CONFIRMEE'),
  ('REFUSE');

-- 4) Types de prêt
INSERT INTO type_pret(libelle) VALUES
  ('normal'),
  ('reservation');

-- 5) États d’un exemplaire
INSERT INTO etat(id_etat, libelle) VALUES
  (TRUE,  'Bon état'),
  (FALSE, 'Endommagé');

-- 6) Livres
INSERT INTO livre(resume, titre, auteur, annee_publication, age_min) VALUES
  ('Un classique de la litterature française.', 'Les Miserables', 'Victor Hugo', '1844-08-28', 16),
  ('Philosophie', 'L Etranger', 'Albert Camus', '2019-02-15', 14),
  ('Jeunesse/Fantastique', 'J.K. Rowling', 'Jules Verne', '1864-11-25', 12);

-- 7) Exemplaires (les premiers pour prêts/réservations existants)
INSERT INTO exemplaire(disponible, id_etat, id_livre) VALUES
  (TRUE,  TRUE,  1),
  (TRUE,  TRUE,  1),
  (TRUE,  TRUE,  1),
  (TRUE,  TRUE,  2),
  (TRUE,  TRUE,  2),
  (TRUE, TRUE,  3);

-- 8) Inscriptions d adhérents
INSERT INTO inscription(date_inscription, id_status) VALUES
  ('2025-01-10', 3),
  ('2024-09-05', 2),
  ('2025-03-15', 3),
  ('2025-04-20', 3),
  ('2025-05-10', 3);

-- 9) Utilisateurs
INSERT INTO users(nom, mot_de_passe, id_type_users) VALUES
  ('admin',    '123', 1),
  ('ETU001',  '123', 2),
  ('ETU002',  '123', 2),
  ('ETU003',  '123', 2),
  ('ENS001',  '123', 2),
  ('ENS002',  '123', 2),
  ('ENS003',  '123', 2),
  ('PROF001',  '123', 2),
  ('PROF002',  '123', 2);

-- 10) Adhérents (téléphones uniques)
INSERT INTO adherant(nom, prenom, date_de_naissance, telephone, limite_quota, id_users, id_inscription, id_type_adherant) VALUES
  ('Amine',  'Besaid',  '1995-04-12', 611111111, 2, 2, 1, 1),
  ('Sarah', 'El Khattabi',  '1980-11-03', 622222222, 2, 3, 2, 1),
  ('Youssef','Moujahid', '1990-08-22', 633333333, 2, 4, 3, 1),
  ('Nadia',  'Benali', '1998-12-05', 644444424, 3, 5, 4, 2),
  ('Karim', 'Haddadi',  '1985-03-30', 655555555, 3, 6, 5, 2),
  ('Salima',  'Touhami', '1998-12-05', 644844444, 3, 7, 4, 2),
  ('Rachid',  'El Mansouri', '1998-12-05', 644447444, 4, 8, 4, 3),
  ('Amina',  'Zerouali', '1998-12-05', 649444444, 4, 9, 4, 3);

-- 14) Pénalités (prêts et adhérents uniques)
INSERT INTO penalite(motif, date_debut_penalite, est_reglee, duree, id_pret, id_adherant) VALUES
  ('Retard de retour', '2025-06-28', TRUE, 10, NULL, 1),
  ('Livre abîmé',      '2025-06-30', TRUE,  10, NULL, 2),
  ('Retard de retour', '2025-06-28', TRUE, 10, NULL, 3),
  ('Retard de retour', '2025-06-28', TRUE, 10, NULL, 4),
  ('Retard de retour', '2025-06-28', TRUE, 10, NULL, 5),
  ('Retard de retour', '2025-06-28', TRUE, 10, NULL, 6),
  ('Retard de retour', '2025-06-28', TRUE, 10, NULL, 7),
  ('Retard de retour', '2025-06-28', TRUE, 10, NULL, 8);

-- 15) Abonnements (après adhérents)
INSERT INTO abonnement(date_debut, date_fin, id_adherant) VALUES
  ('2025-02-01', '2025-07-24', 1),
  ('2025-02-01', '2025-07-01', 2),
  ('2025-04-01', '2025-12-01', 3),
  ('2025-07-01', '2026-07-01', 4),
  ('2025-08-01', '2026-05-01', 5),
  ('2025-07-01', '2026-06-01', 6),
  ('2025-06-01', '2025-12-24', 7),
  ('2024-10-01', '2025-06-01', 8);


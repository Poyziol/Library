-- 3. Insertion de données d’exemple dans promotion
INSERT INTO promotion (nom) VALUES
  ('Promotion 2023'),
  ('Promotion 2024'),
  ('Promotion 2025');

-- 4. Insertion de données d’exemple dans eleve
--    Certains élèves sont rattachés à une promotion, d’autres non.
INSERT INTO eleve (id_promotion, nom) VALUES
  (1, 'Alice Dupont'),
  (1, 'Boris Martin'),
  (2, 'Carla Fernandez'),
  (3, 'David Nguyen');
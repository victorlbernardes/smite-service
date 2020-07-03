DROP TABLE IF EXISTS player;

CREATE TABLE player (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  player_id VARCHAR(250) NOT NULL,
  username VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  last_session_id VARCHAR(250) DEFAULT NULL
);

INSERT INTO player (player_id, username, password) VALUES
  ('12345', 'Admin', '$2y$12$6dXGRIV0.F15uP6nx8.oru9oZ7oNR4l5CaHahlkLNb2RDib.1ZU5G'),
  ('000', 'Akillian', '$2y$12$6dXGRIV0.F15uP6nx8.oru9oZ7oNR4l5CaHahlkLNb2RDib.1ZU5G'),
  ('111', 'Celtzero', '$2y$12$6dXGRIV0.F15uP6nx8.oru9oZ7oNR4l5CaHahlkLNb2RDib.1ZU5G'),
  ('222', 'Daviola2', '$2y$12$6dXGRIV0.F15uP6nx8.oru9oZ7oNR4l5CaHahlkLNb2RDib.1ZU5G'),
  ('333', 'DosAnjos', '$2y$12$6dXGRIV0.F15uP6nx8.oru9oZ7oNR4l5CaHahlkLNb2RDib.1ZU5G'),
  ('444', 'Mantovani', '$2y$12$6dXGRIV0.F15uP6nx8.oru9oZ7oNR4l5CaHahlkLNb2RDib.1ZU5G'),
  ('555', 'ZTrivium', '$2y$12$6dXGRIV0.F15uP6nx8.oru9oZ7oNR4l5CaHahlkLNb2RDib.1ZU5G');
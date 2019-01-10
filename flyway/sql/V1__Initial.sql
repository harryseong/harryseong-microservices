CREATE TABLE artist (
  id            INTEGER NOT NULL AUTO_INCREMENT,
  name          VARCHAR(255),
  created_date  DATETIME,
  PRIMARY KEY (id)
);

CREATE TABLE song (
  id            INTEGER NOT NULL AUTO_INCREMENT,
  name          VARCHAR(255),
  created_date  DATETIME,
  PRIMARY KEY (id)
);

CREATE TABLE artist_song (
  artist_id INTEGER NOT NULL,
  song_id   INTEGER NOT NULL
);

ALTER TABLE artist_song
  ADD CONSTRAINT FK6wndcsr7d8edk8e5h3diyskxg FOREIGN KEY (artist_id) REFERENCES artist(id);

ALTER TABLE artist_song
  ADD CONSTRAINT FK3mcovx0ses6cqpfcof2px67x7 FOREIGN KEY (song_id) REFERENCES song(id);
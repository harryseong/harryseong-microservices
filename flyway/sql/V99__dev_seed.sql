INSERT INTO artist (id, name, created_date) VALUES (1, 'John Legend', NOW());
INSERT INTO artist (id, name, created_date) VALUES (2, 'Beyonce', NOW());
INSERT INTO song (id, name, created_date) VALUES (1, 'All of Me', NOW());
INSERT INTO song (id, name, created_date) VALUES (2, 'Halo', NOW());
INSERT INTO artist_song (artist_id, song_id) VALUES (1, 1);
INSERT INTO artist_song (artist_id, song_id) VALUES (2, 2);
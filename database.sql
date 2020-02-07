DROP TABLE IF EXISTS public.media_modifications;
DROP TABLE IF EXISTS public.beacon_modifications;
DROP TABLE IF EXISTS public.artwork_media;
DROP TABLE IF EXISTS public.exhibition_media;
DROP TABLE IF EXISTS public.administrators;
DROP TABLE IF EXISTS public.media;
DROP TABLE IF EXISTS public.beacons;
DROP TABLE IF EXISTS public.artworks;
DROP TABLE IF EXISTS public.exhibitions;

DROP SEQUENCE IF EXISTS exhibition_id_seq;
DROP SEQUENCE IF EXISTS artwork_id_seq;
DROP SEQUENCE IF EXISTS administrator_id_seq;
DROP SEQUENCE IF EXISTS media_id_seq;
DROP SEQUENCE IF EXISTS beacon_id_seq;

CREATE SEQUENCE exhibition_id_seq;
CREATE SEQUENCE artwork_id_seq;
CREATE SEQUENCE administrator_id_seq;
CREATE SEQUENCE media_id_seq;
CREATE SEQUENCE beacon_id_seq;

CREATE TABLE public.exhibitions
(
    id INTEGER DEFAULT NEXTVAL('exhibition_id_seq'),
    name VARCHAR(255) NOT NULL,
    opening_date DATE NOT NULL,
    closing_date DATE,
    location VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE public.artworks
(
    id INTEGER DEFAULT NEXTVAL('artwork_id_seq'),
    name VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    country VARCHAR(255),
    description VARCHAR(255),
    exhibition_id INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (exhibition_id)
        REFERENCES public.exhibitions (id)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);

CREATE TABLE public.beacons
(
    id INTEGER DEFAULT NEXTVAL('beacon_id_seq'),
	mac VARCHAR(255),
    artwork_id INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (artwork_id)
        REFERENCES public.artworks (id)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);

CREATE TABLE public.media
(
    id INTEGER DEFAULT NEXTVAL('media_id_seq'),
    file_type VARCHAR(255) NOT NULL,
	extension VARCHAR(10) NOT NULL,
	display_name VARCHAR(255) NOT NULL,
	file_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.administrators
(
    id INTEGER DEFAULT NEXTVAL('administrator_id_seq'),
    email VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
  	CONSTRAINT username_UNIQUE UNIQUE (username)
);

CREATE TABLE public.artwork_media
(
    artwork_id INTEGER NOT NULL,
    media_id INTEGER NOT NULL,
    PRIMARY KEY (artwork_id, media_id),
    FOREIGN KEY (artwork_id)
        REFERENCES public.artworks (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (media_id)
        REFERENCES public.media (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.exhibition_media
(
    exhibition_id INTEGER NOT NULL,
    media_id INTEGER NOT NULL,
    PRIMARY KEY (exhibition_id, media_id),
    FOREIGN KEY (exhibition_id)
        REFERENCES public.exhibitions (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (media_id)
        REFERENCES public.media (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.beacon_modifications
(
    date TIMESTAMP NOT NULL DEFAULT NOW(),
    description VARCHAR(255) NOT NULL,
    administrator_id INTEGER NOT NULL,
    beacon_id INTEGER NOT NULL,
    PRIMARY KEY (date, administrator_id, beacon_id),
    FOREIGN KEY (beacon_id)
        REFERENCES public.beacons (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (administrator_id)
        REFERENCES public.administrators (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.media_modifications
(
    date TIMESTAMP DEFAULT NOW(),
    description VARCHAR(255) NOT NULL,
    administrator_id INTEGER NOT NULL,
    media_id INTEGER NOT NULL,
    PRIMARY KEY (date, administrator_id, media_id),
    FOREIGN KEY (administrator_id)
        REFERENCES public.administrators (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fkmedia_modi293841 FOREIGN KEY (media_id)
        REFERENCES public.media (id)
		ON UPDATE CASCADE
        ON DELETE CASCADE
);
INSERT INTO public.exhibitions(name,opening_date,closing_date,location) VALUES 
('Italian Classics','2019/10/20','2019/11/19','Area 1'),
('French Revolution','2019/10/20','2020/01/31','Area 2'),
('Greek Mythology','2020/02/01','2020/05/31','Area 2');

INSERT INTO public.artworks(name,author,country,exhibition_id) VALUES 
('La Gioconda','Leonardo Da Vinci','Italy',1),
('La Creación de Adán','Miguel Ángel','Italy',1),
('La última cena','Leonardo Da Vinci','Italy',1);

INSERT INTO public.artworks(name,author,country,exhibition_id) VALUES 
('La Libertad Guiando al Pueblo','Eugène Delacroix','France',2),
('La Ejecución de Cadoudal','Georges Cadoudal','France',2);

INSERT INTO public.beacons(artwork_id,mac) VALUES 
(1,'UKAH832'),
(1,'JKDD947'),
(1,'KDNRO97'),
(2,'PAME293'),
(2,'UDMG012');

INSERT INTO public.administrators(first_name, last_name, email, password, username) VALUES
('Luat','Dinh','luatdb@gmail.com','$2y$12$SgJuB8tnWArJhnmmFqhWqOFF4/h31Hpt43vzCfu1IJLJ3HulAixQi','luatdb'),
('Richard','Vinueza','richardvr@gmail.com','$2y$12$SgJuB8tnWArJhnmmFqhWqOFF4/h31Hpt43vzCfu1IJLJ3HulAixQi','richardvr');

INSERT INTO public.beacon_modifications(date,administrator_id,beacon_id,description) VALUES
('2019-11-19 20:00:00', 1, 1, 'Hello'),
('2019-11-19 21:00:00', 1, 1, 'nothing changed'),
('2019-11-19 20:00:00', 1, 2, 'something changed');

INSERT INTO public.media(file_type,file_name,display_name,extension) VALUES
('image','la_gioconda','La Gioconda - Mona Lisa','jpg'),
('video','la_gioconda_following_eyes', 'La Gioconda - Following Eyes','mp4'),
('audio','o_fortuna','O Fortuna - Carl Orff','mp3'),
('image','la_ejecucion_de_cadoudal','La Ejecución de George Cadoudal','jpg'),
('image','la_libertad_guiando_al_pueblo','La Libertad Guiando al Pueblo','jpg');

INSERT INTO public.media_modifications(date,administrator_id,media_id,description) VALUES
('2019-11-19 20:00:00', 1, 1, 'Hello'),
('2019-11-19 21:00:00', 1, 1, 'nothing changed'),
('2019-11-19 20:00:00', 1, 2, 'something changed');

INSERT INTO public.artwork_media(artwork_id, media_id) VALUES
('1','1'),
('1','2'),
('4','5'),
('5','4');

INSERT INTO public.exhibition_media(exhibition_id, media_id) VALUES
('1','3');

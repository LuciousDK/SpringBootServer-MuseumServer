DROP TABLE IF EXISTS public.media_modifications;
DROP TABLE IF EXISTS public.beacon_modifications;
DROP TABLE IF EXISTS public.artwork_modifications;
DROP TABLE IF EXISTS public.exhibition_modifications;
DROP TABLE IF EXISTS public.user_modifications;
DROP TABLE IF EXISTS public.artwork_media;
DROP TABLE IF EXISTS public.exhibition_media;
DROP TABLE IF EXISTS public.app_users;
DROP TABLE IF EXISTS public.media;
DROP TABLE IF EXISTS public.beacons;
DROP TABLE IF EXISTS public.artworks;
DROP TABLE IF EXISTS public.exhibitions;
DROP TABLE IF EXISTS public.states;
DROP TABLE IF EXISTS public.roles;

DROP SEQUENCE IF EXISTS exhibition_id_seq;
DROP SEQUENCE IF EXISTS artwork_id_seq;
DROP SEQUENCE IF EXISTS user_id_seq;
DROP SEQUENCE IF EXISTS media_id_seq;
DROP SEQUENCE IF EXISTS beacon_id_seq;
DROP SEQUENCE IF EXISTS state_id_seq;
DROP SEQUENCE IF EXISTS role_id_seq;

CREATE SEQUENCE exhibition_id_seq;
CREATE SEQUENCE artwork_id_seq;
CREATE SEQUENCE user_id_seq;
CREATE SEQUENCE media_id_seq;
CREATE SEQUENCE beacon_id_seq;
CREATE SEQUENCE state_id_seq;
CREATE SEQUENCE role_id_seq;

CREATE TABLE public.roles
(
	id INTEGER DEFAULT NEXTVAL('role_id_seq'),
    name VARCHAR(25) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE public.states
(
	id INTEGER DEFAULT NEXTVAL('state_id_seq'),
    name VARCHAR(15) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE public.app_users
(
    id INTEGER DEFAULT NEXTVAL('user_id_seq'),
    email VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255),
    last_name VARCHAR(255),
    first_name VARCHAR(255),
	role_id INTEGER DEFAULT(NUll),
	state_id INTEGER NOT NULL DEFAULT(1),
    PRIMARY KEY (id),
	FOREIGN KEY (role_id) 
		REFERENCES public.roles (id)
		ON DELETE SET NULL
		ON UPDATE CASCADE,
	FOREIGN KEY (state_id) 
		REFERENCES public.states (id)
		ON DELETE SET NULL
		ON UPDATE CASCADE
);

CREATE TABLE public.exhibitions
(
    id INTEGER DEFAULT NEXTVAL('exhibition_id_seq'),
    name VARCHAR(255) NOT NULL,
    opening_date DATE,
    closing_date DATE,
    location VARCHAR(255),
	state_id INTEGER NOT NULL DEFAULT(1),
    PRIMARY KEY (id),
	FOREIGN KEY (state_id) 
		REFERENCES public.states (id)
		ON DELETE SET NULL
		ON UPDATE CASCADE
	
);

CREATE TABLE public.artworks
(
    id INTEGER DEFAULT NEXTVAL('artwork_id_seq'),
    name VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    country VARCHAR(255),
    description VARCHAR(10485760),
    exhibition_id INTEGER,
	state_id INTEGER NOT NULL DEFAULT(1),
    PRIMARY KEY (id),
	FOREIGN KEY (state_id) 
		REFERENCES public.states (id)
		ON DELETE SET NULL
		ON UPDATE CASCADE,
    FOREIGN KEY (exhibition_id)
        REFERENCES public.exhibitions (id)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);

CREATE TABLE public.beacons
(
    id INTEGER DEFAULT NEXTVAL('beacon_id_seq'),
	uuid VARCHAR(255),
    artwork_id INTEGER,
	state_id INTEGER NOT NULL DEFAULT(1),
    PRIMARY KEY (id),
	FOREIGN KEY (state_id) 
		REFERENCES public.states (id)
		ON DELETE SET NULL
		ON UPDATE CASCADE,
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

CREATE TABLE public.exhibition_modifications
(
    date TIMESTAMP NOT NULL DEFAULT NOW(),
    description VARCHAR(10485760) NOT NULL,
    user_id INTEGER NOT NULL,
    exhibition_id INTEGER NOT NULL,
    PRIMARY KEY (date, user_id, exhibition_id),
    FOREIGN KEY (exhibition_id)
        REFERENCES public.exhibitions (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (user_id)
        REFERENCES public.app_users (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.artwork_modifications
(
    date TIMESTAMP NOT NULL DEFAULT NOW(),
    description VARCHAR(10485760) NOT NULL,
    user_id INTEGER NOT NULL,
    artwork_id INTEGER NOT NULL,
    PRIMARY KEY (date, user_id, artwork_id),
    FOREIGN KEY (artwork_id)
        REFERENCES public.artworks (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (user_id)
        REFERENCES public.app_users (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.user_modifications
(
    date TIMESTAMP NOT NULL DEFAULT NOW(),
    description VARCHAR(10485760) NOT NULL,
    user_id INTEGER NOT NULL,
    modified_user_id INTEGER NOT NULL,
    PRIMARY KEY (date, user_id, modified_user_id),
    FOREIGN KEY (modified_user_id)
        REFERENCES public.app_users (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (user_id)
        REFERENCES public.app_users (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.beacon_modifications
(
    date TIMESTAMP NOT NULL DEFAULT NOW(),
    description VARCHAR(10485760) NOT NULL,
    user_id INTEGER NOT NULL,
    beacon_id INTEGER NOT NULL,
    PRIMARY KEY (date, user_id, beacon_id),
    FOREIGN KEY (beacon_id)
        REFERENCES public.beacons (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (user_id)
        REFERENCES public.app_users (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.media_modifications
(
    date TIMESTAMP DEFAULT NOW(),
    description VARCHAR(10485760) NOT NULL,
    user_id INTEGER NOT NULL,
    media_id INTEGER NOT NULL,
    PRIMARY KEY (date, user_id, media_id),
    FOREIGN KEY (user_id)
        REFERENCES public.app_users (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (media_id)
        REFERENCES public.media (id)
		ON UPDATE CASCADE
        ON DELETE CASCADE
);

INSERT INTO public.roles(name) VALUES
('ADMIN'),
('USER');

INSERT INTO public.states(name) VALUES
('ACTIVE'),
('INACTIVE');

INSERT INTO public.app_users(first_name, last_name, email, password, username, role_id) VALUES
('Luat','Dinh','luatdb@gmail.com','$2y$12$SgJuB8tnWArJhnmmFqhWqOFF4/h31Hpt43vzCfu1IJLJ3HulAixQi','luatdb', 1),
('Richard','Vinueza','richardvr@gmail.com','$2y$12$SgJuB8tnWArJhnmmFqhWqOFF4/h31Hpt43vzCfu1IJLJ3HulAixQi','richardvr', 1),
('Admin',null,'admin@admin.com','$2y$12$SgJuB8tnWArJhnmmFqhWqOFF4/h31Hpt43vzCfu1IJLJ3HulAixQi','admin', 1);

UPDATE public.app_users SET state_id = 2 WHERE id = 2;
UPDATE public.app_users SET role_id = (2) WHERE id = 2;

INSERT INTO public.exhibitions(name,opening_date,closing_date,location) VALUES 
('Italian Classics','2019/10/20','2019/11/19','Area 1'),
('French Revolution','2019/10/20','2020/01/31','Area 2'),
('Greek Mythology','2020/02/01','2020/05/31','Area 2');

INSERT INTO public.artworks(name,author,country,description,exhibition_id) VALUES 
('La Gioconda','Leonardo Da Vinci','Italy','El Retrato de Lisa Gherardini, esposa de Francesco del Giocondo, más conocido como La Gioconda o La Mona Lisa, es una obra pictórica del polímata renacentista italiano Leonardo da Vinci. Fue adquirida por el rey Francisco I de Francia a comienzos del siglo XVI y desde entonces es propiedad del Estado Francés. Se halla expuesta en el Museo del Louvre de París, siendo, sin duda, la «joya» de sus colecciones. Su nombre, La Gioconda (la alegre, en castellano), deriva de la tesis más aceptada acerca de la identidad de la modelo: la esposa de Francesco Bartolomeo de Giocondo, que realmente se llamaba Lisa Gherardini, de donde viene su otro nombre: Mona (señora, en el italiano antiguo) Lisa. El Museo del Louvre acepta el título completo indicado al principio como el título original de la obra, aunque no reconoce la identidad de la modelo y tan solo la acepta como una hipótesis. Es un óleo sobre tabla de álamo de 77 × 53 cm, pintado entre 1503 y 1519, y retocado varias veces por el autor. Se considera el ejemplo más logrado de sfumato, técnica muy característica de Leonardo, si bien actualmente su colorido original es menos perceptible por el oscurecimiento de los barnices. El cuadro está protegido por múltiples sistemas de seguridad y ambientado a temperatura estable para su preservación óptima. Es revisado constantemente para verificar y prevenir su deterioro. Por medio de estudios históricos se ha determinado que la modelo podría ser una vecina de Leonardo, que podrían conocerse sus descendientes y que la modelo podría haber estado embarazada, por la forma de esconder que tienen sus manos. Pese a todas las suposiciones, las respuestas en firme a los varios interrogantes en torno a la obra de arte resultan francamente insuficientes, lo cual genera más curiosidad entre los admiradores del cuadro. La fama de esta pintura no se basa únicamente en la técnica empleada o en su belleza, sino también en los misterios que la rodean. Además, el robo que sufrió en 1911, las reproducciones realizadas, las múltiples obras de arte que se han inspirado en el cuadro y las parodias existentes contribuyen a convertir a La Gioconda en el cuadro más famoso del mundo, visitado por millones de personas anualmente.',1),
('La Creación de Adán','Miguel Ángel','Italy','',1),
('La Última cena','Leonardo Da Vinci','Italy','',1);

INSERT INTO public.artworks(name,author,country,description,exhibition_id) VALUES 
('La Libertad Guiando al Pueblo','Eugène Delacroix','France','',2),
('La Ejecución de Cadoudal','Georges Cadoudal','France','',2);

INSERT INTO public.beacons(artwork_id,uuid) VALUES 
(1,'DE:67:0D:F4:83:EO'),
(1,'JKDD947'),
(1,'KDNRO97'),
(2,'PAME293'),
(2,'UDMG012');

INSERT INTO public.media(file_type,file_name,display_name,extension) VALUES
('image','la_gioconda','Mona Lisa','jpg'),
('video','la_gioconda_following_eyes', 'Following Eyes','mp4'),
('audio','o_fortuna','O Fortuna - Carl Orff','mp3'),
('image','la_ejecucion_de_cadoudal','La Ejecución de George Cadoudal','jpg'),
('image','la_libertad_guiando_al_pueblo','La Libertad Guiando al Pueblo','jpg');

INSERT INTO public.artwork_media(artwork_id, media_id) VALUES
('1','1'),
('1','2'),
('1','3'),
('1','4'),
('1','5'),
('4','5'),
('5','4');

INSERT INTO public.exhibition_media(exhibition_id, media_id) VALUES
('1','3');

INSERT INTO public.user_modifications(date,user_id,modified_user_id,description) VALUES
('2019-11-19 20:00:00',1,2,'Changed role from ''ADMIN'' to ''USER'''),
(DEFAULT,1,2,'Changed state from ''ACTIVE'' to ''INACTIVE''');

INSERT INTO public.beacon_modifications(date,user_id,beacon_id,description) VALUES
('2019-11-19 20:00:00', 1, 1, 'Created Beacon'),
('2019-11-19 21:00:00', 1, 1, 'Assigned Beacon to Artwork 1'),
('2019-11-19 20:00:00', 1, 2, 'Created Beacon');

INSERT INTO public.media_modifications(date,user_id,media_id,description) VALUES
('2019-11-19 20:00:00', 1, 1, 'Uploaded ''la_gioconda.jpg'' as ''foto1'''),
('2019-11-19 21:00:00', 1, 1, 'Changed Display Name from ''foto1'' to ''Mona Lisa'''),
('2019-11-19 20:00:00', 1, 2, 'Uploaded ''la_gioconda_following-eyes.mp4'' as ''Following Eyes''');

SELECT * FROM public.artworks;
INSERT INTO region (nombre) VALUES ('Metropolitana');

INSERT INTO comuna (nombre, region_id) VALUES ('Independencia', 1);
INSERT INTO comuna (nombre, region_id) VALUES ('Santiago', 1);

INSERT INTO recinto (nombre, ubicacion, capacidad, cantidad_pistas, comuna_id) VALUES ('Hipódromo Chile', 'Av. Hipódromo Chile 1715', 15000, 3, 1);
INSERT INTO recinto (nombre, ubicacion, capacidad, cantidad_pistas, comuna_id) VALUES ('Club Hípico de Santiago', 'Av. Blanco Encalada 2540', 18000, 2, 2);

INSERT INTO raza (nombre, descripcion, pais_origen) VALUES ('Pura Sangre', 'Raza de alta velocidad para carreras', 'Inglaterra');
INSERT INTO raza (nombre, descripcion, pais_origen) VALUES ('Árabe', 'Una de las razas más antiguas del mundo', 'Arabia');
INSERT INTO raza (nombre, descripcion, pais_origen) VALUES ('Cuarto de Milla', 'Especialista en distancias cortas', 'Estados Unidos');
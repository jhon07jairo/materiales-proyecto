-- 1. Crear la base de datos
DROP DATABASE IF EXISTS materiales_db;
GO

CREATE DATABASE materiales_db;
GO

USE materiales_db;
GO

-- 2. Tabla Departamentos
CREATE TABLE Departamentos (
    id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(100) NOT NULL UNIQUE
);
GO

-- 3. Tabla Ciudades
CREATE TABLE Ciudades (
    id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(100) NOT NULL,
    departamento_id INT NOT NULL,
    CONSTRAINT FK_Ciudades_Departamentos FOREIGN KEY (departamento_id) REFERENCES Departamentos(id)
);
GO

-- 4. Tabla Materiales
CREATE TABLE Materiales (
    id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(100) NOT NULL,
    descripcion NVARCHAR(255) NULL,
    tipo NVARCHAR(50) NOT NULL,
    precio DECIMAL(18,2) NOT NULL,
    fecha_compra DATE NOT NULL,
    fecha_venta DATE NULL,
    estado VARCHAR(20) NOT NULL CHECK (estado IN ('ACTIVO', 'DISPONIBLE', 'ASIGNADO')),
    ciudad_id INT NOT NULL,
    CONSTRAINT FK_Materiales_Ciudades FOREIGN KEY (ciudad_id) REFERENCES Ciudades(id),
    CONSTRAINT CK_Materiales_Fechas CHECK (fecha_venta IS NULL OR fecha_compra <= fecha_venta)
);
GO

-- 5. Datos iniciales

-- Departamentos
INSERT INTO Departamentos (nombre) VALUES ('Antioquia');
INSERT INTO Departamentos (nombre) VALUES ('Cundinamarca');

-- Ciudades
INSERT INTO Ciudades (nombre, departamento_id) VALUES ('Medellín', 1);
INSERT INTO Ciudades (nombre, departamento_id) VALUES ('Bogotá', 2);

-- Materiales
INSERT INTO Materiales (nombre, descripcion, tipo, precio, fecha_compra, fecha_venta, estado, ciudad_id)
VALUES ('Cemento', 'Bolsa de 50kg', 'Construccion', 35000, '2025-09-01', NULL, 'DISPONIBLE', 1);

INSERT INTO Materiales (nombre, descripcion, tipo, precio, fecha_compra, fecha_venta, estado, ciudad_id)
VALUES ('Arena', 'Arena de río', 'Construccion', 15000, '2025-08-15', NULL, 'ACTIVO', 2);
GO

-- 6. Índices para mejorar búsquedas
CREATE INDEX IX_Materiales_Tipo ON Materiales(tipo);
CREATE INDEX IX_Materiales_FechaCompra ON Materiales(fecha_compra);
CREATE INDEX IX_Materiales_Ciudad ON Materiales(ciudad_id);
GO

    select * from departamentos
    select * from ciudades
    select * from materiales


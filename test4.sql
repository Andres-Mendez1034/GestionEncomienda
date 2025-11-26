-- ============================================
-- Base de datos
-- ============================================
CREATE DATABASE IF NOT EXISTS encomiendas
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_general_ci;

USE encomiendas;

-- ============================================
-- Tabla Remitentes
-- ============================================
CREATE TABLE IF NOT EXISTS remitentes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(200),
    movil VARCHAR(20) UNIQUE
);

-- Datos por defecto
INSERT INTO remitentes (nombre, direccion, movil) VALUES
('Juan Pérez', 'Calle 123 #45-67', '3001234567'),
('María López', 'Carrera 10 #20-30', '3109876543');

-- ============================================
-- Tabla Empleados
-- ============================================
CREATE TABLE IF NOT EXISTS empleados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    rol VARCHAR(50)
);

-- Datos por defecto
INSERT INTO empleados (nombre, rol) VALUES
('Empleado Default', 'Administrador'),
('Carlos Martínez', 'Recogida'),
('Ana Gómez', 'Despacho');

-- ============================================
-- Tabla Encomiendas
-- ============================================
CREATE TABLE IF NOT EXISTS encomiendas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero_guia VARCHAR(50) NOT NULL UNIQUE,
    peso DECIMAL(10,2),
    destino VARCHAR(200),
    estado VARCHAR(50),
    remitente_id INT NULL,
    FOREIGN KEY (remitente_id) REFERENCES remitentes(id) ON DELETE SET NULL
);

-- Datos por defecto
INSERT INTO encomiendas (numero_guia, peso, destino, estado, remitente_id) VALUES
('GUIA001', 2.5, 'Bogotá', 'Registrada', 1),
('GUIA002', 5.0, 'Medellín', 'Despachada', 2),
('GUIA003', 1.2, 'Cali', 'En tránsito', 1);

-- ============================================
-- Tabla Tarifas
-- ============================================
CREATE TABLE IF NOT EXISTS tarifas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    peso_min DECIMAL(10,2),
    peso_max DECIMAL(10,2),
    costo DECIMAL(10,2)
);

-- Datos por defecto
INSERT INTO tarifas (peso_min, peso_max, costo) VALUES
(0.0, 1.0, 5000.00),
(1.01, 5.0, 10000.00),
(5.01, 10.0, 20000.00);

-- ============================================
-- Tabla Notificaciones
-- ============================================
CREATE TABLE IF NOT EXISTS notificaciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mensaje VARCHAR(255),
    tipo VARCHAR(50),
    encomienda_id INT,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (encomienda_id) REFERENCES encomiendas(id) ON DELETE CASCADE
);

-- Datos por defecto
INSERT INTO notificaciones (mensaje, tipo, encomienda_id) VALUES
('Encomienda GUIA001 registrada', 'Registro', 1),
('Encomienda GUIA002 despachada', 'Despacho', 2),
('Encomienda GUIA003 en tránsito', 'Actualización', 3);



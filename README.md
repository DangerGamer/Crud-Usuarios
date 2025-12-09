-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-12-2025 a las 06:00:46
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_usuarios`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `usu_id` int(11) NOT NULL,
  `usu_nombre` varchar(50) NOT NULL,
  `usu_papellido` varchar(50) NOT NULL,
  `usu_sapellido` varchar(50) DEFAULT NULL,
  `usu_direccion` varchar(50) DEFAULT NULL,
  `usu_telefono` varchar(10) DEFAULT NULL,
  `usu_correo` varchar(50) DEFAULT NULL,
  `usu_genero` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`usu_id`, `usu_nombre`, `usu_papellido`, `usu_sapellido`, `usu_direccion`, `usu_telefono`, `usu_correo`, `usu_genero`) VALUES
(1, 'Juan Jose', 'Ardila', 'Sanchez', 'calle 1 #4-57', '3212342367', 'jjsanchez@gmail.com', 'M'),
(2, 'Maria Fernanda', 'Lopez', 'Gomez', 'carrera 12 #8-90', '3109876543', 'mflopez@gmail.com', 'F'),
(3, 'Carlos Andres', 'Ramirez', 'Torres', 'avenida 3 #45-21', '3001234567', 'caramirez@gmail.com', 'M'),
(4, 'Laura Sofia', 'Martinez', 'Diaz', 'calle 9 #23-10', '3112233445', 'lsmartinez@hotmail.com', 'F'),
(5, 'Andres Felipe', 'Gonzalez', 'Rojas', 'carrera 7 #15-33', '3123344556', 'afgonzalez@yahoo.com', 'M'),
(6, 'Camila Alejandra', 'Moreno', 'Vargas', 'transversal 6 #10-20', '3134455667', 'camoreno@gmail.com', 'F'),
(7, 'Sebastian', 'Castro', 'Mejia', 'calle 20 #30-40', '3145566778', 'secastro@hotmail.com', 'M'),
(8, 'Valentina', 'Rodriguez', 'Perez', 'carrera 5 #11-22', '3156677889', 'valrodriguez@gmail.com', 'F'),
(9, 'Daniel', 'Ortiz', 'Navarro', 'avenida 1 #2-34', '3167788990', 'danortiz@yahoo.com', 'M'),
(10, 'Isabella', 'Suarez', 'Mendoza', 'calle 15 #6-78', '3178899001', 'issuarez@gmail.com', 'F'),
(11, 'Carlos', 'Ramirez', 'Lopez', 'Carrera 8 #12-45', '3124567890', 'carlos.ramirez@gmail.com', 'M'),
(12, 'Valentina', 'Gomez', 'Torres', 'Calle 23 #9-67', '3159876543', 'valentina.gomez@hotmail.com', 'F'),
(13, 'Andres', 'Martinez', 'Diaz', 'Av. Siempre Viva #101', '3102233445', 'andres.martinez@yahoo.com', 'M'),
(14, 'Camila', 'Fernandez', 'Rojas', 'Calle 45 #7-89', '3167788990', 'camila.fernandez@gmail.com', 'F'),
(15, 'Juan', 'Castro', 'Moreno', 'Carrera 10 #34-56', '3119988776', 'juan.castro@gmail.com', 'M'),
(16, 'Sofia', 'Vargas', 'Jimenez', 'Calle 12 #3-21', '3176655443', 'sofia.vargas@gmail.com', 'F'),
(17, 'Mateo', 'Hernandez', 'Perez', 'Av. Central #56', '3142233445', 'mateo.hernandez@gmail.com', 'M'),
(18, 'Laura', 'Ortiz', 'Garcia', 'Calle 67 #45-12', '3185566778', 'laura.ortiz@gmail.com', 'F'),
(19, 'Sebastian', 'Morales', 'Ruiz', 'Carrera 20 #78-90', '3193344556', 'sebastian.morales@gmail.com', 'M'),
(20, 'Isabella', 'Suarez', 'Mendoza', 'Calle 15 #6-78', '3178899001', 'issuarez@gmail.com', 'F');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`usu_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `usu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

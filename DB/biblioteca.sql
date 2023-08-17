-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-08-2023 a las 19:08:08
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `biblioteca`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `BuscarFolioPorTitulo` (IN `tituloBusqueda` VARCHAR(255))   BEGIN
    -- Seleccionar los campos necesarios de la tabla de folio y unirse con las tablas de libros y usuarios
    SELECT f.idFolio,              -- ID del folio
           l.titulo AS tituloLibro, -- Título del libro (renombrado para mostrar)
           u.idUsuario,            -- ID del usuario
           u.nombre AS nombreUsuario, -- Nombre del usuario (renombrado para mostrar)
           f.estado                -- Estado del folio
    FROM folio f
    JOIN libros l ON f.idLibro = l.idLibro  -- Unirse con la tabla de libros por ID de libro
    JOIN usuarios u ON f.idUsuario = u.idUsuario -- Unirse con la tabla de usuarios por ID de usuario
    WHERE l.titulo LIKE CONCAT('%', tituloBusqueda, '%'); -- Filtrar por título de libro similar al texto de búsqueda
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `BuscarLibroPorID` (IN `libro_id` INT)   BEGIN
    -- Realizamos la consulta SQL con operaciones JOIN para obtener información detallada del libro
    SELECT libros.idLibro, libros.titulo, autor.nombre AS autor, genero.genero AS genero, editorial.editorial AS editorial
    FROM libros
    INNER JOIN autor ON libros.idAutor = autor.idAutor
    INNER JOIN genero ON libros.idGenero = genero.idGenero
    INNER JOIN editorial ON libros.idEditorial = editorial.idEditorial
    WHERE libros.idLibro = libro_id; -- Filtramos por el ID del libro recibido como parámetro
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `BuscarLibrosPorNombre` (IN `nombreLibro` VARCHAR(255))   BEGIN
    SET @nombreBusqueda = CONCAT('%', nombreLibro, '%');
    
    SELECT 
        l.idLibro, 
        l.titulo, 
        a.nombre AS autor, 
        g.genero, 
        e.editorial
    FROM libros l
    INNER JOIN autor a ON l.idAutor = a.idAutor
    INNER JOIN genero g ON l.idGenero = g.idGenero
    INNER JOIN editorial e ON l.idEditorial = e.idEditorial
    WHERE l.titulo LIKE @nombreBusqueda;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `buscarPrestamosPorFecha` (IN `fechaInicioParam` DATE, IN `fechaFinParam` DATE)   BEGIN
    SELECT * FROM prestamos
    WHERE fechaPrestamo BETWEEN fechaInicioParam AND fechaFinParam;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `HistorialPrestamosPorUsuario` (IN `idUsuario` INT)   BEGIN
    SELECT p.idPrestamo, u.nombre AS Usuario, p.fechaPrestamo, p.fechaDev, p.adeudo
    FROM prestamos p
    JOIN usuarios u ON p.idUsuario = u.idUsuario
    WHERE p.idUsuario = idUsuario;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `MostrarLibros` ()   BEGIN
    -- Consulta para obtener los registros de libros con nombres de autor, género y editorial
    SELECT
        L.idLibro,
        L.titulo,
        A.nombre AS autor,
        G.genero,
        E.editorial,
        L.cantDisp,
        L.estado
    FROM
        libros AS L
        INNER JOIN autor AS A ON L.idAutor = A.idAutor
        INNER JOIN genero AS G ON L.idGenero = G.idGenero
        INNER JOIN editorial AS E ON L.idEditorial = E.idEditorial;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `MostrarPrestamosVencidos` ()   BEGIN
    SELECT *
    FROM prestamos
    WHERE fechaDev < CURDATE() AND estado = true;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PrestamosConAdeudoPendiente` ()   BEGIN
    SELECT p.idPrestamo, u.nombre AS Usuario, p.adeudo
    FROM prestamos p
    JOIN usuarios u ON p.idUsuario = u.idUsuario
    WHERE p.adeudo > 0;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UsuarioConMasPrestamos` ()   BEGIN
    SELECT u.nombre AS Usuario, COUNT(p.idUsuario) AS Numero_Prestamos
    FROM usuarios u
    JOIN prestamos p ON u.idUsuario = p.idUsuario
    GROUP BY u.idUsuario
    ORDER BY Numero_Prestamos DESC
    LIMIT 1;
END$$

--
-- Funciones
--
CREATE DEFINER=`root`@`localhost` FUNCTION `libroExiste` (`idLibro` INT) RETURNS TINYINT(1)  BEGIN
    DECLARE existe BOOLEAN;
    SET existe = EXISTS (SELECT 1 FROM libros WHERE idLibro = idLibro);
    RETURN existe;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `obtenerCantidadFolios` (`idPrestamo` INT) RETURNS INT(11)  BEGIN
    DECLARE cantidad INT; -- Variable para almacenar la cantidad de folios
    
    -- Contar los registros en la tabla de folio que tengan el mismo idPrestamo
    SELECT COUNT(*) INTO cantidad FROM folio WHERE idPrestamo = idPrestamo;
    
    RETURN cantidad; -- Retornar la cantidad de folios relacionados con el préstamo
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `obtenerCantidadPermitida` (`idPrestamo` INT) RETURNS INT(11)  BEGIN
    -- Variable para almacenar la cantidad de libros
    DECLARE cantidad INT;
    
    -- Obtener la cantidad de libros de un préstamo en base a su idPrestamo
    SELECT cantLib INTO cantidad FROM prestamos WHERE idPrestamo = idPrestamo;
    
    -- Retornar la cantidad de libros obtenida
    RETURN cantidad;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `obtenerDatosPrestamo_IDUsuario` (`idPrestamo` INT) RETURNS INT(11)  BEGIN
    DECLARE usuarioID INT;
    
    SELECT idUsuario INTO usuarioID FROM prestamos WHERE idPrestamo = idPrestamo;

    RETURN usuarioID;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `obtenerIdAutor` (`autorNombre` VARCHAR(255)) RETURNS INT(11)  BEGIN
    DECLARE autorId INT;
    SELECT idAutor INTO autorId FROM autor WHERE nombre = autorNombre;
    RETURN autorId;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `obtenerIdEditorial` (`editorialNombre` VARCHAR(255)) RETURNS INT(11)  BEGIN
    DECLARE editorialId INT;
    SELECT idEditorial INTO editorialId FROM editorial WHERE editorial = editorialNombre;
    RETURN editorialId;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `obtenerIdGenero` (`generoNombre` VARCHAR(255)) RETURNS INT(11)  BEGIN
    DECLARE generoId INT;
    SELECT idGenero INTO generoId FROM genero WHERE genero = generoNombre;
    RETURN generoId;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `obtenerUltimoIdInsertado` () RETURNS INT(11)  BEGIN
    DECLARE lastId INT;
    SELECT MAX(idPrestamo) INTO lastId FROM prestamos;
    RETURN lastId;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `prestamoExiste` (`idPrestamo` INT) RETURNS TINYINT(1)  BEGIN
    DECLARE existe BOOLEAN;
    SET existe = EXISTS (SELECT 1 FROM prestamos WHERE idPrestamo = idPrestamo);
    RETURN existe;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `usuarioExiste` (`idUsuario` INT) RETURNS TINYINT(1)  BEGIN
    DECLARE existe BOOLEAN;
    SET existe = EXISTS (SELECT 1 FROM usuarios WHERE idUsuario = idUsuario AND estado = true);
    RETURN existe;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `admins`
--

CREATE TABLE `admins` (
  `idAdmin` int(11) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `contra` varbinary(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `admins`
--

INSERT INTO `admins` (`idAdmin`, `usuario`, `contra`) VALUES
(2, 'Flaka', 0x38346335613436633765643065636563313366623332613161313630323636346133326537653233653039663065376362333434353761616635636333616137),
(3, 'Manzano', 0x31363631623538313165613034353865656166633963623062313464366138356366393231656338393530343836626166343439363835383035316632326436);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autor`
--

CREATE TABLE `autor` (
  `idAutor` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `nacionalidad` varchar(50) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `autor`
--

INSERT INTO `autor` (`idAutor`, `nombre`, `nacionalidad`, `fechaNacimiento`, `estado`) VALUES
(1, 'Gabriel García Márquez', 'Colombiano', '1927-03-06', 0),
(2, 'J.K. Rowling', 'Británica', '1965-07-31', 1),
(3, 'Haruki Murakami', 'Japonés', '1949-01-12', 1),
(4, 'Jane Austen', 'Británica', '1775-12-16', 1),
(5, 'Isabel Allende', 'Chilena', '1942-08-02', 1),
(6, 'Paquito Ramos', 'Mexicano', '1998-07-17', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `editorial`
--

CREATE TABLE `editorial` (
  `idEditorial` int(11) NOT NULL,
  `editorial` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `editorial`
--

INSERT INTO `editorial` (`idEditorial`, `editorial`) VALUES
(1, 'Penguin Random House'),
(2, 'HarperCollins'),
(3, 'Anagrama'),
(4, 'Alfaguara'),
(5, 'Planeta'),
(6, 'Casa de Origami');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `folio`
--

CREATE TABLE `folio` (
  `idFolio` int(11) NOT NULL,
  `idPrestamo` int(11) NOT NULL,
  `idLibro` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `folio`
--

INSERT INTO `folio` (`idFolio`, `idPrestamo`, `idLibro`, `idUsuario`, `estado`) VALUES
(1, 1, 1, 2, 0),
(2, 5, 2, 1, 1);

--
-- Disparadores `folio`
--
DELIMITER $$
CREATE TRIGGER `actualizarCantidadLibros` AFTER INSERT ON `folio` FOR EACH ROW BEGIN
    DECLARE estadoPrestamo BOOLEAN;
    DECLARE cantDisponible INT;

    -- Obtener el estado del préstamo asociado al folio insertado
    SELECT estado INTO estadoPrestamo FROM prestamos WHERE idPrestamo = NEW.idPrestamo;

    -- Verificar si el estado del préstamo es "ACTIVO"
    IF estadoPrestamo = TRUE THEN
        -- Obtener la cantidad disponible de libros del libro asociado al folio
        SELECT cantDisp INTO cantDisponible FROM libros WHERE idLibro = NEW.idLibro;

        -- Restar 1 a la cantidad disponible de libros
        UPDATE libros SET cantDisp = cantDisponible - 1 WHERE idLibro = NEW.idLibro;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genero`
--

CREATE TABLE `genero` (
  `idGenero` int(11) NOT NULL,
  `genero` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `genero`
--

INSERT INTO `genero` (`idGenero`, `genero`) VALUES
(1, 'Ficción'),
(2, 'Fantasía'),
(3, 'Misterio'),
(4, 'Romance'),
(5, 'Ciencia Ficción');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libros`
--

CREATE TABLE `libros` (
  `idLibro` int(11) NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `idAutor` int(11) NOT NULL,
  `idGenero` int(11) NOT NULL,
  `idEditorial` int(11) NOT NULL,
  `cantDisp` int(11) DEFAULT 1,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `libros`
--

INSERT INTO `libros` (`idLibro`, `titulo`, `idAutor`, `idGenero`, `idEditorial`, `cantDisp`, `estado`) VALUES
(1, 'Las Aventuras del FLAKA', 2, 5, 1, 5, 1),
(2, 'El guardian entre el centeno', 1, 2, 2, 11, 1),
(3, 'Harry Potter 2', 2, 1, 2, 4, 1),
(4, 'Cronicas de Narnia', 2, 2, 2, 3, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamos`
--

CREATE TABLE `prestamos` (
  `idPrestamo` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `fechaPrestamo` date NOT NULL DEFAULT curdate(),
  `fechaDev` date DEFAULT NULL,
  `cantLib` int(11) NOT NULL,
  `adeudo` float DEFAULT 0,
  `estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `prestamos`
--

INSERT INTO `prestamos` (`idPrestamo`, `idUsuario`, `fechaPrestamo`, `fechaDev`, `cantLib`, `adeudo`, `estado`) VALUES
(1, 2, '2023-08-13', '2023-08-20', 1, 0, 1),
(2, 2, '2023-08-15', '2023-08-22', 3, 0, 1),
(3, 2, '2023-08-15', '2023-08-22', 3, 0, 1),
(4, 4, '2023-08-15', '2023-08-22', 2, 0, 1),
(5, 1, '2023-08-16', '2023-08-23', 1, 0, 1),
(6, 1, '2023-08-17', '2023-08-24', 1, 0, 1),
(7, 2, '2023-08-17', '2023-08-24', 1, 0, 1);

--
-- Disparadores `prestamos`
--
DELIMITER $$
CREATE TRIGGER `actualizarCantidadLibrosDespuesBorrar` AFTER UPDATE ON `prestamos` FOR EACH ROW BEGIN
    -- Verificar si el estado del préstamo cambió de activo a inactivo
    IF OLD.estado = true AND NEW.estado = false THEN
        -- Actualizar la cantidad disponible en la tabla de libros para los registros en la tabla de folio
        UPDATE libros l
        JOIN folio f ON l.idLibro = f.idLibro
        SET l.cantDisp = l.cantDisp + 1
        WHERE f.idPrestamo = OLD.idPrestamo;
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `actualizar_folio_estado` AFTER UPDATE ON `prestamos` FOR EACH ROW BEGIN
    -- Verificar si el estado del préstamo se actualizó
    IF OLD.estado != NEW.estado THEN
        -- Actualizar los registros en la tabla folio relacionados con el ID del préstamo
        UPDATE folio SET estado = NEW.estado WHERE idPrestamo = NEW.idPrestamo;
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `calcular_fecha_devolucion` BEFORE INSERT ON `prestamos` FOR EACH ROW BEGIN
    SET NEW.fechaDev = DATE_ADD(NEW.fechaPrestamo, INTERVAL 7 DAY);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `idUsuario` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idUsuario`, `nombre`, `direccion`, `telefono`, `estado`) VALUES
(1, 'Jorge Rendon', 'Calle valle de bravo #7627', '6692495240', 1),
(2, 'Manuel Macias', 'Los zapotes 18108 Villa florida', '848949004', 1),
(3, 'Bernardp Rosas Gatcia', 'clementre sttllo', '6691270200', 1),
(4, 'Jose yair', 'Intrrnsionsl 827', '699299293', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`idAdmin`);

--
-- Indices de la tabla `autor`
--
ALTER TABLE `autor`
  ADD PRIMARY KEY (`idAutor`);

--
-- Indices de la tabla `editorial`
--
ALTER TABLE `editorial`
  ADD PRIMARY KEY (`idEditorial`);

--
-- Indices de la tabla `folio`
--
ALTER TABLE `folio`
  ADD PRIMARY KEY (`idFolio`),
  ADD KEY `idPrestamo` (`idPrestamo`),
  ADD KEY `idLibro` (`idLibro`),
  ADD KEY `idUsuario` (`idUsuario`);

--
-- Indices de la tabla `genero`
--
ALTER TABLE `genero`
  ADD PRIMARY KEY (`idGenero`);

--
-- Indices de la tabla `libros`
--
ALTER TABLE `libros`
  ADD PRIMARY KEY (`idLibro`),
  ADD KEY `idAutor` (`idAutor`),
  ADD KEY `idGenero` (`idGenero`),
  ADD KEY `idEditorial` (`idEditorial`);

--
-- Indices de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD PRIMARY KEY (`idPrestamo`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`idUsuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `admins`
--
ALTER TABLE `admins`
  MODIFY `idAdmin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `autor`
--
ALTER TABLE `autor`
  MODIFY `idAutor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `editorial`
--
ALTER TABLE `editorial`
  MODIFY `idEditorial` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `folio`
--
ALTER TABLE `folio`
  MODIFY `idFolio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `genero`
--
ALTER TABLE `genero`
  MODIFY `idGenero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `libros`
--
ALTER TABLE `libros`
  MODIFY `idLibro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  MODIFY `idPrestamo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `folio`
--
ALTER TABLE `folio`
  ADD CONSTRAINT `folio_ibfk_1` FOREIGN KEY (`idPrestamo`) REFERENCES `prestamos` (`idPrestamo`),
  ADD CONSTRAINT `folio_ibfk_2` FOREIGN KEY (`idLibro`) REFERENCES `libros` (`idLibro`),
  ADD CONSTRAINT `folio_ibfk_3` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`);

--
-- Filtros para la tabla `libros`
--
ALTER TABLE `libros`
  ADD CONSTRAINT `libros_ibfk_1` FOREIGN KEY (`idAutor`) REFERENCES `autor` (`idAutor`),
  ADD CONSTRAINT `libros_ibfk_2` FOREIGN KEY (`idGenero`) REFERENCES `genero` (`idGenero`),
  ADD CONSTRAINT `libros_ibfk_3` FOREIGN KEY (`idEditorial`) REFERENCES `editorial` (`idEditorial`);

DELIMITER $$
--
-- Eventos
--
CREATE DEFINER=`root`@`localhost` EVENT `actualizar_adeudo_diario_evento` ON SCHEDULE EVERY 1 DAY STARTS '2023-08-13 22:22:46' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
    -- Actualizar el adeudo para los registros donde la fecha de devolución ha pasado
    UPDATE prestamos
    SET adeudo = adeudo + 20
    WHERE fechaDev < NOW();
END$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: db:3306
-- Tiempo de generación: 25-11-2023 a las 10:16:38
-- Versión del servidor: 5.7.44
-- Versión de PHP: 8.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tiendamusica`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id` int(11) NOT NULL,
  `descripcion` text,
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `descripcion`, `nombre`) VALUES
(1, 'Guitarras', 'Guitarras'),
(2, 'Drums', 'Drums'),
(3, 'Teclados', 'Teclados'),
(4, 'Studio', 'Studio');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id` int(11) NOT NULL,
  `correo_electronico` varchar(255) NOT NULL,
  `direccion_envio` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `password` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id`, `correo_electronico`, `direccion_envio`, `nombre`, `password`) VALUES
(1, 'Lisa@gmail.com', 'Calle de los Berrocales 10, Madrid', 'Lisa', 'Lisa'),
(2, 'carla@gmail.com', 'calle de carla', 'carla', 'carla'),
(3, 'pedro@gmail.com', 'calle de pedro', 'pedro', 'pedro'),
(4, 'paco@gmail.com', 'calle de paco', 'paco', 'paco'),
(5, 'rafa@gmail.com', 'calle de rafa', 'rafa', 'rafa'),
(6, 'lola@gmail.com', 'calle de lola', 'lola', 'lola'),
(7, 'jaime@gmail.com', 'calle de jaime', 'jaime', 'jaime'),
(8, 'fran@gmail.com', 'calle de fran', 'fran', 'fran'),
(9, 'sergio@gmail.com', 'calle de sergio', 'sergio', 'sergio'),
(15, 'silvia@gmail.com', 'calle de silvia', 'silvia', 'silvia'),
(22, 'tomas@gmail.com', 'calle de sergio', 'tomas', 'tomas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marcas`
--

CREATE TABLE `marcas` (
  `id` int(11) NOT NULL,
  `descripcion` text,
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `marcas`
--

INSERT INTO `marcas` (`id`, `descripcion`, `nombre`) VALUES
(1, 'Shure', 'Shure'),
(2, 'Roland', 'Roland'),
(3, 'Fender', 'Fender'),
(4, 'Behringer', 'Behringer'),
(5, 'Yamaha', 'Yamaha'),
(6, 'Harley Benton', 'Harley Benton'),
(7, 'Millenium', 'Millenium');


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `id` int(11) NOT NULL,
  `estado_pedido` varchar(50) NOT NULL,
  `fecha_pedido` datetime(6) NOT NULL,
  `total` decimal(38,2) NOT NULL,
  `idcliente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`id`, `estado_pedido`, `fecha_pedido`, `total`, `idcliente`) VALUES
(1, 'Entregado', '2023-11-29 11:13:45.382000', 270.00, 2),
(2, 'Entregado', '2023-12-04 11:15:10.010000', 270.00, 3),
(3, 'Entregado', '2023-12-05 12:15:58.488000', 189.00, 4),
(4, 'Entregado', '2023-12-05 15:46:02.265000', 270.00, 2),
(5, 'Entregado', '2023-12-08 21:33:02.521000', 688.00, 3),
(6, 'Reparto', '2023-12-10 16:39:14.231000', 459.00, 5),
(7, 'Reparto', '2023-12-10 21:27:20.792000', 459.00, 8),
(8, 'Reparto', '2023-12-11 22:36:25.642000', 499.00, 15),
(9, 'Reparto', '2023-12-11 10:28:01.443000', 270.00, 22);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `cantidad_en_stock` int(11) NOT NULL,
  `descripcion` text,
  `imagen` varchar(50) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `precio` decimal(38,2) NOT NULL,
  `idcategoria` int(11) DEFAULT NULL,
  `idmarca` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `cantidad_en_stock`, `descripcion`, `imagen`, `nombre`, `precio`, `idcategoria`, `idmarca`) VALUES
(1, 2, 'Guitarra eléctrica\r\nCuerpo de álamo\r\nMástil de arce\r\nDiapasón de laurel\r\nForma del mástil en \'C\'\r\nIncrustaciones de puntos de perloide\r\n21 trastes Medium Jumbo', 'imagen1.jpg', 'Squier Affinity Strat Laurel SG', 189.00, 1, 2),
(2, 3, 'Guitarra eléctrica\r\nEstilo ST\r\nPro Series\r\nContorno del cuerpo cómodo con clavijero a juego\r\nCuerpo de nyatoh\r\nTapa de chapa de arce ultraflameado\r\nMástil de arce azucarero canadiense\r\nMontaje del mástil: Atornillado (bolt-on)\r\nForma del mástil en \'C\' moderna\r\nDiapasón de ébano Macassar', 'imagen2.jpg', 'Harley Benton Fusion-III HSH ', 270.00, 1, 3),
(3, 4, 'Más información de IBANEZ LGB300 VINTAGE YELLOW SB GEORGE BENSON SIGNATURE\r\nGuitarra eléctrica de semi caja IBANEZ LGB300 VINTAGE YELLOW SB GEORGE BENSON SIGNATURE.\r\nTapa de abeto. \r\nFondo y lados de arce.\r\nDiapasón en ébano de 20 trastes.\r\nProfundidad de caja 80 mm.', 'imagen3.jpg', 'IBANEZ LGB300 VINTAGE YELLOW', 499.00, 1, 4),
(4, 5, 'Guitarra electroacústica con cuerdas de acero\r\nTamaño \'Gran Symphony\' mini de viaje\r\nCuerpo de caoba seleccionada con fondo curvado\r\nMástil de nato en forma de \'V\'\r\nDiapasón de granadilla\r\nEscala de 596 mm\r\nAnchura de la cejilla: 43 mm\r\n20 trastes', 'imagen4.jpg', 'Harley Benton GS-Travel-E Mahogany', 150.00, 1, 2),
(5, 6, 'El set de batería Millenium Focus Junior Drum Set Black es un paquete completo con el que bateristas incipientes ambiciosos podrán dar los primeros compases sin preocupaciones.', 'imagen5.jpg', 'Millenium Focus Junior Drum Set Black', 180.00, 2, 7),
(6, 9, 'El Yamaha PSR-SX900 es un teclado avanzado que ofrece una gama extensa de sonidos y ritmos con funciones de acompañamiento automático, ideal para músicos creativos y actuaciones en vivo. Con su interfaz táctil intuitiva y capacidades de grabación, es perfecto para composición y arreglos musicales.', 'imagen6.jpg', 'Yamaha PSR-SX900', 1500.00, 3, 5),
(7, 9, 'El Shure SM7B es un micrófono dinámico de estudio con un rango de frecuencia plano para grabaciones limpias y naturales, muy valorado por su aislamiento de ruido y su capacidad para capturar voces con claridad y calidez.', 'imagen7.jpg', 'Shure SM 7 BShure SM 7 B', 389.00, 4, 1);


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resenas`
--

CREATE TABLE `resenas` (
  `id` int(11) NOT NULL,
  `calificacion` int(11) NOT NULL,
  `comentario` text,
  `fecha_resena` date NOT NULL,
  `idcliente` int(11) DEFAULT NULL,
  `idproducto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `resenas`
--

INSERT INTO `resenas` (`id`, `calificacion`, `comentario`, `fecha_resena`, `idcliente`, `idproducto`) VALUES
(1, 3, 'Suena de fábula', '2023-12-11', 1, 1),
(2, 4, 'Suena baja', '2023-12-12', 2, NULL),
(3, 5, 'fds', '2023-12-12', 2, 2),
(4, 1, 'Estaba rota', '2023-12-12', 3, 2),
(5, 1, 'Estaba rota', '2023-12-12', 3, 2),
(6, 1, 'Estaba rota', '2023-12-12', 3, 2),
(7, 5, 'Suena bajo', '2023-12-12', 3, 2),
(8, 4, 'Prueba', '2023-12-13', 7, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_NOMBRE` (`nombre`);

--
-- Indices de la tabla `marcas`
--
ALTER TABLE `marcas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7ac0ogsfqrutbpb4hldlti2t2` (`idcliente`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2oywmpf2v8j9uh299mbtw3wvw` (`idmarca`),
  ADD KEY `FK9l0ne47hxyjttveacwaia5a0v` (`idcategoria`);

--
-- Indices de la tabla `resenas`
--
ALTER TABLE `resenas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjfup10fpvf4itkjlukkuv97td` (`idcliente`),
  ADD KEY `FKrcr64d6tiv2glnfoh1n9pb5iw` (`idproducto`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de la tabla `marcas`
--
ALTER TABLE `marcas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `resenas`
--
ALTER TABLE `resenas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `FK7ac0ogsfqrutbpb4hldlti2t2` FOREIGN KEY (`idcliente`) REFERENCES `clientes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `FK2oywmpf2v8j9uh299mbtw3wvw` FOREIGN KEY (`idmarca`) REFERENCES `marcas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK9l0ne47hxyjttveacwaia5a0v` FOREIGN KEY (`idcategoria`) REFERENCES `categorias` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `resenas`
--
ALTER TABLE `resenas`
  ADD CONSTRAINT `FKjfup10fpvf4itkjlukkuv97td` FOREIGN KEY (`idcliente`) REFERENCES `clientes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FKrcr64d6tiv2glnfoh1n9pb5iw` FOREIGN KEY (`idproducto`) REFERENCES `productos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

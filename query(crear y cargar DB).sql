-- Crear la base de datos
CREATE DATABASE deso;
USE deso;

-- Tabla Coordenada
CREATE TABLE coordenada (
    idCoordenada INT AUTO_INCREMENT PRIMARY KEY,
    lat DOUBLE(20,11) NOT NULL,
    lng DOUBLE(20,11) NOT NULL
);

-- Tabla Cliente
CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    cuit VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(100) NOT NULL,
    direccion VARCHAR(200) NOT NULL,
    id_Coordenada INT,
    FOREIGN KEY (id_Coordenada) REFERENCES coordenada(idCoordenada) ON DELETE CASCADE 
);

-- Tabla Vendedor
CREATE TABLE vendedor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(200) NOT NULL,
    id_Coordenada INT,
    FOREIGN KEY (id_Coordenada) REFERENCES coordenada(idCoordenada) ON DELETE CASCADE 
);

-- Tabla ItemMenu
CREATE TABLE item_menu (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    peso DOUBLE NOT NULL,
    vendedorId INT,
    
    -- Campos para Comida (opcional para bebidas)
    calorias INT,
    apto_Celiaco BOOLEAN,
    apto_Vegano BOOLEAN,
    
    -- Campos para Bebida (opcional para comidas)
    graduacion_Alcohol DECIMAL(4, 2) DEFAULT 0.0,  -- Valor por defecto para productos sin alcohol
    volumen DOUBLE DEFAULT NULL,
    
    FOREIGN KEY (vendedorId) REFERENCES vendedor(id) ON DELETE CASCADE 
);

-- Tabla Pedido
CREATE TABLE pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    estado VARCHAR(50) NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    fecha DATE NOT NULL,
    clienteId INT,
    FOREIGN KEY (clienteId) REFERENCES cliente(id) ON DELETE CASCADE ,
    metodoPago VARCHAR(50) NOT NULL,
    cuit VARCHAR(255),
    alias VARCHAR(255),
    cbu VARCHAR(255),
    vendedorId INT ,
    FOREIGN KEY (vendedorId) REFERENCES vendedor(id) ON DELETE CASCADE 
);

-- Tabla PedidoDetalle
CREATE TABLE pedido_detalle (
    id INT AUTO_INCREMENT PRIMARY KEY,
    itemMenuId INT NOT NULL,
    pedidoId INT NOT NULL,
    cantidad INT NOT NULL,
    FOREIGN KEY (itemMenuId) REFERENCES item_menu(id) ON DELETE CASCADE ,
    FOREIGN KEY (pedidoId) REFERENCES pedido(id) ON DELETE CASCADE 
);

-- Agregar datos




-- Insert Data for Coordenada
INSERT INTO coordenada (lat, lng) VALUES
(-31.7333, -60.5333),  -- Cliente 1
(-31.7320, -60.5360),  -- Cliente 2
(-31.7310, -60.5350),  -- Cliente 3
(-31.7300, -60.5340),  -- Cliente 4
(-31.7290, -60.5330),  -- Cliente 5
(-31.7280, -60.5320),  -- Cliente 6
(-31.7270, -60.5310),  -- Cliente 7
(-31.7260, -60.5300),  -- Cliente 8
(-31.7250, -60.5290),  -- Cliente 9
(-31.7240, -60.5280),  -- Cliente 10
(-31.7200, -60.5250),  -- McDonald\'s
(-31.7210, -60.5260),  -- Starbucks
(-31.7220, -60.5270),  -- Burger King
(-31.7230, -60.5280),  -- KFC
(-31.7240, -60.5290),  -- Subway
(-31.7250, -60.5300),  -- Dunkin\' Donuts
(-31.7260, -60.5310),  -- Pizza Hut
(-31.7270, -60.5320),  -- Domino\'s Pizza
(-31.7280, -60.5330),  -- Taco Bell
(-31.7290, -60.5340);  -- Wendy\'s


-- Insert Data for Cliente
INSERT INTO cliente (nombre, cuit, email, direccion, id_Coordenada) VALUES
('Juan Perez', '20-12345678-9', 'juan.perez@example.com', 'Av. Siempre Viva 123', 1),
('Maria Gomez', '27-87654321-0', 'maria.gomez@example.com', 'Calle Falsa 456', 2),
('Carlos Ruiz', '23-11223344-5', 'carlos.ruiz@example.com', 'Pasaje Lunar 789', 3),
('Laura Fernandez', '25-33445566-7', 'laura.fernandez@example.com', 'Boulevard del Sol 321', 4),
('Jose Martinez', '28-99887766-5', 'jose.martinez@example.com', 'Calle Larga 432', 5),
('Ana Lopez', '21-55443322-1', 'ana.lopez@example.com', 'Avenida Central 654', 6),
('Daniela Rodriguez', '26-66778899-0', 'daniela.rodriguez@example.com', 'Calle Angosta 987', 7),
('Luis Sanchez', '22-44332211-9', 'luis.sanchez@example.com', 'Plaza Mayor 741', 8),
('Jorge Alvarez', '24-22334455-6', 'jorge.alvarez@example.com', 'Paseo del Bosque 852', 9),
('Sofia Herrera', '29-77665544-3', 'sofia.herrera@example.com', 'Calle Nueva 963', 10);


-- Insert Data for Vendedor
INSERT INTO vendedor (id,nombre, direccion, id_Coordenada) VALUES
(1,'McDonald\'s', 'Av. Principal 1001', 11),
(2,'Starbucks', 'Calle Comercio 202', 12),
(3,'Burger King', 'Paseo del Centro 303', 13),
(4,'KFC', 'Boulevard Norte 404', 14),
(5,'Subway', 'Calle Mercado 505', 15),
(6,'Dunkin\' Donuts', 'Av. Libertad 606', 16),
(7,'Pizza Hut', 'Calle Central 707', 17),
(8,'Domino\'s Pizza', 'Paseo del Sur 808', 18),
(9,'Taco Bell', 'Av. Independencia 909', 19),
(10,'Wendy\'s', 'Calle Oeste 1010', 20);


INSERT INTO item_menu (id, nombre, descripcion, precio, peso, vendedorId, calorias, apto_Celiaco, apto_Vegano, graduacion_Alcohol, volumen) VALUES
-- McDonald's
(1, 'Big Mac', 'Deliciosa hamburguesa con carne de res, lechuga y salsa especial.', 4.50, 250, 1, 540, 0, 0, NULL, NULL),
(2, 'McNuggets', 'Nuggets de pollo crujientes y sabrosos.', 3.20, 200, 1, 300, 0, 0, NULL, NULL),
(3, 'McFries', 'Papas fritas doradas y crujientes.', 2.10, 150, 1, 320, 0, 0, NULL, NULL),
(4, 'McFlurry', 'Helado suave con trozos de chocolate.', 2.50, 150, 1, 350, 0, 0, NULL, NULL),
(5, 'Coca-Cola', 'Refresco gaseoso.', 1.20, 500, 1, NULL, NULL, 0, 0.0, 1000),
-- Starbucks
(6, 'Latte', 'Café con leche.', 3.80, 250, 2, NULL, NULL, 0, 0.0, 300),
(7, 'Caramel Macchiato', 'Café espresso con caramelo.', 4.20, 300, 2, NULL, NULL, 0, 0.0, NULL),
(8, 'Croissant', 'Croissant de mantequilla fresco.', 2.00, 100, 2, 280, 0, 0, NULL, NULL),
(9, 'Blueberry Muffin', 'Muffin de arándanos.', 2.50, 150, 2, 350, 0, 0, NULL, NULL),
(10, 'Chai Tea', 'Té chai con especias.', 2.90, 250, 2, NULL, NULL, 0, 0.0, NULL),
-- Burger King
(11, 'Whopper', 'Hamburguesa grande con carne de res, lechuga y tomate.', 5.00, 300, 3, 600, 0, 0, NULL, NULL),
(12, 'Cheeseburger', 'Hamburguesa con queso.', 2.80, 200, 3, 400, 0, 0, NULL, NULL),
(13, 'Onion Rings', 'Aros de cebolla crujientes.', 1.90, 100, 3, 220, 0, 0, NULL, NULL),
(14, 'Milkshake', 'Malteada de vainilla.', 2.60, 200, 3, 380, 0, 0, NULL, NULL),
(15, 'Pepsi', 'Refresco gaseoso.', 1.20, 500, 3, NULL, NULL, 0, 0.0, 500),
-- KFC
(16, 'Fried Chicken', 'Pollo frito crujiente.', 4.00, 250, 4, 520, 0, 0, NULL, NULL),
(17, 'Mashed Potatoes', 'Puré de papas cremoso.', 2.00, 200, 4, 180, 0, 0, NULL, NULL),
(18, 'Coleslaw', 'Ensalada de col.', 1.50, 150, 4, 140, 0, 0, NULL, NULL),
(19, 'Biscuit', 'Panecillo de mantequilla.', 1.20, 100, 4, 200, 0, 0, NULL, NULL),
(20, 'Lemonade', 'Limonada fresca.', 1.80, 500, 4, NULL, NULL, 0, 0.0, 350),
-- Subway
(21, 'Sub de Pollo', 'Sándwich de pollo con vegetales frescos.', 4.50, 300, 5, 450, 0, 0, NULL, NULL),
(22, 'Veggie Delight', 'Sándwich vegetariano con vegetales frescos.', 3.80, 250, 5, 300, 1, 1, NULL, NULL),
(23, 'Italian BMT', 'Sándwich italiano con pepperoni y salami.', 5.00, 350, 5, 600, 0, 0, NULL, NULL),
(24, 'Chips', 'Bolsa de papas fritas.', 1.20, 50, 5, 200, 0, 0, NULL, NULL),
(25, 'Iced Tea', 'Té helado.', 1.50, 500, 5, NULL, NULL, 0, 0.0, 350),
-- Dunkin' Donuts
(26, 'Glazed Donut', 'Dona glaseada.', 1.00, 100, 6, 200, 0, 0, NULL, NULL),
(27, 'Chocolate Donut', 'Dona de chocolate.', 1.20, 100, 6, 250, 0, 0, NULL, NULL),
(28, 'Coffee', 'Café negro.', 1.50, 250, 6, NULL, NULL, 0, 0.0, 100),
(29, 'Latte', 'Café con leche.', 2.50, 300, 6, NULL, NULL, 0, 0.0, 300),
(30, 'Bagel', 'Bagel con queso crema.', 2.00, 150, 6, 300, 0, 0, NULL, NULL),
-- Pizza Hut
(31, 'Cheese Pizza', 'Pizza de queso.', 8.00, 600, 7, 1200, 0, 0, NULL, NULL),
(32, 'Pepperoni Pizza', 'Pizza de pepperoni.', 9.00, 650, 7, 1300, 0, 0, NULL, NULL),
(33, 'Garlic Bread', 'Pan de ajo.', 3.00, 150, 7, 300, 0, 0, NULL, NULL),
(34, 'Wings', 'Alitas de pollo.', 6.00, 300, 7, 600, 0, 0, NULL, NULL),
(35, 'Pepsi', 'Refresco gaseoso.', 1.20, 500, 7, NULL, NULL, 0, 0.0, 500),
-- Domino's Pizza
(36, 'Margherita Pizza', 'Pizza margherita.', 7.50, 600, 8, 1100, 0, 0, NULL, NULL),
(37, 'BBQ Chicken Pizza', 'Pizza de pollo BBQ.', 9.50, 650, 8, 1400, 0, 0, NULL, NULL),
(38, 'Cheesy Bread', 'Pan con queso.', 4.00, 200, 8, 400, 0, 0, NULL, NULL),
(39, 'Pasta', 'Pasta con salsa marinara.', 5.50, 300, 8, 450, 0, 0, NULL, NULL),
(40, 'Coke', 'Refresco gaseoso.', 1.20, 500, 8, NULL, NULL, 0, 0.0, 500),
-- Taco Bell
(41, 'Crunchy Taco', 'Taco crujiente con carne y queso.', 2.50, 150, 9, 350, 0, 0, NULL, NULL),
(42, 'Bean Burrito', 'Burrito de frijoles.', 3.00, 200, 9, 400, 1, 1, NULL, NULL),
(43, 'Quesadilla', 'Quesadilla de pollo con queso.', 4.00, 250, 9, 600, 0, 0, NULL, NULL),
(44, 'Nachos', 'Nachos con salsa de queso.', 2.80, 200, 9, 500, 0, 0, NULL, NULL),
(45, 'Mountain Dew', 'Refresco gaseoso.', 1.20, 500, 9, NULL, NULL, 0, 0.0, 500),
-- Wendy's
(46, 'Dave\'s Single', 'Hamburguesa simple con carne de res.', 4.50, 250, 10, 550, 0, 0, NULL, NULL),
(47, 'Spicy Chicken Sandwich', 'Sándwich de pollo picante.', 4.80, 300, 10, 650, 0, 0, NULL, NULL),
(48, 'French Fries', 'Papas fritas doradas.', 2.20, 150, 10, 320, 0, 0, NULL, NULL),
(49, 'Frosty', 'Batido helado.', 2.60, 200, 10, 400, 0, 0,NULL, NULL),
(50, 'Sprite', 'Refresco gaseoso.', 1.20, 500, 10, NULL,NULL, 0, 0.0, 1500);


-- Insert Data for Pedido
INSERT INTO pedido (estado, total, fecha, clienteId, metodoPago, cuit, alias, cbu, vendedorId) VALUES
('RECIBIDO', 1200.50, '2024-12-10', 1, 'mercadopago', NULL, 'alias1', NULL, 1), -- McDonald's
('ACEPTADO', 850.75, '2024-12-11', 2, 'transferencia', '30-12345678-9', NULL, 'cbu1', 2), -- Starbucks
('PREPARACION', 640.00, '2024-12-09', 3, 'efectivo', NULL, NULL, NULL, 3), -- Burger King
('EN_ENVIO', 1500.00, '2024-12-08', 1, 'mercadopago', NULL, 'alias2', NULL, 1), -- McDonald's
('ENVIADO', 950.25, '2024-12-07', 2, 'transferencia', '30-87654321-0', NULL, 'cbu2', 7), -- Pizza Hut
('RECIBIDO', 1100.40, '2024-12-06', 3, 'efectivo', NULL, NULL, NULL, 4), -- KFC
('ACEPTADO', 780.90, '2024-12-05', 1, 'mercadopago', NULL, 'alias3', NULL, 1), -- McDonald's
('PREPARACION', 960.60, '2024-12-04', 2, 'transferencia', '30-22334455-6', NULL, 'cbu3', 2), -- Starbucks
('EN_ENVIO', 1299.99, '2024-12-03', 3, 'efectivo', NULL, NULL, NULL, 7), -- Pizza Hut
('ENVIADO', 1110.75, '2024-12-02', 1, 'mercadopago', NULL, 'alias4', NULL, 1); -- McDonald's



-- Detalle de Pedido
INSERT INTO pedido_detalle (itemMenuId, pedidoId, cantidad) VALUES
(1, 1, 2),  -- Big Mac para Pedido 1
(3, 1, 1),  -- McFries para Pedido 1
(6, 2, 1),  -- Latte para Pedido 2
(7, 2, 1),  -- Caramel Macchiato para Pedido 2
(11, 3, 2), -- Whopper para Pedido 3
(15, 3, 1), -- Pepsi para Pedido 3
(16, 4, 3), -- Fried Chicken para Pedido 4
(20, 4, 2), -- Lemonade para Pedido 4
(31, 5, 2), -- Cheese Pizza para Pedido 5
(35, 5, 1), -- Pepsi para Pedido 5
(41, 6, 1), -- Crunchy Taco para Pedido 6
(45, 6, 2), -- Mountain Dew para Pedido 6
(46, 7, 2), -- Dave's Single para Pedido 7
(50, 7, 1), -- Sprite para Pedido 7
(8, 8, 3),  -- Croissant para Pedido 8
(10, 8, 2), -- Chai Tea para Pedido 8
(31, 9, 1), -- Cheese Pizza para Pedido 9
(34, 9, 2), -- Wings para Pedido 9
(42, 10, 2),-- Bean Burrito para Pedido 10
(45, 10, 1);-- Mountain Dew para Pedido 10

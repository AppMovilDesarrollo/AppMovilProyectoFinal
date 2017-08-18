BEGIN TRANSACTION;
CREATE TABLE `Tarjetas` (
	`ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`Cedula_Cliente`	NUMERIC NOT NULL,
	`NumeroTarjeta`	NUMERIC NOT NULL,
	`FechaVencimiento`	TEXT NOT NULL,
	`TipoTarjeta`	INTEGER NOT NULL
);
INSERT INTO `Tarjetas` (ID,Cedula_Cliente,NumeroTarjeta,FechaVencimiento,TipoTarjeta) VALUES (1,604150516,4152779504604072,'07/12',1);
INSERT INTO `Tarjetas` (ID,Cedula_Cliente,NumeroTarjeta,FechaVencimiento,TipoTarjeta) VALUES (2,604110437,4152046077954040,'07/12',1);
CREATE TABLE `RegistroPago` (
	`ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`Cedula_Cliente`	NUMERIC NOT NULL,
	`Monto`	NUMERIC NOT NULL,
	`Tarjeta_ID`	INTEGER NOT NULL
);
INSERT INTO `RegistroPago` (ID,Cedula_Cliente,Monto,Tarjeta_ID) VALUES (1,604150516,30000,1);
INSERT INTO `RegistroPago` (ID,Cedula_Cliente,Monto,Tarjeta_ID) VALUES (2,604110437,12000,2);
CREATE TABLE "Clientes_Visitas" (
	`ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`Nombre`	TEXT NOT NULL,
	`Telefono`	TEXT NOT NULL,
	`Direccion`	TEXT NOT NULL,
	`Cedula_Cliente`	NUMERIC NOT NULL
);
INSERT INTO `Clientes_Visitas` (ID,Nombre,Telefono,Direccion,Cedula_Cliente) VALUES (1,'Jeison Andres Cespedes Morales','85978859','Heredia, Corazon de Jesus',604150516);
INSERT INTO `Clientes_Visitas` (ID,Nombre,Telefono,Direccion,Cedula_Cliente) VALUES (2,'Yensy Vannessa Cespedes Morales','83735557','Heredia, Corazon de Jesus',604110437);
CREATE TABLE "Clientes" (
	`ID`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`Cedula_Cliente`	NUMERIC NOT NULL UNIQUE,
	`Salario`	NUMERIC DEFAULT 0,
	`LugarTrabajo`	TEXT NOT NULL,
	`Fotografia`	NUMERIC NOT NULL
);
INSERT INTO `Clientes` (ID,Cedula_Cliente,Salario,LugarTrabajo,Fotografia) VALUES (1,604150516,1000000,'Grupo Mutual','foto1.jpg');
INSERT INTO `Clientes` (ID,Cedula_Cliente,Salario,LugarTrabajo,Fotografia) VALUES (2,604110437,50000,'Estudiante','foto2.jpg');
COMMIT;

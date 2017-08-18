BEGIN TRANSACTION;
CREATE TABLE `Tarjetas` (
	`ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`Cedula_Cliente`	NUMERIC NOT NULL,
	`NumeroTarjeta`	NUMERIC NOT NULL,
	`FechaVencimiento`	TEXT NOT NULL,
	`TipoTarjeta`	INTEGER NOT NULL
);
INSERT INTO `Tarjetas` VALUES (1,604150516,4152779504604072,'07/12',1);
INSERT INTO `Tarjetas` VALUES (2,604110437,4152046077954040,'07/12',1);
CREATE TABLE `RegistroPago` (
	`ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`Cedula_Cliente`	NUMERIC NOT NULL,
	`Monto`	NUMERIC NOT NULL,
	`Tarjeta_ID`	INTEGER NOT NULL
);
INSERT INTO `RegistroPago` VALUES (1,604150516,30000,1);
INSERT INTO `RegistroPago` VALUES (2,604110437,12000,2);
CREATE TABLE "Clientes" (
	`ID`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`Nombre`	TEXT NOT NULL,
	`Cedula_Cliente`	NUMERIC NOT NULL UNIQUE,
	`LugarTrabajo`	TEXT NOT NULL,
	`Salario`	NUMERIC DEFAULT 0,
	`Telefono`	TEXT NOT NULL,
	`Direccion`	TEXT NOT NULL,
	`Fotografia`	TEXT NOT NULL
);
COMMIT;

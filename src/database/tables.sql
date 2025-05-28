CREATE DATABASE cite_system_db;

CREATE TABLE IF NOT EXISTS Paciente(
	idPaciente INT PRIMARY KEY,
	nombres VARCHAR(45) NOT NULL,
	apellidos VARCHAR(45) NOT NULL,
	fechaNacimiento DATE NOT NULL,
	telefono INT NOT NULL,
	direccion VARCHAR(100) NOT NULL,
	email VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS Medico(
	idMedico INT PRIMARY KEY,
	nombres VARCHAR(45) NOT NULL,
	apellidos VARCHAR(45) NOT NULL,
	fechaNacimiento DATE NOT NULL,
	telefono INT NOT NULL,
	direccion VARCHAR(100) NOT NULL,
	email VARCHAR(45) NOT NULL,
	fechaDeIngreso DATE NOT NULL,
	especialidad VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS Agenda(
	idAgenda SERIAL PRIMARY KEY,
	idMedico INT REFERENCES Medico(idMedico) NOT NULL,
	fecha DATE NOT NULL,
	horaInicio TIME NOT NULL,
	horaFinal TIME NOT NULL
);

CREATE TABLE IF NOT EXISTS Recepcionista(
	idRecepcionista INT PRIMARY KEY,
	nombres VARCHAR(45) NOT NULL,
	fechaDeIngreso DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS Cita(
	idCita SERIAL PRIMARY KEY,
	fechaCita DATE NOT NULL,
	horaCita TIME NOT NULL,
	estadoCita VARCHAR(15) NOT NULL,
	motivoConsulta VARCHAR(150) NOT NULL,
	fechaAsignacion DATE NOT NULL,
	idPaciente INT REFERENCES Paciente(idPaciente) NOT NULL,
	idMedico INT REFERENCES Medico(idMedico) NOT NULL,
	idRecepcionista INT REFERENCES Recepcionista(idRecepcionista) NOT NULL
);

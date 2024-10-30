CREATE DATABASE aps;

use aps;

/*-----------------------------------------      Criando tabelas do projeto       -----------------------------------------------*/

CREATE TABLE Categoria (
	id_categoria int(3) PRIMARY KEY auto_increment not null,
    tipo_categoria VARCHAR(50) not null
);

CREATE TABLE Status_imovel (
	id_status int(3) PRIMARY KEY auto_increment not null,
    tipo_status VARCHAR(50) not null
);

CREATE TABLE Fotos (
	id_fotos int(3) PRIMARY KEY auto_increment not null,
    link_fotos VARCHAR(50) not null
);

CREATE TABLE Cliente(
	id_cliente int(3) primary key auto_increment not null,
    nome_cliente varchar(50) not null,
    telefone int(11) not null,
    email varchar(50) not null,
    relacao varchar(50) not null
);

CREATE TABLE Corretor(
	id_corretor int(3) primary key auto_increment not null,
    nome_corretor varchar(50) not null,
    comissao_porcent decimal(3,1) not null
);

CREATE TABLE Casa (
	codigo_casa int(3) primary key auto_increment not null,
	categoria_casa int(3) not null,
    quartos int(2) not null,
    suítes int(2) not null,
	sala_estar int(2) not null,
    sala_jantar int(2) not null,
    vaga_garagem int(2) not null,
    area_metros_quadrados int(5) not null,
    armario_embutido varchar(3) not null,
    end_casa varchar(200) not null,
    valor_casa decimal(9,2) not null,
    comissao_casa decimal(9,2) not null,
    data_construcao DATE not null,
    obs varchar(50) not null,
    cliente_id int(3) not null,
    corretor_id int(3) not null,
    status_id int(3) not null,
    fotos_id int(3) not null,
    FOREIGN KEY (categoria_casa) REFERENCES Categoria (id_categoria),
    FOREIGN KEY (corretor_id) REFERENCES Corretor (id_corretor),
    FOREIGN KEY (cliente_id) REFERENCES Cliente (id_cliente),
    FOREIGN KEY (status_id) REFERENCES Status_imovel(id_status),
    FOREIGN KEY (fotos_id) REFERENCES Fotos(id_fotos)
);

CREATE TABLE Apartamento (
	codigo_apartamento int(3) primary key auto_increment not null,
	categoria_apartamento int(3) not null,
    quartos int(2) not null,
    suítes int(2) not null,
	sala_estar int(2) not null,
    sala_jantar int(2) not null,
    vaga_garagem int(2) not null,
    area_metros_quadrados int(5) not null,
    armario_embutido varchar(3) not null,
    obs varchar(50) not null,
    andar int(2) not null,
    valor_condominio int(5) not null,
    portaria_24_horas varchar(3) not null,
	end_apartamento varchar(200) not null,
    valor_apartamento decimal(9,2) not null,
    comissao_apartamento decimal(9,2) not null,
    data_construcao DATE,
    corretor_id int(3) not null,
    cliente_id int(3) not null,
    status_id int(3) not null,
    fotos_id int(3) not null,
    FOREIGN KEY (categoria_apartamento) REFERENCES Categoria (id_categoria),
    FOREIGN KEY (corretor_id) REFERENCES Corretor (id_corretor),
    FOREIGN KEY (cliente_id) REFERENCES Cliente (id_cliente),
    FOREIGN KEY (status_id) REFERENCES Status_imovel(id_status),
    FOREIGN KEY (fotos_id) REFERENCES Fotos(id_fotos)
);

CREATE TABLE Terreno(
	codigo_terreno int(3) primary key auto_increment not null,
    categoria_terreno int(3) not null,
    area_metro_quadrado int(5) not null,
    largura_metro int(5) not null,
    comprimento_metro int(5) not null,
    aclive_ou_declive varchar(7) not null,
    end_terreno varchar(200) not null,
    valor_terreno decimal(9,2) not null,
    comissao_terreno decimal(9,2) not null,
    corretor_id int(3) not null,
    cliente_id int(3) not null,
    status_id int(3) not null,
    fotos_id int(3) not null,
    FOREIGN KEY (categoria_terreno) REFERENCES Categoria (id_categoria),
    FOREIGN KEY (corretor_id) REFERENCES Corretor (id_corretor),
    FOREIGN KEY (cliente_id) REFERENCES Cliente (id_cliente),
    FOREIGN KEY (status_id) REFERENCES Status_imovel(id_status),
    FOREIGN KEY (fotos_id) REFERENCES Fotos(id_fotos)
);

CREATE TABLE Sala_Comercial(
	codigo_comercial int(3) primary key auto_increment not null,
    categoria_comercial int(3) not null,
    area_metro_quadrado int(5) not null,
    banheiro int(2) not null,
    comodo int(2) not null,
    end_comercial varchar(200) not null,
    valor_comercial decimal(9,2) not null,
    comissao_comercial decimal(9,2) not null,
    data_construcao DATE,
    corretor_id int(3) not null,
    cliente_id int(3) not null,
    status_id int(3) not null,
    fotos_id int(3) not null,
    FOREIGN KEY (categoria_comercial) REFERENCES Categoria (id_categoria),
    FOREIGN KEY (corretor_id) REFERENCES Corretor (id_corretor),
    FOREIGN KEY (cliente_id) REFERENCES Cliente (id_cliente),
    FOREIGN KEY (status_id) REFERENCES Status_imovel(id_status),
    FOREIGN KEY (fotos_id) REFERENCES Fotos(id_fotos)
);


/*-----------------------------------------      Inserindo valores nas tabelas       -----------------------------------------------*/

INSERT Categoria VALUES 
(0, "Casa"),
(0, "Apartamento"),
(0, "Terreno"),
(0, "Sala comercial");

INSERT Status_imovel VALUES 
(0, "Disponível para venda"),
(0, "Vendido"),
(0, "Disponível para locação"),
(0, "Locado");

INSERT INTO Fotos VALUES 
(0, 'https://imobiliaria.com/fotos/casa1.jpg'),
(0, 'https://imobiliaria.com/fotos/casa2.jpg'),
(0, 'https://imobiliaria.com/fotos/apartamento1.jpg'),
(0, 'https://imobiliaria.com/fotos/terreno1.jpg'),
(0, 'https://imobiliaria.com/fotos/sala_comercial1.jpg'),
(0, 'https://imobiliaria.com/fotos/casa3.jpg'),
(0, 'https://imobiliaria.com/fotos/apartamento2.jpg'),
(0, 'https://imobiliaria.com/fotos/terreno2.jpg'),
(0, 'https://imobiliaria.com/fotos/sala_comercial2.jpg'),
(0, 'https://imobiliaria.com/fotos/sala_comercial3.jpg'),
(0, 'https://imobiliaria.com/fotos/terreno3.jpg'),
(0, 'https://imobiliaria.com/fotos/apartamento3.jpg');

INSERT INTO Cliente VALUES 
(0, 'João Silva', 987654321, 'joao.silva@email.com','Comprador'),
(0, 'Maria Oliveira', 999887766, 'maria.oliveira@email.com','Vendedor'),
(0, 'Pedro Santos', 988776655, 'pedro.santos@email.com', 'Locatário');

INSERT INTO Corretor VALUES 
(0, 'Ana Costa', 3.5),
(0, 'Lucas Ferreira', 4.0),
(0, 'Juliana Souza', 5.0);

INSERT INTO Casa VALUES 
(0, 1, 3, 1, 1, 1, 2, 120, 'Sim', 'Rua das Flores, 123, Bairro Primavera', 450000.00 , 31500.00 ,'2023-03-03', 'Recém-reformada', 1, 1, 1, 1),
(0, 1, 2, 1, 1, 0, 1, 85, 'Não', 'Avenida Central, 456, Bairro Jardim', 320000.00 , 22400.00 ,'1992-10-09', 'Excelente localização', 2, 3, 2, 2),
(0, 1, 4, 2, 1, 1, 3, 200, 'Sim', 'Rua das Palmeiras, 789, Bairro Estrela', 750000.00 , 52500.00,'2000-11-01', 'Com piscina', 1, 3, 2, 6);

INSERT INTO Apartamento VALUES 
(1, 2, 2, 1, 1, 0, 1, 75,'Sim','Dahora', 5, 500, 'Sim', 'Rua das Águias, 102, Bairro Bela Vista', 300000.00, 21000.00 ,'2030-08-03', 3, 2, 4, 3),
(2, 2, 3, 1, 1, 1, 2, 110,'Não','Nice', 12, 800, 'Não', 'Avenida das Pedras, 210, Bairro Alto', 450000.00,31500.00 ,'2010-01-15', 2, 1, 4, 7),
(3, 2, 1, 0, 1, 0, 1, 55,'Não','Bacana', 2, 250, 'Sim', 'Rua do Sol, 55, Bairro Centro', 220000.00, 15400.00 ,'2035-12-25', 3 ,1, 3, 12);

INSERT INTO Terreno VALUES 
(1, 3, 500, 20, 25, 'Aclive', 'Estrada da Montanha, S/N, Bairro Colina', 150000.00, 10500.00 , 2, 2, 3, 4),
(2, 3, 750, 30, 25, 'Declive', 'Rua do Vale, 100, Bairro Verde', 210000.00, 14700.00 , 1, 1, 3, 8),
(3, 3, 1000, 40, 25, 'Plano', 'Avenida da Paz, 200, Bairro Novo', 300000.00, 21000.00 , 3, 3, 2, 11);

INSERT INTO Sala_Comercial VALUES 
(1, 4, 60, 1, 2, 'Rua das Empresas, 101, Bairro Industrial', 120000.00, 8400.00 ,'2022-04-20', 2, 1, 1, 5),
(2, 4, 45, 1, 1, 'Avenida do Comércio, 202, Bairro Comercial', 100000.00, 7000.00 ,'2028-06-17', 1, 3, 3, 9),
(3, 4, 80, 2, 3, 'Rua Principal, 303, Bairro Central', 180000.00, 12600.00 ,'2012-12-12', 3, 1, 4, 10);


/*-----------------------------------------      1ª pesquisa       -----------------------------------------------*/

SELECT Categoria.tipo_categoria AS 'Tipo do imóvel',  Apartamento.quartos, Apartamento.valor_condominio, Apartamento.end_apartamento AS 'Endereço'
FROM Apartamento
INNER JOIN Categoria
ON Apartamento.categoria_apartamento = Categoria.id_categoria;

SELECT Categoria.tipo_categoria AS 'Tipo do imóvel', sala_comercial.banheiro, sala_comercial.comodo, sala_comercial.end_comercial, sala_comercial.valor_comercial, sala_comercial.area_metro_quadrado, sala_comercial.codigo_comercial, sala_comercial.categoria_comercial
FROM sala_comercial
INNER JOIN categoria
ON sala_comercial.categoria_comercial = Categoria.id_categoria;

SELECT * FROM categoria;



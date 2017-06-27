# prova-project
Primeiro exercicio da prova

Foi criado os CRUDs para as campanhas.
O webservice foi criado usando cxf em soap, pois (para mim) é mais facil de trabalhar.
Todos os endpoints retornam uma string soap.
O banco de dados utilizado foi MySQL, mais a baixo segue os comando para criação da tabela.

Os WSDLs estão no projeto dentro da pasta webapp.

Como eu ainda estou trabalhando, não foi possivel o projeto 100% nos conformes. Por isso algumas coisas não estão em enum e alguma validações não estão separadas com deviam.

Foi utilizado spring, maven e o cxf para criação do webservice.
Para gerar o json foi usado o javax.json.

Comandos SQL:
create table client (clientId int PRIMARY KEY, name varchar(100), email varchar(100) UNIQUE KEY, nascimento date, idTeam varchar(100));
create table campanha (campanhaId int PRIMARY KEY, name varchar(100), idTeam varchar(100), duration date);
insert into client values (1, "Melhor Cliente","melhor@cliente.com",STR_TO_DATE('10/07/1985','%d/%m/%Y'),"Corinthians");
insert into campanha values (1,"campanha desconhecida","Corinthians",STR_TO_DATE('28/06/2017','%d/%m/%Y'));

create table cliente_campanha(clientId int, campanhaId int)
insert into cliente_campanha values (1,1)

Padrão Json:
{
	"campanhaId":1,
	"name":"TesteXX",
	"idteam":"Corinthians",
	"duration":"01/07/2017",
	"clientId":1,
	"returnMsg":"x"
}

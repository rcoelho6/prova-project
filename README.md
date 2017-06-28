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

Melhorias necessarias: Para tornar este projeto 100% utilizavel no ambito empresarial é necessario fazer melhorias.
* Foi utilizado SOAP, para criar os endpoints, que por padrão encapsula a mensagem em XML. Diferente do REST que manda mensagens GET\POST em XML ou JSON. Por isso a utilização de JSON neste projeto não é necessario, seria muito melhor utilizar um classe model para fazer as trocas de informação que seria enviada junto com o wsdl em um xsd.
* Implementar a utilização do Log4j (de preferencia o Log4j 2) para a geração dos logs, assim ficaria mais facil a manipulação de logs, debug e afins.
* Criar testes utilizando Mockito ou EasyMock, que não foram criados por causa da necessacidade de entrga do teste.


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

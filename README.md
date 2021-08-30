# Aplicação de Cadastro de transação através de arquivo CNAB

Aplicação foi construida para o cadastro de trasações financeiras através do upload de um arquivo txt com o seguinte padrão:

| Descrição do campo  | Inicio | Fim | Tamanho | Comentário
| ------------- | ------------- | -----| ---- | ------
| Tipo  | 1  | 1 | 1 | Tipo da transação
| Data  | 2  | 9 | 8 | Data da ocorrência
| Valor | 10 | 19 | 10 | Valor da movimentação. *Obs.* O valor deverar ser multiplicado por 100.
| CPF | 20 | 30 | 11 | CPF do beneficiário
| Cartão | 31 | 42 | 12 | Cartão utilizado na transação 
| Hora  | 43 | 48 | 6 | Hora da ocorrência atendendo ao fuso de UTC-3
| Dono da loja | 49 | 62 | 14 | Nome do representante da loja
| Nome loja | 63 | 81 | 19 | Nome da loja

#Endpoints

http://localhost:9091/transacao/
- metodo aceito get
- retorno uma lista de lojas e transacoes que foram carregados no banco.

http://localhost:9091/transacao/addAll
- metodo aceito post
- recebe um arquivo txt modelo CNAB realiza o processamento dos dados e a persitencia no banco de dados

#Requisidos para rodar a aplicação
- Docker
- Docker Compose
- Maven

#Rodando a aplicação
1. Abra o diretório na pasta raiz da aplicação
2. No diretório ./cadastro-transacao-service execute via terminal o comando: mvn clean package
3. No diretório ./ execute via termial docker-compose up -d
4. No navegador de preferência acesse http://localhost:9090
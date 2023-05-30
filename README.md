# Focuzd
## Gerenciador de rotinas

##RFs (Requisitos funcionais)
 Deve ser possível se cadastrar;
 Deve ser possível se autenticar;
 Deve ser possível obter o perfil de um usuário logado;
 Deve ser possível o usuário obter seu histórico de rotina;
 Deve ser possível o usuário realizar tarefas;
 Deve ser possível validar tarefas;
 Deve ser possível solicitar nova rotina;
 
##RNs (Regras de negócio)
 O usuário não deve poder se cadastrar com um e-mail duplicado;
 O usuário deve validar todas tarefas para iniciar uma nova rotina;
 O usuário não pode fazer duas ou mais rotinas no mesmo dia;

##RNFs (Requisitos não-funcionais)
 A senha do usuário precisa estar criptografada;
 Os dados da aplicação precisam estar persistidos em um banco DynamoDB;
 A infraestrutura da aplicação deve estar em containers Docker;

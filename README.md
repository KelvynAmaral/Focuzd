<div align="center">
  <img src="https://user-images.githubusercontent.com/89614811/244193389-610222cd-6289-4bf0-afa7-eff5ac13b510.png" width="400px" />
</div>

# FOCUZD | Gerenciador de rotinas
### Organize sua rotina com facilidade

Nosso aplicativo é a solução ideal para ajudar essas pessoas gerenciar suas tarefas diárias e organizar seus compromissos.  Além disso, o aplicativo oferece ferramentas de análise para que os usuários possam monitorar seu desempenho e avaliar áreas onde podem melhorar. 

Com a ajuda do nosso aplicativo, as pessoas podem ter uma vida mais organizada, eficiente e menos estressante.


## Requisitos
RFs (Requisitos funcionais)
 Deve ser possível se cadastrar;
 Deve ser possível se autenticar;
 Deve ser possível obter o perfil de um usuário logado;
 Deve ser possível o usuário obter seu histórico de rotina;
 Deve ser possível o usuário realizar tarefas;
 Deve ser possível validar tarefas;
 Deve ser possível solicitar nova rotina;
 
RNs (Regras de negócio)
 O usuário não deve poder se cadastrar com um e-mail duplicado;
 O usuário deve validar todas tarefas para iniciar uma nova rotina;
 O usuário não pode fazer duas ou mais rotinas no mesmo dia;

RNFs (Requisitos não-funcionais)
 A senha do usuário precisa estar criptografada;
 Os dados da aplicação precisam estar persistidos em um banco DynamoDB;
 A infraestrutura da aplicação deve estar em containers Docker;

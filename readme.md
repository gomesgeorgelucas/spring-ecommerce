## e-Commerce Spring Boot Project

powered By: Infected Mushroom - Saeed ;)

## Specs
**GENERAL**
  - Only roles are used, no authorities at this point.
  - Filter uses Data JPA Specification

**USER**

- logado:
  - deverá conseguir visualizar as informações de todos os produtos com paginação e ordenação padrão por menor preço.
  - retorno do endpoint os produtos devem vir ordenados pelo preço.
  - deverá conseguir fazer pedido de produtos.
  - o pedido deverá ter status(Pedido realizado, pedido enviado, pedido entregue(concluído).
  - A lógica para pedido enviado e entregue fica a critério do desenvolvedor.
  
**ADMIN**
- logado
  - deverá conseguir cadastrar, atualizar, listar e deletar produtos.
  - pode cancelar um pedido que estiver com status realizado.


**USER/ADMIN**
- logado 
  - deverão conseguir visualizar todos os produtos de acordo com categoria. exemplo: products/all-categories
  - deverá conseguir visualizar todos os produtos de acordo com categoria(s) filtrada(s). exemplo: products/all-categories/{ids}
- deslogado
  - deverão visualizar apenas home

**ER Diagram**

![ERD](src/main/resources/static/ERD.webp?raw=true "ERD")

### Dependencies:
- Java 17
- Lombok
- Spring Boot
  - Data JPA
    - pagination
    - sorting
    - filtering (specifications)
  - PostgreSQL
  - WEB
  - Security
    - JWT

### Extra-credit
- [ ] remover @Query dos repositories e colocar no ORM.xml namedQuery    
- [ ] Campo de busca de produto com sugestões
- [ ] Cancel Order -- All orders must keep registerer!
- [ ] FrontPage
- [ ] Texto das exceções deve ficar em arquivo separado ou constante (Classe separada)
## e-Commerce Spring Boot Project

## Specs
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

***Filtros por categoria: deve ser feito usando projections, retornando DTOs e utilizando interfaces***
***Service deve ter interface e implementação***
***Utilizar apenas roles!!!***

**MODELAGEM**
 - [ ] TODO
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

### Extra-credit
- [ ] Campo de busca de produto com sugestões
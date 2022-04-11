## e-Commerce Spring Boot Project

powered By: Infected Mushroom - Saeed ;)

## Specs
**GENERAL**
  - Only roles are used, no authorities at this point.
  - Filter uses Data JPA Specification
  - Works with JWT
  - Version with formLogin is implemented - check branch spring_security
    - has custom login page using thymeleaf, custom logout button
  - It has Open-API (Swagger)
     

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
  - deverá conseguir visualizar todos os produtos de acordo com categoria(s) filtrada(s). exemplo: products/all-categories/{productName}
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

### TODO
- [x] Filter by product category
- [ ] Fix Order Deletion (Cascade/Orphan problem)
  - Issue: There is no product inventory, product entity acts as repository and when delete order operation cascades, all matching products are deleted.
  - Fix#1 - Change table ec_ordered_products to @ElementCollection - let ec_order manage the products (fix remaining products and order quantity).
  - Fix#2 - Create entity ec_product_inventory and remove product quantity from entity ec_products
    - Workaround: Method createOrder iterates over the quantity of products bought and adds products to products ordered 1-by-1 to match quantity bought. By counting the products with same productId on ec_ordered_products a DTO can be supplied with the quantities of each product ordered.
- [ ] Replace all answers and requests with DTOs
- [ ] Thin controllers - remove duplicate method calls and fix antMatchers
- [ ] Implement basic Service Layer Interface (GET,POST,PUT,DELETE)
- [ ] Update postman collection to match endpoints and auth

### Extra-credit
- [ ] implemente refresh token controller
- [ ] remover @Query dos repositories e colocar no ORM.xml namedQuery    
- [ ] Campo de busca de produto com sugestões
- [x] Cancel Order -- All orders must keep registerer!
- [ ] Texto das exceções deve ficar em arquivo separado ou constante (Classe separada)
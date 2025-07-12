Feature: acessar url shopping e fazer a compra do produto


  Scenario: incluir produto no carrinho em um site de compras
    Given que acessei um site de compras
    When pesquisar um produto
    Then devo incluir o mesmo no carrinho



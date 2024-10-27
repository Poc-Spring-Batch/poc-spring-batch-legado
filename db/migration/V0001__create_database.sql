CREATE TABLE `Produto` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(150) NOT NULL,
  `preco` DOUBLE NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `CarrinhoCompra` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `idUsuario` bigint NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `Item` (
  `idCarrinho` BIGINT NOT NULL,
  `idProduto` BIGINT NOT NULL,
  `quantidade` INT NOT NULL,
  PRIMARY KEY (`idCarrinho`, `idProduto`),
  INDEX `fk_Item_2_idx` (`idProduto` ASC) VISIBLE,
  CONSTRAINT `fk_Item_1`
    FOREIGN KEY (`idCarrinho`)
    REFERENCES `CarrinhoCompra` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Item_2`
    FOREIGN KEY (`idProduto`)
    REFERENCES `Produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


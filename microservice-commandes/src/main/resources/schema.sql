CREATE TABLE commande
(
    id          INT PRIMARY KEY,
    productId   INT NOT NULL,
    dateCommande DATE NOT NULL,
    quantity    INT NOT NULL,
    commandePayee  bit NOT NULL
);
CREATE TABLE paiement
(
    id          INT PRIMARY KEY,
    idCommande  INT NOT NULL,
    montant     DOUBLE PRECISION NOT NULL,
    quantity    INT NOT NULL,
    numeroCarte DOUBLE PRECISION not null
);
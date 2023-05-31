-- INSERT roles --
INSERT INTO roles (ROLE_NAME)
SELECT * FROM (SELECT 'ROLE_ADMIN') AS temp
WHERE NOT EXISTS (
    SELECT ROLE_NAME FROM roles WHERE ROLE_NAME = 'ROLE_ADMIN'
);

INSERT INTO roles (ROLE_NAME)
SELECT * FROM (SELECT 'ROLE_USER') AS temp
WHERE NOT EXISTS (
    SELECT ROLE_NAME FROM roles WHERE ROLE_NAME = 'ROLE_USER'
);

-- INSERT coins --
INSERT INTO coins (NAME, DESCRIPTION, USD_VALUE, STATUS) VALUES
('BTC', 'Bitcoin', '27689.30', false),
('ETH', 'Ethereum', '1894.84', false),
('BNB', 'Binance Coin', '310.20', false),
('USDT', 'TetherUS', '1.00', false),
('ARS', 'Argentine Peso', '474.50', false);

-- INSERT payment methods --
INSERT INTO payment_methods (PAYMENT_TYPE) VALUES
('Bank Deposit'),
('Cash Balance');
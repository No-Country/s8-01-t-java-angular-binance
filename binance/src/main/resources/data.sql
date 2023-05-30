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
INSERT INTO coins (NAME, DESCRIPTION, STATUS) VALUES
('BTC', 'Bitcoin', false),
('ETH', 'Ethereum', false),
('BNB', 'Binance Coin', false),
('USDT', 'Tether', false),
('ARS', 'Argentine Peso', false);

-- INSERT payment methods --
INSERT INTO payment_methods (PAYMENT_TYPE) VALUES
('Bank Deposit'),
('Cash Balance'),
('P2P Trading');
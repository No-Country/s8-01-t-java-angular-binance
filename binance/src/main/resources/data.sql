-- INSERT roles --
INSERT INTO roles (ROLE_NAME) VALUES
('ROLE_ADMIN'),
('ROLE_USER');

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
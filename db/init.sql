-- Database initialization completed

CREATE TABLE IF NOT EXISTS customers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS carts (
    id SERIAL PRIMARY KEY,
    customer_id INTEGER NOT NULL REFERENCES customers(id)
);

CREATE TABLE IF NOT EXISTS cart_items (
    id SERIAL PRIMARY KEY,
    cart_id INTEGER NOT NULL REFERENCES carts(id),
    product_id INTEGER NOT NULL,
    quantity INTEGER NOT NULL
);

-- Données d'exemple pour customers
INSERT INTO customers (id, name, email) VALUES
  (1, 'Alice Dupont', 'alice@example.com'),
  (2, 'Bob Martin', 'bob@example.com');

-- Données d'exemple pour cart
INSERT INTO cart (id, customer_id, status) VALUES
  (1, 1, 'OPEN'),
  (2, 2, 'OPEN');
